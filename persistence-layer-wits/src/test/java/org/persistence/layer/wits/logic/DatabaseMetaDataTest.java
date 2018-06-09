package org.persistence.layer.wits.logic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.MyTableMetaData;
import org.persistence.layer.wits.dao.DatabaseMetaDataDAO;
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
public class DatabaseMetaDataTest {
    @Autowired
    DatabaseMetaDataDAO databaseMetaDataDAO;
	@Test
	public void getTableMetaDataTest(){
		databaseMetaDataDAO.getTableMetaData("WAMAT_OBJECT");
    }
}
