package org.services.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.util.AttachToGenericTable;
import org.services.layer.wits.services.FacadeAttachedDocumentService;
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
public class FacadeAttachedDocumentServiceTest {
 @Autowired
 @Qualifier("facadeAttachedDocumentService")
 FacadeAttachedDocumentService facadeAttachedDocumentService;
 @Test
 public void removeAttachedDocumentTest()
 {
		AttachToGenericTable attachToGenericTable = new AttachToGenericTable();
		attachToGenericTable.setTableName(WamatObject.class.getAnnotation(javax.persistence.Table.class).name());
		attachToGenericTable.setIdTableRelated(161);
		facadeAttachedDocumentService.removeAttachedDocument(21,attachToGenericTable);
		
	 
 }
}
