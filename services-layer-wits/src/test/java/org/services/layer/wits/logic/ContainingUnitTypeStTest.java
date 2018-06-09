package org.services.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.ContainingUnitTypeSt;
import org.services.layer.wits.services.ContainingUnitTypeStService;
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
public class ContainingUnitTypeStTest {
	@Autowired
	ContainingUnitTypeStService geometryTypeStService;
	
	@Test
	public void geometryTypeStServiceTest() {
		ContainingUnitTypeSt geometryTypeSt = new ContainingUnitTypeSt();
		geometryTypeSt.setNameContUnitTypeSt("BUILDING 3");
		geometryTypeStService.addContainingUnitTypeSt(geometryTypeSt);
	  }

}
