package org.front.end.wits.vaadin.listener;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Table;

import org.front.end.wits.vaadin.WitsApp;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.persistence.layer.wits.dao.LoggingEventWorkflowDAO;
import org.persistence.layer.wits.dao.ViewMetadataColumnTableDAO;
import org.persistence.layer.wits.dao.to.AttachedDocument2TableDifferencesTO;
import org.persistence.layer.wits.dao.to.CurrentWorkingWorkflowInfo;
import org.persistence.layer.wits.dao.to.ItemDifferencesTO;
import org.persistence.layer.wits.dao.to.WacComplianceClaimDifferencesTO;
import org.persistence.layer.wits.dao.to.WamatExtendedFieldDifferencesTO;
import org.persistence.layer.wits.dao.to.WamatObjectDifferencesTO;
import org.persistence.layer.wits.dao.to.impl.AttachedDocument2TableDifferencesTOImpl;
import org.persistence.layer.wits.dao.to.impl.AttachedDocument2TableTOImpl;
import org.persistence.layer.wits.dao.to.impl.ItemDifferencesTOImpl;
import org.persistence.layer.wits.dao.to.impl.ItemTOImpl;
import org.persistence.layer.wits.dao.to.impl.WacComplianceClaimDifferencesTOImpl;
import org.persistence.layer.wits.dao.to.impl.WacComplianceClaimTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatExtendedFieldDifferencesTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatExtendedFieldTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatObjectDifferencesTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatObjectTOImpl;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
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
public class MyPostUpdateEventListener implements PostUpdateEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final Logger logger = LoggerFactory.getLogger(MyPostUpdateEventListener.class);
	@Autowired
	@Qualifier("loggingEventWorkflowService")
	LoggingEventWorkflowService loggingEventWorkflowService;

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		logger.debug("Entering MyPostUpdateListener");
		Integer idEntity = null;
		String entityName = null;
		Object objectTO = null;
		Class<?> typeClass = null;
		String entityEventType = EntityEventType.POST_UPDATE.getValue();
		LoggingEventWorkflow loggingEventWorkflow = new LoggingEventWorkflow();
		if (event.getEntity() instanceof WamatObject )
		{
			WamatObject entity = (WamatObject)event.getEntity();
			idEntity = new Integer(entity.getIdWamatObject());
			WamatObject entityBeforeUpdate = new WamatObject();
			final Object[] oldValues = event.getOldState();
			final String[] properties = event.getPersister().getPropertyNames();
			for (int index=0;index<oldValues.length;index++)
			{
				if (event.getOldState()[index]!=null)
				{
					trySetUsingSetter(entityBeforeUpdate,properties[index],(event.getOldState()[index]));
				}

			}
			entityBeforeUpdate.setIdWamatObject(idEntity);
			WamatObjectDifferencesTO wamatObjectDifferencesTO = 
					new WamatObjectDifferencesTOImpl(entityBeforeUpdate,entity);
			entityName = WamatObject.class.getAnnotation(Table.class).name();
			objectTO = new WamatObjectTOImpl(wamatObjectDifferencesTO.getEntitywithChanging(),
					WitsApp.get().getViewMetadataColumnTableCache().loadTableDescriptions(entityName));
			typeClass = WamatObjectTOImpl.class;
		}
		if (event.getEntity() instanceof Item )
		{
			Item entity = (Item)event.getEntity();
			idEntity = new Integer(entity.getWamatObject().getIdWamatObject());
			entityName = Item.class.getAnnotation(Table.class).name();
			Item itemBeforeUpdate = new Item();
			final Object[] oldValues = event.getOldState();
			final String[] properties = event.getPersister().getPropertyNames();
			for (int index=0;index<oldValues.length;index++)
			{
				if (event.getOldState()[index]!=null)
				{
					trySetUsingSetter(itemBeforeUpdate,properties[index],(event.getOldState()[index]));
				}

			}
			itemBeforeUpdate.setIdItem(entity.getIdItem());
			ItemDifferencesTO itemDifferencesTO = 
					new ItemDifferencesTOImpl(itemBeforeUpdate,entity);
			objectTO = new ItemTOImpl(itemDifferencesTO.getItemwithChanging(),
					WitsApp.get().getViewMetadataColumnTableCache().loadTableDescriptions(entityName));
			typeClass = ItemTOImpl.class;
		}
		if (event.getEntity() instanceof WacComplianceClaim )
		{
			WacComplianceClaim entity = (WacComplianceClaim)event.getEntity();
			idEntity = new Integer(entity.getWamatObject().getIdWamatObject());
			entityName = WacComplianceClaim.class.getAnnotation(Table.class).name();
			WacComplianceClaim wacComplianceClaimBefore = new WacComplianceClaim();
			final Object[] oldValues = event.getOldState();
			final String[] properties = event.getPersister().getPropertyNames();
			for (int index=0;index<oldValues.length;index++)
			{
				if (event.getOldState()[index]!=null)
				{
					trySetUsingSetter(wacComplianceClaimBefore,properties[index],(event.getOldState()[index]));
				}

			}
			wacComplianceClaimBefore.setIdWacComplianceClaim(entity.getIdWacComplianceClaim());
			WacComplianceClaimDifferencesTO wacComplianceClaimDifferencesTO =
					new WacComplianceClaimDifferencesTOImpl(wacComplianceClaimBefore,entity);
			objectTO = new WacComplianceClaimTOImpl(wacComplianceClaimDifferencesTO.getWacComplianceClaimBeforeChanging(),
					WitsApp.get().getViewMetadataColumnTableCache().loadTableDescriptions(entityName));
			typeClass = WacComplianceClaimTOImpl.class;
		}
		if (event.getEntity() instanceof WamatExtendedField )
		{
			WamatExtendedField entity = (WamatExtendedField)event.getEntity();
			idEntity = new Integer(entity.getWamatObject().getIdWamatObject());
			entityName = WamatExtendedField.class.getAnnotation(Table.class).name();
			WamatExtendedField wamatExtendedFieldBefore = new WamatExtendedField();
			final Object[] oldValues = event.getOldState();
			final String[] properties = event.getPersister().getPropertyNames();
			for (int index=0;index<oldValues.length;index++)
			{
				if (event.getOldState()[index]!=null)
				{
					trySetUsingSetter(wamatExtendedFieldBefore,properties[index],(event.getOldState()[index]));
				}

			}
			wamatExtendedFieldBefore.setIdWamatExtendedField(entity.getIdWamatExtendedField());
			WamatExtendedFieldDifferencesTO wamatExtendedFieldDifferencesTO = 
					new WamatExtendedFieldDifferencesTOImpl(wamatExtendedFieldBefore,entity);
			objectTO = new WamatExtendedFieldTOImpl(wamatExtendedFieldDifferencesTO.getWamatExtendedFieldChanging());
			typeClass = WamatExtendedFieldTOImpl.class;
		}
		if (event.getEntity() instanceof AttachedDocument2Table )
		{
			AttachedDocument2Table entity = (AttachedDocument2Table)event.getEntity();
			idEntity = entity.getId().getFkTableId();
			entityName = entity.getId().getFkTableName();
			AttachedDocument2Table attachedDocument2TableBefore = new AttachedDocument2Table();
			final Object[] oldValues = event.getOldState();
			final String[] properties = event.getPersister().getPropertyNames();
			for (int index=0;index<oldValues.length;index++)
			{
				if (event.getOldState()[index]!=null)
				{
					trySetUsingSetter(attachedDocument2TableBefore,properties[index],(event.getOldState()[index]));
				}

			}
			attachedDocument2TableBefore.setId(entity.getId());
			AttachedDocument2TableDifferencesTO attachedDocument2TableDifferencesTO =
					new AttachedDocument2TableDifferencesTOImpl(attachedDocument2TableBefore,entity);
			objectTO = new AttachedDocument2TableTOImpl(attachedDocument2TableDifferencesTO.getEntitywithChanging(),
					EntityEventType.POST_UPDATE,
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
		loggingEventWorkflow.setCallerMethod("MyPostUpdateListener");
		XmlUtil xmlUtil = new XmlUtil();
		String xml = xmlUtil.convertToXml(objectTO, typeClass);
		loggingEventWorkflow.setFormattedMessage(xml);
		loggingEventWorkflow.setWitsUserSt(WitsAuthentication.getAuthenticatedWitsUserStThreadLocal());
		loggingEventWorkflowService.addLoggingEventWorkflow(loggingEventWorkflow);

	}
	/**
	 * Finds the setter for a property and sets the value of the property using
	 * the found setter method.
	 * 
	 * @param entity
	 *            the entity containing the property to set the value for.
	 * @param propertyName
	 *            the name of the property.
	 * @param value
	 *            the new value of the property.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <E> void setUsingSetter(E entity, String propertyName, Object value)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException,
			SecurityException, NoSuchMethodException {
		Method setter = findSetterFor(entity, propertyName);
		if (setter != null) {
			Class<?> parameterType = setter.getParameterTypes()[0];
			if (Collection.class.isAssignableFrom(parameterType)) {
				if (Set.class.isAssignableFrom(parameterType)) {
					value = new HashSet((Collection) value);
				}
			} else if (value instanceof Collection) {
				// "Unwrap" the value from the collection, since the setter
				// doesn't accept collections.
				value = ((Collection) value).iterator().next();
			}
			setter.invoke(entity, value);
		}
	}
	private <E> Method findSetterFor(E entity, String propertyName) {
		for (Method m : entity.getClass().getMethods()) {
			if (m.getName().equalsIgnoreCase("set" + propertyName)) {
				return m;
			}
		}
		return null;
	}
	private <E> void trySetUsingSetter(E entity, String propertyName,
			Object value) {
		try {
			setUsingSetter(entity, propertyName, value);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(
					"Could not set lazy loaded value for entity.", e);
		} catch (SecurityException e) {
			throw new RuntimeException(
					"Could not set lazy loaded value for entity.", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Could not set lazy loaded value for entity.", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(
					"Could not set lazy loaded value for entity.", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Could not set lazy loaded value for entity.", e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(
					"Could not set lazy loaded value for entity.", e);
		}
	}

}
