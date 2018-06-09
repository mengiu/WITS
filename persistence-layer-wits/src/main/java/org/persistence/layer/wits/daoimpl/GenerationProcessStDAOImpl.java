package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.GenerationProcessStDAO;
import org.persistence.layer.wits.form.GenerationProcessSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class GenerationProcessStDAOImpl implements GenerationProcessStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addGenerationProcessSt(GenerationProcessSt wp) {
		em.persist(wp);
		return wp.getIdGenerationProcessSt();
	}

	@Override
	public List<GenerationProcessSt> listGenerationProcessSt() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<GenerationProcessSt> criteria = builder.createQuery(GenerationProcessSt.class);
		Root<GenerationProcessSt> wp = criteria.from(GenerationProcessSt.class);

		/*
		 * Swap criteria statements if you would like to try out type-safe criteria queries, a new
		 * feature in JPA 2.0 criteria.select(RcContatto).orderBy(cb.asc(RcContatto.get(RcContatto_.name)));
		 */

		criteria.select(wp);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeGenerationProcessSt(int id) {
		GenerationProcessSt wp = em.find(GenerationProcessSt.class, id);
		if (wp!=null)
		{
			em.remove(wp);
		}
	}

	@Override
	public GenerationProcessSt getGenerationProcessSt(int id) {
		return em.find(GenerationProcessSt.class, id);
	}

	@Override
	public void updateGenerationProcessSt(GenerationProcessSt wp) {
		em.merge(wp);

	}

	@Override
	public List<GenerationProcessSt> listGenerationProcessSt(Boolean active) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<GenerationProcessSt> criteria = builder.createQuery(GenerationProcessSt.class);
		Root<GenerationProcessSt> gps = criteria.from(GenerationProcessSt.class);
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		Predicate p1 = null;
		if (active!=null)
		{
			p1 = builder.equal(gps.get("active"),pActive);           
			criteria.select(gps).where(builder.and(p1)).orderBy(builder.asc(gps.get("nameGenerationProcessSt")));
			return em.createQuery(criteria)
					.setParameter(pActive, active)
					.getResultList();
		}
		else
		{
			criteria.select(gps).orderBy(builder.asc(gps.get("nameGenerationProcessSt")));
			return em.createQuery(criteria)
					.getResultList();
		}
	}

	@Override
	public GenerationProcessSt getGenerationProcessSt(String nameProcess) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<GenerationProcessSt> criteria = builder.createQuery(GenerationProcessSt.class);
		Root<GenerationProcessSt> gps = criteria.from(GenerationProcessSt.class);
		ParameterExpression<String> pNameProcess = builder.parameter(String.class);
		Predicate p1 = builder.equal(gps.get("nameGenerationProcessSt"),pNameProcess);           
		criteria.select(gps).where(builder.and(p1));
		try {
			return em.createQuery(criteria)
					.setParameter(pNameProcess, nameProcess)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
