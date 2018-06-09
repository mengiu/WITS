package org.front.end.wits;

import com.vaadin.Application;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PrintBarcodeGridFieldApplicationTest extends Application {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeUndefined();
		Window mainWindow = new Window("PrintBarcodeGridField Application");
		PrintBarcodeGridFieldTest printBarcodeGridFieldTest = new PrintBarcodeGridFieldTest();
		mainWindow.addComponent(printBarcodeGridFieldTest);
		setMainWindow(mainWindow);

	}

}
