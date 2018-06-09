package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.ItemDAO;
import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableItems;
import org.persistence.layer.wits.form.GenerationProcessInstance;
import org.persistence.layer.wits.form.Item;
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
public class ItemDAOImpl implements ItemDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;
    private Logger logger = LoggerFactory.getLogger(ItemDAOImpl.class);

	@Override
	public int addItem(Item item) {
		logger.debug(item.toString());
		em.persist(item);
		return item.getIdItem();
	}

	@Override
	public List<Item> listItem() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
		Root<Item> item = criteria.from(Item.class);

		criteria.select(item).orderBy(builder.asc(item.get("idItem")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeItem(int id) {
		Item item = em.find(Item.class, id);
		if (item!=null)
		{
			logger.debug(item.toString());
			em.remove(item);
		}

	}

	@Override
	public Item getItem(int id) {
		return em.find(Item.class, id);
	}

	@Override
	public void updateItem(Item item) {
		logger.debug(item.toString());
		em.merge(item);

	}
	@Override
    public Short getMaxItemForWamatObject(WamatObject wamatObject)
    {
		/*CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
    	Root<Item> item = criteria.from(Item.class);
		ParameterExpression<WamatObject> p = builder.parameter(WamatObject.class);
		Expression<Short> field = item.get("itemNumber");
		Predicate p1 = builder.equal(item.get("wamatObject"),p);
		Predicate p2 = builder.equal(field, builder.greatest(field));
		criteria.select(item).where(builder.and(p1,p2));
    	try {
			return em.createQuery(criteria).setParameter(p, wamatObject).getSingleResult();
		} catch (Exception e) {
			logger.debug("getMaxItemForWamatObject(WamatObject wamatObject : " + wamatObject.getIdWamatObject()
						+ " ) : " + e.getMessage());
		}
    	return null;*/
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Short> criteria = builder.createQuery( Short.class );
    	Root<Item> itemRoot = criteria.from( Item.class );
		ParameterExpression<WamatObject> p = builder.parameter(WamatObject.class);
		Expression<Short> field = itemRoot.get("itemNumber");
      	try {
        	  criteria.select( builder.greatest(field) );
        	  criteria.where(builder.equal(itemRoot.get("wamatObject"),p));
        	  return em.createQuery( criteria ).setParameter(p, wamatObject).getSingleResult();
    		} catch (Exception e) {
    			logger.debug("getMaxItemForWamatObject(WamatObject wamatObject : " + wamatObject.getIdWamatObject()
    						+ " ) : " + e.getMessage());
    		}
      	return 0;
    }
	@Override
	public List<Item> listItem(WamatObject wamatObject , OrdersFieldsTableItems ordersFieldsTableItems , 
			Boolean ascending ) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
		Root<Item> item = criteria.from(Item.class);
		ParameterExpression<WamatObject> p = builder.parameter(WamatObject.class);
		if (ordersFieldsTableItems!=null)
		{
			switch (ordersFieldsTableItems)
			{
			case ID :
			{
				if (ascending)
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.asc(item.get("itemNumber")));
				else
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.desc(item.get("itemNumber")));
				break;
			}
			case DESCRIPTIONITEM :
			{
				if (ascending)
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.asc(item.get("descriptionItem")));
				else
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.desc(item.get("descriptionItem")));
				break;
			}
			case DATE :
			{
				if (ascending)
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.asc(item.get("itemDate")));
				else
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.desc(item.get("itemDate")));
				break;
			}
			case MATERIAL :
			{
				if (ascending)
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.asc(item.get("materialSt").get("nameMaterialSt")));
				else
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.desc(item.get("materialSt").get("nameMaterialSt")));
				break;
			}
			case QUANTITY :
			{
				if (ascending)
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.asc(item.get("quantity")));
				else
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.desc(item.get("quantity")));
				break;
			}
			case SAMPLE :
			{
				if (ascending)
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.asc(item.get("sampleReference")));
				else
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.desc(item.get("sampleReference")));
				break;
			}
			case INSTANCEPROCESS :
			{
				if (ascending)
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.asc(item.get("generationProcessInstance").get("nameGpInstance")));
				else
					criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.desc(item.get("generationProcessInstance").get("nameGpInstance")));
				break;
			}
			default :
			{
				criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.asc(item.get("idItem")));
				break;
			}
			}
		}
		else
			criteria.select(item).where(builder.equal(item.get("wamatObject"),p)).orderBy(builder.asc(item.get("idItem")));

		return em.createQuery(criteria).setParameter(p, wamatObject ).getResultList();
	}
	@Override
	public List<Item> listItem(GenerationProcessInstance generationProcessInstance) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
		Root<Item> item = criteria.from(Item.class);
		ParameterExpression<GenerationProcessInstance> pGenerationProcessInstance = builder.parameter(GenerationProcessInstance.class);
		Predicate p1 = builder.equal(item.get("generationProcessInstance"),pGenerationProcessInstance);

		criteria.select(item).where(builder.and(p1)).orderBy(builder.asc(item.get("idItem")));
		return em.createQuery(criteria)
				.setParameter(pGenerationProcessInstance, generationProcessInstance )
				.getResultList();
	}

}
