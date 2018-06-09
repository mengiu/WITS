package org.services.layer.wits.processes;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.RoleSt;
import org.persistence.layer.wits.PersistenceConstants;
import org.services.layer.wits.services.RoleStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",
                      defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class DefaultTasksFilterTest {
	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Test
	public void getQueryTest()
	{
		
	}

	private TaskQuery getQuery(String processDefinition ,String nameTaskDefinition ) {
		TaskQuery retQuery = null;
		if (processDefinition!=null && 
				nameTaskDefinition!=null)
		{
		  retQuery = getBaseQuery()
		    		.processDefinitionId(processDefinition)
		    		.taskDefinitionKey(nameTaskDefinition)
		            .orderByTaskPriority().desc()
		            .orderByTaskName().asc();
		}
		else
		{
			if (processDefinition!=null)
			{
				  retQuery = getBaseQuery()
				    		.processDefinitionId(processDefinition)
				            .orderByTaskPriority().desc()
				            .orderByTaskName().asc();
			}
			if (nameTaskDefinition!=null)
			{
				  retQuery = getBaseQuery()
				    		.taskDefinitionKey(nameTaskDefinition)
				            .orderByTaskPriority().desc()
				            .orderByTaskName().asc();
			}
			
		}
		if (retQuery==null)
			retQuery = 	getBaseQuery()
		                .orderByTaskPriority().desc()
		                .orderByTaskName().asc(); // all task assigned to userid
		return retQuery;
	}

	private TaskQuery getCountQuery(String processDefinition ,String nameTaskDefinition) {
		return getQuery(processDefinition,nameTaskDefinition);
	}
	
	private TaskQuery getBaseQuery() {
		String swimlane = "LEADER"+PersistenceConstants.SEPARATOR_ROLES+"";
		    return taskService
		            .createTaskQuery()
		            //.taskAssignee(WitsApp.get().getLoggedInUser().getId());
		            .taskCandidateUser(swimlane);
		  }

}
