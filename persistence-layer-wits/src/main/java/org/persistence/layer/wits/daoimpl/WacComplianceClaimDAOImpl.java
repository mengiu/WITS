package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.WacComplianceClaimDAO;
import org.persistence.layer.wits.form.WacComplianceClaim;
import org.persistence.layer.wits.form.WamatObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class WacComplianceClaimDAOImpl implements WacComplianceClaimDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;
	protected static Logger logger = LoggerFactory.getLogger(WacComplianceClaimDAOImpl.class);

	@Override
	public int addWacComplianceClaim(WacComplianceClaim wcc) {
	       em.persist(wcc);
	       return wcc.getIdWacComplianceClaim();
	}

	@Override
	public List<WacComplianceClaim> listWacComplianceClaim() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<WacComplianceClaim> criteria = builder.createQuery(WacComplianceClaim.class);
	        Root<WacComplianceClaim> wcc = criteria.from(WacComplianceClaim.class);


	        criteria.select(wcc).orderBy(builder.asc(wcc.get("idWacComplianceClaim")));
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<WacComplianceClaim> listWacComplianceClaim(WamatObject wamatObject) {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<WacComplianceClaim> criteria = builder.createQuery(WacComplianceClaim.class);
	        Root<WacComplianceClaim> wcc = criteria.from(WacComplianceClaim.class);
			ParameterExpression<WamatObject> pWamatObject = builder.parameter(WamatObject.class);
			Predicate p1 = builder.equal(wcc.get("wamatObject"), pWamatObject);


	        criteria.select(wcc).where(builder.and(p1)).orderBy(builder.asc(wcc.get("idWacComplianceClaim")));
	        try {
				return em.createQuery(criteria)
						.setParameter(pWamatObject, wamatObject)
						.getResultList();
			} catch (Exception e) {
	   			logger.debug("listWacComplianceClaim(WamatObject wamatObject : " + wamatObject.getIdWamatObject()
						+ " ) : " + e.getMessage());
				return null;
			}
	}

	@Override
	public void removeWacComplianceClaim(int id) {
		WacComplianceClaim st = em.find(WacComplianceClaim.class, id);
    	if (st!=null)
    	{
    	 em.remove(st);
    	}

	}

	@Override
	public WacComplianceClaim getWacComplianceClaim(int id) {
	      return em.find(WacComplianceClaim.class, id);
	}

	@Override
	public void updateWacComplianceClaim(WacComplianceClaim wcc) {
		em.merge(wcc);

	}

}
