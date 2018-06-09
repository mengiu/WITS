package org.persistence.layer.wits.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.AttachedDocument2TableDAO;
import org.persistence.layer.wits.form.AttachedDocument2Table;
import org.persistence.layer.wits.form.AttachedDocument2TableId;
import org.persistence.layer.wits.form.utility.AttachmentWits;
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
public class AttachedDocument2TableTest {
 @Autowired
 AttachedDocument2TableDAO attachedDocument2TableDAO;
 
 @Test
 public void listAttachedDocument2TableTest()
 {
	 List<Integer> list = new ArrayList<Integer>();
	 list.add(1);
	 for (AttachedDocument2Table item : attachedDocument2TableDAO.listAttachedDocument2Table("WAMAT_OBJECT", list , null,true))
	 {
		 item.getId().getFkTableName();
	 }
 }
 @Test
 public void listAttachedDocument2TableTest2()
 {
	 List<AttachedDocument2Table> list = new ArrayList<AttachedDocument2Table>();
	 AttachedDocument2TableId attachedDocument2TableId = new AttachedDocument2TableId();
	 attachedDocument2TableId.setFkTableName("WAMAT_OBJECT");
	 attachedDocument2TableId.setFkTableId(161);
	 attachedDocument2TableId.setFkAttachedDocument(1);
	 list.add(attachedDocument2TableDAO.getAttachedDocument2Table(attachedDocument2TableId));
	 AttachedDocument2TableId attachedDocument2TableId2 = new AttachedDocument2TableId();
	 attachedDocument2TableId2.setFkTableName("ITEM");
	 attachedDocument2TableId2.setFkTableId(161);
	 attachedDocument2TableId2.setFkAttachedDocument(2);
	 list.add(attachedDocument2TableDAO.getAttachedDocument2Table(attachedDocument2TableId2));
	 for (AttachedDocument2Table item : attachedDocument2TableDAO.listAttachedDocument2Table(list,null,null,null,true))
	 {
		 item.getId().getFkTableName();
	 }
 }
}
