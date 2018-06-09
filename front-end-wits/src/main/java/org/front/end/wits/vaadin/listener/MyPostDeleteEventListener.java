package org.front.end.wits.vaadin.listener;


import java.util.Date;
import java.util.List;

import javax.persistence.Table;

import org.front.end.wits.vaadin.WitsApp;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.persistence.layer.wits.dao.LoggingEventWorkflowDAO;
import org.persistence.layer.wits.dao.ViewMetadataColumnTableDAO;
import org.persistence.layer.wits.dao.to.CurrentWorkingWorkflowInfo;
import org.persistence.layer.wits.dao.to.impl.AttachedDocument2TableTOImpl;
import org.persistence.layer.wits.dao.to.impl.ItemTOImpl;
import org.persistence.layer.wits.dao.to.impl.WacComplianceClaimTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatExtendedFieldTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatObjectTOImpl;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.enumusertypes.WITSEventType;
import org.persistence.layer.wits.form.AttachedDocument2Table;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.LoggingEventWorkflow;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;
import org.persistence.layer.wits.form.WacComplianceClaim;
import org.persistence.layer.wits.form.WamatExtendedField;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.identity.WitsAuthentication;
import org.persistence.layer.wits.util.XmlUtil;
import org.services.layer.wits.services.LoggingEventWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope("singleton")
public class MyPostDeleteEventListener implements PostDeleteEventListener {
	@Autowired
	@Qualifier("loggingEventWorkflowService")
	LoggingEventWorkflowService loggingEventWorkflowService;
	
	static final Logger logger = LoggerFactory.getLogger(MyPostDeleteEventListener.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		logger.debug("Entering MyPostDeleteListener");
		Integer idEntity = null;
		String entityName = null;
		Object objectTO = null;
		Class<?> typeClass = null;
		String entityEventType = EntityEventType.POST_DELETE.getValue();
		/*if (event.getEntity() instanceof ContainingUnit )
		{
			ContainingUnit entity = (ContainingUnit)event.getEntity();
			idEntity = new Integer(entity.getIdContainingUnit());
			entityName = "ContainingUnit";

		}*/
		LoggingEventWorkflow loggingEventWorkflow = new LoggingEventWorkflow();
		if (event.getEntity() instanceof WamatObject )
		{
			WamatObject entity = (WamatObject)event.getEntity();
			idEntity = new Integer(entity.getIdWamatObject());
			entityName = WamatObject.class.getAnnotation(Table.class).name();
			objectTO = new WamatObjectTOImpl(entity,
					WitsApp.get().getViewMetadataColumnTableCache().loadTableDescriptions(entityName));
			typeClass = WamatObjectTOImpl.class;
		}
		if (event.getEntity() instanceof Item )
		{
			Item entity = (Item)event.getEntity();
			idEntity = new Integer(entity.getWamatObject().getIdWamatObject());
			entityName = Item.class.getAnnotation(Table.class).name();
			objectTO = new ItemTOImpl(entity,
					WitsApp.get().getViewMetadataColumnTableCache().loadTableDescriptions(entityName));
			typeClass = ItemTOImpl.class;
		}
		if (event.getEntity() instanceof WamatExtendedField )
		{
			WamatExtendedField entity = (WamatExtendedField)event.getEntity();
			idEntity = new Integer(entity.getWamatObject().getIdWamatObject());
			entityName = WamatExtendedField.class.getAnnotation(Table.class).name();
			objectTO = new WamatExtendedFieldTOImpl(entity);
			typeClass = WamatExtendedFieldTOImpl.class;
		}
		if (event.getEntity() instanceof WacComplianceClaim )
		{
			WacComplianceClaim entity = (WacComplianceClaim)event.getEntity();
			idEntity = new Integer(entity.getWamatObject().getIdWamatObject());
			entityName = WacComplianceClaim.class.getAnnotation(Table.class).name();
			objectTO = new WacComplianceClaimTOImpl(entity,
					WitsApp.get().getViewMetadataColumnTableCache().loadTableDescriptions(entityName));
			typeClass = WacComplianceClaimTOImpl.class;
		}
		CurrentWorkingWorkflowInfo currentWorkingWorkflowInfo = WitsAuthentication.getCurrentWorkingWorkflowInfo();
		if (currentWorkingWorkflowInfo!=null)
		{
			loggingEventWorkflow.setTaskId(currentWorkingWorkflowInfo.getActivitiTaskId());
			loggingEventWorkflow.setExecutionId(currentWorkingWorkflowInfo.getExecutionId());
			loggingEventWorkflow.setWorkflowId(currentWorkingWorkflowInfo.getProcessInstanceId());
		}
		if (idEntity!=null)
		{
			insertRecordLog(entityEventType,idEntity,entityName,objectTO , typeClass,
					loggingEventWorkflow );
		}
	}
	private void insertRecordLog(String entityEventType , 
			Integer entityId , String entityValue , Object objectTO , Class<?> typeClass ,
			LoggingEventWorkflow loggingEventWorkflow ) {

		Date currentDate = new Date();
		loggingEventWorkflow.setTimestmp(currentDate);
		loggingEventWorkflow.setLoggerName(logger.getName());
		loggingEventWorkflow.setEntityEventType(entityEventType);
		loggingEventWorkflow.setEntityId(entityId);
		loggingEventWorkflow.setEntityValue(entityValue);
		loggingEventWorkflow.setCallerMethod("MyPostDeleteListener");
		XmlUtil xmlUtil = new XmlUtil();
		String xml = xmlUtil.convertToXml(objectTO, typeClass);
		loggingEventWorkflow.setFormattedMessage(xml);
		loggingEventWorkflow.setWitsUserSt(WitsAuthentication.getAuthenticatedWitsUserStThreadLocal());
		loggingEventWorkflowService.addLoggingEventWorkflow(loggingEventWorkflow);

	}
	/*@Override
	public boolean requiresPostCommitHanding(EntityPersister arg0) {
		// TODO Auto-generated method stub
		return false;
	}*/


}
