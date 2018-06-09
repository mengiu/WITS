package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.WacStDAO;
import org.persistence.layer.wits.form.WacSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class WacStDAOImpl implements WacStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addWacSt(WacSt wacSt) {
	       em.persist(wacSt);
	       return wacSt.getIdWacSt();
	}

	@Override
	public List<WacSt> listWacSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<WacSt> criteria = builder.createQuery(WacSt.class);
	        Root<WacSt> wacSt = criteria.from(WacSt.class);


	        criteria.select(wacSt).orderBy(builder.asc(wacSt.get("nameWacSt")));
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeWacSt(int id) {
		WacSt st = em.find(WacSt.class, id);
    	if (st!=null)
    	{
    	 em.remove(st);
    	}

	}

	@Override
	public WacSt getWacSt(int id) {
	      return em.find(WacSt.class, id);
	}

	@Override
	public void updateWacSt(WacSt wacSt) {
		em.merge(wacSt);
	}

}
