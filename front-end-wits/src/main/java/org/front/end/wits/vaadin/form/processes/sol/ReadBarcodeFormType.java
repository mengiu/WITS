package org.front.end.wits.vaadin.form.processes.sol;

import org.activiti.engine.impl.form.AbstractFormType;

public class ReadBarcodeFormType extends AbstractFormType {
	public static final String TYPE_NAME = "readingbarcodegrid";

	@Override
	public String getName() {
	    return TYPE_NAME;
	}

	@Override
	public Object convertFormValueToModelValue(String propertyValue) {
		return propertyValue;
	}

	@Override
	public String convertModelValueToFormValue(Object modelValue) {
	    return (String) modelValue;
	}

}
