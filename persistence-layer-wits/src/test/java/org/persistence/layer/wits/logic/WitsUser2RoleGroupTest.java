package org.persistence.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.GroupStDAO;
import org.persistence.layer.wits.dao.GroupTypeStDAO;
import org.persistence.layer.wits.dao.OwnerStDAO;
import org.persistence.layer.wits.dao.RoleStDAO;
import org.persistence.layer.wits.dao.WitsUser2RoleGroupDAO;
import org.persistence.layer.wits.dao.WitsUserStDAO;
import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.GroupTypeSt;
import org.persistence.layer.wits.form.OwnerSt;
import org.persistence.layer.wits.form.RoleSt;
import org.persistence.layer.wits.form.WitsUser2RoleGroup;
import org.persistence.layer.wits.form.WitsUserSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class WitsUser2RoleGroupTest {
    @Autowired
    WitsUser2RoleGroupDAO witsUser2RoleGroupDAO;
    @Autowired
    WitsUserStDAO witsUserStDAO;
    @Autowired
    RoleStDAO roleStDAO;
    @Autowired
    GroupTypeStDAO groupTypeStDAO;
	@Autowired
	OwnerStDAO ownerStDAO;
    @Autowired
    GroupStDAO groupStDAO;
    
    @Test
    public void listWitsUserSt2RoleGroupTest()
    {
    	WitsUserSt WitsUserSt = witsUserStDAO.getWitsUserSt("kermit",true);
    	List<WitsUser2RoleGroup> listWitsUserSt2RoleGroup = witsUser2RoleGroupDAO.listWitsUser2RoleGroup(WitsUserSt);
    	for (WitsUser2RoleGroup item : listWitsUserSt2RoleGroup )
    	{
    		item.getRoleSt().getDescription();
    	}
    }
    @Test
    public void listWitsUserSt2RoleGroupTest2()
    {
    	RoleSt role = roleStDAO.getRoleSt(0);
    	GroupTypeSt witsGroup = groupTypeStDAO.getGroupTypeSt(5);
    	OwnerSt ownerSt = ownerStDAO.getOwnerSt(30);
    	try {
			for ( WitsUser2RoleGroup item : witsUser2RoleGroupDAO.listWitsUser2RoleGroup(role, witsGroup, ownerSt))
			{
				item.getId();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
 
    @Test
    public void listWitsUserSt2RoleGroupTest3()
    {
    	GroupSt witsGroup = groupStDAO.getGroupSt(2);
    	try {
			for ( WitsUser2RoleGroup item : witsUser2RoleGroupDAO.listWitsUser2RoleGroup(witsGroup))
			{
				item.getId();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
