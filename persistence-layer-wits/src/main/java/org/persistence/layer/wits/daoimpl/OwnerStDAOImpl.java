package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.OwnerStDAO;
import org.persistence.layer.wits.form.OwnerSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class OwnerStDAOImpl implements OwnerStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addOwnerSt(OwnerSt ow) {
	       em.persist(ow);
	       return ow.getIdOwnerSt();
	}

	@Override
	public List<OwnerSt> listOwnerSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<OwnerSt> criteria = builder.createQuery(OwnerSt.class);
	        Root<OwnerSt> ow = criteria.from(OwnerSt.class);

	        /*
	         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
	         * feature in JPA 2.0 criteria.select(RcContatto).orderBy(cb.asc(RcContatto.get(RcContatto_.name)));
	         */

	        criteria.select(ow);
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeOwnerSt(int id) {
		OwnerSt ow = em.find(OwnerSt.class, id);
    	if (ow!=null)
    	{
    	 em.remove(ow);
    	}

	}

	@Override
	public OwnerSt getOwnerSt(int id) {
		return em.find(OwnerSt.class, id);
	}

	@Override
	public void updateOwnerSt(OwnerSt ow) {
		em.merge(ow);
	}

}
