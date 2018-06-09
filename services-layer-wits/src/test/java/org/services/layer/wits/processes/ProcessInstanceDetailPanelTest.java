package org.services.layer.wits.processes;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.PersistenceConstants;
import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.RoleSt;
import org.persistence.layer.wits.form.WitsUser2RoleGroup;
import org.services.layer.wits.services.FacadeIdentityService;
import org.services.layer.wits.services.GroupStService;
import org.services.layer.wits.services.RoleStService;
import org.services.layer.wits.services.WitsUser2RoleGroupService;
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
defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class ProcessInstanceDetailPanelTest {
	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	@Autowired
	@Qualifier("roleStService")
	protected RoleStService roleStService;
	@Autowired
	@Qualifier("groupStService")
	protected GroupStService groupStService;
	@Autowired
	@Qualifier("facadeIdentityService")
	protected FacadeIdentityService facadeIdentityService;
	@Autowired
	@Qualifier("witsUser2RoleGroupService")
	protected WitsUser2RoleGroupService witsUser2RoleGroupService;
	@Test
	public void addTaskItemTest()
	{
		  List<IdentityLink> identityLinks = taskService.getIdentityLinksForTask("5963");
		  List<String> listUserName = new ArrayList<String>();
		  for (final IdentityLink identityLink : identityLinks) { 
			  if (identityLink.getUserId() != null) { // only user identity links, ignoring the group ids
				  if (!IdentityLinkType.ASSIGNEE.equals(identityLink.getType())
						  && !IdentityLinkType.OWNER.equals(identityLink.getType())) {
					  String[] rolesInfo = identityLink.getUserId().split(PersistenceConstants.SEPARATOR_ROLES);
					  RoleSt roleSt = roleStService.getRoleSt(rolesInfo[0]); // roles name
					  GroupSt witsInstanceGroup = groupStService.getGroupSt(new Integer(rolesInfo[1]));
					  List<WitsUser2RoleGroup> listWitsUser2RoleGroup = this.witsUser2RoleGroupService.listWitsUser2RoleGroup(roleSt, witsInstanceGroup);
					  for (WitsUser2RoleGroup itemWitsUser2RoleGroup : listWitsUser2RoleGroup)
					  {
						  String userName = witsUser2RoleGroupService.getWitsUserName(itemWitsUser2RoleGroup.getId());   
						  if (!listUserName.contains(userName))
							  listUserName.add(userName);
					  }
				  }
			  }
		  }

	}
}
