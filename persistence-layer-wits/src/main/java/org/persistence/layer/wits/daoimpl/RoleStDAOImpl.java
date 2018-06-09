package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.RoleStDAO;
import org.persistence.layer.wits.form.RoleSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class RoleStDAOImpl implements RoleStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addRoleSt(RoleSt role) {
		em.persist(role);
		return role.getIdRole();
	}

	@Override
	public List<RoleSt> listRoleSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<RoleSt> criteria = builder.createQuery(RoleSt.class);
	        Root<RoleSt> role = criteria.from(RoleSt.class);

	        criteria.select(role);
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeRoleSt(int id) {
		RoleSt role = em.find(RoleSt.class, id);
    	if (role!=null)
    	{
    	 em.remove(role);
    	}

	}

	@Override
	public RoleSt getRoleSt(int id) {
		return em.find(RoleSt.class, id);
	}

	@Override
	public void updateRoleSt(RoleSt role) {
		em.merge(role);

	}

	@Override
	public RoleSt getRoleSt(String name) {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<RoleSt> criteria = builder.createQuery(RoleSt.class);
	        Root<RoleSt> role = criteria.from(RoleSt.class);
			ParameterExpression<String> p = builder.parameter(String.class);

	        criteria.select(role).where(builder.equal(role.get("name"),p));
	        return em.createQuery(criteria).setParameter(p, name).getSingleResult();
	}

}
