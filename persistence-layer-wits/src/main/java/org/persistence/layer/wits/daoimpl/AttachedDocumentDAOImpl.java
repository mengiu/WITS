package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.AttachedDocumentDAO;
import org.persistence.layer.wits.form.AttachedDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class AttachedDocumentDAOImpl implements AttachedDocumentDAO {
	protected static Logger logger = LoggerFactory.getLogger(AttachedDocumentDAOImpl.class);
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public int addAttachedDocument(AttachedDocument attachedDocument) {
		em.persist(attachedDocument);
		return attachedDocument.getIdAttachedDocument();
	}

	@Override
	public List<AttachedDocument> listAttachedDocument() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AttachedDocument> criteria = builder.createQuery(AttachedDocument.class);
		Root<AttachedDocument> attachedDocument = criteria.from(AttachedDocument.class);
		criteria.select(attachedDocument);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeAttachedDocument(int id) {
		AttachedDocument ad = em.find(AttachedDocument.class, id);
		if (ad!=null)
		{
			em.remove(ad);
		}

	}

	@Override
	public AttachedDocument getAttachedDocument(int id) {
		return em.find(AttachedDocument.class, id);
	}

	@Override
	public void updateAttachedDocument(AttachedDocument attachedDocument) {
		em.merge(attachedDocument);
	}


}
