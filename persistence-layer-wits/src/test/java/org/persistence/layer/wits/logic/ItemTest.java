package org.persistence.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.GenerationProcessInstanceDAO;
import org.persistence.layer.wits.dao.ItemDAO;
import org.persistence.layer.wits.form.GenerationProcessInstance;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.identity.WitsAuthentication;
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
public class ItemTest {
	@Autowired
	ItemDAO itemDAO;
	@Autowired
	GenerationProcessInstanceDAO generationProcessInstanceDAO;

	@Test
	public void listItemTest()
	{
		System.out.println(2 | 3);
		GenerationProcessInstance generationProcessInstance = generationProcessInstanceDAO.getGenerationProcessInstance(60);
		List<Item> listItem = itemDAO.listItem(generationProcessInstance);
		for (Item item :listItem )
		{
			System.out.println(item.getDescriptionItem());
		}
	}
	@Test
	public void setItemOnPostUpdateTest()
	{
		WitsAuthentication.setAuthenticatedUserId("castepo");
        Item item = itemDAO.getItem(25);
        item.setDescriptionItem("pipp");
        itemDAO.updateItem(item);
	}
}
