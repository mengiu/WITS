package org.services.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.to.ItemTO;
import org.persistence.layer.wits.dao.to.WacComplianceClaimTO;
import org.persistence.layer.wits.dao.to.WamatExtendedFieldTO;
import org.persistence.layer.wits.dao.to.WamatObjectTO;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.form.WitsUserSt;
import org.services.layer.wits.services.FacadeHystoryService;
import org.services.layer.wits.services.WamatObjectService;
import org.services.layer.wits.services.WitsUserStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",
defaultRollback=true)
@Transactional( propagation = Propagation.REQUIRED )
public class FacadeHystoryServiceTest {
	@Autowired
	@Qualifier("facadeHystoryService")
	FacadeHystoryService facadeHystoryService;
	@Autowired
	@Qualifier("witsUserStService")
	WitsUserStService witsUserStService;
	@Autowired
	@Qualifier("wamatObjectService")
	WamatObjectService wamatObjectService;
	
	 @Test
	 public void getListWamatExtendedFieldTOTest()
	 {
		 WamatObject wamatObject = wamatObjectService.getWamatObject(264);
		 WitsUserSt witsUserSt = witsUserStService.getWitsUserSt(104);
		 List<WamatObjectTO> listWamatObjectTO = facadeHystoryService.getListWamatObjectTO(wamatObject, witsUserSt, null, null);
		 for (WamatObjectTO item : listWamatObjectTO)
		 {
			 System.out.println(item.getIdWamatObject());
		 }
		 WamatObject wamatObject1 = wamatObjectService.getWamatObject(6160);
		 List<WacComplianceClaimTO> listWacComplianceClaimTO = facadeHystoryService.getListWacComplianceClaimTO(wamatObject1, witsUserSt, null, null);
		 for (WacComplianceClaimTO item : listWacComplianceClaimTO)
		 {
			 System.out.println(item.getIdWacComplianceClaim());
		 }
		 WamatObject wamatObject2 = wamatObjectService.getWamatObject(8329);
		 List<WamatExtendedFieldTO> listWamatExtendedFieldTO = facadeHystoryService.getListWamatExtendedFieldTO(wamatObject2, witsUserSt, null, null);
		 for (WamatExtendedFieldTO item : listWamatExtendedFieldTO)
		 {
			 System.out.println(item.getIdWamatExtendedField());
		 }
		 WamatObject wamatObject3 = wamatObjectService.getWamatObject(25);
		 List<ItemTO> listItemTO = facadeHystoryService.getListItemTO(wamatObject3, witsUserSt, null, null);
		 for (ItemTO item : listItemTO)
		 {
			 System.out.println(item.getIdItem());
		 }
	 }

}
