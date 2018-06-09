package org.persistence.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.GraficFormatStDAO;
import org.persistence.layer.wits.form.GraficFormatSt;
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
public class GraficFormatStTest {
	@Autowired
	GraficFormatStDAO graficFormatStDAO;

	@Test
	public void listGraficFormatStTest()
	{
		List<GraficFormatSt> listGraficFormatSt = graficFormatStDAO.listGraficFormatSt();
		for (GraficFormatSt item : listGraficFormatSt)
			item.getFileExtention();
	}
	
}
