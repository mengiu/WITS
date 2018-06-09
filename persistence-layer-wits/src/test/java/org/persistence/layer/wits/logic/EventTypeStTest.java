package org.persistence.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.EventTypeStDAO;
import org.persistence.layer.wits.form.EventTypeSt;
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
public class EventTypeStTest {
	@Autowired
	EventTypeStDAO eventTypeStDAO;
	@Test
	public void listEventTypeStTest()
	{
		for(EventTypeSt item : eventTypeStDAO.listEventTypeSt())
		{
			item.getDescriptionEventTypeSt();
		}
	}

}
