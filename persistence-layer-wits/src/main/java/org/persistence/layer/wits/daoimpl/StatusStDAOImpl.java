package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.StatusStDAO;
import org.persistence.layer.wits.form.StatusSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class StatusStDAOImpl implements StatusStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addStatusSt(StatusSt statusSt) {
	       em.persist(statusSt);
	       return statusSt.getIdStatusSt();
	}

	@Override
	public List<StatusSt> listStatusSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<StatusSt> criteria = builder.createQuery(StatusSt.class);
	        Root<StatusSt> statusSt = criteria.from(StatusSt.class);


	        criteria.select(statusSt).orderBy(builder.asc(statusSt.get("idStatusSt")));
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeStatusSt(int id) {
		StatusSt st = em.find(StatusSt.class, id);
    	if (st!=null)
    	{
    	 em.remove(st);
    	}
	}

	@Override
	public StatusSt getStatusSt(int id) {
	      return em.find(StatusSt.class, id);
	}

	@Override
	public void updateStatusSt(StatusSt statusSt) {
		em.merge(statusSt);
	}

}
