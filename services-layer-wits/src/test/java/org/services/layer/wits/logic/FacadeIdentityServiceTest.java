package org.services.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.to.GenericRole;
import org.services.layer.wits.services.FacadeIdentityService;
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
public class FacadeIdentityServiceTest {
  @Autowired
  @Qualifier("facadeIdentityService")
  FacadeIdentityService facadeIdentityService;
  @Test
  public void getListUserRolesTest()
  {
	  List<GenericRole> list = facadeIdentityService.getListUserRoles("kermit");
	  for (GenericRole item : list)
	  {
		  System.out.println(item.getRole_description());
	  }
  }
}
