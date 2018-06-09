package org.services.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.OwnerSt;
import org.persistence.layer.wits.form.WitsUserSt;
import org.services.layer.wits.services.OwnerStService;
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
public class OwnerStTest {
    @Autowired
	@Qualifier("ownerStService")
    OwnerStService ownerStService;
	//@Autowired
	//@Qualifier("roleStService")
	//RoleStService roleStService;
	@Autowired
	@Qualifier("witsUserStService")
	WitsUserStService witsUserStService;
 
 @Test
 public void ownerStServiceTest()
 {
	 OwnerSt ow = new OwnerSt();
	 ow.setNameOwnerSt("First Owner");
	 ownerStService.addOwnerSt(ow);
	 ow = new OwnerSt();
	 ow.setNameOwnerSt("Second Owner");
	 ownerStService.addOwnerSt(ow);
	 
 }
 @Test
 public void getPropertyFieldTest()
 {
		List<OwnerSt> list = ownerStService.listOwnerSt();
		List<WitsUserSt> listWitsUserSt = witsUserStService.listWitsUserSt();
		/*RoleSt leader = roleStService.getRoleSt(1); // leader
		WitsUser currenWitsUser = null;
		for (WitsUser itemWitsUser : listWitsUser)
		{
			if (itemWitsUser.getName().equalsIgnoreCase("kermit"))
			{
				currenWitsUser = itemWitsUser;
				break;
			}
		}
		if (list!=null && !list.isEmpty() && currenWitsUser!=null)
		{
			for ( OwnerSt item : list)
			{
				OwnerSt ownerSt = ownerStService.getOwnerSt(item.getIdOwner());
				for (WitsGroupSt itemWitsGroupSt : ownerSt.getWitsGroupSts())
				{
					for (WitsUser2Role2Group itemWitsUser2Role2Group : itemWitsGroupSt.getWitsUser2Role2Groups())
					{
						if (itemWitsUser2Role2Group.getRoleSt().equals(leader) &&
								itemWitsUser2Role2Group.getWitsUser().equals(currenWitsUser))
						{
							break;
						}
					}
				}


			}
		}*/
	 
 }
 
}
