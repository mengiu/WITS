package org.services.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.GroupStDAO;
import org.persistence.layer.wits.dao.GroupTypeStDAO;
import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.GroupTypeSt;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GroupStTest {
	@Autowired
	GroupStDAO groupStDAO;
	@Autowired
	GroupTypeStDAO groupTypeStDAO;
	
  @Test
  public void GroupStDAOTest()
  {
	  GroupTypeSt groupTypeSt = groupTypeStDAO.getGroupTypeSt(0);
	  GroupSt groupSt = groupStDAO.getGroupSt(3);
	  groupSt.setNameGroupSt("Goup SGRR 11");
	  groupStDAO.updateGroupSt(groupSt);
  }
}
