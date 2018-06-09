package org.services.layer.wits.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.enumusertypes.ContainingUnitType;
import org.persistence.layer.wits.form.ContainingUnit;
import org.persistence.layer.wits.form.WamatObject;
import org.services.layer.wits.services.ContainingUnitService;
import org.services.layer.wits.services.WamatObjectService;
import org.services.layer.wits.services.drools.PositionCheckRuleRunner;
import org.services.layer.wits.services.model.SecondLevelControlPosition;
import org.services.layer.wits.services.model.StatusReturnAnswer;
import org.services.layer.wits.services.parser.ReadingObjectCoordinate;
import org.services.layer.wits.services.parser.impl.ReadingObjectCoordinateImpl;
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
@Deployment(resources = { "classpath*:/rules/CheckPositionRules.drl" })
public class PositionCheckTest {
	@Autowired
	@Qualifier("containingUnitService")
	ContainingUnitService containingUnitService;
	 @Autowired
	 @Qualifier("wamatObjectService")
	 WamatObjectService wamatObjectService;

	@Test
	public void testPositionCheckPassed()
	{
		SecondLevelControlPosition secondLevelControlPosition = 
				new SecondLevelControlPosition(wamatObjectService,containingUnitService);
		ContainingUnit containingUnit = containingUnitService.getContainingUnit(143);
		WamatObject wamatObject = wamatObjectService.getWamatObject(321);
		secondLevelControlPosition.setWamatObjectMoved(wamatObject);
		secondLevelControlPosition.setContainingUnitDest(containingUnit);
		ReadingObjectCoordinate objectCoordinate = new ReadingObjectCoordinateImpl(7,10,13);
		secondLevelControlPosition.setStatusReturnAnswer(StatusReturnAnswer.OK);
		secondLevelControlPosition.setObjectCoordinate(objectCoordinate);
		boolean duplicateCoordinate = containingUnitService.getContainingUnit(containingUnit, 
				objectCoordinate.getCoordinateXYZ().get("X").shortValue(), 
				objectCoordinate.getCoordinateXYZ().get("Y").shortValue(), 
				objectCoordinate.getCoordinateXYZ().get("Z").shortValue())!=null;
		secondLevelControlPosition.setDuplicateAdmitted(duplicateCoordinate);
		boolean result = false;
		try {
			result = PositionCheckRuleRunner.runRules(secondLevelControlPosition);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		System.out.println(secondLevelControlPosition.getMessageErrorRet());
		assertTrue(result);
		
	}

	@Test
	public void testPositionCheckPassed2()
	{
		SecondLevelControlPosition secondLevelControlPosition = 
				new SecondLevelControlPosition(wamatObjectService,containingUnitService);
		ContainingUnit containingUnit = containingUnitService.getContainingUnit(143);
		ContainingUnit containingUnitMove = containingUnitService.getContainingUnit(361);
		secondLevelControlPosition.setContainingUnitMoved(containingUnitMove);
		secondLevelControlPosition.setContainingUnitDest(containingUnit);
		ReadingObjectCoordinate objectCoordinate = new ReadingObjectCoordinateImpl(7,10,13);
		secondLevelControlPosition.setStatusReturnAnswer(StatusReturnAnswer.OK);
		secondLevelControlPosition.setObjectCoordinate(objectCoordinate);
		boolean duplicateCoordinate = containingUnitService.getContainingUnit(containingUnit, 
				objectCoordinate.getCoordinateXYZ().get("X").shortValue(), 
				objectCoordinate.getCoordinateXYZ().get("Y").shortValue(), 
				objectCoordinate.getCoordinateXYZ().get("Z").shortValue())!=null;
		secondLevelControlPosition.setDuplicateAdmitted(duplicateCoordinate);
		boolean result = false;
		try {
			//result = PositionCheckRuleRunner.runRules(secondLevelControlPosition);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		System.out.println(secondLevelControlPosition.getMessageErrorRet());
		assertTrue(result);
		
	}
	
	@Test
	public void testPositionEvaluationRule_1CheckFailed()
	{
		boolean result = false;
		SecondLevelControlPosition secondLevelControlPosition = 
				new SecondLevelControlPosition(wamatObjectService,containingUnitService);
		ContainingUnit containingUnit = containingUnitService.getContainingUnit(143);
		ContainingUnitType containingUnitType = containingUnitService.getTypeContainingUnit(143);
		secondLevelControlPosition.setContainingUnitDest(containingUnit);
		ReadingObjectCoordinate objectCoordinate = new ReadingObjectCoordinateImpl(3,20,6);
		secondLevelControlPosition.setStatusReturnAnswer(StatusReturnAnswer.OK);
		secondLevelControlPosition.setObjectCoordinate(objectCoordinate);
		try {
			 //result = PositionCheckRuleRunner.runRules(secondLevelControlPosition);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		assertFalse(result);
	}
	@Test
	public void testPositionEvaluationRule_2CheckFailed()
	{
		SecondLevelControlPosition secondLevelControlPosition = 
				new SecondLevelControlPosition(wamatObjectService,containingUnitService);
		ContainingUnit containingUnit = containingUnitService.getContainingUnit(144);
		ContainingUnitType containingUnitType = containingUnitService.getTypeContainingUnit(144);
		secondLevelControlPosition.setContainingUnitDest(containingUnit);
		ReadingObjectCoordinate objectCoordinate = new ReadingObjectCoordinateImpl(8,9,10);
		secondLevelControlPosition.setStatusReturnAnswer(StatusReturnAnswer.OK);
		secondLevelControlPosition.setObjectCoordinate(objectCoordinate);
		boolean duplicateCoordinate = containingUnitService.getContainingUnit(containingUnit, 
				objectCoordinate.getCoordinateXYZ().get("X").shortValue(), 
				objectCoordinate.getCoordinateXYZ().get("Y").shortValue(), 
				objectCoordinate.getCoordinateXYZ().get("Z").shortValue())!=null;
		secondLevelControlPosition.setDuplicateAdmitted(duplicateCoordinate);
		boolean result = false;
		try {
			 //result = PositionCheckRuleRunner.runRules(secondLevelControlPosition);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		assertFalse(result);
		System.out.println(secondLevelControlPosition.getMessageErrorRet());
		
	}

	@Test
	public void testPositionEvaluationRule_3CheckFailed()
	{
		SecondLevelControlPosition secondLevelControlPosition = 
				new SecondLevelControlPosition(wamatObjectService,containingUnitService);
		ContainingUnit containingUnit = containingUnitService.getContainingUnit(143);
		WamatObject wamatObject = wamatObjectService.getWamatObject(321);
		secondLevelControlPosition.setWamatObjectMoved(wamatObject);
		secondLevelControlPosition.setContainingUnitDest(containingUnit);
		ReadingObjectCoordinate objectCoordinate = new ReadingObjectCoordinateImpl(7,10,13);
		secondLevelControlPosition.setStatusReturnAnswer(StatusReturnAnswer.OK);
		secondLevelControlPosition.setObjectCoordinate(objectCoordinate);
		boolean duplicateCoordinate = containingUnitService.getContainingUnit(containingUnit, 
				objectCoordinate.getCoordinateXYZ().get("X").shortValue(), 
				objectCoordinate.getCoordinateXYZ().get("Y").shortValue(), 
				objectCoordinate.getCoordinateXYZ().get("Z").shortValue())!=null;
		secondLevelControlPosition.setDuplicateAdmitted(duplicateCoordinate);
		boolean result = false;
		try {
			//result = PositionCheckRuleRunner.runRules(secondLevelControlPosition);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		assertFalse(result);
		System.out.println(secondLevelControlPosition.getMessageErrorRet());

	}

	@Test
	public void testPositionEvaluationRule_4CheckFailed()
	{
		SecondLevelControlPosition secondLevelControlPosition = 
				new SecondLevelControlPosition(wamatObjectService,containingUnitService);
		ContainingUnit containingUnit = containingUnitService.getContainingUnit(143);
		WamatObject wamatObject = wamatObjectService.getWamatObject(321);
		secondLevelControlPosition.setWamatObjectMoved(wamatObject);
		secondLevelControlPosition.setContainingUnitDest(containingUnit);
		ReadingObjectCoordinate objectCoordinate = new ReadingObjectCoordinateImpl(7,10,13);
		secondLevelControlPosition.setStatusReturnAnswer(StatusReturnAnswer.OK);
		secondLevelControlPosition.setObjectCoordinate(objectCoordinate);
		boolean duplicateCoordinate = containingUnitService.getContainingUnit(containingUnit, 
				objectCoordinate.getCoordinateXYZ().get("X").shortValue(), 
				objectCoordinate.getCoordinateXYZ().get("Y").shortValue(), 
				objectCoordinate.getCoordinateXYZ().get("Z").shortValue())!=null;
		secondLevelControlPosition.setDuplicateAdmitted(duplicateCoordinate);
		boolean result = false;
		try {
			result = PositionCheckRuleRunner.runRules(secondLevelControlPosition);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		System.out.println(secondLevelControlPosition.getMessageErrorRet());
		assertFalse(result);
		
	}

}
