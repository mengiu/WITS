package org.front.end.wits.vaadin.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.persistence.layer.wits.form.AttachedDocument;
import org.persistence.layer.wits.form.AttachedDocument2Table;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;
import org.persistence.layer.wits.form.WacComplianceClaim;
import org.persistence.layer.wits.form.WamatExtendedField;
import org.persistence.layer.wits.form.WamatObject;
import org.services.layer.wits.services.ViewMetadataColumnTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/* 
 * @author Mennea Giuseppe
 */
@Component
public class ViewMetadataColumnTableCacheImpl implements ViewMetadataColumnTableCache {

	protected Map<String, List<ViewMetadataColumnTable>> tableDescriptionsCache = new HashMap<String, List<ViewMetadataColumnTable>>();
	@Autowired
	@Qualifier("viewMetadataColumnTableService")
	ViewMetadataColumnTableService viewMetadataColumnTableService;

	public void refresh() {
		if (tableDescriptionsCache.size()==0)
		{
			List<ViewMetadataColumnTable> listViewMetadataColumnTableWO = viewMetadataColumnTableService.listViewMetadataColumnTable(WamatObject.class.getAnnotation(Table.class).name());
			if (listViewMetadataColumnTableWO!=null)
			{
				tableDescriptionsCache.put(WamatObject.class.getAnnotation(Table.class).name(), listViewMetadataColumnTableWO);
			}
			List<ViewMetadataColumnTable> listViewMetadataColumnTableItem = viewMetadataColumnTableService.listViewMetadataColumnTable(Item.class.getAnnotation(Table.class).name());
			if (listViewMetadataColumnTableItem!=null)
			{
				tableDescriptionsCache.put(Item.class.getAnnotation(Table.class).name(), listViewMetadataColumnTableItem);
			}
			List<ViewMetadataColumnTable> listViewMetadataColumnTableAD = viewMetadataColumnTableService.listViewMetadataColumnTable(AttachedDocument.class.getAnnotation(Table.class).name());
			if (listViewMetadataColumnTableAD!=null)
			{
				tableDescriptionsCache.put(AttachedDocument.class.getAnnotation(Table.class).name(), listViewMetadataColumnTableAD);
			}
			List<ViewMetadataColumnTable> listViewMetadataColumnTableAD2T = viewMetadataColumnTableService.listViewMetadataColumnTable(AttachedDocument2Table.class.getAnnotation(Table.class).name());
			if (listViewMetadataColumnTableAD2T!=null)
			{
				tableDescriptionsCache.put(AttachedDocument2Table.class.getAnnotation(Table.class).name(), listViewMetadataColumnTableAD2T);
			}
			List<ViewMetadataColumnTable> listViewMetadataColumnTableWEF = viewMetadataColumnTableService.listViewMetadataColumnTable(WamatExtendedField.class.getAnnotation(Table.class).name());
			if (listViewMetadataColumnTableWEF!=null)
			{
				tableDescriptionsCache.put(WamatExtendedField.class.getAnnotation(Table.class).name(), listViewMetadataColumnTableWEF);
			}
			List<ViewMetadataColumnTable> listViewMetadataColumnTableWCC = viewMetadataColumnTableService.listViewMetadataColumnTable(WacComplianceClaim.class.getAnnotation(Table.class).name());
			if (listViewMetadataColumnTableWCC!=null)
			{
				tableDescriptionsCache.put(WacComplianceClaim.class.getAnnotation(Table.class).name(), listViewMetadataColumnTableWCC);
			}

		}
	}

	@Override
	public List<ViewMetadataColumnTable> loadTableDescriptions(String tableName) {
		if (tableDescriptionsCache.size()>0)
			return tableDescriptionsCache.get(tableName);
		else
			return null;
	}

}
