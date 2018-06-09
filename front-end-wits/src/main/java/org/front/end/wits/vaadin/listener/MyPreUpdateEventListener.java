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
import org.front.end.wits.vaadin.cache.ViewMetadataColumnTableCache;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.persistence.layer.wits.dao.ItemDAO;
import org.persistence.layer.wits.dao.LoggingEventWorkflowDAO;
import org.persistence.layer.wits.dao.ViewMetadataColumnTableDAO;
import org.persistence.layer.wits.dao.WamatExtendedFieldDAO;
import org.persistence.layer.wits.dao.WamatObjectDAO;
import org.persistence.layer.wits.dao.WitsUserStDAO;
import org.persistence.layer.wits.dao.to.CurrentWorkingWorkflowInfo;
import org.persistence.layer.wits.dao.to.impl.ItemTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatExtendedFieldTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatObjectTOImpl;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.LoggingEventWorkflow;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;
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
public class MyPreUpdateEventListener implements PreUpdateEventListener {
	@Autowired
	@Qualifier("loggingEventWorkflowService")
	LoggingEventWorkflowService loggingEventWorkflowService;
	
	static final Logger logger = LoggerFactory.getLogger(MyPreUpdateEventListener.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		logger.debug("Entering MyPreUpdateEventListener");
		Integer idEntity = null;
		String entityName = null;
		Object objectTO = null;
		Class<?> typeClass = null;
		String entityEventType = EntityEventType.PRE_UPDATE.getValue();
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
			entityName = WamatObject.class.getAnnotation(Table.class).name();
			objectTO = new WamatObjectTOImpl(entityBeforeUpdate,
					WitsApp.get().getViewMetadataColumnTableCache().loadTableDescriptions(entityName));
			typeClass = WamatObjectTOImpl.class;
		}
		if (event.getEntity() instanceof Item )
		{
			Item entity = (Item)event.getEntity();
			idEntity = new Integer(entity.getWamatObject().getIdWamatObject());
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
			entityName = Item.class.getAnnotation(Table.class).name();
			objectTO = new ItemTOImpl(itemBeforeUpdate,
					WitsApp.get().getViewMetadataColumnTableCache().loadTableDescriptions(entityName));
			typeClass = ItemTOImpl.class;
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
			objectTO = new WamatExtendedFieldTOImpl(wamatExtendedFieldBefore);
			typeClass = WamatExtendedFieldTOImpl.class;
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
		return false;
	}
	private void insertRecordLog(String entityEventType , 
			Integer entityId , String entityValue , Object objectTO , Class<?> typeClass,
			LoggingEventWorkflow loggingEventWorkflow ) {


		Date currentDate = new Date();
		loggingEventWorkflow.setTimestmp(currentDate);
		loggingEventWorkflow.setLoggerName(logger.getName());
		loggingEventWorkflow.setEntityEventType(entityEventType);
		loggingEventWorkflow.setEntityId(entityId);
		loggingEventWorkflow.setEntityValue(entityValue);
		loggingEventWorkflow.setCallerMethod("MyPreUpdateEventListener");
		XmlUtil xmlUtil = new XmlUtil();
		String xml = xmlUtil.convertToXml(objectTO, typeClass);
		loggingEventWorkflow.setFormattedMessage(xml);
		String userId = WitsAuthentication.getAuthenticatedUserId();
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
