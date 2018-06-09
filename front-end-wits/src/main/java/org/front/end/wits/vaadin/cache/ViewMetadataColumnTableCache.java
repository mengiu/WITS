package org.front.end.wits.vaadin.cache;

import java.util.List;

import org.persistence.layer.wits.form.ViewMetadataColumnTable;

public interface ViewMetadataColumnTableCache {
	public List<ViewMetadataColumnTable> loadTableDescriptions(String tableName);
	public void refresh();
	
}
