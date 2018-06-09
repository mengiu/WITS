package org.services.layer.wits.rules;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;
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
import org.services.layer.wits.services.model.LoanApplicant;

public class LoanRequestRulesTest {
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule(
			"activiti.cfg-mem-rules.xml");

	@Test
	@Deployment(resources = { "processes/loanrequest.rules.bpmn20.xml", "rules/LoanRequestRules.drl" })
	public void testCreditCheckFailed() {
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Miss Piggy");
		variableMap.put("emailAddress", "piggy@holliwod.us");
		variableMap.put("income", (long)100 );
		variableMap.put("loanAmount", (long)90 );

		ProcessInstance processInstance = activitiRule.getRuntimeService()
				.startProcessInstanceByKey("loanrequestWithRules", variableMap);
		assertNotNull(processInstance);
		
		/*Collection<Object> ruleOutputList = (Collection<Object>) activitiRule
				.getRuntimeService().getVariable(processInstance.getId(),
						"rulesOutput");
		assertNotNull(ruleOutputList);*/
		
	}
	@Test
	@Deployment(resources = { "processes/loanrequest.rules.bpmn20.xml", "rules/LoanRequestRules.drl" })
	public void testCreditCheckPassed() {
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Miss Piggy");
		variableMap.put("emailAddress", "piggy@holliwod.us");
		variableMap.put("income", (long)100 );
		variableMap.put("loanAmount", (long)30 );

		ProcessInstance processInstance = activitiRule.getRuntimeService()
				.startProcessInstanceByKey("loanrequestWithRules", variableMap);
		assertNotNull(processInstance);
		
		/*Collection<Object> ruleOutputList = (Collection<Object>) activitiRule
				.getRuntimeService().getVariable(processInstance.getId(),
						"rulesOutput");
		assertNotNull(ruleOutputList);*/
		
	}
	@Test
	@Deployment(resources = { "processes/loanrequest.rules.bpmn20.xml", "rules/LoanRequestRules.drl" })
	public void testCreditCheckPassedWithManager() {
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Miss Piggy");
		variableMap.put("emailAddress", "piggy@holliwood.us");
		variableMap.put("income", (long)300000 );
		variableMap.put("loanAmount", (long)100010 );

		ProcessInstance processInstance = activitiRule.getRuntimeService()
				.startProcessInstanceByKey("loanrequestWithRules", variableMap);
		assertNotNull(processInstance);

	    // Get the first task
	    TaskService taskService = activitiRule.getTaskService();
	    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
	    for (Task task : tasks) {
	      System.out.println("Following task is available for management group: " + task.getName());
	      // claim it
	      taskService.claim(task.getId(), "kermit");
	    }
		activitiRule.getRuntimeService().setVariable(processInstance.getId(), "requestApproved", "true");
	    activitiRule.getRuntimeService().setVariable(processInstance.getId(), "motivation", "Perchè si");


	    
	    // Verify Fozzie can now retrieve the task
	    tasks = taskService.createTaskQuery().taskAssignee("kermit").list();
	    for (Task task : tasks) {
	      System.out.println("Task for fozzie: " + task.getName());
	      
	      // Complete the task
	      taskService.complete(task.getId());
	    }
		
	}

}
