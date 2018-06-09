package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.HomogeneousLotDAO;
import org.persistence.layer.wits.form.HomogeneousGroupSt;
import org.persistence.layer.wits.form.HomogeneousLot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class HomogeneousLotDAOImpl implements HomogeneousLotDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;
    private Logger logger = LoggerFactory.getLogger(HomogeneousLotDAOImpl.class);

	@Override
	public int addHomogeneousLot(HomogeneousLot hl) {
		em.persist(hl);
		return hl.getIdHomogeneousLot();
	}

	@Override
	public List<HomogeneousLot> listHomogeneousLot() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<HomogeneousLot> criteria = builder.createQuery(HomogeneousLot.class);
	        Root<HomogeneousLot> wg = criteria.from(HomogeneousLot.class);

	        criteria.select(wg).orderBy(builder.asc(wg.get("nameHomogeneousLot")));
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeHomogeneousLot(int id) {
		HomogeneousLot wg = em.find(HomogeneousLot.class, id);
    	if (wg!=null)
    	{
    	 em.remove(wg);
    	}
	}

	@Override
	public HomogeneousLot getHomogeneousLot(int id) {
		return em.find(HomogeneousLot.class, id);
	}

	@Override
	public void updateHomogeneousLot(HomogeneousLot hl) {
		em.merge(hl);

	}

	@Override
	public List<HomogeneousLot> listHomogeneousLot(HomogeneousGroupSt hgSt) {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<HomogeneousLot> criteria = builder.createQuery(HomogeneousLot.class);
	        Root<HomogeneousLot> wg = criteria.from(HomogeneousLot.class);
			ParameterExpression<HomogeneousGroupSt> pHomogeneousGroupSt = builder.parameter(HomogeneousGroupSt.class);
			Predicate p1 = builder.equal(wg.get("homogeneousGroupSt"),pHomogeneousGroupSt);

	        criteria.select(wg).where(builder.and(p1)).orderBy(builder.asc(wg.get("nameHomogeneousLot")));
	        return em.createQuery(criteria).setParameter(pHomogeneousGroupSt, hgSt).getResultList();
	}

	@Override
	public HomogeneousLot getHomogeneousLot(HomogeneousGroupSt hgSt, String nameHomogeneousLot) {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<HomogeneousLot> criteria = builder.createQuery(HomogeneousLot.class);
	        Root<HomogeneousLot> wg = criteria.from(HomogeneousLot.class);
			ParameterExpression<HomogeneousGroupSt> pHomogeneousGroupSt = builder.parameter(HomogeneousGroupSt.class);
			ParameterExpression<String> pNameHomogeneousLot = builder.parameter(String.class);
			Predicate p1 = builder.equal(wg.get("homogeneousGroupSt"),pHomogeneousGroupSt);
			Predicate p2 = builder.equal(wg.get("nameHomogeneousLot"),pNameHomogeneousLot);

	        criteria.select(wg).where(builder.and(p1,p2));
	        try {
				return em.createQuery(criteria)
						.setParameter(pHomogeneousGroupSt, hgSt)
						.setParameter(pNameHomogeneousLot, nameHomogeneousLot)
						.getSingleResult();
			} catch (Exception e) {
				logger.debug(e.getMessage());
				return null;
			}
	}
}
