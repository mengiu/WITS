package org.persistence.layer.wits.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
//import javax.persistence.ParameterMode;
//import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.persistence.layer.wits.dao.ViewMetadataColumnTableDAO;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class ViewMetadataColumnTableDAOImpl implements
ViewMetadataColumnTableDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

	@Override
	public String addViewMetadataColumnTable(
			ViewMetadataColumnTable viewMetadataColumnTable) {
		em.persist(viewMetadataColumnTable);
		return viewMetadataColumnTable.getColumnName();
	}

	@Override
	public List<ViewMetadataColumnTable> listViewMetadataColumnTable(String tableName) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ViewMetadataColumnTable> criteria = builder.createQuery(ViewMetadataColumnTable.class);
		Root<ViewMetadataColumnTable> vmct = criteria.from(ViewMetadataColumnTable.class);


		criteria.select(vmct).orderBy(builder.asc(vmct.get("columnName")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void executeProcedureSET_VALUES(String tableName)
    {
		Query query = em.createNativeQuery("{call WITS.VIEW_COLUMN_METADATA.SET_VALUES(?)}")           
                .setParameter(1, tableName);
    	
    }
	
	@Override
	public Object executeFunctionGET_TABLENAME()
	{
	 Query query = em.createNativeQuery("select WITS.VIEW_COLUMN_METADATA.GET_TABLENAME() from dual");
	 return query.getSingleResult();
	}
	
	@Override
	public void removeViewMetadataColumnTable(String id) {
		ViewMetadataColumnTable vmct = em.find(ViewMetadataColumnTable.class, id);
		if (vmct!=null)
		{
			em.remove(vmct);
		}
	}

	@Override
	public ViewMetadataColumnTable getViewMetadataColumnTable(
			String id) {
		return em.find(ViewMetadataColumnTable.class, id);
	}

	@Override
	public void updateViewMetadataColumnTable(
			ViewMetadataColumnTable viewMetadataColumnTable) {
		em.merge(viewMetadataColumnTable);

	}
	
	@Override
    public List<ViewMetadataColumnTable> getInfoMetadataColumnTable(String tableName) {
		String queryString = "select t1.column_name," + 
				"substr(data_type||'" +
				"('" + 
				"||data_length||'" +
				")'" + 
				", 0, 20) as data_type," +
				"decode(nullable,'" +
				"N'" +
				",'" +
				"NOT NULL'"+ 
				", '"+
				"'" +
				") as null_status, comments " +
				"from all_tab_columns t1, all_col_comments t2 " +
				"where t1.table_name = t2.table_name " +
				"and t1.column_name = t2.column_name " +
				"and t1.table_name = ? " + 
				"ORDER BY COLUMN_ID"; 
    	Query query = em.createNativeQuery(
    			queryString , ViewMetadataColumnTable.class );
        return query.setParameter(1, tableName).getResultList();		
        
	}

}
