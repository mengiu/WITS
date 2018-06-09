package org.persistence.layer.wits.dao;

import java.util.List;




import org.persistence.layer.wits.form.ViewMetadataColumnTable;

public interface ViewMetadataColumnTableDAO {
	public String addViewMetadataColumnTable(ViewMetadataColumnTable viewMetadataColumnTable );
	public List<ViewMetadataColumnTable> listViewMetadataColumnTable(String tableName);
	public void removeViewMetadataColumnTable(String id);
	public ViewMetadataColumnTable getViewMetadataColumnTable(String id);
	public void updateViewMetadataColumnTable(ViewMetadataColumnTable viewMetadataColumnTable);
	public List<ViewMetadataColumnTable> getInfoMetadataColumnTable(String tableName);
	public void executeProcedureSET_VALUES(String tableName);
	public Object executeFunctionGET_TABLENAME();
}
