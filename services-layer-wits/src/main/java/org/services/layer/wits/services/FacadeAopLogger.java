package org.services.layer.wits.services;

import org.aspectj.lang.JoinPoint;
import org.persistence.layer.wits.enumusertypes.EventAopLoggerType;
import org.slf4j.Logger;

public interface FacadeAopLogger {
	public void insertRecordLog(EventAopLoggerType eventAopLoggerType , JoinPoint joinPoint , Object[] args ,  
			Object result , Throwable error , String prefix , String suffix);

}
