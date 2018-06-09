package org.front.end.wits.vaadin.listener;

import java.util.Date;

import javax.persistence.Table;

import org.front.end.wits.vaadin.WitsApp;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.persistence.layer.wits.dao.to.CurrentWorkingWorkflowInfo;
import org.persistence.layer.wits.dao.to.impl.AttachedDocument2TableTOImpl;
import org.persistence.layer.wits.dao.to.impl.ItemTOImpl;
import org.persistence.layer.wits.dao.to.impl.WacComplianceClaimTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatExtendedFieldTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatObjectTOImpl;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.form.AttachedDocument2Table;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.LoggingEventWorkflow;
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

@Component
@Scope("singleton")
public class MyPostInsertEventListener implements PostInsertEventListener {
	static final Logger logger = LoggerFactory.getLogger(MyPostUpdateEventListener.class);
	@Autowired
	@Qualifier("loggingEventWorkflowService")
	LoggingEventWorkflowService loggingEventWorkflowService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onPostInsert(PostInsertEvent event) {
		logger.debug("Entering MyPostInsertListener");
		Object objectTO = null;
		Class<?> typeClass = null;
		Integer idEntity = null;
		String entityName = null;
		String entityEventType = EntityEventType.POST_INSERT.getValue();
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
		if (event.getEntity() instanceof WacComplianceClaim )
		{
			WacComplianceClaim entity = (WacComplianceClaim)event.getEntity();
			idEntity = new Integer(entity.getWamatObject().getIdWamatObject());
			entityName = WacComplianceClaim.class.getAnnotation(Table.class).name();
			objectTO = new WacComplianceClaimTOImpl(entity,
					WitsApp.get().getViewMetadataColumnTableCache().loadTableDescriptions(entityName));
			typeClass = WacComplianceClaimTOImpl.class;
		}
		if (event.getEntity() instanceof WamatExtendedField )
		{
			WamatExtendedField entity = (WamatExtendedField)event.getEntity();
			idEntity = new Integer(entity.getWamatObject().getIdWamatObject());
			entityName = WamatExtendedField.class.getAnnotation(Table.class).name();
			objectTO = new WamatExtendedFieldTOImpl(entity);
			typeClass = WamatExtendedFieldTOImpl.class;
		}
		if (event.getEntity() instanceof AttachedDocument2Table )
		{
			AttachedDocument2Table entity = (AttachedDocument2Table)event.getEntity();
			idEntity = entity.getId().getFkTableId();
			entityName = entity.getId().getFkTableName();
			objectTO = new AttachedDocument2TableTOImpl(entity,
					EntityEventType.POST_INSERT,
					WitsApp.get().getViewMetadataColumnTableCache().loadTableDescriptions(AttachedDocument2Table.class.getAnnotation(Table.class).name()));
			typeClass = AttachedDocument2TableTOImpl.class;
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
	public void insertRecordLog(String entityEventType , 
			Integer entityId , String entityValue , Object objectTO , Class<?> typeClass,
			LoggingEventWorkflow loggingEventWorkflow ) {

		Date currentDate = new Date();
		loggingEventWorkflow.setTimestmp(currentDate);
		loggingEventWorkflow.setLoggerName(logger.getName());
		loggingEventWorkflow.setEntityEventType(entityEventType);
		loggingEventWorkflow.setEntityId(entityId);
		loggingEventWorkflow.setEntityValue(entityValue);
		loggingEventWorkflow.setCallerMethod("MyPostInsertListener");
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
