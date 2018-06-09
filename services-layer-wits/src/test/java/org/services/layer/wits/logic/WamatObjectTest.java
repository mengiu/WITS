package org.services.layer.wits.logic;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.enumusertypes.RoleStType;
import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.RoleSt;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.form.Wmo2ActHiProcinst;
import org.services.layer.wits.services.FacadeDefaultBarcodeService;
import org.services.layer.wits.services.GroupStService;
import org.services.layer.wits.services.RoleStService;
import org.services.layer.wits.services.WamatObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class WamatObjectTest {
	@Autowired
	@Qualifier("wamatObjectService")
	WamatObjectService wamatObjectService;
	@Autowired
	@Qualifier("groupStService")
	GroupStService groupStService;
	@Autowired
	@Qualifier("roleStService")
	RoleStService roleStService;
	@Autowired
	@Qualifier("facadeDefaultBarcodeService")
	FacadeDefaultBarcodeService facadeDefaultBarcodeService;
	@Autowired
	private ProcessEngine processEngine;

	@Test
	public void getWamatObjectServiceTest()
	{
		WamatObject item = wamatObjectService.getWamatObject(264);
		for ( Wmo2ActHiProcinst innerItem : item.getWmo2ActHiProcinsts())
		{
			innerItem.getInitiator();
		}
	}
	@Test
	public void getPositionWamatObjectTest()
	{
		String position = wamatObjectService.getPositionWamatObject(62);
		System.out.println(position);
	}
	@Test
	public void getListWamatObjecttoReadByGroupTest()
	{
		GroupSt groupSt = groupStService.getGroupSt(8);
		RoleSt leader = roleStService.getRoleSt(RoleStType.RESPONSABILE.ordinal()+1);
		HistoryService historyService = processEngine.getHistoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		List<WamatObject> listBarcodeToRead = facadeDefaultBarcodeService.getListWamatObjecttoReadByGroup(groupSt, leader, historyService, runtimeService);
		for (WamatObject item : listBarcodeToRead)
			System.out.println(item.getNameWamatObject());
	}


}
