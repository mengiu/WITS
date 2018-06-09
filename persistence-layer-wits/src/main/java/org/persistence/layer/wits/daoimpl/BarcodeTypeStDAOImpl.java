package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.BarcodeTypeStDAO;
import org.persistence.layer.wits.form.BarcodeTypeSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class BarcodeTypeStDAOImpl implements BarcodeTypeStDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addBarcodeTypeSt(BarcodeTypeSt barcode) {
	       em.persist(barcode);
	       return barcode.getIdBarcodeTypeSt();
	}

	@Override
	public List<BarcodeTypeSt> listBarcodeTypeSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<BarcodeTypeSt> criteria = builder.createQuery(BarcodeTypeSt.class);
	        Root<BarcodeTypeSt> barcode = criteria.from(BarcodeTypeSt.class);

	        /*
	         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
	         * feature in JPA 2.0 criteria.select(RcContatto).orderBy(cb.asc(RcContatto.get(RcContatto_.name)));
	         */

	        criteria.select(barcode);
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeBarcodeTypeSt(int id) {
		BarcodeTypeSt bt = em.find(BarcodeTypeSt.class, id);
    	if (bt!=null)
    	{
    	 em.remove(bt);
    	}

	}

	@Override
	public BarcodeTypeSt getBarcodeTypeSt(int id) {
	      return em.find(BarcodeTypeSt.class, id);
	}

	@Override
	public void updateBarcodeTypeSt(BarcodeTypeSt barcode) {
		em.merge(barcode);

	}

}
