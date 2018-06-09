package org.services.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.enumusertypes.RoleStType;
import org.persistence.layer.wits.enumusertypes.WitsGroupStType;
import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.OwnerSt;
import org.persistence.layer.wits.form.RoleSt;
import org.persistence.layer.wits.form.GroupTypeSt;
import org.persistence.layer.wits.form.WitsUser2RoleGroup;
import org.services.layer.wits.services.GroupStService;
import org.services.layer.wits.services.OwnerStService;
import org.services.layer.wits.services.RoleStService;
import org.services.layer.wits.services.GroupTypeStService;
import org.services.layer.wits.services.WitsUser2RoleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class WitsUser2RoleGroupTest {
	@Autowired
	@Qualifier("witsUser2RoleGroupService")
	WitsUser2RoleGroupService witsUser2RoleGroupService;
	@Autowired
	@Qualifier("roleStService")
	RoleStService roleStService;
	@Autowired
	@Qualifier("groupTypeStService")
	GroupTypeStService groupTypeStService;
	@Autowired
	@Qualifier("ownerStService")
	OwnerStService ownerStService;
	@Autowired
	@Qualifier("groupStService")
	GroupStService groupStService;

  @Test
  public void WitsUser2RoleGroupServiceTest()
  {
	  OwnerSt ownerSt = ownerStService.getOwnerSt(0);
	  RoleSt operator = roleStService.getRoleSt(RoleStType.OPERATORE.ordinal());
	  GroupTypeSt opg = groupTypeStService.getGroupTypeSt(WitsGroupStType.GRUPPO_OPERAZIONALE.ordinal()+1);
	  List<WitsUser2RoleGroup> list = witsUser2RoleGroupService.listWitsUser2RoleGroup(operator,opg,ownerSt);
	  for (WitsUser2RoleGroup item : list)
	  {
		  item.getId().getFkRole();
	  }
  }
  @Test
  public void WitsUser2RoleGroupServiceTest2()
  {
	  GroupSt groupSt = groupStService.getGroupSt(2);
	  List<WitsUser2RoleGroup> list = witsUser2RoleGroupService.listWitsUser2RoleGroup(groupSt);
	  for (WitsUser2RoleGroup item : list)
	  {
		  item.getId().getFkRole();
	  }
  }

}
