package org.services.layer.wits.services;

import org.persistence.layer.wits.MyTableMetaData;

public interface DatabaseMetaDataService {
	public MyTableMetaData getTableMetaData(String tableName);

}
