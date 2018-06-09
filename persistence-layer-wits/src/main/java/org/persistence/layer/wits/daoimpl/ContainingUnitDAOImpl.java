package org.persistence.layer.wits.daoimpl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.ContainingUnitDAO;
import org.persistence.layer.wits.form.ContainingUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class ContainingUnitDAOImpl implements ContainingUnitDAO {

	//@PersistenceContext(type=PersistenceContextType.EXTENDED)
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;
	private static Logger logger = LoggerFactory.getLogger(ContainingUnitDAOImpl.class);

	@Override
	public int addContainingUnit(ContainingUnit cu) {
		logger.debug(cu.toString());
		em.persist(cu);
		return cu.getIdContainingUnit();
	}

	@Override
	public List<ContainingUnit> listContainingUnit() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ContainingUnit> criteria = builder.createQuery(ContainingUnit.class);
		Root<ContainingUnit> gst = criteria.from(ContainingUnit.class);


		criteria.select(gst);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeContainingUnit(int id) {
		ContainingUnit cu = em.find(ContainingUnit.class, id);
		if (cu!=null)
		{
			logger.debug(cu.toString());
			em.remove(cu);
		}

	}

	@Override
	public ContainingUnit getContainingUnit(int id) {
		ContainingUnit containingUnit = em.find(ContainingUnit.class, id);
		if (containingUnit!=null)
		{
			if (containingUnit.getContainingUnit()!=null)
				containingUnit.getContainingUnit().getIdContainingUnit();
			if (containingUnit.getContainingUnitTypeSt()!=null)
				containingUnit.getContainingUnitTypeSt().getIsImmobile();
		}
		return containingUnit;
	}

	@Override
	public void updateContainingUnit(ContainingUnit cu) {
		logger.debug(cu.toString());
		em.merge(cu);

	}

	@Override
	public ContainingUnit getContainingUnit(String cuName ) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ContainingUnit> criteria = builder.createQuery(ContainingUnit.class);
		Root<ContainingUnit> cu = criteria.from(ContainingUnit.class);
		ParameterExpression<String> pCuName = builder.parameter(String.class);
		Predicate predicate1 = builder.equal(cu.get("nameContainingUnit"), pCuName); 
		criteria.select(cu).where(builder.and(predicate1));
		try {
			return em.createQuery(criteria).setParameter(pCuName , cuName ).
					getSingleResult();
		} catch (Exception e) {
			logger.debug("getContainingUnit(String cuName : " + cuName + 
					" ) : " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<ContainingUnit> hasChildren(ContainingUnit father) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ContainingUnit> criteria = builder.createQuery(ContainingUnit.class);
		Root<ContainingUnit> cu = criteria.from(ContainingUnit.class);
		ParameterExpression<ContainingUnit> pFather = builder.parameter(ContainingUnit.class);
		Predicate predicate1 = builder.equal(cu.get("containingUnit"), pFather); 
		criteria.select(cu).where(builder.and(predicate1));
		try {
			return em.createQuery(criteria).setParameter(pFather , father )
					.getResultList();
		} catch (Exception e) {
			logger.debug("hasChildren(ContainingUnit father : " + father.getNameContainingUnit() + 
					" ) : " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<ContainingUnit> listContainingUnit( Boolean immovable,Boolean active ) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ContainingUnit> criteria = builder.createQuery(ContainingUnit.class);
		Root<ContainingUnit> cu = criteria.from(ContainingUnit.class);
		ParameterExpression<Boolean> pImmovable = builder.parameter(Boolean.class);
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		Predicate p1 = builder.equal(cu.get("containingUnitTypeSt").get("isImmobile"),pImmovable);           
		Predicate p2 = builder.equal(cu.get("containingUnitTypeSt").get("active"),pActive);           

		criteria.select(cu).where(builder.and(p1,p2))
		.orderBy(builder.asc(cu.get("containingUnitTypeSt").get("nameContUnitTypeSt")));
		/* Crea la stringa sql 
		 * em.createQuery(criteria).unwrap(org.hibernate.Query.class).getQueryString(); 
		 */

		return em.createQuery(criteria).setParameter(pImmovable, immovable )
				.setParameter(pActive, active)
				.getResultList();
	}

	@Override
	public List<ContainingUnit> listContainingUnit( Boolean immovable, Boolean active, ContainingUnit father ) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ContainingUnit> criteria = builder.createQuery(ContainingUnit.class);
		Root<ContainingUnit> cu = criteria.from(ContainingUnit.class);
		ParameterExpression<Boolean> pImmovable = builder.parameter(Boolean.class);
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		ParameterExpression<ContainingUnit> pFather = builder.parameter(ContainingUnit.class);
		Predicate p1,p2,p3;
		p1 = builder.equal(cu.get("containingUnitTypeSt").get("isImmobile"), pImmovable);
		p2 = builder.equal(cu.get("containingUnitTypeSt").get("active"), pActive);
		if (father!=null)
			p3 = builder.equal(cu.get("containingUnit"), pFather);
		else	
			p3 = builder.isNull(cu.get("containingUnit"));
		/* Crea la stringa sql 
		 * em.createQuery(criteria).unwrap(org.hibernate.Query.class).getQueryString(); 
		 */

		if (immovable!=null)
		{
			criteria.select(cu).where(builder.and(p1,p2,p3)).orderBy(builder.asc(cu.join("containingUnitTypeSt").get("nameContUnitTypeSt")));
			if (father!=null)
				return em.createQuery(criteria).setParameter(pImmovable, immovable )
						.setParameter(pActive, active)
						.setParameter(pFather, father ).getResultList();
			else
				return em.createQuery(criteria).setParameter(pImmovable, immovable )
						.setParameter(pActive, active)
						.getResultList();
		}
		else
		{
			criteria.select(cu).where(builder.and(p2,p3)).orderBy(builder.asc(cu.join("containingUnitTypeSt").get("nameContUnitTypeSt")));
			if (father!=null)
				return em.createQuery(criteria)
						.setParameter(pActive, active)
						.setParameter(pFather, father ).getResultList();
			else
				return em.createQuery(criteria)
						.setParameter(pActive, active)
						.getResultList();

		}
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<ContainingUnit> listContainingUnitMovable( Boolean immovable, Boolean active, 
			List<Integer> listCUByFkCuFirstImmobile ) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ContainingUnit> criteria = builder.createQuery(ContainingUnit.class);
		Root<ContainingUnit> cu = criteria.from(ContainingUnit.class);
		ParameterExpression<Boolean> pImmovable = builder.parameter(Boolean.class);
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		ParameterExpression<Collection> pListCUByFkCuFirstImmobile = builder.parameter(Collection.class);
		Predicate p1,p2,p3;
		p1 = builder.equal(cu.get("containingUnitTypeSt").get("isImmobile"), pImmovable);
		p2 = builder.equal(cu.get("containingUnitTypeSt").get("active"), pActive);
		Expression<Integer> exp = cu.get("containingUnit").get("idContainingUnit");
		p3 = exp.in(pListCUByFkCuFirstImmobile);
		/* Crea la stringa sql 
		 * em.createQuery(criteria).unwrap(org.hibernate.Query.class).getQueryString(); 
		 */

		if (immovable!=null)
		{
			criteria.select(cu).where(builder.and(p1,p2,p3)).distinct(true);
			return em.createQuery(criteria).setParameter(pImmovable, immovable )
					.setParameter(pActive, active)
					.setParameter(pListCUByFkCuFirstImmobile, listCUByFkCuFirstImmobile ).getResultList();
		}
		else
		{
			criteria.select(cu).where(builder.and(p2,p3)).distinct(true).orderBy(builder.asc(cu.get("containingUnitTypeSt").get("nameContUnitTypeSt")));
			return em.createQuery(criteria)
					.setParameter(pActive, active)
					.setParameter(pListCUByFkCuFirstImmobile, listCUByFkCuFirstImmobile ).getResultList();

		}
	}

	public ContainingUnit getContainingUnit(ContainingUnit containingUnit, Short positionX,
			Short positionY, Short positionZ) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ContainingUnit> criteria = builder.createQuery(ContainingUnit.class);
		Root<ContainingUnit> cu = criteria.from(ContainingUnit.class);
		ParameterExpression<ContainingUnit> pContainingUnit = builder.parameter(ContainingUnit.class);
		ParameterExpression<Short> pPositionX = builder.parameter(Short.class);
		ParameterExpression<Short> pPositionY = builder.parameter(Short.class);
		ParameterExpression<Short> pPositionZ = builder.parameter(Short.class);
		Predicate p1 = builder.equal(cu.get("containingUnit"),pContainingUnit);
		Predicate p2 = builder.equal(cu.get("positionX"),pPositionX);
		Predicate p3 = builder.equal(cu.get("positionY"),pPositionY);
		Predicate p4 = builder.equal(cu.get("positionZ"),pPositionZ);

		criteria.select(cu).where(builder.and(p1,p2,p3,p4));
		try {
			return em.createQuery(criteria)
					.setParameter(pContainingUnit, containingUnit )
					.setParameter(pPositionX, positionX )
					.setParameter(pPositionY, positionY )
					.setParameter(pPositionZ, positionZ )
					.getSingleResult();
		} catch (Exception e) {
			logger.debug("getContainingUnit(ContainingUnit containingUnit : " + containingUnit.getIdContainingUnit() + 
					" , Short positionX : " + positionX + " , Short positionY : " 
					+ positionY + " , Short positionZ : " + positionZ
					+ " ) : " + e.getMessage());
		}
		return null;
	}
	@Override
	public String getPositionContainingUnit(int id) {
		ContainingUnit containingUnit = getContainingUnit(id);
		/* find last containing unit in the chain */
		while (containingUnit.getContainingUnit()!=null)	
		{
			containingUnit = containingUnit.getContainingUnit();
			if (containingUnit.getContainingUnitTypeSt().getIsImmobile())
				break; // exit at the first CU Immovable in the CU chain	
		}
		if (containingUnit.getContainingUnitTypeSt().getIsImmobile())
			return containingUnit.getContainingUnitTypeSt().getNameContUnitTypeSt();
		else 
			return "Scononosciuta";
		
	}

}
