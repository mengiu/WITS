package org.services.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.services.layer.wits.services.DatabaseMetaDataService;
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
public class DatabaseMetaDataServiceTest {
  @Autowired
  @Qualifier("databaseMetaDataService")
  DatabaseMetaDataService databaseMetaDataService;
  @Test
  public void getTableMetaData()
  {
	  databaseMetaDataService.getTableMetaData("");
  }
}
