package org.persistence.layer.wits.logic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.ViewMetadataColumnTableDAO;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;
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
public class ViewMetadataColumnTableDAOTest {
 @Autowired
 ViewMetadataColumnTableDAO viewMetadataColumnTableDAO;
 
 @Test
 public void listViewMetadataColumnTableTest()
 {
	 //viewMetadataColumnTableDAO.executeProcedureSET_VALUES("WAMAT_OBJECT");
	 //Object tableName = viewMetadataColumnTableDAO.executeFunctionGET_TABLENAME();
	 List<ViewMetadataColumnTable> listViewMetadataColumnTable = viewMetadataColumnTableDAO.listViewMetadataColumnTable("WAMAT_OBJECT");
	 for (ViewMetadataColumnTable item : listViewMetadataColumnTable)
	 {
		 System.out.println(item.getColumnName());
		 if (item.getComments()!=null)
		  System.out.println(item.getComments());
	 }
	 
 }
 
 @Test
 public void getInfoMetadataColumnTableTest()
 {
	 for (ViewMetadataColumnTable item : viewMetadataColumnTableDAO.getInfoMetadataColumnTable("WAMAT_OBJECT"))
	 {
		 System.out.println(item.getColumnName());
		 System.out.println(item.getComments());
		 System.out.println(item.getDataType());
		 System.out.println(item.getNullStatus());
	 }
 }
}
