package org.front.end.wits;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.front.end.wits.vaadin.ui.AbstractTableDetail;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

public class PrintBarcodeGridFieldTest extends AbstractTableDetail implements Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7256009441499989373L;
	protected TextField wrappedField;
	protected Table printBarcodeTable;
	
	public PrintBarcodeGridFieldTest(){
		
	}

	public void selectElement(int index) {
	}
	protected com.vaadin.data.Property.ValueChangeListener getListSelectionListener() {
		return new com.vaadin.data.Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				// in multiselect mode, a Set of itemIds is returned,
				// in singleselect mode the itemId is returned directly
				Set<?> value = (Set<?>) event.getProperty().getValue();
				if (null == value || value.size() == 0) {
					// Nothing is selected
				} else {
					List<Item> listItem =  new ArrayList<Item>();
					for (Object item : value )
					{
						Item propItem = printBarcodeTable.getItem(item);
						listItem.add(propItem);
					}
					getWindow().showNotification("Selected cities: " + event.getProperty());					
				}


			}
		};
	}

	public boolean isInvalidCommitted() {
		return wrappedField.isInvalidCommitted();
	}

	public void setInvalidCommitted(boolean isCommitted) {
		wrappedField.setInvalidCommitted(isCommitted);
	}

	public void commit() throws SourceException, InvalidValueException {
		wrappedField.commit();
	}

	public void discard() throws SourceException {
		wrappedField.discard();
	}

	public boolean isWriteThrough() {
		return wrappedField.isWriteThrough();
	}

	public void setWriteThrough(boolean writeThrough) throws SourceException, InvalidValueException {
		wrappedField.setWriteThrough(true);
	}

	public boolean isReadThrough() {
		return wrappedField.isReadThrough();
	}

	public void setReadThrough(boolean readThrough) throws SourceException {
		wrappedField.setReadThrough(readThrough);
	}

	public boolean isModified() {
		return wrappedField.isModified();
	}

	public void addValidator(Validator validator) {
		wrappedField.addValidator(validator);
	}

	public void removeValidator(Validator validator) {
		wrappedField.removeValidator(validator);
	}

	public Collection<Validator> getValidators() {
		return wrappedField.getValidators();
	}

	public boolean isValid() {
		return wrappedField.isValid();
	}

	public void validate() throws InvalidValueException {
		wrappedField.validate();
	}

	public boolean isInvalidAllowed() {
		return wrappedField.isInvalidAllowed();
	}

	public void setInvalidAllowed(boolean invalidValueAllowed) throws UnsupportedOperationException {
		wrappedField.setInvalidAllowed(invalidValueAllowed);
	}

	public Object getValue() {
		return wrappedField.getValue();
	}

	public void setValue(Object newValue) throws ReadOnlyException, ConversionException {
		wrappedField.setValue(newValue);

	}

	protected Object getSelectedUserLabel() {
		return wrappedField.getValue();
	}

	public Class< ? > getType() {
		return wrappedField.getType();
	}

	public void addListener(ValueChangeListener listener) {
		wrappedField.addListener(listener);
	}

	public void removeListener(ValueChangeListener listener) {
		wrappedField.removeListener(listener);
	}

	public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
		wrappedField.valueChange(event);
	}

	public void setPropertyDataSource(Property newDataSource) {
		wrappedField.setPropertyDataSource(newDataSource);
	}

	public Property getPropertyDataSource() {
		return wrappedField.getPropertyDataSource();
	}

	public int getTabIndex() {
		return wrappedField.getTabIndex();
	}

	public void setTabIndex(int tabIndex) {
		wrappedField.setTabIndex(tabIndex);
	}

	public boolean isRequired() {
		return wrappedField.isRequired();
	}

	public void setRequired(boolean required) {
		wrappedField.setRequired(required);
	}

	public void setRequiredError(String requiredMessage) {
		wrappedField.setRequiredError(requiredMessage);
	}

	public String getRequiredError() {
		return wrappedField.getRequiredError();
	}

	@Override
	public void focus() {
		wrappedField.focus();
	}
	@Override
	protected Table createList() {
		printBarcodeTable = new Table();
		printBarcodeTable.setWidth(100, UNITS_PERCENTAGE);

		// Listener 
		printBarcodeTable.addListener(getListSelectionListener());


		printBarcodeTable.addContainerProperty("wamatobjectcode", String.class, null, "1",
				null, Table.ALIGN_CENTER);
		printBarcodeTable.addContainerProperty("containerunittype", String.class, null, "2",
				null, Table.ALIGN_CENTER);
		printBarcodeTable.addContainerProperty("userrequesting", String.class, null, "3",
				null, Table.ALIGN_CENTER);
		printBarcodeTable.addContainerProperty("recipient", String.class, null, "4",
				null, Table.ALIGN_CENTER);
		printBarcodeTable.addContainerProperty("barcodeprinted", String.class, null, "5",
				null, Table.ALIGN_CENTER);
		printBarcodeTable.setPageLength(printBarcodeTable.size());

        for (int index=0;index<7;index++)
        {
        	addItemToTable(index);
        }
		return printBarcodeTable;
	}
	@Override
	protected void setTableProperties()
	{
		// Set non-editable, selectable and full-size
		printBarcodeTable.setEditable(false);
		printBarcodeTable.setImmediate(true);
		printBarcodeTable.setSelectable(true);
		printBarcodeTable.setNullSelectionAllowed(false);
		printBarcodeTable.setSortDisabled(true);
		printBarcodeTable.setSizeFull();
		printBarcodeTable.setMultiSelect(true);
	}
	@Override
	public Component getDetailsComponent() {
		return null;
	}
	@Override
	public Component getBottomComponent() {
		return null;
	}
	protected void addItemToTable(int index) {
		Item item = printBarcodeTable.addItem(index);
		item.getItemProperty("wamatobjectcode").setValue("aaaaaa");
		item.getItemProperty("containerunittype").setValue("bbbbbb");
		item.getItemProperty("userrequesting").setValue("cccccc");
		item.getItemProperty("recipient").setValue("dddddd");
		item.getItemProperty("barcodeprinted").setValue("eeeeee");
	}

}
