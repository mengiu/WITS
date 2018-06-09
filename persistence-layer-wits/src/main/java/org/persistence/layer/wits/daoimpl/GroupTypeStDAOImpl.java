package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.GroupTypeStDAO;
import org.persistence.layer.wits.form.GroupTypeSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class GroupTypeStDAOImpl implements GroupTypeStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addGroupTypeSt(GroupTypeSt wg) {
		em.persist(wg);
		return wg.getIdGroupTypeSt();
	}

	@Override
	public List<GroupTypeSt> listGroupTypeSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<GroupTypeSt> criteria = builder.createQuery(GroupTypeSt.class);
	        Root<GroupTypeSt> wg = criteria.from(GroupTypeSt.class);

	        criteria.select(wg);
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeGroupTypeSt(int id) {
		GroupTypeSt wg = em.find(GroupTypeSt.class, id);
    	if (wg!=null)
    	{
    	 em.remove(wg);
    	}

	}

	@Override
	public GroupTypeSt getGroupTypeSt(int id) {
		return em.find(GroupTypeSt.class, id);
	}

	@Override
	public void updateGroupTypeSt(GroupTypeSt wg) {
		em.merge(wg);

	}

}
