package org.services.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableItems;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.MaterialSt;
import org.persistence.layer.wits.form.WamatObject;
import org.services.layer.wits.services.ItemService;
import org.services.layer.wits.services.WamatObjectService;
import org.services.layer.wits.services.delegate.cwmo.CloseDetailedScheduleWaMatService;
import org.services.layer.wits.servicesimpl.delegate.cwmo.CloseDetailedScheduleWaMatServiceImpl;
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
public class CloseDetailedScheduleWaMatServiceTest {
	@Autowired
	@Qualifier("itemService")
	protected ItemService itemService;
	@Autowired
	@Qualifier("wamatObjectService")
	WamatObjectService wamatObjectService;
	
	@Test
	public void CompareMaterialTest()
	{
		CloseDetailedScheduleWaMatServiceImpl 
		closeDetailedScheduleWaMatService = new CloseDetailedScheduleWaMatServiceImpl();
		WamatObject wamatObject = wamatObjectService.getWamatObject(8117);
		List<Item> listItem = itemService.listItem(wamatObject, OrdersFieldsTableItems.ID, false);
		MaterialSt anchorMaterialSt = null;
		if (listItem!=null && listItem.size()>0)
		{
			anchorMaterialSt = listItem.get(0).getMaterialSt();
			for (int index=0;index<listItem.size();index++)
			{
				anchorMaterialSt = closeDetailedScheduleWaMatService.
						compareMaterial(anchorMaterialSt,listItem.get(index).getMaterialSt());
			}
			System.out.println(anchorMaterialSt.getNameMaterialSt());
			System.out.println(anchorMaterialSt.getIdMaterialSt());
		}
	}
}
