package org.persistence.layer.wits.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.LoggingEventWorkflowDAO;
import org.persistence.layer.wits.dao.to.impl.AttachedDocument2TableTOImpl;
import org.persistence.layer.wits.dao.to.impl.ItemTOImpl;
import org.persistence.layer.wits.dao.to.impl.WacComplianceClaimTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatExtendedFieldTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatObjectTOImpl;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.enumusertypes.MonitoredTablesType;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.LoggingEventWorkflow;
import org.persistence.layer.wits.form.WacComplianceClaim;
import org.persistence.layer.wits.form.WamatExtendedField;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.form.WitsUserSt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class LoggingEventWorkflowDAOImpl implements LoggingEventWorkflowDAO {
	private static Logger logger = LoggerFactory.getLogger(LoggingEventWorkflowDAOImpl.class);

	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public long addLoggingEventWorkflow(LoggingEventWorkflow lew) {
		em.persist(lew);
		return lew.getEventId();
	}

	@Override
	public List<LoggingEventWorkflow> listLoggingEventWorkflow() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<LoggingEventWorkflow> criteria = builder.createQuery(LoggingEventWorkflow.class);
		Root<LoggingEventWorkflow> lew = criteria.from(LoggingEventWorkflow.class);
		criteria.select(lew);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeLoggingEventWorkflow(long id) {
		LoggingEventWorkflow lew = em.find(LoggingEventWorkflow.class, id);
		if (lew!=null)
		{
			em.remove(lew);
		}

	}

	@Override
	public LoggingEventWorkflow getLoggingEventWorkflow(long id) {
		return em.find(LoggingEventWorkflow.class, id);
	}

	@Override
	public void updateLoggingEventWorkflow(LoggingEventWorkflow lew) {
		em.merge(lew);

	}
	@Override
	public LoggingEventWorkflow getLoggingEventWorkflow(Object entity , Class<?> classEntity ,
			EntityEventType entityEventType ) {
		Object resulObject = null;   
		CriteriaBuilder builder = em.getCriteriaBuilder();
		//CriteriaQuery<LoggingEventWorkflow> criteria = builder.createQuery(LoggingEventWorkflow.class);
		//CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
		CriteriaQuery<Object> criteria = builder.createQuery();
		Root<LoggingEventWorkflow> lew = criteria.from(LoggingEventWorkflow.class);
		ParameterExpression<Integer> pidEntity = builder.parameter(Integer.class);
		ParameterExpression<String> pEntityName = builder.parameter(String.class);
		ParameterExpression<String> pEventType = builder.parameter(String.class);
		Predicate predicate1 = null,predicate2 = null,predicate3 = null;
		Integer idEntity = 0;
		String strClassEntity = "";
		predicate1 = builder.equal(lew.get("entityId"), pidEntity);
		predicate2 = builder.equal(lew.get("entityValue"), pEntityName);
		predicate3 = builder.equal(lew.get("entityEventType"), pEventType);
		if (classEntity.equals(WamatObject.class))
		{
			idEntity = ((WamatObject)entity).getIdWamatObject();
			strClassEntity = WamatObject.class.getAnnotation(Table.class).name();
		}
		if (classEntity.equals(Item.class))
		{
			idEntity = ((Item)entity).getIdItem();
			strClassEntity = Item.class.getAnnotation(Table.class).name();
		}
		if (entityEventType.equals(EntityEventType.PRE_UPDATE))
		{
			Expression<Date> select = lew.<Date>get("timestmp");
			Expression<Date> selectMin = builder.least(select);
			//Expression<Date> fetchDate = r.get("fetchDate").as(Date.class);

			//predicate4 = builder.equal(lew.get("timestmp").as(Date.class), selectMin);
			List<Expression<?>> listGrouping = new ArrayList<Expression<?>>();
			listGrouping.add(lew.get("timestmp"));
			listGrouping.add(lew.get("eventId"));
			criteria.multiselect(selectMin , lew.get("eventId")).
			where(builder.and(predicate1,predicate2,predicate3)).
			groupBy(listGrouping);

		}
		if (entityEventType.equals(EntityEventType.POST_UPDATE))
		{
			Expression<Date> select = lew.<Date>get("timestmp");
			Expression<Date> selectMax = builder.greatest(select);
			List<Expression<?>> listGrouping = new ArrayList<Expression<?>>();
			listGrouping.add(lew.get("timestmp"));
			listGrouping.add(lew.get("eventId"));
			criteria.multiselect(selectMax , lew.get("eventId")).
			where(builder.and(predicate1,predicate2,predicate3)).
			groupBy(listGrouping);
		}

		try {
			resulObject = em.createQuery(criteria).
					setParameter(pidEntity, idEntity).
					setParameter(pEntityName, strClassEntity).
					setParameter(pEventType, entityEventType.getValue()).
					getSingleResult();
			if (resulObject!=null)
			{
				Object[] resultListObject = (Object[])resulObject;
				return em.find(LoggingEventWorkflow.class, (Long)resultListObject[1]);
			}
			/*String sqlString = "SELECT MIN(timestmp) AS MINIMO , EVENT_ID FROM LOGGING_EVENT_WORKFLOW  WHERE ( ENTITY_ID = :pidEntity " +
						"AND ENTITY_VALUE= :pEntityName AND ENTITY_EVENT_TYPE= :pEventType ) GROUP BY (timestmp) , EVENT_ID"; 
	        	em.createQuery(sqlString).
	        	setParameter("pidEntity", idEntity).
				setParameter("pEntityName", WamatObject.class.getAnnotation(Table.class).name()).
				setParameter("pEventType", entityEventType.getValue()).
	        	getSingleResult();*/

		} catch (Exception e) {
			logger.debug("getLoggingEventWorkflow(Integer idEntity : " + idEntity + 
					" , String pEntityName : " + strClassEntity + 
					" , EntityEventType entityEventType : " + entityEventType.getValue() + 
					" ) : " + e.getMessage());
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LoggingEventWorkflow> getLoggingEventWorkflowChanging(List<Object> listEntity , 
			Class<?> classEntity ,
			WitsUserSt witsUserSt , Date dateFrom , Date dateTo ,
			MonitoredTablesType monitoredTablesType,
			List<EntityEventType> listEntityEventType ) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> criteria = builder.createQuery();
		Root<LoggingEventWorkflow> lew = criteria.from(LoggingEventWorkflow.class);
		Integer idEntity = 0;
		String strClassEntity = "";
		Map<Integer, Predicate> mapPredicateParameters = new HashMap<Integer, Predicate>();
		Map<Integer, Map<ParameterExpression<?>, Object>> mapParameters = new HashMap<Integer, Map<ParameterExpression<?>, Object>>();
		int indexParam = 0;
		int indexPredicate = 0;
		if (classEntity.equals(WacComplianceClaim.class))
		{
			strClassEntity = WacComplianceClaim.class.getAnnotation(Table.class).name();
		}
		if (classEntity.equals(WamatObject.class))
		{
			strClassEntity = WamatObject.class.getAnnotation(Table.class).name();
		}
		if (classEntity.equals(Item.class))
		{
			strClassEntity = Item.class.getAnnotation(Table.class).name();
		}
		if (classEntity.equals(WamatExtendedField.class))
		{
			strClassEntity = WamatExtendedField.class.getAnnotation(Table.class).name();
		}
		{
			idEntity = ((WamatObject)listEntity.get(0)).getIdWamatObject();
			ParameterExpression<Integer> pidEntity = builder.parameter(Integer.class);
			Predicate predicate1 = builder.equal(lew.get("entityId"), pidEntity);
			mapPredicateParameters.put(new Integer(indexPredicate++), predicate1);
			Map<ParameterExpression<?>, Object> mapNameValueParameter = new HashMap<ParameterExpression<?>, Object>();
			mapNameValueParameter.put(pidEntity, idEntity);
			mapParameters.put(new Integer(indexParam++), mapNameValueParameter);
		}
		{
			ParameterExpression<String> pEntityName = builder.parameter(String.class);
			Predicate predicate2 = builder.equal(lew.get("entityValue"), pEntityName);
			mapPredicateParameters.put(new Integer(indexPredicate++), predicate2);
			Map<ParameterExpression<?>, Object> mapNameValueParameter = new HashMap<ParameterExpression<?>, Object>();
			mapNameValueParameter.put(pEntityName, strClassEntity);
			mapParameters.put(new Integer(indexParam++), mapNameValueParameter);
		}
		{
			int index = 0;
			Predicate[] listPredicate = new Predicate[listEntityEventType.size()];
			for (EntityEventType item : listEntityEventType)
			{
				listPredicate[index++] =  builder.equal(lew.get("entityEventType"),item.getValue());
			}
			Predicate predicate = builder.or(listPredicate);
			mapPredicateParameters.put(new Integer(indexPredicate++), predicate);
		}
		if (witsUserSt!=null)
		{
			ParameterExpression<WitsUserSt> pWitsUserSt = builder.parameter(WitsUserSt.class);
			Predicate predicate = builder.equal(lew.get("witsUserSt"), pWitsUserSt);
			mapPredicateParameters.put(new Integer(indexPredicate++), predicate);
			Map<ParameterExpression<?>, Object> mapNameValueParameter = new HashMap<ParameterExpression<?>, Object>();
			mapNameValueParameter.put(pWitsUserSt, witsUserSt);
			mapParameters.put(new Integer(indexParam++), mapNameValueParameter);
		}
		if (dateFrom!=null && dateTo!=null)
		{
			Predicate predicate = builder.between( lew.<Date>get("timestmp") , dateFrom , dateTo); 
			mapPredicateParameters.put(new Integer(indexPredicate++), predicate);
		}
		try {

			Predicate[] paramPredicate = new Predicate[indexPredicate];
			for (int i=0;i<indexPredicate;i++)
			{
				paramPredicate[i] = (Predicate)mapPredicateParameters.get(new Integer(i));
			}

			criteria.select(lew).where(builder.and(paramPredicate)).orderBy(builder.asc(lew.get("eventId"))).distinct(true);

			TypedQuery result = em.createQuery(criteria);
			for (int i=0;i<indexParam;i++)
			{
				Map<ParameterExpression<?>, Object> localMapNameValueParameter = new HashMap<ParameterExpression<?>, Object>();
				localMapNameValueParameter = mapParameters.get(i); 
				for (ParameterExpression<?> item : localMapNameValueParameter.keySet())
					result.setParameter( item , localMapNameValueParameter.get(item));
			}
			List<LoggingEventWorkflow> listLoggingEventWorkflow = result.getResultList();
			if (listLoggingEventWorkflow!=null)
			{
				if (monitoredTablesType.equals(MonitoredTablesType.WAC_COMPLIANCE_CLAIM))
				{
					/*
					 * Find only insert in table WAC_COMPLIANCE_CLAIM
					 */
					List<LoggingEventWorkflow> list = new ArrayList<LoggingEventWorkflow>();
					list.addAll(listLoggingEventWorkflow);
					listLoggingEventWorkflow.clear();
					for (LoggingEventWorkflow item : list)
					{
						String source = item.getFormattedMessage().toLowerCase();
						String subString = WacComplianceClaimTOImpl.class.getSimpleName().toLowerCase(); 
						/*
						 * Find between record POST_INSERT/POST_UPDATE/POST_DELETE 
						 * only those that
						 * inside item.getFormattedMessage() have
						 * word WacComplianceClaimTOImpl
						 */
						if (source.indexOf(subString)>=0)
						{
							listLoggingEventWorkflow.add(item);
						}
					}

				}
				if (monitoredTablesType.equals(MonitoredTablesType.ATTACHED_DOCUMENT_2_TABLE))
				{
					/*
					 * Find only insert in table ATTACHED_DOCUMENT_2_TABLE
					 */
					List<LoggingEventWorkflow> list = new ArrayList<LoggingEventWorkflow>();
					list.addAll(listLoggingEventWorkflow);
					listLoggingEventWorkflow.clear();
					for (LoggingEventWorkflow item : list)
					{
						String source = item.getFormattedMessage().toLowerCase();
						String subString = AttachedDocument2TableTOImpl.class.getSimpleName().toLowerCase(); 
						/*
						 * Find between record POST_INSERT/POST_UPDATE/POST_DELETE 
						 * only those that
						 * inside item.getFormattedMessage() have
						 * word AttachedDocument2TableTOImpl
						 */
						if (source.indexOf(subString)>=0)
						{
							listLoggingEventWorkflow.add(item);
						}
					}

				}
				if (monitoredTablesType.equals(MonitoredTablesType.WAMAT_OBJECT))
				{
					/*
					 * Find only insert in table WAMAT_OBJECT
					 */
					List<LoggingEventWorkflow> list = new ArrayList<LoggingEventWorkflow>();
					list.addAll(listLoggingEventWorkflow);
					listLoggingEventWorkflow.clear();
					for (LoggingEventWorkflow item : list)
					{
						String source = item.getFormattedMessage().toLowerCase();
						String subString = WamatObjectTOImpl.class.getSimpleName().toLowerCase(); 
						/*
						 * Find between record POST_INSERT/POST_UPDATE/POST_DELETE 
						 * only those that
						 * inside item.getFormattedMessage() have
						 * word WamatObjectTOImpl
						 */
						if (source.indexOf(subString)>=0)
						{
							listLoggingEventWorkflow.add(item);
						}
					}

				}
				if (monitoredTablesType.equals(MonitoredTablesType.ITEM))
				{
					/*
					 * Find only insert in table ITEM
					 */
					List<LoggingEventWorkflow> list = new ArrayList<LoggingEventWorkflow>();
					list.addAll(listLoggingEventWorkflow);
					listLoggingEventWorkflow.clear();
					for (LoggingEventWorkflow item : list)
					{
						String source = item.getFormattedMessage().toLowerCase();
						String subString = ItemTOImpl.class.getSimpleName().toLowerCase(); 
						/*
						 * Find between record POST_INSERT/POST_UPDATE/POST_DELETE 
						 * only those that
						 * inside item.getFormattedMessage() have
						 * word WamatObjectTOImpl
						 */
						if (source.indexOf(subString)>=0)
						{
							listLoggingEventWorkflow.add(item);
						}
					}

				}
				if (monitoredTablesType.equals(MonitoredTablesType.WAMAT_EXTENDED_FIELD))
				{
					/*
					 * Find only insert in table WAMAT_EXTENDED_FIELD
					 */
					List<LoggingEventWorkflow> list = new ArrayList<LoggingEventWorkflow>();
					list.addAll(listLoggingEventWorkflow);
					listLoggingEventWorkflow.clear();
					for (LoggingEventWorkflow item : list)
					{
						String source = item.getFormattedMessage().toLowerCase();
						String subString = WamatExtendedFieldTOImpl.class.getSimpleName().toLowerCase(); 
						/*
						 * Find between record POST_INSERT/POST_UPDATE/POST_DELETE 
						 * only those that
						 * inside item.getFormattedMessage() have
						 * word WamatExtendedFieldTOImpl
						 */
						if (source.indexOf(subString)>=0)
						{
							listLoggingEventWorkflow.add(item);
						}
					}

				}
			}
			return listLoggingEventWorkflow;

		} catch (Exception e) {
			logger.debug("getLoggingEventWorkflowChanging(Integer idEntity : " + idEntity + 
					" , String pEntityName : " + strClassEntity + 
					" , WitsUserSt witsUserSt : " + witsUserSt.getActIdUser().getId() + 
					" , Date dateFrom : " + ((dateFrom!=null) ? dateFrom.toString() : "null") + 
					" , Date dateTo : " + ((dateTo!=null) ? dateTo.toString() : "null") + 
					" ) : " + e.getMessage());
		}
		return null;
	}

	@Override
	public LoggingEventWorkflow getLoggingEventWorkflow(Object entity , Class<?> classEntity) {
		Integer idEntity = 0;
		String strClassEntity = "";
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> criteria = builder.createQuery();
		Root<LoggingEventWorkflow> lew = criteria.from(LoggingEventWorkflow.class);
		ParameterExpression<Integer> pidEntity = builder.parameter(Integer.class);
		ParameterExpression<String> pEntityName = builder.parameter(String.class);
		Predicate predicate1 = null,predicate2 = null,predicate3 = null;
		predicate1 = builder.equal(lew.get("entityId"), pidEntity);
		predicate2 = builder.equal(lew.get("entityValue"), pEntityName);
		//Expression<Date> select = lew.<Date>get("timestmp");
		//predicate3 = builder.equal(select, builder.greatest(select));
		predicate3 = builder.isNotNull(lew.get("workflowId"));
		if (classEntity.equals(WamatObject.class))
		{
			idEntity = ((WamatObject)entity).getIdWamatObject();
			strClassEntity = WamatObject.class.getAnnotation(Table.class).name();
		}
		if (classEntity.equals(Item.class))
		{
			idEntity = ((Item)entity).getIdItem();
			strClassEntity = Item.class.getAnnotation(Table.class).name();
		}
		criteria.select(lew).where(builder.and(predicate1,predicate2,predicate3)).orderBy(builder.desc(lew.get("eventId"))).distinct(true);
		try {
			return (LoggingEventWorkflow)em.createQuery(criteria).
					setParameter(pidEntity, idEntity).
					setParameter(pEntityName, strClassEntity).setMaxResults(1).getSingleResult();

		} catch (Exception e) {
			logger.debug("getLoggingEventWorkflow(Integer idEntity : " + idEntity + 
					" , String pEntityName : " + strClassEntity + 
					" ) : " + e.getMessage());
		}
		return null;

	}

}
