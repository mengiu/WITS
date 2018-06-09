package org.persistence.layer.wits.logic;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.LoggingEventWorkflowDAO;
import org.persistence.layer.wits.dao.WamatObjectDAO;
import org.persistence.layer.wits.dao.WitsUserStDAO;
import org.persistence.layer.wits.dao.to.WamatObjectTO;
import org.persistence.layer.wits.dao.to.impl.WamatObjectDifferencesTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatObjectTOImpl;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.form.LoggingEventWorkflow;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.form.WitsUserSt;
import org.persistence.layer.wits.util.XmlUtil;
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
public class LoggingEventWorkflowTest {
	@Autowired
	LoggingEventWorkflowDAO loggingEventWorkflowDAO;
	@Autowired
	WitsUserStDAO  witsUserStDAO;
	@Autowired
	WamatObjectDAO  wamatObjectDAO;

	@Test
	public void LoggingEventWorkflowDAOTest()
	{
		LoggingEventWorkflow loggingEventWorkflow = new LoggingEventWorkflow();
		Date pippo = new Date();
		loggingEventWorkflow.setTimestmp(pippo);
		loggingEventWorkflow.setWitsUserSt(witsUserStDAO.getWitsUserSt(0));
		loggingEventWorkflowDAO.addLoggingEventWorkflow(loggingEventWorkflow);
	}

	@Test
	public void getLoggingEventWorkflowTest()
	{
		WamatObject wamatObject = wamatObjectDAO.getWamatObject(161);
		LoggingEventWorkflow loggingEventWorkflowBefore = loggingEventWorkflowDAO.getLoggingEventWorkflow(wamatObject, WamatObject.class, 
				EntityEventType.PRE_UPDATE);
		WamatObjectTOImpl wamatObjectTOImplBefore = null;
		WamatObjectTOImpl wamatObjectTOImplAfter = null;
		if (loggingEventWorkflowBefore!=null)
		{
			XmlUtil xmlUtil = new XmlUtil();
			wamatObjectTOImplBefore = (WamatObjectTOImpl)xmlUtil.convertToObject(loggingEventWorkflowBefore.getFormattedMessage(), 
					WamatObjectTOImpl.class);
			//WamatObjectTOImpl WamatObjectTOImpl = new WamatObjectTOImpl(loggingEventWorkflow.);
		}
		LoggingEventWorkflow loggingEventWorkflowAfter = loggingEventWorkflowDAO.getLoggingEventWorkflow(wamatObject, WamatObject.class, 
				EntityEventType.POST_UPDATE);
		if (loggingEventWorkflowAfter!=null)
		{
			XmlUtil xmlUtil = new XmlUtil();
			wamatObjectTOImplAfter = (WamatObjectTOImpl)xmlUtil.convertToObject(loggingEventWorkflowAfter.getFormattedMessage(), 
					WamatObjectTOImpl.class);
			//WamatObjectTOImpl WamatObjectTOImpl = new WamatObjectTOImpl(loggingEventWorkflow.);
		}
		/*WamatObjectDifferencesTOImpl wamatObjectDifferencesTOImpl = new  
				WamatObjectDifferencesTOImpl(wamatObjectTOImplBefore,wamatObjectTOImplAfter,
						loggingEventWorkflowAfter.getWitsUserSt().getActIdUser().getId(),
						loggingEventWorkflowAfter.getTimestmp(),
						loggingEventWorkflowAfter.getWorkflowId());
		wamatObjectDifferencesTOImpl.getIsContainingUnitchanged();*/
	}
	@Test
	public void getLoggingEventWorkflowTest2()
	{
		WamatObject wamatObject = wamatObjectDAO.getWamatObject(204);
		LoggingEventWorkflow loggingEventWorkflow = loggingEventWorkflowDAO.getLoggingEventWorkflow(wamatObject, WamatObject.class );
		System.out.println(loggingEventWorkflow.getTimestmp().toString());
	}
}
