package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.MaterialStDAO;
import org.persistence.layer.wits.form.MaterialSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class MaterialStDAOImpl implements MaterialStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addMaterialSt(MaterialSt mat) {
		em.persist(mat);
		return mat.getIdMaterialSt();
	}

	@Override
	public List<MaterialSt> listMaterialSt() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MaterialSt> criteria = builder.createQuery(MaterialSt.class);
		Root<MaterialSt> mat = criteria.from(MaterialSt.class);


		criteria.select(mat);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeMaterialSt(int id) {
		MaterialSt mat = em.find(MaterialSt.class, id);
		if (mat!=null)
		{
			em.remove(mat);
		}

	}

	@Override
	public MaterialSt getMaterialSt(int id) {
		return em.find(MaterialSt.class, id);
	}

	@Override
	public void updateMaterialSt(MaterialSt mat) {
		em.merge(mat);

	}

	@Override
	public List<MaterialSt> listMaterialSt(MaterialSt father,Boolean active) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MaterialSt> criteria = builder.createQuery(MaterialSt.class);
		Root<MaterialSt> mat = criteria.from(MaterialSt.class);
		ParameterExpression<MaterialSt> pFather = builder.parameter(MaterialSt.class);
		Predicate p1 = null;
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		Predicate p2 = builder.equal(mat.get("active"), pActive);
		if (father!=null)
			p1 = builder.equal(mat.get("materialSt"), pFather);
		else	
			p1 = builder.isNull(mat.get("materialSt"));
		criteria.select(mat).where(builder.and(p1,p2)).orderBy(builder.asc(mat.get("nameMaterialSt")));
		if (father!=null)
			return em.createQuery(criteria)
					.setParameter(pFather, father)
					.setParameter(pActive, active)
					.getResultList();
		else
			return em.createQuery(criteria)
					.setParameter(pActive, active)
					.getResultList();
	}

	@Override
	public List<MaterialSt> listMaterialSt(Boolean active) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MaterialSt> criteria = builder.createQuery(MaterialSt.class);
		Root<MaterialSt> mat = criteria.from(MaterialSt.class);
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		Predicate p1 = null;
		if (active!=null)
		{
			p1 = builder.equal(mat.get("active"), pActive);
			criteria.select(mat).where(builder.and(p1)).orderBy(builder.asc(mat.get("nameMaterialSt")));
			return em.createQuery(criteria)
					.setParameter(pActive, active)
					.getResultList();
		}
		else
		{
			criteria.select(mat).orderBy(builder.asc(mat.get("nameMaterialSt")));
			return em.createQuery(criteria).getResultList();
		}
	}

}
