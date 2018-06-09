package org.persistence.layer.wits.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.EventInstDAO;
import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableEvents;
import org.persistence.layer.wits.form.EventInstance;
import org.persistence.layer.wits.form.EventTypeSt;
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
public class EventInstDAOImpl implements EventInstDAO {
	private static Logger logger = LoggerFactory.getLogger(EventInstDAOImpl.class);
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addEventInst(EventInstance ei) {
		   logger.debug(ei.toString());
	       em.persist(ei);
	       return ei.getIdEventInstance();
	}

	@Override
	public List<EventInstance> listEventInst() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<EventInstance> criteria = builder.createQuery(EventInstance.class);
	        Root<EventInstance> ei = criteria.from(EventInstance.class);


	        criteria.select(ei);
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeEventInst(int id) {
		EventInstance ei = em.find(EventInstance.class, id);
    	if (ei!=null)
    	{
 		 logger.debug(ei.toString());
    	 em.remove(ei);
    	}

	}

	@Override
	public EventInstance getEventInst(int id) {
	      return em.find(EventInstance.class, id);
		
	}

	@Override
	public void updateEventInst(EventInstance ei) {
		logger.debug(ei.toString());
		em.merge(ei);

	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<EventInstance> listEventInst(WamatObject wamatObject, WitsUserSt user, EventTypeSt eventTypeSt,
			OrdersFieldsTableEvents ordersFieldsTableEvents , Boolean ascending ) {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<EventInstance> criteria = builder.createQuery(EventInstance.class);
	        Root<EventInstance> ei = criteria.from(EventInstance.class);

			ParameterExpression<WamatObject> pWamatObject = builder.parameter(WamatObject.class);
			ParameterExpression<WitsUserSt> pUser = builder.parameter(WitsUserSt.class);
			ParameterExpression<EventTypeSt> pEventTypeSt = builder.parameter(EventTypeSt.class);
			Predicate predicate1,predicate2,predicate3;
			Map<Integer, Predicate> mapPredicateParameters = new HashMap<Integer, Predicate>();
			Map<Integer, Map<ParameterExpression<?>, Object>> mapParameters = new HashMap<Integer, Map<ParameterExpression<?>, Object>>();

			int index = 0;
			{
			 predicate1 = builder.equal(ei.get("wamatObject"), pWamatObject);
			 mapPredicateParameters.put(new Integer(index), predicate1);
			 Map<ParameterExpression<?>, Object> mapNameValueParameter = new HashMap<ParameterExpression<?>, Object>();
			 mapNameValueParameter.put(pWamatObject, wamatObject);
			 mapParameters.put(new Integer(index++), mapNameValueParameter);
	        }
			if (user!=null)
			{
				predicate2 = builder.equal(ei.get("witsUserSt"), pUser);
				mapPredicateParameters.put(new Integer(index), predicate2);
				Map<ParameterExpression<?>, Object> mapNameValueParameter = new HashMap<ParameterExpression<?>, Object>();
				mapNameValueParameter.put(pUser, user);
				mapParameters.put(new Integer(index++), mapNameValueParameter);
				
			}
			if (eventTypeSt!=null)
			{
				predicate3 = builder.equal(ei.get("eventTypeSt"), pEventTypeSt);
				mapPredicateParameters.put(new Integer(index), predicate3);
				Map<ParameterExpression<?>, Object> mapNameValueParameter = new HashMap<ParameterExpression<?>, Object>();
				mapNameValueParameter.put(pEventTypeSt, eventTypeSt);
				mapParameters.put(new Integer(index++), mapNameValueParameter);
				
			}
			if (index>0)
			{
				//no paramater
				Class noparams[] = {};
				//Predicate parameter
				Class[] paramPredicate = new Class[index];	
				for (int i=0;i<index;i++)
					paramPredicate[i] = Predicate.class;

				Class cls;
				try {
					Predicate[] paramsValue = new Predicate[index];
					for (int i=0;i<index;i++)
					{
						paramsValue[i] = (Predicate)mapPredicateParameters.get(new Integer(i));
					}
					if (ordersFieldsTableEvents!=null)
					{
						switch (ordersFieldsTableEvents)
						{
						case DATE :
						{
							if (ascending)
								criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.asc(ei.get("eventDate")));
							else
								criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.desc(ei.get("eventDate")));
							break;
						}
						case TYPE :
						{
							if (ascending)
								criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.asc(ei.get("eventTypeSt").get("nameEventTypeSt")));
							else
								criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.desc(ei.get("eventTypeSt").get("nameEventTypeSt")));
							break;
						}
						case DESCRIPTION :
						{
							if (ascending)
								criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.asc(ei.get("descriptionEventInstance")));
							else
								criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.desc(ei.get("descriptionEventInstance")));
							break;
						}
						case USER :
						{
							if (ascending)
								criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.asc(ei.get("witsUserSt").get("actIdUser").get("id")));
							else
								criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.desc(ei.get("witsUserSt").get("actIdUser").get("id")));
							break;
						}
						case WORKFLOWINSTANCE :
						{
							if (ascending)
								criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.asc(ei.get("workflowId")));
							else
								criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.desc(ei.get("workflowId")));
							break;
						}
						default :
						{
							criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.asc(ei.get("idEventInstance")));
							break;
						}
						}
					}
					else
						criteria.select(ei).where(builder.and(paramsValue)).orderBy(builder.asc(ei.get("idEventInstance")));
					TypedQuery result = em.createQuery(criteria);
					for (int i=0;i<index;i++)
					{
						Map<ParameterExpression<?>, Object> localMapNameValueParameter = new HashMap<ParameterExpression<?>, Object>();
						localMapNameValueParameter = mapParameters.get(i); 
						for (ParameterExpression<?> item : localMapNameValueParameter.keySet())
							result.setParameter( item , localMapNameValueParameter.get(item));
					}
					return result.getResultList();
				} catch (Exception e1) {
					logger.debug("listEventInst(WamatObject wamatObject : " + wamatObject.getIdWamatObject() +
				            " , WitsUserSt user : " + user.getIdWitsUserSt() + 
							" , EventTypeSt eventTypeSt : " + eventTypeSt.getIdEventTypeSt() + 
							" , OrdersFieldsTableEvents ordersFieldsTableEvents : " + ordersFieldsTableEvents.getValue() + 
							" ,  Boolean ascending : " + ascending.toString()
							+ " ) : " + e1.getMessage());
				}
			}
			return new ArrayList<EventInstance>();
	}

}
