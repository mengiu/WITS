package org.persistence.layer.wits.logic;

import java.util.List;

import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.ActIdUserDAO;
import org.persistence.layer.wits.dao.WitsUserStDAO;
import org.persistence.layer.wits.form.ActIdUser;
import org.persistence.layer.wits.form.WitsUserSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:activiti.cfg.xml", "classpath:activiti-context.xml"})
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class WitsUserTest {
 @Autowired
 WitsUserStDAO witsUserStDAO;
 @Autowired
 ActIdUserDAO actIdUserDAO;
 
 @Test
 //@Deployment
 public void WitsUserStDAOTest() {
	 List<WitsUserSt> listWitsUserSt = witsUserStDAO.listWitsUserSt();
     for (WitsUserSt item : listWitsUserSt)
     {
    	 item.getIdWitsUserSt();
     }
     WitsUserSt  witsUserSt = new WitsUserSt();
     ActIdUser actIdUser = actIdUserDAO.getActIdUser("kermit");
     witsUserSt.setActIdUser(actIdUser);
     witsUserStDAO.addWitsUserSt(witsUserSt);
 }
 
 @Test
 public void getWitsUserStTest()
 {
	 WitsUserSt witsUserSt = witsUserStDAO.getWitsUserSt(25);
	 witsUserSt.getActIdUser().getId();
	 witsUserStDAO.removeWitsUserSt(witsUserSt.getIdWitsUserSt());
 }

}
