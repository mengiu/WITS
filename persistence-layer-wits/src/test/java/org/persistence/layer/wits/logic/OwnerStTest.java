package org.persistence.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.OwnerStDAO;
import org.persistence.layer.wits.form.OwnerSt;
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
public class OwnerStTest {
	@Autowired
	OwnerStDAO ownerStDAO;
	
	@Test
	public void ownerStDAOTest()
	{
		List<OwnerSt> listOwnerSt = ownerStDAO.listOwnerSt();
		for (OwnerSt item : listOwnerSt)
		{
			item.getNameOwnerSt();
		}
	}
}
