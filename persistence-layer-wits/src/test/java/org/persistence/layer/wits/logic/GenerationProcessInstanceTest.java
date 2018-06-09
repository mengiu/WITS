package org.persistence.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.GenerationProcessInstanceDAO;
import org.persistence.layer.wits.form.GenerationProcessInstance;
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
public class GenerationProcessInstanceTest {
   @Autowired
   GenerationProcessInstanceDAO generationProcessInstanceDAO;
   
   @Test
   public void listGenerationProcessInstanceTest()
   {
	   for (GenerationProcessInstance item : generationProcessInstanceDAO.listGenerationProcessInstance())
	   {
		   System.out.println(item.getPiStartDate());
		   System.out.println(item.getWmp2GenerationProcess().getIdWmp2GenerationProcess());
	   }
   }
}
