package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.ContainingUnitTypeStDAO;
import org.persistence.layer.wits.form.ContainingUnitTypeSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class ContainingUnitTypeStDAOImpl implements ContainingUnitTypeStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addContainingUnitTypeSt(ContainingUnitTypeSt gst) {
		em.persist(gst);
		return gst.getIdContainingUnitTypeSt();
	}

	@Override
	public List<ContainingUnitTypeSt> listContainingUnitTypeSt() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ContainingUnitTypeSt> criteria = builder.createQuery(ContainingUnitTypeSt.class);
		Root<ContainingUnitTypeSt> gst = criteria.from(ContainingUnitTypeSt.class);

		/*
		 * Swap criteria statements if you would like to try out type-safe criteria queries, a new
		 * feature in JPA 2.0 criteria.select(RcContatto).orderBy(cb.asc(RcContatto.get(RcContatto_.name)));
		 */

		criteria.select(gst);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeContainingUnitTypeSt(int id) {
		ContainingUnitTypeSt gst = em.find(ContainingUnitTypeSt.class, id);
		if (gst!=null)
		{
			em.remove(gst);
		}
	}

	@Override
	public ContainingUnitTypeSt getContainingUnitTypeSt(int id) {
		return em.find(ContainingUnitTypeSt.class, id);
	}

	@Override
	public void updateContainingUnitTypeSt(ContainingUnitTypeSt gst) {
		em.merge(gst);
	}
	@Override
	public List<ContainingUnitTypeSt> listContainingUnitTypeSt(Boolean immovable, Boolean active) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ContainingUnitTypeSt> criteria = builder.createQuery(ContainingUnitTypeSt.class);
		Root<ContainingUnitTypeSt> gst = criteria.from(ContainingUnitTypeSt.class);
		ParameterExpression<Boolean> pImmovable = builder.parameter(Boolean.class);
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		Predicate p1 = null;
		Predicate p2 = null;
		if (immovable!=null && active!=null)
		{
			p1 = builder.equal(gst.get("isImmobile"),pImmovable);
			p2 = builder.equal(gst.get("active"),pActive);           
			criteria.select(gst).where(builder.and(p1,p2)).orderBy(builder.asc(gst.get("nameContUnitTypeSt")));
			return em.createQuery(criteria).setParameter(pImmovable, immovable )
					.setParameter(pActive, active)
					.getResultList();
		}
		else if (immovable!=null)
		{
			p1 = builder.equal(gst.get("isImmobile"),pImmovable);
			criteria.select(gst).where(p1).orderBy(builder.asc(gst.get("nameContUnitTypeSt")));
			return em.createQuery(criteria).setParameter(pImmovable, immovable )
					.getResultList();
		}
		else if (active!=null)
		{
			p2 = builder.equal(gst.get("active"),pActive);           
			criteria.select(gst).where(p2).orderBy(builder.asc(gst.get("nameContUnitTypeSt")));
			return em.createQuery(criteria)
					.setParameter(pActive, active)
					.getResultList();
		}
		else
		{
			criteria.select(gst).orderBy(builder.asc(gst.get("nameContUnitTypeSt")));
			return em.createQuery(criteria)
					.getResultList();
		}

	}

}
