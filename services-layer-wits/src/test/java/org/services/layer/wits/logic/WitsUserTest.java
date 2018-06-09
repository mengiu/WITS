package org.services.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.WitsUserSt;
import org.services.layer.wits.services.WitsUserStService;
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
public class WitsUserTest {
	@Autowired
	WitsUserStService witsUserStService;

	  @Test
	  public void WitsUserStServiceTest() {
			WitsUserSt user =  new WitsUserSt();
			int id = witsUserStService.addWitsUserSt(user);
			//List<WitsUserSt> list = WitsUserStService.listWitsUserSt();
			
			//WitsUserStService.removeWitsUserSt(id);
	  }
	  @Test
	  public void getWitsUserStTest() {
		  WitsUserSt witsUserSt = witsUserStService.getWitsUserSt("kermit",true);
		  System.out.println(witsUserSt.getIdWitsUserSt());
	  }

}
