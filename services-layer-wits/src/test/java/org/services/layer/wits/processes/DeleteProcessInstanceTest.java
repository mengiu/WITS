package org.services.layer.wits.processes;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.test.Deployment;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.enumusertypes.ProcessDefinitionType;
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
public class DeleteProcessInstanceTest {
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private HistoryService historyService;

	@After
	public void closeProcessEngine() {
		// Required, since all the other tests seem to do a specific drop on the end 
		processEngine.close();
	}

	@Test
	public void deleteProcessInstanceWorkflowTest()
	{
		RuntimeService runtimeService = processEngine.getRuntimeService();
		runtimeService.deleteProcessInstance("5916", null);
		runtimeService.deleteProcessInstance("5938", null);

	}

	@Test
	@Deployment
	public void createHistoricProcessInstanceQueryTest()
	{
		historyService = processEngine.getHistoryService();
		List<HistoricProcessInstance> processInstancesUnfined = historyService
				.createHistoricProcessInstanceQuery()
				.startedBy("castepo")
				.processDefinitionKey(ProcessDefinitionType.PROCESS_RWMO.getValue())
				.unfinished()
				.list();
		for (HistoricProcessInstance item  : processInstancesUnfined)
		{
			System.out.println(item.getId());
		}

	}
}
