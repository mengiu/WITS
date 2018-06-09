package org.services.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.ContainingUnit;
import org.persistence.layer.wits.form.ContainingUnitTypeSt;
import org.persistence.layer.wits.form.WamatObject;
import org.services.layer.wits.services.ContainingUnitService;
import org.services.layer.wits.services.ContainingUnitTypeStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",
                      defaultRollback=true)
@Transactional( propagation = Propagation.REQUIRED )
public class ContainingUnitTest {
	@Autowired
	@Qualifier("containingUnitService")
	ContainingUnitService containingUnitService;
	@Autowired
	@Qualifier("containingUnitTypeStService")
	ContainingUnitTypeStService containingUnitTypeStService;
  
	@Test
  public void getContainingUnitServiceTest(){
		ContainingUnitTypeSt geometryTypeSt = containingUnitTypeStService.getContainingUnitTypeSt(2); // Building
		ContainingUnit containingUnit = new ContainingUnit();
		containingUnit.setNameContainingUnit("Building 1");
		containingUnit.setContainingUnitTypeSt(geometryTypeSt);
		containingUnitService.addContainingUnit(containingUnit);
		ContainingUnit containingUnit1 = new ContainingUnit();
		containingUnit1.setNameContainingUnit("Building 2");
		containingUnit1.setContainingUnitTypeSt(geometryTypeSt);
		containingUnitService.addContainingUnit(containingUnit1);
 }
	@Test
	  public void getContainingUnitTest(){
		ContainingUnit dest = containingUnitService.getContainingUnit(4809);
		ContainingUnit containingUnit =containingUnitService.getContainingUnit(dest, (short)0, (short)0, (short)0);
		if (containingUnit!=null)
		 System.out.println(containingUnit.getNameContainingUnit());
	}
	
}
