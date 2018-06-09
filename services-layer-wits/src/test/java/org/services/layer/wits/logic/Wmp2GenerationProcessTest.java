package org.services.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.GenerationProcessSt;
import org.persistence.layer.wits.form.WasteManagementPlanSt;
import org.persistence.layer.wits.form.Wmp2GenerationProcess;
import org.services.layer.wits.services.GenerationProcessStService;
import org.services.layer.wits.services.WasteManagementPlanStService;
import org.services.layer.wits.services.Wmp2GenerationProcessService;
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
public class Wmp2GenerationProcessTest {
	@Autowired
	Wmp2GenerationProcessService wmp2GenerationProcessService;
	@Autowired
	GenerationProcessStService generationProcessStService;
	@Autowired
	WasteManagementPlanStService wasteManagementPlanStService;
	
	  @Test
	  public void Wmp2GenerationProcessServiceTest() {
		  Wmp2GenerationProcess wmp2GenerationProcess = new Wmp2GenerationProcess();
		  GenerationProcessSt wp = generationProcessStService.getGenerationProcessSt(80);
		  WasteManagementPlanSt wmp = wasteManagementPlanStService.getWasteManagementPlanSt(86);
		  wmp2GenerationProcess.setGenerationProcessSt(wp);
		  wmp2GenerationProcess.setWasteManagementPlanSt(wmp);
		  wmp2GenerationProcessService.addWmp2GenerationProcess(wmp2GenerationProcess);
		  wmp2GenerationProcess = new Wmp2GenerationProcess();
		  wp = generationProcessStService.getGenerationProcessSt(81);
		  wmp = wasteManagementPlanStService.getWasteManagementPlanSt(87);
		  wmp2GenerationProcess.setGenerationProcessSt(wp);
		  wmp2GenerationProcess.setWasteManagementPlanSt(wmp);
		  wmp2GenerationProcessService.addWmp2GenerationProcess(wmp2GenerationProcess);
	}
	
}
