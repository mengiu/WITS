package org.persistence.layer.wits.daoimpl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.EntityManager;
//import javax.persistence.ParameterMode;
//import javax.persistence.StoredProcedureQuery;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import org.persistence.layer.wits.MyTableMetaData;
import org.persistence.layer.wits.dao.DatabaseMetaDataDAO;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;
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
public class DatabaseMetaDataDAOImpl implements DatabaseMetaDataDAO {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;
	private static Logger logger = LoggerFactory.getLogger(DatabaseMetaDataDAOImpl.class);

	@Override
	public DatabaseMetaData getDatabaseMetaData() {
		Map<String, Object> emfProperties = em.getProperties();

		//String driverClass = (String)emfProperties.get(PROPERTY);
		//-- For PostgreSQL, it will have value "org.postgresql.Driver"

		//if(driverClass.lastIndexOf("postgresql") != -1)
		//    postGreSQL_DB = true;
		Connection connection = em.unwrap(Connection.class);  
		try {
			DatabaseMetaData metaData = connection.getMetaData();
			return metaData;
		} catch (SQLException e) {
		}
		return null;
	}

	@Override
	public MyTableMetaData getTableMetaData(String tableName) {
        Metamodel metamodel = em.getMetamodel();

         /*Set<SingularAttribute> singularAttributes = entity.getSingularAttributes();
        for (SingularAttribute singularAttribute : singularAttributes) {
            if (singularAttribute.isId()){
                String idProperty=singularAttribute.getName();
                singularAttribute.
                break;
            }
        }*/
        
        /*for (Field field : entity.getClass().getDeclaredFields()) {
        	   Column column = field.getAnnotation(Column.class);
        	   if (column != null) {
					System.out.print(column.name());
					column.columnDefinition();
        	   }
        	}*/        
		MyTableMetaData result = new MyTableMetaData();
		org.hibernate.engine.spi.SessionImplementor sessionImp = 
				(org.hibernate.engine.spi.SessionImplementor) em.getDelegate();		
		//Connection connection = em.unwrap(Connection.class);  
		try {
			DatabaseMetaData metaData = sessionImp.connection().getMetaData();
			/*Connection conn = null;
			try {
				conn = getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			DatabaseMetaData mtdt = conn.getMetaData();
			ResultSet rs = mtdt.getTables(conn.getCatalog(), "WITS", "%", null);

			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
			for (int i = 1; i <= numCols; i++) {
				if (i > 1)
					System.out.print(", ");
				System.out.print(rsmd.getColumnLabel(i));
			}
			System.out.println("");
			while (rs.next()) {
				for (int i = 1; i <= numCols; i++) {
					if (i > 1)
						System.out.print(", ");
					System.out.print(rs.getString(i));
				}
				System.out.println("");
			}
			   conn.close();
			*/
			try {
				result.setTableName(tableName);


		        //ResultSet resultSet =  metaData.getColumns(null, "WITS", tableName , "%");
		        ResultSet resultSet = metaData.getColumns(null, null, tableName, null);
				while(resultSet.next()) {
					String name = resultSet.getString("COLUMN_NAME").toUpperCase();
					String type = resultSet.getString("TYPE_NAME").toUpperCase();
					String remark = "";
					if (resultSet.getString("REMARKS")!=null)
					 remark = resultSet.getString("REMARKS"); 
					result.addColumnMetaData(name, type, remark);
				}

			} catch (SQLException e) {
				logger.error("Could not retrieve database metadata: " + e.getMessage());
			}

			if(result.getColumnNames().size() == 0) {
				// According to API, when a table doesn't exist, null should be returned
				result = null;
			}
			return result;
		} catch (SQLException e) {
			logger.error("Could not retrieve database metadata: " + e.getMessage());
		}
		return null;

	}
	private static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.xa.client.OracleXADataSource");
		String url = "jdbc:oracle:thin:@dbdev.jrc.org:1521:appl_duc";

		return DriverManager.getConnection(url, "WITS", "d2IpZDTj");
	}
	public ViewMetadataColumnTable getTableColumnMetaData(String tableName) {
		//StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("WITS.VIEW_COLUMN_METADATA.SET_VALUES");
		// set parameters
	    //storedProcedure.registerStoredProcedureParameter("TABLENAME", String.class, ParameterMode.IN);
	    //storedProcedure.registerStoredProcedureParameter("tax", Double.class, ParameterMode.OUT);
	    //storedProcedure.setParameter("TABLENAME", tableName);
	    // execute SP
	    //storedProcedure.execute();
		//StoredProcedureQuery query = em.createNamedStoredProcedureQuery("WITS.VIEW_COLUMN_METADATA.SET_VALUES");
		//query.setParameter("TABLENAME", "WAMAT_OBJECT");
		//query.execute();		
		//List<ViewMetadataColumnTable> result = (List<ViewMetadataColumnTable>)query.getOutputParameterValue("CUR_COLUMN_METADATA");
		// get result
	    //Double tax = (Double)storedProcedure.getOutputParameterValue("tax");
	    //System.out.println("Tax is: " + tax);
		return null;
	}
	
}
