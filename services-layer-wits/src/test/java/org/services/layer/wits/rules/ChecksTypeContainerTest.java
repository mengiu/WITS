package org.services.layer.wits.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.enumusertypes.ContainingUnitTypeRequestType;
import org.services.layer.wits.RWMOVariables;
import org.services.layer.wits.services.drools.ChecksTypeContainerRunner;
import org.services.layer.wits.services.model.RequestContainingUnit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ChecksTypeContainerTest {
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule(
			"activiti.cfg-mem-rules.xml");

	@Test
    public void checksTypeContainerTest()
	{
		RequestContainingUnit requestContainingUnit = new RequestContainingUnit(ContainingUnitTypeRequestType.NO_CU_REQUESTED.getValue());
		boolean result = false;
		try {
			 result = ChecksTypeContainerRunner.runRules(requestContainingUnit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(result);
		
	}

	@Test
	@Deployment(resources = { "processes/requestWamatObjectProcess.bpmn20.xml", 
			"rules/ChecksTypeContainer.drl" })
	public void testChecksTypeContainerRule() {
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put(RWMOVariables.ID_DESTINATION, "1");
		variableMap.put(RWMOVariables.QUANTITY, "1");
		variableMap.put(RWMOVariables.TYPE_CU_REQUESTED, "NO_CU_REQUESTED" );
		variableMap.put(RWMOVariables.ID_OWNER, new Integer(11));

		ProcessInstance processInstance = activitiRule.getRuntimeService()
				.startProcessInstanceByKey("processRWMO", variableMap);
		assertNotNull(processInstance);

	    // Get the first task
	    TaskService taskService = activitiRule.getTaskService();
	    List<Task> tasks = taskService.createTaskQuery().list();
	    for (Task task : tasks) {
	      System.out.println("Following task is available : " + task.getName());
	      // Complete the task
	      taskService.complete(task.getId());
	    }


	    
	    tasks = taskService.createTaskQuery().list();
	    for (Task task : tasks) {
	      System.out.println("Following task is available: " + task.getName());
	      
	      // Complete the task
	      taskService.complete(task.getId());
	    }
		
	}

}
