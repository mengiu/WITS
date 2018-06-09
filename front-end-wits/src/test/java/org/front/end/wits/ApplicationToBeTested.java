package org.front.end.wits;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class ApplicationToBeTested extends Application {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		final Window main = new Window("Test Window");
		setMainWindow(main);
		Button button = new Button("Push me");
		button.setDebugId("main.button");
		button.addListener(new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				main.addComponent(new Label("Thanks"));
				
			}
		});
	}

}
