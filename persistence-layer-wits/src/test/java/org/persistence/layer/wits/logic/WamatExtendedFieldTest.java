package org.persistence.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.WamatExtendedFieldDAO;
import org.persistence.layer.wits.dao.to.WamatExtendedFieldTO;
import org.persistence.layer.wits.dao.to.impl.WamatExtendedFieldTOImpl;
import org.persistence.layer.wits.form.WamatExtendedField;
import org.persistence.layer.wits.identity.WitsAuthentication;
import org.persistence.layer.wits.util.XmlUtil;
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
public class WamatExtendedFieldTest {
	@Autowired
	WamatExtendedFieldDAO wamatExtendedFieldDAO;
	
	@Test
	public void setWamatExtendedFieldOnPostUpdateTest()
	{
		WitsAuthentication.setAuthenticatedUserId("castepo");
		WamatExtendedField wamatExtendedField = wamatExtendedFieldDAO.getWamatExtendedField(72441);
		wamatExtendedField.setValue("Item 1 - Date: 19/03/2014 9.09.10 (Generation date)");
		wamatExtendedFieldDAO.updateWamatExtendedField(wamatExtendedField);
		/*WamatExtendedField wamatExtendedField = new WamatExtendedField();
		wamatExtendedField.setIdWamatExtendedField(72441);
		wamatExtendedField.setValue("Item 1 - Date: 19/03/2014 9.09.10 (Generation date)");
		Object objectTO = new WamatExtendedFieldTOImpl(wamatExtendedField);
		XmlUtil xmlUtil = new XmlUtil();
		String xml = xmlUtil.convertToXml(objectTO, WamatExtendedFieldTOImpl.class);
		System.out.println(xml);*/
	}

}
