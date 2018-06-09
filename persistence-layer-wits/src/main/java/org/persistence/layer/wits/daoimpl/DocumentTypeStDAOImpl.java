package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.DocumentTypeStDAO;
import org.persistence.layer.wits.form.DocumentTypeSt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class DocumentTypeStDAOImpl implements DocumentTypeStDAO {
	protected static Logger logger = LoggerFactory.getLogger(DocumentTypeStDAOImpl.class);
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addDocumentTypeSt(DocumentTypeSt documentTypeSt) {
	       em.persist(documentTypeSt);
	       return documentTypeSt.getIdDocumentTypeSt();
	}

	@Override
	public List<DocumentTypeSt> listDocumentTypeSt() {
	       CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<DocumentTypeSt> criteria = builder.createQuery(DocumentTypeSt.class);
	        Root<DocumentTypeSt> dst = criteria.from(DocumentTypeSt.class);


	        criteria.select(dst).orderBy(builder.asc(dst.get("nameDocumentTypeSt")));
	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeDocumentTypeSt(int id) {
		DocumentTypeSt documentTypeSt = em.find(DocumentTypeSt.class, id);
    	if (documentTypeSt!=null)
    	{
    	 em.remove(documentTypeSt);
    	}

	}

	@Override
	public DocumentTypeSt getDocumentTypeSt(int id) {
	      return em.find(DocumentTypeSt.class, id);
	}

	@Override
	public void updateDocumentTypeSt(DocumentTypeSt documentTypeSt) {
		em.merge(documentTypeSt);

	}

	@Override
	public DocumentTypeSt getDocumentTypeSt(String docType) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<DocumentTypeSt> criteria = builder.createQuery(DocumentTypeSt.class);
		Root<DocumentTypeSt> cu = criteria.from(DocumentTypeSt.class);
		ParameterExpression<String> p = builder.parameter(String.class);

		criteria.select(cu).where(builder.equal(cu.get("nameDocumentTypeSt"),p));
		try {
			return em.createQuery(criteria).setParameter(p, docType.toLowerCase() ).getSingleResult();
		} catch (Exception e) {
			logger.debug("getDocumentTypeSt(docType : " + docType + " ) : " + e.getMessage());
			return null;
		}
	}

}
