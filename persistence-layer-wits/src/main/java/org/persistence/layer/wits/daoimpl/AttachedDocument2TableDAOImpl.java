package org.persistence.layer.wits.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.AttachedDocument2TableDAO;
import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableAttached;
import org.persistence.layer.wits.form.AttachedDocument2Table;
import org.persistence.layer.wits.form.AttachedDocument2TableId;
import org.persistence.layer.wits.form.utility.AttachmentWits;
import org.persistence.layer.wits.form.utility.AttachmentWitsImpl;
import org.persistence.layer.wits.util.AttachToGenericTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class AttachedDocument2TableDAOImpl implements AttachedDocument2TableDAO {
	protected static Logger logger = LoggerFactory.getLogger(AttachedDocument2TableDAOImpl.class);
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public AttachedDocument2TableId addAttachedDocument2Table(AttachedDocument2Table ad2t) {
		em.persist(ad2t);
		return ad2t.getId();
	}

	@Override
	public List<AttachedDocument2Table> listAttachedDocument2Table() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AttachedDocument2Table> criteria = builder.createQuery(AttachedDocument2Table.class);
		Root<AttachedDocument2Table> attachedDocument2Table = criteria.from(AttachedDocument2Table.class);
		criteria.select(attachedDocument2Table);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeAttachedDocument2Table(AttachedDocument2TableId id) {
		AttachedDocument2Table ad2t = em.find(AttachedDocument2Table.class, id);
		if (ad2t!=null)
		{
			em.remove(ad2t);
		}
	}

	@Override
	public AttachedDocument2Table getAttachedDocument2Table(
			AttachedDocument2TableId id) {
		return em.find(AttachedDocument2Table.class, id);
	}

	@Override
	public void updateAttachedDocument2Table(AttachedDocument2Table ad2t) {
		em.merge(ad2t);
	}

	@Override
	public List<AttachmentWits> listAttachedDocument2Table(
			String tableName, Integer idTableRelated, Boolean active ) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AttachedDocument2Table> criteria = builder.createQuery(AttachedDocument2Table.class);
		Root<AttachedDocument2Table> attachedDocument2Table = criteria.from(AttachedDocument2Table.class);
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		ParameterExpression<String> ptableName = builder.parameter(String.class);
		ParameterExpression<Integer> pidTableRelated = builder.parameter(Integer.class);
		Predicate predicate1 =  builder.equal(attachedDocument2Table.get("id").get("fkTableName"), ptableName);
		Predicate predicate2 =  builder.equal(attachedDocument2Table.get("id").get("fkTableId"), pidTableRelated);
		List<AttachmentWits> listResult = new ArrayList<AttachmentWits>();
		try {
			List<AttachedDocument2Table> listAttachedDocument2Table = null;
			if (active!=null)
			{
				Predicate predicate3 =  builder.equal(attachedDocument2Table.get("active"), pActive);
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2,predicate3)).distinct(true);
				listAttachedDocument2Table = em.createQuery(criteria).
						setParameter(ptableName, tableName).
						setParameter(pidTableRelated, idTableRelated).
						setParameter(pActive, active).
						getResultList();

			}
			else
			{
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true);
				listAttachedDocument2Table = em.createQuery(criteria).
						setParameter(ptableName, tableName).
						setParameter(pidTableRelated, idTableRelated).
						getResultList();
			}
			if (listAttachedDocument2Table!=null)
			{
				for (AttachedDocument2Table item : listAttachedDocument2Table)
				{
					AttachmentWitsImpl attachmentWits = new AttachmentWitsImpl(item.getAttachedDocument());
					listResult.add(attachmentWits);

				}
			}

		} catch (Exception e) {
			logger.debug("listAttachedDocument2Table(String tableName : " + tableName + 
					" , Integer idTableRelated : " + idTableRelated.toString() +
					" , Boolean active : ", (active!=null) ? active.toString() : "null" + " ) : " + e.getMessage());
		}
		return listResult;
	}


	@Override
	public List<AttachmentWits> listAttachedDocument2Table(
			AttachToGenericTable attachToGenericTable, Boolean active ) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AttachedDocument2Table> criteria = builder.createQuery(AttachedDocument2Table.class);
		Root<AttachedDocument2Table> attachedDocument2Table = criteria.from(AttachedDocument2Table.class);
		ParameterExpression<String> ptableName = builder.parameter(String.class);
		ParameterExpression<Integer> pidTableRelated = builder.parameter(Integer.class);
		ParameterExpression<String> pNameDocument = builder.parameter(String.class);
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		Predicate predicate1 =  builder.equal(attachedDocument2Table.get("id").get("fkTableName"), ptableName);
		Predicate predicate2 =  builder.equal(attachedDocument2Table.get("id").get("fkTableId"), pidTableRelated);
		Predicate predicate3 =  builder.equal(attachedDocument2Table.get("nameAttachedDocument") , pNameDocument);
		if (attachToGenericTable.getNameAttachedDocument()!=null)
		{
			if (active!=null)
			{
				Predicate predicate4 =  builder.equal(attachedDocument2Table.get("active"), pActive);
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2,predicate3,predicate4)).distinct(true);
			}
			else
			{
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2,predicate3)).distinct(true);

			}
		}
		else
		{
			if (active!=null)
			{
				Predicate predicate4 =  builder.equal(attachedDocument2Table.get("active"), pActive);
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2,predicate4)).distinct(true);
			}
			else
			{
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true);
			}

		}
		List<AttachmentWits> listResult = new ArrayList<AttachmentWits>();
		try {
			List<AttachedDocument2Table> listAttachedDocument2Table = null;
			if (attachToGenericTable.getNameAttachedDocument()!=null)
			{
				if (active!=null)
				{
					listAttachedDocument2Table = em.createQuery(criteria).
							setParameter(ptableName, attachToGenericTable.getTableName()).
							setParameter(pidTableRelated, attachToGenericTable.getIdTableRelated()).
							setParameter(pNameDocument, attachToGenericTable.getNameAttachedDocument()).
							setParameter(pActive, active).
							getResultList();
				}
				else
				{
					listAttachedDocument2Table = em.createQuery(criteria).
							setParameter(ptableName, attachToGenericTable.getTableName()).
							setParameter(pidTableRelated, attachToGenericTable.getIdTableRelated()).
							setParameter(pNameDocument, attachToGenericTable.getNameAttachedDocument()).
							getResultList();

				}
			}
			else
			{
				if (active!=null)
				{
					listAttachedDocument2Table = em.createQuery(criteria).
							setParameter(ptableName, attachToGenericTable.getTableName()).
							setParameter(pidTableRelated, attachToGenericTable.getIdTableRelated()).
							setParameter(pActive, active).
							getResultList();
				}
				else
				{
					listAttachedDocument2Table = em.createQuery(criteria).
							setParameter(ptableName, attachToGenericTable.getTableName()).
							setParameter(pidTableRelated, attachToGenericTable.getIdTableRelated()).
							getResultList();

				}

			}
			if (listAttachedDocument2Table!=null)
			{
				for (AttachedDocument2Table item : listAttachedDocument2Table)
				{
					AttachmentWitsImpl attachmentWits = new AttachmentWitsImpl(item.getAttachedDocument());
					listResult.add(attachmentWits);

				}
			}

		} catch (Exception e) {
			logger.debug("listAttachedDocument2Table(String tableName : " + attachToGenericTable.getIdTableRelated() + 
					" , Integer idTableRelated : " + attachToGenericTable.getIdTableRelated().toString() +
					" , String nameAttached : " + attachToGenericTable.getNameAttachedDocument() +
					" , Boolean active : ", (active!=null) ? active.toString() : "null" + " ) : " + e.getMessage());
		}
		return listResult;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<AttachedDocument2Table> listAttachedDocument2Table(String tableName,
			List<Integer> listIdTableRelated, String documentType, Boolean active ) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AttachedDocument2Table> criteria = builder.createQuery(AttachedDocument2Table.class);
		Root<AttachedDocument2Table> attachedDocument2Table = criteria.from(AttachedDocument2Table.class);
		ParameterExpression<String> ptableName = builder.parameter(String.class);
		ParameterExpression<List> pListIdTableRelated = builder.parameter(List.class);
		ParameterExpression<String> pDocumentType = builder.parameter(String.class);
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		Predicate predicate1 =  builder.equal(attachedDocument2Table.get("id").get("fkTableName"), ptableName);
		Expression<Integer> exp = attachedDocument2Table.get("id").get("fkTableId");
		Predicate predicate2 =  exp.in(pListIdTableRelated );
		Predicate predicate3 =  builder.equal(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt"), pDocumentType);
		if (documentType!=null)
		{
			if (active!=null)
			{
				Predicate predicate4 =  builder.equal(attachedDocument2Table.get("active"), pActive);
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2,predicate3,predicate4)).distinct(true);
			}
			else
			{
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2,predicate3)).distinct(true);

			}
		}
		else
		{
			if (active!=null)
			{
				Predicate predicate4 =  builder.equal(attachedDocument2Table.get("active"), pActive);
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2,predicate4)).distinct(true);
			}
			else
			{
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true);

			}

		}
		try {
			List<AttachedDocument2Table> listAttachedDocument2Table = null;
			if (documentType!=null)
			{
				if (active!=null)
				{
					listAttachedDocument2Table = em.createQuery(criteria).
							setParameter(ptableName, tableName).
							setParameter(pListIdTableRelated, listIdTableRelated).
							setParameter(pDocumentType, documentType).
							setParameter(pActive, active).
							getResultList();
				}
				else
				{
					listAttachedDocument2Table = em.createQuery(criteria).
							setParameter(ptableName, tableName).
							setParameter(pListIdTableRelated, listIdTableRelated).
							setParameter(pDocumentType, documentType).
							getResultList();

				}
			}
			else
			{
				if (active!=null)
				{
					listAttachedDocument2Table = em.createQuery(criteria).
							setParameter(ptableName, tableName).
							setParameter(pListIdTableRelated, listIdTableRelated).
							setParameter(pActive, active).
							getResultList();
				}
				else
				{
					listAttachedDocument2Table = em.createQuery(criteria).
							setParameter(ptableName, tableName).
							setParameter(pListIdTableRelated, listIdTableRelated).
							getResultList();

				}

			}
			if (listAttachedDocument2Table!=null)
			{

				for (int index = 0;index<listAttachedDocument2Table.size();index++)
				{
					if (listAttachedDocument2Table.get(index).getAttachedDocument().getDocumentTypeSt()!=null)
					{
						listAttachedDocument2Table.get(index).getAttachedDocument().getDocumentTypeSt().getNameDocumentTypeSt();
						listAttachedDocument2Table.get(index).getAttachedDocument().getIdAttachedDocument();
					}
				}

			}
			return listAttachedDocument2Table;
		} catch (Exception e) {
			String listInteger = "";
			for ( Integer item : listIdTableRelated ) 
				listInteger += item.toString() + " ";
			logger.debug("listAttachedDocument2Table(String tableName : " + tableName + 
					" , List<Integer> listIdTableRelated : " +  listInteger +
					" , Boolean active : ", (active!=null) ? active.toString() : "null" + " ) : " + e.getMessage());
			return new ArrayList<AttachedDocument2Table>();
		}
	}

	@SuppressWarnings("rawtypes")
	public List<AttachedDocument2Table> listAttachedDocument2Table(List<AttachedDocument2Table> listIdAttachedDocument2Table , 
			String documentType ,  Boolean active, OrdersFieldsTableAttached ordersFieldsTableAttached , 
			Boolean ascending) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AttachedDocument2Table> criteria = builder.createQuery(AttachedDocument2Table.class);
		Root<AttachedDocument2Table> attachedDocument2Table = criteria.from(AttachedDocument2Table.class);
		ParameterExpression<List> plistIdAttachedDocument2Table = builder.parameter(List.class);
		ParameterExpression<String> pDocumentType = builder.parameter(String.class);
		ParameterExpression<Boolean> pActive = builder.parameter(Boolean.class);
		Expression<AttachedDocument2Table> exp = attachedDocument2Table.get("id");
		Predicate predicate1 =  exp.in(plistIdAttachedDocument2Table );
		Predicate predicate2 = null;
		if (ordersFieldsTableAttached!=null)
		{
			switch (ordersFieldsTableAttached)
			{
			case REFER :
			{
				if (ascending)
				{
					if (documentType!=null)
					{
						predicate2 =  builder.equal(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt"), pDocumentType);
						criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true).orderBy(builder.asc(attachedDocument2Table.get("id").get("fkTableName")));
					}
					else
						criteria.select(attachedDocument2Table).where(builder.and(predicate1)).distinct(true).orderBy(builder.asc(attachedDocument2Table.get("id").get("fkTableName")));
				}
				else
				{
					if (documentType!=null)
					{
						predicate2 =  builder.equal(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt"), pDocumentType);
						criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true).orderBy(builder.desc(attachedDocument2Table.get("id").get("fkTableName")));
					}
					else
						criteria.select(attachedDocument2Table).where(builder.and(predicate1)).distinct(true).orderBy(builder.desc(attachedDocument2Table.get("id").get("fkTableName")));
				}
				break;
			}
			case TYPE :
			{
				if (ascending)
				{
					if (documentType!=null)
					{
						predicate2 =  builder.equal(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt"), pDocumentType);
						criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true).orderBy(builder.asc(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt")));
					}
					else
						criteria.select(attachedDocument2Table).where(builder.and(predicate1)).distinct(true).orderBy(builder.asc(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt")));
				}
				else
				{
					if (documentType!=null)
					{
						predicate2 =  builder.equal(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt"), pDocumentType);
						criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true).orderBy(builder.desc(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt")));
					}
					else
						criteria.select(attachedDocument2Table).where(builder.and(predicate1)).distinct(true).orderBy(builder.desc(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt")));
				}
				break;
			}
			case ATTACHED :
			{
				if (ascending)
				{
					if (documentType!=null)
					{
						predicate2 =  builder.equal(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt"), pDocumentType);
						criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true).orderBy(builder.asc(attachedDocument2Table.get("nameAttachedDocument")));
					}
					else
						criteria.select(attachedDocument2Table).where(builder.and(predicate1)).distinct(true).orderBy(builder.asc(attachedDocument2Table.get("nameAttachedDocument")));
				}
				else
				{
					if (documentType!=null)
					{
						predicate2 =  builder.equal(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt"), pDocumentType);
						criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true).orderBy(builder.desc(attachedDocument2Table.get("nameAttachedDocument")));
					}
					else
						criteria.select(attachedDocument2Table).where(builder.and(predicate1)).distinct(true).orderBy(builder.desc(attachedDocument2Table.get("nameAttachedDocument")));
				}
				break;
			}
			default :
			{
				if (documentType!=null)
				{
					predicate2 =  builder.equal(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt"), pDocumentType);
					criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true);
				}
				else
					criteria.select(attachedDocument2Table).where(builder.and(predicate1)).distinct(true);
				break;
			}
			}
		}
		else
		{
			if (documentType!=null)
			{
				predicate2 =  builder.equal(attachedDocument2Table.get("attachedDocument").get("documentTypeSt").get("nameDocumentTypeSt"), pDocumentType);
				criteria.select(attachedDocument2Table).where(builder.and(predicate1,predicate2)).distinct(true);
			}
			else
				criteria.select(attachedDocument2Table).where(builder.and(predicate1)).distinct(true);

		}

		try {
			List<AttachedDocument2Table> listAttachedDocument2Table = null;
			if (documentType!=null)
			{
				listAttachedDocument2Table = em.createQuery(criteria).
						setParameter(plistIdAttachedDocument2Table, listIdAttachedDocument2Table).
						setParameter(pDocumentType, documentType).
						getResultList();
			}
			else
			{
				listAttachedDocument2Table = em.createQuery(criteria).
						setParameter(plistIdAttachedDocument2Table, listIdAttachedDocument2Table).
						getResultList();

			}
			return listAttachedDocument2Table;
		} catch (Exception e) {
			String slistIdAttachedDocument2Table = "";
			for ( AttachedDocument2Table item : listIdAttachedDocument2Table )
			{
				slistIdAttachedDocument2Table += item.getId().getFkTableName() + "-";
				slistIdAttachedDocument2Table += item.getId().getFkAttachedDocument() + "-";
				slistIdAttachedDocument2Table += item.getId().getFkTableId() + "-";
				slistIdAttachedDocument2Table += "||";
			}
			logger.debug("listAttachedDocument2Table(List<AttachedDocument2TableId> listIdAttachedDocument2Table : " + slistIdAttachedDocument2Table + 
					" , String documentType : " +  documentType!=null ? documentType : "null" +
							" , OrdersFieldsTableAttached ordersFieldsTableAttached : " +  ordersFieldsTableAttached!=null ? ordersFieldsTableAttached.getValue() : "null" +
									" , Boolean ascending : " +  ascending!=null ? ascending.toString() : "null" +
											" ) : " + e.getMessage());
			return new ArrayList<AttachedDocument2Table>();
		}
	}


}
