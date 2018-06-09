package org.persistence.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.ViewMetadataColumnTableDAO;
import org.persistence.layer.wits.dao.WacComplianceClaimDAO;
import org.persistence.layer.wits.dao.WacStDAO;
import org.persistence.layer.wits.dao.to.impl.WacComplianceClaimTOImpl;
import org.persistence.layer.wits.form.WacComplianceClaim;
import org.persistence.layer.wits.form.WacSt;
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
public class WacComplianceClaimTest {
	@Autowired
	WacComplianceClaimDAO wacComplianceClaimDAO;
	@Autowired
	WacStDAO wacStDAO;
	@Autowired
	ViewMetadataColumnTableDAO viewMetadataColumnTableDAO; 
	
	@Test
	public void setWacComplianceClaimOnPostUpdateTest()
	{
		WitsAuthentication.setAuthenticatedUserId("castepo");
		WacComplianceClaim wacComplianceClaim = wacComplianceClaimDAO.getWacComplianceClaim(32);
		//WacComplianceClaim wacComplianceClaim = new WacComplianceClaim();
		//wacComplianceClaim.setIdWacComplianceClaim(32);
		//WacSt wacSt = wacStDAO.getWacSt(7);
		//wacComplianceClaim.setWacSt(wacSt);
		//wacComplianceClaimDAO.updateWacComplianceClaim(wacComplianceClaim);
		Object objectTO = new WacComplianceClaimTOImpl(wacComplianceClaim,
				viewMetadataColumnTableDAO.listViewMetadataColumnTable(WacComplianceClaim.class.getAnnotation(javax.persistence.Table.class).name()));
		XmlUtil xmlUtil = new XmlUtil();
		String xml = xmlUtil.convertToXml(objectTO, WacComplianceClaimTOImpl.class);
		System.out.println(xml);

	}

}
