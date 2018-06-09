package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.GroupStDAO;
import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.GroupTypeSt;
import org.persistence.layer.wits.form.OwnerSt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class GroupStDAOImpl implements GroupStDAO {
	private static Logger logger = LoggerFactory.getLogger(GroupStDAOImpl.class);

	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addGroupSt(GroupSt wig) {
		em.persist(wig);
		return wig.getIdGroupSt();
	}

	@Override
	public List<GroupSt> listGroupSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<GroupSt> criteria = builder.createQuery(GroupSt.class);
	        Root<GroupSt> wig = criteria.from(GroupSt.class);

	        criteria.select(wig).orderBy(builder.asc(wig.get("nameGroupSt")));
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeGroupSt(int id) {
		GroupSt wig = em.find(GroupSt.class, id);
    	if (wig!=null)
    	{
    	 em.remove(wig);
    	}

	}

	@Override
	public GroupSt getGroupSt(int id) {
		return em.find(GroupSt.class, id);
	}

	@Override
	public void updateGroupSt(GroupSt wig) {
		em.merge(wig);
	}

	@Override
	public GroupSt getGroupSt(GroupTypeSt groupTypeSt) {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<GroupSt> criteria = builder.createQuery(GroupSt.class);
	        Root<GroupSt> wig = criteria.from(GroupSt.class);
			ParameterExpression<GroupTypeSt> pGroupTypeSt = builder.parameter(GroupTypeSt.class);
			Predicate p1 = builder.equal(wig.get("groupTypeSt"),pGroupTypeSt);

	        criteria.select(wig).where(builder.and(p1));
	        try {
				return em.createQuery(criteria)
						.setParameter(pGroupTypeSt, groupTypeSt)
						.getSingleResult();
			} catch (Exception e) {
				logger.debug("getGroupSt(GroupTypeSt groupTypeSt : " + groupTypeSt.getIdGroupTypeSt()  
						+ " ) : " + e.getMessage());
				return null;
			}
	}

	@Override
	public GroupSt getGroupSt(GroupTypeSt groupTypeSt, OwnerSt ownerSt) {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<GroupSt> criteria = builder.createQuery(GroupSt.class);
	        Root<GroupSt> wig = criteria.from(GroupSt.class);
			ParameterExpression<GroupTypeSt> pGroupTypeSt = builder.parameter(GroupTypeSt.class);
			ParameterExpression<OwnerSt> pOwnerSt = builder.parameter(OwnerSt.class);
			Predicate p1 = builder.equal(wig.get("groupTypeSt"),pGroupTypeSt);
			Predicate p2 = builder.equal(wig.get("ownerSt"),pOwnerSt);

	        criteria.select(wig).where(builder.and(p1,p2));
	        try {
				return em.createQuery(criteria)
						.setParameter(pGroupTypeSt, groupTypeSt)
						.setParameter(pOwnerSt, ownerSt)
						.getSingleResult();
			} catch (Exception e) {
				logger.debug("getGroupSt(GroupTypeSt groupTypeSt : " + groupTypeSt.getIdGroupTypeSt()  
						+ "  OwnerSt ownerSt " + ownerSt.getIdOwnerSt() + 
						" ) : " + e.getMessage());
				return null;
			}
	}
}
