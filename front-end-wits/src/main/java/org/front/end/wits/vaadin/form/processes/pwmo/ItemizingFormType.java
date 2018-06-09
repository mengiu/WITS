package org.front.end.wits.vaadin.form.processes.pwmo;

import org.activiti.engine.impl.form.AbstractFormType;

public class ItemizingFormType extends AbstractFormType {
	public static final String TYPE_NAME = "itemizinggrid";

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
