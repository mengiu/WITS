package org.services.layer.wits.rules;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.GenerationProcessSt;
import org.persistence.layer.wits.form.WamatObject;
import org.services.layer.wits.services.ActGeBytearrayService;
import org.services.layer.wits.services.GenerationProcessStService;
import org.services.layer.wits.services.WamatObjectService;
import org.services.layer.wits.services.drools.RangeWamatInputProcessPWMOCheckRulesRunner;
import org.services.layer.wits.services.model.SecondLevelControlStartPWMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",
defaultRollback=true)
@Transactional( propagation = Propagation.REQUIRED )
@Deployment(resources = { "classpath*:/rules/CheckWamatInputProcessRangeValues.drl" })
public class CheckWamatInputProcessRangeValuesTest {
	@Autowired
	@Qualifier("generationProcessStService")
	GenerationProcessStService generationProcessStService;
	@Autowired
	@Qualifier("wamatObjectService")
	WamatObjectService wamatObjectService;
	@Autowired
	@Qualifier("actGeBytearrayService")
	ActGeBytearrayService actGeBytearrayService;
	@Test
	public void testCheckWamatInputProcessRangeValuesPassed()
	{
		SecondLevelControlStartPWMO secondLevelControlStartPWMO = new SecondLevelControlStartPWMO();
		List<WamatObject> listWamatObject = new ArrayList<WamatObject>();
		WamatObject wamatObject = wamatObjectService.getWamatObject(223);
		listWamatObject.add(wamatObject);
		WamatObject wamatObject1 = wamatObjectService.getWamatObject(224);
		listWamatObject.add(wamatObject1);
		secondLevelControlStartPWMO.setListWamatObjectInputProcess(listWamatObject);
		GenerationProcessSt generationProcessSt = generationProcessStService.getGenerationProcessSt(7);
		secondLevelControlStartPWMO.setGenerationProcessSt(generationProcessSt);
		boolean result = false;
		try {
			result = RangeWamatInputProcessPWMOCheckRulesRunner.runRules(secondLevelControlStartPWMO,actGeBytearrayService);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		System.out.println(secondLevelControlStartPWMO.getMessageErrorRet());
		assertTrue(result);

	}

	@Test
	public void testCheckWamatInputProcessRangeValuesFailed()
	{
		SecondLevelControlStartPWMO secondLevelControlStartPWMO = new SecondLevelControlStartPWMO();
		List<WamatObject> listWamatObject = new ArrayList<WamatObject>();
		secondLevelControlStartPWMO.setListWamatObjectInputProcess(listWamatObject);
		GenerationProcessSt generationProcessSt = generationProcessStService.getGenerationProcessSt(7);
		secondLevelControlStartPWMO.setGenerationProcessSt(generationProcessSt);
		boolean result = false;
		try {
			result = RangeWamatInputProcessPWMOCheckRulesRunner.runRules(secondLevelControlStartPWMO,actGeBytearrayService);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		System.out.println(secondLevelControlStartPWMO.getMessageErrorRet());
		assertTrue(result);

	}
}
