package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.GenerationProcessInstanceDAO;
import org.persistence.layer.wits.form.GenerationProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class GenerationProcessInstanceDAOImpl implements GenerationProcessInstanceDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;
	private static Logger logger = LoggerFactory.getLogger(GenerationProcessInstanceDAOImpl.class);

	@Override
	public int addGenerationProcessInstance(GenerationProcessInstance wpi) {
		logger.debug(wpi.toString());
		em.persist(wpi);
		return wpi.getIdGpInstance();
	}

	@Override
	public List<GenerationProcessInstance> listGenerationProcessInstance() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<GenerationProcessInstance> criteria = builder.createQuery(GenerationProcessInstance.class);
		Root<GenerationProcessInstance> wpi = criteria.from(GenerationProcessInstance.class);

		/*
		 * Swap criteria statements if you would like to try out type-safe criteria queries, a new
		 * feature in JPA 2.0 criteria.select(RcContatto).orderBy(cb.asc(RcContatto.get(RcContatto_.name)));
		 */

		criteria.select(wpi);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeGenerationProcessInstance(int id) {
		GenerationProcessInstance wpi = em.find(GenerationProcessInstance.class, id);
		if (wpi!=null)
		{
			logger.debug(wpi.toString());
			em.remove(wpi);
		}
	}

	@Override
	public GenerationProcessInstance getGenerationProcessInstance(int id) {
		return em.find(GenerationProcessInstance.class, id);
	}

	@Override
	public void updateGenerationProcessInstance(GenerationProcessInstance wpi) {
		logger.debug(wpi.toString());
		em.merge(wpi);
	}

	@Override
	public GenerationProcessInstance getGenerationProcessInstance(String processNameInstance) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<GenerationProcessInstance> criteria = builder.createQuery(GenerationProcessInstance.class);
		Root<GenerationProcessInstance> wpi = criteria.from(GenerationProcessInstance.class);
		ParameterExpression<String> pNameProcess = builder.parameter(String.class);
		Predicate p1 = builder.equal(wpi.get("nameGpInstance"),pNameProcess);           
		criteria.select(wpi).where(builder.and(p1));

		try {
			return em.createQuery(criteria)
					.setParameter(pNameProcess, processNameInstance)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
