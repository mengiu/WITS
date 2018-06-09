package org.persistence.layer.wits.dao;

import java.sql.DatabaseMetaData;

import org.persistence.layer.wits.MyTableMetaData;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;

public interface DatabaseMetaDataDAO {
	public DatabaseMetaData getDatabaseMetaData();
	public MyTableMetaData getTableMetaData(String tableName);
	public ViewMetadataColumnTable getTableColumnMetaData(String tableName);
	
}
