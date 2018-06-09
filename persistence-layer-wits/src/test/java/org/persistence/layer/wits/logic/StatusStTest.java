package org.persistence.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.StatusStDAO;
import org.persistence.layer.wits.form.StatusSt;
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
public class StatusStTest {
  @Autowired
  StatusStDAO statusStDAO;
	@Test
	public void statusStDAOTest()
	{
		StatusSt statusSt = new StatusSt();
		statusSt.setNameStatusSt("EMPTY");
		statusSt.setDescriptionStatusSt("Vuoto");
		statusStDAO.addStatusSt(statusSt);
		StatusSt statusSt1 = new StatusSt();
		statusSt1.setNameStatusSt("FILLING");
		statusSt1.setDescriptionStatusSt("In Riempimento");
		statusStDAO.addStatusSt(statusSt1);
		StatusSt statusSt2 = new StatusSt();
		statusSt2.setNameStatusSt("CLOSED");
		statusSt2.setDescriptionStatusSt("Chiuso");
		statusStDAO.addStatusSt(statusSt2);
		StatusSt statusSt3 = new StatusSt();
		statusSt3.setNameStatusSt("APPROVED");
		statusSt3.setDescriptionStatusSt("Approvato");
		statusStDAO.addStatusSt(statusSt3);
	}
  
}
