package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.ActIdUserDAO;
import org.persistence.layer.wits.form.ActIdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class ActIdUserDAOImpl implements ActIdUserDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public List<ActIdUser> listActIdUser() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<ActIdUser> criteria = builder.createQuery(ActIdUser.class);
	        Root<ActIdUser> actIdUser = criteria.from(ActIdUser.class);

	        criteria.select(actIdUser);
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public ActIdUser getActIdUser(String id) {
	      return em.find(ActIdUser.class, id);
	}

}
