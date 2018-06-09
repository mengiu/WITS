package org.persistence.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.WamatExtendedFieldTypeStDAO;
import org.persistence.layer.wits.dao.WamatObjectDAO;
import org.persistence.layer.wits.form.WamatExtendedFieldTypeSt;
import org.persistence.layer.wits.form.WamatObject;
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
public class WamatExtendedFieldTypeStTest {
	@Autowired
	WamatExtendedFieldTypeStDAO wamatExtendedFieldTypeStDAO;
	@Autowired
	WamatObjectDAO wamatObjectDAO;

	@Test
	public void listWamatExtendedFieldTypeStTest()
	{
		WamatObject wamatObject = wamatObjectDAO.getWamatObject(16);
		List<WamatExtendedFieldTypeSt> listWamatExtendedFieldTypeSt = wamatExtendedFieldTypeStDAO.listWamatExtendedFieldTypeSt(wamatObject);
		for (WamatExtendedFieldTypeSt item : listWamatExtendedFieldTypeSt)
		{
			System.out.println(item.getNameWamatExtField());
		}
	}

}
