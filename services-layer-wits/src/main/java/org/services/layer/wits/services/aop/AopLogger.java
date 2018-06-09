package org.services.layer.wits.services.aop;



import java.util.Arrays;
import java.util.List;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.task.Task;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.persistence.layer.wits.dao.to.impl.CurrentWorkingWorkflowInfoImpl;
import org.persistence.layer.wits.enumusertypes.EventAopLoggerType;
import org.persistence.layer.wits.enumusertypes.LoggingLevelType;
import org.persistence.layer.wits.enumusertypes.MonitoredServicesType;
import org.persistence.layer.wits.identity.WitsAuthentication;
import org.services.layer.wits.services.FacadeAopLogger;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

@Aspect
@Scope("singleton")
public class AopLogger {
	@Autowired
	@Qualifier("facadeAopLogger")
	FacadeAopLogger facadeAopLogger;
	protected TaskService taskService;
	
	public AopLogger()
	{
	}
	
	private ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
    protected LoggingLevelType level;
    protected String prefix;
    protected String suffix;
	/*@Around(value="execution(@MethodLog * *(..)) && @annotation(methodLogging)", argNames="methodLogging" )
	public Object logMethodBefore(ProceedingJoinPoint pjp, MethodLog methodLogging ) throws Throwable {
		Object[] args = pjp.getArgs();
		Logger logger = getLoggerForClass(pjp);
		Object methodResult;
		try {
			methodResult = pjp.proceed();
			if (methodLogging.entry()) {
				String enterMsg = "ENTER: " 
						+ methodLogging.prefix() 
						+ pjp.getSignature().toShortString()  
						+ methodLogging.suffix();
				String parmsMsg = "\tPARAMS: " + Arrays.toString(args);
				myLog(enterMsg,LoggingLevelType.DEBUG,logger);
				myLog(parmsMsg,LoggingLevelType.DEBUG,logger);
				for (Object item : args)
				{
				 if(item instanceof DelegateExecution)
				 {

				 }

				}
			}
			if (methodLogging.exit()) {
				String exitMsg = "EXIT: " 
						+ methodLogging.prefix() 
						+ pjp.getSignature().toShortString()  
						+ methodLogging.suffix();
				String rtrnMsg = "\tRETURNING: " 
						+ (methodResult == null ? "null" : methodResult.toString());
				myLog(exitMsg,LoggingLevelType.DEBUG,logger);
				myLog(rtrnMsg,LoggingLevelType.DEBUG,logger);
			}
			return methodResult;
		} catch (Throwable e) {
			if (e.getMessage()!=null &&
				 methodLogging.throwing())
			 myLog( e.getMessage(),LoggingLevelType.DEBUG,logger);
			throw new Throwable(e);
		}
	}*/
	@AfterReturning(
			value="execution(@MethodLog * *(..)) && @annotation(methodLogging)",			
			//pointcut = "execution(@MethodLog * *(..))",
			returning= "result" )
	public void logAfterReturning(JoinPoint joinPoint, Object result, MethodLog methodLogging ) {

		Logger logger = getLoggerForClass(joinPoint);
		level = methodLogging.level();
		prefix = methodLogging.prefix();
		suffix = methodLogging.suffix();
		Object[] args = joinPoint.getArgs();
		String afterReturningMsg = prefix + " AfterReturning: " 
				+ joinPoint.getSignature().toShortString() + " " 
				+ suffix;  
		myLog(afterReturningMsg,logger);
		if (result!=null)
			myLog("Method returned value is : " + result,logger);
		if (isEnabledFor())
		 facadeAopLogger.insertRecordLog( EventAopLoggerType.AFTER_RETURNING , 
				joinPoint,args,result,null, prefix , suffix);
		/*
         * delete info about current workflow process 
         * only if prefix is not empty and if It is not contained into 
         * Monitored Services Type enum
 		 */
		boolean found = false;
		for ( MonitoredServicesType item : MonitoredServicesType.values())
		{
			if (item.getValue().equals(prefix.toUpperCase()))
			{
				found = true;
				break;
			}
		}
		//if (prefix!=null && !prefix.isEmpty() && !found)
		// WitsAuthentication.setCurrentWorkingWorkflowInfo(null);

	}
	//@Before("execution(@MethodLog * *(..))")
	@Before(value="execution(@MethodLog * *(..)) && @annotation(methodLogging)" , argNames="methodLogging")
	public void logBefore(JoinPoint joinPoint , MethodLog methodLogging ) {

		if (taskService==null)
		 taskService =  ProcessEngines.getDefaultProcessEngine().getTaskService();
		Logger logger = getLoggerForClass(joinPoint);
		level = methodLogging.level();
		prefix = methodLogging.prefix();
		suffix = methodLogging.suffix();
		
		Object[] args = joinPoint.getArgs();
		String enterMsg = prefix + " ENTER: " 
				+ joinPoint.getSignature().toShortString() + " " 
				+ suffix;  
		myLog(enterMsg,logger);
		if (args!=null)
		{
		 String parmsMsg = "\tPARAMS: " + Arrays.toString(args);
		 myLog(parmsMsg,logger);
		}
		//if (!(prefix!=null && !prefix.isEmpty()))
		// WitsAuthentication.setCurrentWorkingWorkflowInfo(null);
		for (Object item : args)
		{
			if(item instanceof DelegateExecution)
			{
				/*
				 * Set Info on current workflow process
				 */
				List<Task> listTask = taskService.createTaskQuery().processInstanceId(((DelegateExecution) item).getProcessInstanceId()).list();
				CurrentWorkingWorkflowInfoImpl currentWorkingWorkflowInfo = new CurrentWorkingWorkflowInfoImpl();
				currentWorkingWorkflowInfo.setActivitiTaskId(((listTask!=null) && (listTask.size()>0)) ? listTask.get(0).getId() : ((DelegateExecution) item).getCurrentActivityId());
				currentWorkingWorkflowInfo.setExecutionId(((DelegateExecution) item).getId());
				currentWorkingWorkflowInfo.setProcessInstanceId(((DelegateExecution) item).getProcessInstanceId());
				WitsAuthentication.setCurrentWorkingWorkflowInfo(currentWorkingWorkflowInfo);
			}
		}
		if (isEnabledFor())
		 facadeAopLogger.insertRecordLog(EventAopLoggerType.BEFORE , 
		 	joinPoint,args,null,null, prefix , suffix);

	}

	@AfterThrowing(
			//pointcut="execution(@MethodLog * *(..))",
			value="execution(@MethodLog * *(..)) && @annotation(methodLogging)",			
			throwing= "error" )
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error, MethodLog methodLogging ) {

		Logger logger = getLoggerForClass(joinPoint);
		level = methodLogging.level();
		prefix = methodLogging.prefix();
		suffix = methodLogging.suffix();
		Object[] args = joinPoint.getArgs();
		String afterThrowingMsg = prefix + " AfterThrowing: " 
				+ joinPoint.getSignature().toShortString() + " " 
				+ suffix;  
		myLog(afterThrowingMsg,logger);
		myLog("Exception : " + error,logger);
		if (isEnabledFor())
		 facadeAopLogger.insertRecordLog(EventAopLoggerType.AFTER_THROWING,
				joinPoint,args,null,error, prefix , suffix );
		/*
		 * clear info about current workflow process
		 * only if prefix is not empty
		 */
		//if (prefix!=null && !prefix.isEmpty())
		// WitsAuthentication.setCurrentWorkingWorkflowInfo(null);

	}

	private Logger getLoggerForClass(final JoinPoint pjp )
	{
		StaticPart sp = pjp.getStaticPart();
		String classname = sp.getSignature().getDeclaringTypeName();
		return this.iLoggerFactory.getLogger(classname);
	}
	/*@Before("get(@FieldLog * *) && @annotation(fieldLogging)")
	public void logFieldRead(JoinPoint jp, FieldLog fieldLogging) {
		Logger logger = getLoggerForClass(jp);
		boolean enabledForLevel = isEnabledFor(LoggingLevelType.DEBUG,logger);
		if (enabledForLevel && fieldLogging.read()) {
			String readMsg = "READING: " 
					+ fieldLogging.prefix() 
					+ jp.getSignature().toShortString()  
					+ fieldLogging.suffix();
			myLog(readMsg,LoggingLevelType.DEBUG,logger);
		}
	}

	@Before("set(@FieldLog * *) && @annotation(fieldLogging) && args(newval)")
	public void logFieldWrite(JoinPoint jp, FieldLog fieldLogging, Object newval) {
		Logger logger = getLoggerForClass(jp);
		boolean enabledForLevel = isEnabledFor(LoggingLevelType.DEBUG,logger);
		if (enabledForLevel && fieldLogging.write()) {
			String writeMsg = "ASSIGNING: " 
					+ fieldLogging.prefix() 
					+ jp.getSignature().toShortString()  
					+ " = " 
					+ "'" + (newval == null ? "null" : newval.toString()) + "'" 
					+ fieldLogging.suffix();
			myLog(writeMsg,LoggingLevelType.DEBUG,logger);
		}
	}*/
	private boolean isEnabledFor()
	{
      boolean bRet = false;
      Logger logger = LoggerFactory.getLogger(AopLogger.class);
      switch (level)
      {
       case DEBUG :
       {
  		if (logger.isDebugEnabled()) 
  			bRet = true;
       	  break;
       }
       default :
    	   break;
      }
      return bRet;
    }
	private void myLog(String msg , Logger logger)
	{
	      switch (level)
	      {
	       case TRACE :
	       {
	   		if (logger.isTraceEnabled())
			 logger.trace(msg);
	       	  break;
	       }
	       case INFO :
	       {
	   		if (logger.isInfoEnabled())
			 logger.info(msg);
	       	  break;
	       }
	       case DEBUG :
	       {
	   		if (logger.isDebugEnabled())
			 logger.debug(msg);
	       	  break;
	       }
	       default :
	    	   break;
	      }
	}
	
}
