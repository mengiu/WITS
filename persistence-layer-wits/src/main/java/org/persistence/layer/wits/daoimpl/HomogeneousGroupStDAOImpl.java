package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.HomogeneousGroupStDAO;
import org.persistence.layer.wits.form.HomogeneousGroupSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class HomogeneousGroupStDAOImpl implements HomogeneousGroupStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addHomogeneousGroupSt(HomogeneousGroupSt hgSt) {
		em.persist(hgSt);
		return hgSt.getIdHomogeneousGroupSt();
	}

	@Override
	public List<HomogeneousGroupSt> listHomogeneousGroupSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<HomogeneousGroupSt> criteria = builder.createQuery(HomogeneousGroupSt.class);
	        Root<HomogeneousGroupSt> hgSt = criteria.from(HomogeneousGroupSt.class);

	        criteria.select(hgSt).orderBy(builder.asc(hgSt.get("nameHomogeneousGroupSt")));
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeHomogeneousGroupSt(int id) {
		HomogeneousGroupSt hgSt = em.find(HomogeneousGroupSt.class, id);
    	if (hgSt!=null)
    	{
    	 em.remove(hgSt);
    	}

	}

	@Override
	public HomogeneousGroupSt getHomogeneousGroupSt(int id) {
		return em.find(HomogeneousGroupSt.class, id);
	}

	@Override
	public void updateHomogeneousGroupSt(HomogeneousGroupSt hgSt) {
		em.merge(hgSt);

	}

}
