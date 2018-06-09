package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.QuantityUnitStDAO;
import org.persistence.layer.wits.form.QuantityUnitSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class QuantityUnitStDAOImpl implements QuantityUnitStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addQuantityUnitSt(QuantityUnitSt qus) {
	       em.persist(qus);
	       return qus.getIdQuantityUnitSt();
	}

	@Override
	public List<QuantityUnitSt> listQuantityUnitSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<QuantityUnitSt> criteria = builder.createQuery(QuantityUnitSt.class);
	        Root<QuantityUnitSt> qus = criteria.from(QuantityUnitSt.class);

	        /*
	         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
	         * feature in JPA 2.0 criteria.select(RcContatto).orderBy(cb.asc(RcContatto.get(RcContatto_.name)));
	         */

	        criteria.select(qus).orderBy(builder.asc(qus.get("nameQuantityUnitSt")));
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeQuantityUnitSt(int id) {
		QuantityUnitSt ow = em.find(QuantityUnitSt.class, id);
    	if (ow!=null)
    	{
    	 em.remove(ow);
    	}

	}

	@Override
	public QuantityUnitSt getQuantityUnitSt(int id) {
		return em.find(QuantityUnitSt.class, id);
	}

	@Override
	public void updateQuantityUnitSt(QuantityUnitSt qus) {
		em.merge(qus);

	}

}
