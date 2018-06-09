package org.persistence.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.ContainingUnitTypeStDAO;
import org.persistence.layer.wits.form.ContainingUnitTypeSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=true)
@Transactional( propagation = Propagation.REQUIRED )
public class ContainingUnitTypeStTest {
	@Autowired
	ContainingUnitTypeStDAO geometryTypeStDAO;
	
	 @Test
	 public void geometryTypeStDAOTest() {
		 ContainingUnitTypeSt geometryTypeSt = new ContainingUnitTypeSt();
		 geometryTypeSt.setNameContUnitTypeSt("DRUM 220");
		 geometryTypeStDAO.addContainingUnitTypeSt(geometryTypeSt);
		 ContainingUnitTypeSt geometryTypeSt1 = new ContainingUnitTypeSt();
		 geometryTypeSt1.setNameContUnitTypeSt("DRUM 440");
		 geometryTypeStDAO.addContainingUnitTypeSt(geometryTypeSt1);
		 ContainingUnitTypeSt geometryTypeSt2 = new ContainingUnitTypeSt();
		 geometryTypeSt2.setNameContUnitTypeSt("BUILDING");
		 geometryTypeSt2.setIsImmobile(true);
		 geometryTypeStDAO.addContainingUnitTypeSt(geometryTypeSt2);
	 }

	 @Test
     public void listContainingUnitTypeStTest()
     {
    	 for (ContainingUnitTypeSt item : geometryTypeStDAO.listContainingUnitTypeSt())
    	 {
    		 item.getNameContUnitTypeSt();
    	 }
     }
}
