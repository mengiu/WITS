package org.front.end.wits.vaadin.form.processes.pwmo;

import org.activiti.engine.impl.form.AbstractFormType;

public class StartProcessingWmoFormType extends AbstractFormType {
	public static final String TYPE_NAME = "startprocessingpwmogrid";

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
