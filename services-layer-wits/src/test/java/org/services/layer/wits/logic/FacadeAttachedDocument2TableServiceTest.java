package org.services.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableAttached;
import org.persistence.layer.wits.form.AttachedDocument2Table;
import org.persistence.layer.wits.form.WamatObject;
import org.services.layer.wits.services.FacadeAttachedDocument2TableService;
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
@TransactionConfiguration(transactionManager="txManager",
defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class FacadeAttachedDocument2TableServiceTest {
	@Autowired
	@Qualifier("facadeAttachedDocument2TableService")
	FacadeAttachedDocument2TableService facadeAttachedDocument2TableService;
	@Autowired
	@Qualifier("wamatObjectService")
	WamatObjectService wamatObjectService;
	@Test
	public void listAttachedDocument2TableTest()
	{
		WamatObject wamatObject = wamatObjectService.getWamatObject(161);
		OrdersFieldsTableAttached ordersFieldsTableAttached = OrdersFieldsTableAttached.ATTACHED;
		for ( AttachedDocument2Table item : facadeAttachedDocument2TableService.listAttachedDocument2Table(wamatObject, null, true, ordersFieldsTableAttached, true))
		{
			System.out.println(item.getNameAttachedDocument());
			System.out.println(item.getAttachedDocument().getIdAttachedDocument());
			try {
				System.out.println(item.getAttachedDocument().getDocumentTypeSt().getNameDocumentTypeSt());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
