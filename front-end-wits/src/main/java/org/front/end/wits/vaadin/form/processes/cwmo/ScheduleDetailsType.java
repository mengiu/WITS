package org.front.end.wits.vaadin.form.processes.cwmo;

import org.activiti.engine.impl.form.AbstractFormType;

public class ScheduleDetailsType extends AbstractFormType {
	public static final String TYPE_NAME = "scheduledetailsgrid";

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
