package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.Wac2ExtFieldTypeStDAO;
import org.persistence.layer.wits.form.Wac2ExtFieldTypeSt;
import org.persistence.layer.wits.form.WacSt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class Wac2ExtFieldTypeStDAOImpl implements Wac2ExtFieldTypeStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;
	protected static Logger logger = LoggerFactory.getLogger(Wac2ExtFieldTypeStDAOImpl.class);

	@Override
	public int addWac2ExtFieldTypeSt(Wac2ExtFieldTypeSt w2eft) {
		em.persist(w2eft);
		return w2eft.getIdWac2ExtFieldTypeSt();
	}

	@Override
	public List<Wac2ExtFieldTypeSt> listWac2ExtFieldTypeSt() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Wac2ExtFieldTypeSt> criteria = builder.createQuery(Wac2ExtFieldTypeSt.class);
		Root<Wac2ExtFieldTypeSt> wcc = criteria.from(Wac2ExtFieldTypeSt.class);


		criteria.select(wcc).orderBy(builder.asc(wcc.get("idWac2ExtFieldTypeSt")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeWac2ExtFieldTypeSt(int id) {
		Wac2ExtFieldTypeSt st = em.find(Wac2ExtFieldTypeSt.class, id);
		if (st!=null)
		{
			em.remove(st);
		}

	}

	@Override
	public Wac2ExtFieldTypeSt getWac2ExtFieldTypeSt(int id) {
		return em.find(Wac2ExtFieldTypeSt.class, id);
	}

	@Override
	public void updateWac2ExtFieldTypeSt(Wac2ExtFieldTypeSt w2eft) {
		em.merge(w2eft);

	}

	@Override
	public List<Wac2ExtFieldTypeSt> listWac2ExtFieldTypeSt(WacSt wacSt) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Wac2ExtFieldTypeSt> criteria = builder.createQuery(Wac2ExtFieldTypeSt.class);
		Root<Wac2ExtFieldTypeSt> wcc = criteria.from(Wac2ExtFieldTypeSt.class);
		ParameterExpression<WacSt> pWacSt = builder.parameter(WacSt.class);
		Predicate p1 = builder.equal(wcc.get("wacSt"), pWacSt);


		criteria.select(wcc).where(builder.and(p1)).orderBy(builder.asc(wcc.get("idWac2ExtFieldTypeSt")));
		try {
			return em.createQuery(criteria)
					.setParameter(pWacSt, wacSt)
					.getResultList();
		} catch (Exception e) {
   			logger.debug("listWac2ExtFieldTypeSt(WacSt wacSt : " + wacSt.getIdWacSt()
					+ " ) : " + e.getMessage());
   			return null;
		}

	}

}
