package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.EventTypeStDAO;
import org.persistence.layer.wits.form.EventTypeSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class EventTypeStDAOImpl implements EventTypeStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addEventTypeSt(EventTypeSt ets) {
	       em.persist(ets);
	       return ets.getIdEventTypeSt();
	}

	@Override
	public List<EventTypeSt> listEventTypeSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<EventTypeSt> criteria = builder.createQuery(EventTypeSt.class);
	        Root<EventTypeSt> ets = criteria.from(EventTypeSt.class);


	        criteria.select(ets);
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeEventTypeSt(int id) {
		EventTypeSt ets = em.find(EventTypeSt.class, id);
    	if (ets!=null)
    	{
    	 em.remove(ets);
    	}

	}

	@Override
	public EventTypeSt getEventTypeSt(int id) {
	      return em.find(EventTypeSt.class, id);
	}

	@Override
	public void updateEventTypeSt(EventTypeSt ets) {
		em.merge(ets);

	}

}
