package org.persistence.layer.wits.daoimpl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.ActGeBytearrayDAO;
import org.persistence.layer.wits.form.ActGeBytearray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class ActGeBytearrayDAOImpl implements ActGeBytearrayDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;
	private static Logger logger = LoggerFactory.getLogger(ActGeBytearrayDAOImpl.class);

	@Override
	public List<ActGeBytearray> listActGeBytearray() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<ActGeBytearray> criteria = builder.createQuery(ActGeBytearray.class);
	        Root<ActGeBytearray> actGeBytearray = criteria.from(ActGeBytearray.class);
 
	        criteria.select(actGeBytearray);
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public ActGeBytearray getActGeBytearray(int id) {
	      return em.find(ActGeBytearray.class, id);
	}

	@Override
	public ActGeBytearray getActGeBytearrayRule(String nameRules) {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<ActGeBytearray> criteria = builder.createQuery(ActGeBytearray.class);
	        Root<ActGeBytearray> actGeBytearray = criteria.from(ActGeBytearray.class);
	        ParameterExpression<String> p = builder.parameter(String.class);
	        Expression<String> path = actGeBytearray.get("name");
	        Predicate predicate1 = builder.like( path , p );
	        criteria.select(actGeBytearray).where(predicate1).orderBy(builder.desc(actGeBytearray.get("deploymentId")));
	        try {
				List<ActGeBytearray> list = em.createQuery(criteria).setParameter(p, nameRules ).getResultList();
				if (list!=null)
				{
				 return list.get(0);
				}
			} catch (Exception e) {
				logger.error("getActGeBytearrayRule(nameRules : " + nameRules + " ) : " + e.getMessage());
			}
	        return null;
	}

	@Override
	public byte[] getActGeBytearrayRuleByte(String nameRules) {
	       /*CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<ActGeBytearray> criteria = builder.createQuery(ActGeBytearray.class);
	        Root<ActGeBytearray> actGeBytearray = criteria.from(ActGeBytearray.class);
	        ParameterExpression<String> p = builder.parameter(String.class);
	        Expression<String> path = actGeBytearray.get("name");
	        Predicate predicate1 = builder.like( path , p );
	        criteria.select(actGeBytearray).where(predicate1).orderBy(builder.desc(actGeBytearray.get("deploymentId")));
	        try {
				List<ActGeBytearray> list = em.createQuery(criteria).setParameter(p, nameRules ).getResultList();
				if (list!=null)
				{
				 return list.get(0).getBytes();
				}
			} catch (Exception e) {
				logger.error("getActGeBytearrayRuleByte(nameRules : " + nameRules + " ) : " + e.getMessage());
			}
	        return null;*/
            String sqlString = "select c from ActGeBytearray c where c.name like '";
            sqlString += nameRules;
            sqlString += "' ORDER BY to_number(c.deploymentId) desc";
	        try {
			    TypedQuery<ActGeBytearray> query = em.createQuery(sqlString,ActGeBytearray.class);
				List<ActGeBytearray> list = query.getResultList();
				if (list!=null)
				{
				 return list.get(0).getBytes();
				}
			} catch (Exception e) {
				logger.error("getActGeBytearrayRuleByte(nameRules : " + nameRules + " ) : " + e.getMessage());
			}
	        return null;
	}

}
