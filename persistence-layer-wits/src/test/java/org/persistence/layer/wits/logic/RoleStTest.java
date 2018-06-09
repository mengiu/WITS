package org.persistence.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.RoleStDAO;
import org.persistence.layer.wits.form.RoleSt;
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
public class RoleStTest {
	@Autowired
	RoleStDAO roleStDAO;
	
	@Test
	public void roleStDAOTest()
	{
		/*RoleSt roleSt = new RoleSt();
		roleSt.setName("Leader");
		roleStDAO.addRoleSt(roleSt);
		RoleSt roleSt1 = new RoleSt();
		roleSt1.setName("Supervisor");
		roleStDAO.addRoleSt(roleSt1);
		RoleSt roleSt2 = new RoleSt();
		roleSt2.setName("Operator");
		roleStDAO.addRoleSt(roleSt2);
		RoleSt roleSt3 = new RoleSt();
		roleSt3.setName("Logistic Support");
		roleStDAO.addRoleSt(roleSt3);
		RoleSt roleSt4 = new RoleSt();
		roleSt4.setName("Guest");
		roleStDAO.addRoleSt(roleSt4);
		RoleSt roleSt5 = new RoleSt();
		roleSt5.setName("SuperUser");
		roleStDAO.addRoleSt(roleSt5);*/
		for (RoleSt item : roleStDAO.listRoleSt())
		{
			item.getRoleLevel();
		}
		RoleSt roleSt = new RoleSt();
		roleSt.setName("Leader");
		roleStDAO.addRoleSt(roleSt);
	}

}
