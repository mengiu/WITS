package org.services.layer.wits.logic;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.identity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.enumusertypes.RoleStType;
import org.persistence.layer.wits.form.WitsUserSt;
import org.services.layer.wits.services.FacadeIdentityService;
import org.services.layer.wits.services.FacadeUserDetailPanelService;
import org.services.layer.wits.services.WitsUserStService;
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
public class FacadeUserDetailPanelServiceTest {
	@Autowired
	@Qualifier("facadeUserDetailPanelService")
	FacadeUserDetailPanelService facadeUserDetailPanelService;
	@Autowired
	@Qualifier("witsUserStService")
	WitsUserStService witsUserStService;
	@Autowired
	private ProcessEngine processEngine;

	@Test
	public void addRoleAdministratorOrGuestTest()
	{
		WitsUserSt witsUserSt = witsUserStService.getWitsUserSt(19);
		//facadeUserDetailPanelService.addRoleAdministratorOrGuest(witsUserSt, RoleStType.ADMINISTRATOR);
	}

	@Test
	public void removeUserWitsTest()
	{
		//IdentityService identityService = processEngine.getIdentityService();
		//User user = identityService.createUserQuery().userId("secondotest").singleResult();
		WitsUserSt witsUserSt = witsUserStService.getWitsUserSt(25);
		witsUserStService.removeWitsUserSt(witsUserSt.getIdWitsUserSt());
		//identityService.deleteUser(user.getId());
	}
}
