package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.GraficFormatStDAO;
import org.persistence.layer.wits.form.GraficFormatSt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class GraficFormatStDAOImpl implements GraficFormatStDAO {
	protected static Logger logger = LoggerFactory.getLogger(GraficFormatStDAOImpl.class);
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addGraficFormatSt(GraficFormatSt gfst) {
		em.persist(gfst);
		return gfst.getIdGraficFormatSt();
	}

	@Override
	public List<GraficFormatSt> listGraficFormatSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<GraficFormatSt> criteria = builder.createQuery(GraficFormatSt.class);
	        Root<GraficFormatSt> gfst = criteria.from(GraficFormatSt.class);

	        criteria.select(gfst).orderBy(builder.asc(gfst.get("idGraficFormatSt")));
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeGraficFormatSt(int id) {
		GraficFormatSt gfst = em.find(GraficFormatSt.class, id);
    	if (gfst!=null)
    	{
    	 em.remove(gfst);
    	}

	}

	@Override
	public GraficFormatSt getGraficFormatSt(int id) {
		return em.find(GraficFormatSt.class, id);
	}

	@Override
	public void updateGraficFormatSt(GraficFormatSt gfst) {
		em.merge(gfst);
	}
	@Override
	public GraficFormatSt getGraficFormatSt(String format) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<GraficFormatSt> criteria = builder.createQuery(GraficFormatSt.class);
		Root<GraficFormatSt> cu = criteria.from(GraficFormatSt.class);
		ParameterExpression<String> p = builder.parameter(String.class);

		criteria.select(cu).where(builder.equal(cu.get("nameGraficFormatSt"),p));
		try {
			return em.createQuery(criteria).setParameter(p, format.toLowerCase() ).getSingleResult();
		} catch (Exception e) {
			logger.debug("getGraficFormatSt(format : " + format + " ) : " + e.getMessage());
			return null;
		}
	}

	@Override
	public GraficFormatSt getGraficFormatStForExtention(String extention) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<GraficFormatSt> criteria = builder.createQuery(GraficFormatSt.class);
		Root<GraficFormatSt> cu = criteria.from(GraficFormatSt.class);
		ParameterExpression<String> p = builder.parameter(String.class);

		criteria.select(cu).where(builder.equal(cu.get("fileExtention"),p));
		try {
			return em.createQuery(criteria).setParameter(p, extention.toUpperCase() ).getSingleResult();
		} catch (Exception e) {
			logger.debug("getGraficFormatStForExtention(extention : " + extention + " ) : " + e.getMessage());
			return null;
		}
	}

}
