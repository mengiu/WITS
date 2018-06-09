package org.front.end.wits;

import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class WamatObjectsTest extends Application {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeUndefined();
		Window mainWindow = new Window("Tabletest Application");
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("foo", String.class, null);
		container.addContainerProperty("bar", String.class, null);
		container.addContainerProperty("baz", String.class, null);
		for (int i = 0; i < 100; i++) {
		Item item = container.addItem(i);
		item.getItemProperty("foo").setValue("foo " + i);
		item.getItemProperty("bar").setValue("bar");
		item.getItemProperty("baz").setValue("baz");
		}
		PagedTable table = new PagedTable("footable");
		table.setContainerDataSource(container);
		table.setPageLength(15);
		mainWindow.addComponent(table);
		mainWindow.addComponent(table.createControls());
		setMainWindow(mainWindow);
		
	}

}
