package org.services.layer.wits.processes;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:activiti.cfg.xml", "classpath:activiti-context.xml"})
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",
                        defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class SampleProcessTest {

	  @Autowired
	  private ProcessEngine processEngine;
	  
	  @Autowired
	  private RuntimeService runtimeService;
	  
	  @Autowired
	  private TaskService taskService;
	  
	  /*@Autowired
	  @Rule
	  public ActivitiRule activitiSpringRule;*/

	  @After
	  public void closeProcessEngine() {
	    // Required, since all the other tests seem to do a specific drop on the end 
	    processEngine.close();
	  }
	  
	  @Test
	  @Deployment
	  public void simpleProcessTest() {
	        RepositoryService repositoryService = processEngine.getRepositoryService();
		    /* Deploy the process definition 
		    repositoryService.createDeployment()
		      .addClasspathResource("processes/creditCheckRules.bpmn20.xml")
		      .addClasspathResource("rules/CreditCheckTest.drl")
		      .deploy();
		    */      
		      
			RuntimeService runtimeService = processEngine.getRuntimeService();
		    
		    // Start a process instance
		    String procId = runtimeService.startProcessInstanceByKey("financialReport").getId();
		    
		    // Get the first task
		    TaskService taskService = processEngine.getTaskService();
		    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
		    for (Task task : tasks) {
		      System.out.println("Following task is available for accountancy group: " + task.getName());
		      
		      // claim it
		      taskService.claim(task.getId(), "fozzie");
		    }
		    
		    // Verify Fozzie can now retrieve the task
		    tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
		    for (Task task : tasks) {
		      System.out.println("Task for fozzie: " + task.getName());
		      
		      // Complete the task
		      taskService.complete(task.getId());
		    }
		    
		    System.out.println("Number of tasks for fozzie: " 
		            + taskService.createTaskQuery().taskAssignee("fozzie").count());
		    
		    // Retrieve and claim the second task
		    tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		    for (Task task : tasks) {
		      System.out.println("Following task is available for accountancy group: " + task.getName());
		      taskService.claim(task.getId(), "kermit");
		    }
		    
		    // Completing the second task ends the process
		    for (Task task : tasks) {
		      taskService.complete(task.getId());
		    }
		    HistoryService historyService = processEngine.getHistoryService();
		    HistoricProcessInstance historicProcessInstance = 
		      historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
		    System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
	   
	  }
	  @Test
	  @Deployment
	  public void simpleProcessTest2() {
		for (int i = 0; i<5;i++) {
			System.out.println("Message triggered: " + new Date().getTime() );
			runtimeService.startProcessInstanceByMessage("r_msg"); //start with message name
			System.out.println("Message finished, now trigger the event e1: " + new Date().getTime());
			runtimeService.signalEventReceived("e1"); //trigger event e1
			System.out.println("Event finished: " + new Date().getTime() + "\n");
		}
	  }
}
