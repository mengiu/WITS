package org.services.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.WasteManagementPlanSt;
import org.services.layer.wits.services.WasteManagementPlanStService;
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
                        defaultRollback=true)
@Transactional( propagation = Propagation.REQUIRED )
public class WasteManagementPlanStServiceTest {
      @Autowired
      WasteManagementPlanStService wasteManagementPlanStService;
	  @Test
	  public void WasteManagementPlanStTest() {
		  WasteManagementPlanSt wmp =  new WasteManagementPlanSt();
		  wmp.setDescriptionWmSt("First WMP");
		  wmp.setNameWasteManagementPlanSt("First WMP");
		  int id = wasteManagementPlanStService.addWasteManagementPlanSt(wmp);
		  wmp =  new WasteManagementPlanSt();
		  wmp.setDescriptionWmSt("Second WMP");
		  wmp.setNameWasteManagementPlanSt("Second WMP");
		  id = wasteManagementPlanStService.addWasteManagementPlanSt(wmp);
	  }

}
