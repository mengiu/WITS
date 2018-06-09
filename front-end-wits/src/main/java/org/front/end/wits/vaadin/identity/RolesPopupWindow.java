package org.front.end.wits.vaadin.identity;


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;

import org.front.end.wits.vaadin.I18nManager;
import org.front.end.wits.vaadin.Messages;
import org.front.end.wits.vaadin.ViewManager;
import org.front.end.wits.vaadin.WitsApp;
import org.front.end.wits.vaadin.cache.UserCache;
import org.front.end.wits.vaadin.ui.custom.PopupWindow;
import org.front.end.wits.vaadin.ui.mainlayout.ExplorerLayout;
import org.persistence.layer.wits.dao.to.GenericRole;
import org.persistence.layer.wits.form.WitsUser2RoleGroupId;
import org.persistence.layer.wits.identity.WitsAuthentication;
import org.services.layer.wits.services.FacadeIdentityService;
import org.services.layer.wits.services.WitsUser2RoleGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;

public class RolesPopupWindow extends PopupWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String title;
	protected UserCache userCache;
	protected I18nManager i18nManager;
	protected VerticalLayout rolesSelectionLayout;
	protected HorizontalLayout rolesSelectionHorizontalLayout;
	protected HorizontalLayout buttonsSelectionLayout;
	protected FacadeIdentityService facadeIdentityService;
	protected Panel mainPanel;
	protected ComboBox rolesCombo;
	protected Button selectRolesButton;
	protected static Logger logger = LoggerFactory.getLogger(RolesPopupWindow.class);
	protected WitsUser2RoleGroupService witsUser2RoleGroupService;
	protected LoggedInUser loggedInUser;
	protected ViewManager viewManager;

	public RolesPopupWindow(String title ,
			FacadeIdentityService facadeIdentityService , 
			WitsUser2RoleGroupService witsUser2RoleGroupService ,
			LoggedInUser loggedInUser ) {
		this.title = title;
		this.userCache = WitsApp.get().getUserCache();
		this.i18nManager = WitsApp.get().getI18nManager();
		this.facadeIdentityService = facadeIdentityService;
		this.witsUser2RoleGroupService = witsUser2RoleGroupService;
		this.loggedInUser = loggedInUser;
		this.viewManager = WitsApp.get().getViewManager();
	}
	@Override
	public void attach() {
		super.attach();
		initUi();
	}

	protected void initUi() {
		((VerticalLayout)getContent()).setSizeFull();
		setResizable(true);
		setCaption(title);
		addStyleName(Reindeer.WINDOW_LIGHT);
		setModal(true);
		center();
		mainPanel = new Panel(i18nManager.getMessage(Messages.LIST_ROLES));
		mainPanel.addStyleName(Reindeer.WINDOW_LIGHT);
		mainPanel.setSizeFull();
		mainPanel.getContent().setSizeUndefined(); // <-- This is the important part
		initComboBoxRoles();
		initButtons();
	}
	private void initComboBoxRoles()
    {
	    rolesCombo = new ComboBox();
	    rolesCombo.setNullSelectionAllowed(false);
	    rolesCombo.setInvalidAllowed(false);
	    rolesCombo.setRequired(true);
		rolesCombo.setRequiredError(i18nManager.getMessage(Messages.FORM_FIELD_REQUIRED, i18nManager.getMessage(Messages.NO_ROLES_CHOSE)));
	    int maxlength = 0;
		for (GenericRole item : facadeIdentityService.getListUserRoles(loggedInUser.getId()))
	    {
	        // Add value and label (if any)
	    	rolesCombo.addItem(item.getWitsUser2RoleGroupId());
	    	rolesCombo.setItemCaption(item.getWitsUser2RoleGroupId(), item.getRole_description());
	    	if (maxlength<item.getRole_description().length())
	    		maxlength = item.getRole_description().length();
	    }
		mainPanel.addComponent(rolesCombo);
		setWidth(maxlength*8, UNITS_PIXELS);
	    setHeight(250, UNITS_PIXELS);
    	
    }
	private void initButtons()
	{
		buttonsSelectionLayout = new HorizontalLayout();
		buttonsSelectionLayout.setWidth(100, UNITS_PERCENTAGE);
		buttonsSelectionLayout.setMargin(true, true, true, true);
		GridLayout buttonGridLayout = new GridLayout(1, 1);
		buttonGridLayout.addStyleName(ExplorerLayout.STYLE_TITLE_BLOCK);
		buttonGridLayout.setSpacing(true);
		selectRolesButton = new Button();
		selectRolesButton.addStyleName(Reindeer.BUTTON_DEFAULT);
		selectRolesButton.setCaption(i18nManager.getMessage(Messages.BUTTON_OK));
		buttonGridLayout.addComponent(selectRolesButton , 0, 0);
		selectRolesButton.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				try {
					rolesCombo.validate();
				    try {
				    	
				    	GenericRole genericRole = facadeIdentityService.getUserRoles((WitsUser2RoleGroupId)rolesCombo.getValue());
						/* change role for the user */
						loggedInUser.setGenericRole(genericRole);
						loggedInUser.initializeLoggedInUser();
						WitsApp.get().setUser(loggedInUser);
						WitsAuthentication.setAuthenticatedUserIdGenericRole(genericRole);
		    		    close();
		    		    viewManager.showDefaultPageWITS();
					} catch (Exception e) {
						WitsApp.get().getMainWindow().showNotification(e.getMessage());
						logger.error("processButton.buttonClick : " + e.getMessage());
						loggedInUser = null;
		    		    close();
					}
				} catch (InvalidValueException e) {
					WitsApp.get().getMainWindow().showNotification(e.getMessage());
					logger.error("processButton.buttonClick : " + e.getMessage());
				}
			}
		});
		buttonsSelectionLayout.addComponent(buttonGridLayout);
		buttonsSelectionLayout.setComponentAlignment(buttonGridLayout, Alignment.MIDDLE_CENTER );
		mainPanel.addComponent(buttonsSelectionLayout);
		addComponent(mainPanel);
		
	}
	protected ValueChangeListener getRolesListSelectionListener() {
		return new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			public void valueChange(ValueChangeEvent event) {
				if (null == event.getProperty().getValue()) {
					// Nothing is selected
				} else {
			    	rolesCombo.setCaption(event.getProperty().getValue().toString());
				}

			}
		};
	}
	
}
