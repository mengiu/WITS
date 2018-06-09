package org.persistence.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.DocumentTypeStDAO;
import org.persistence.layer.wits.form.DocumentTypeSt;
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
public class DocumentTypeStTest {
  @Autowired
  DocumentTypeStDAO documentTypeStDAO;
  
  @Test
  public void listDocumentTypeStTest()
  {
	  for (DocumentTypeSt item : documentTypeStDAO.listDocumentTypeSt())
	  {
		  item.getNameDocumentTypeSt();
	  }
  }

  @Test
  public void getDocumentTypeStTest()
  {
	  DocumentTypeSt documentTypeSt = documentTypeStDAO.getDocumentTypeSt("png");
	  if (documentTypeSt!=null)
	  {
		  documentTypeSt.getNameDocumentTypeSt();
	  }
  }

}
