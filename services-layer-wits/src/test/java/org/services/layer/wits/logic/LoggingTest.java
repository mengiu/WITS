package org.services.layer.wits.logic;

import java.awt.Color;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.services.layer.wits.services.WamatObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class LoggingTest {
 @Autowired
 TestPojo testPojo;
 @Autowired
 @Qualifier("wamatObjectService")
 WamatObjectService wamatObjectService;

 @Test
 public void testAOPLogging()
 {
	 //Logger logger = LoggerFactory.getLogger(LoggingTest.class);
     
     /*testPojo.setFirstName("Richard");
     testPojo.setLastName("Milhouse");
     testPojo.setAge(69);
     testPojo.getFirstName();
	 testPojo.getLastName();*/
	 testPojo.setWamatObject(wamatObjectService.getWamatObject(1));
     //logger.debug(testPojo.getFirstName());
     //logger.debug(testPojo.getLastName());	 
 }
}
