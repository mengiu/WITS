package org.front.end.wits.vaadin.form.processes.rwmo;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.form.AbstractFormType;
import org.persistence.layer.wits.form.ContainingUnit;
import org.services.layer.wits.services.ContainingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DestinationFormType extends AbstractFormType {
	@Autowired
	@Qualifier("containingUnitService")
	ContainingUnitService containingUnitService;

	public static final String TYPE_NAME = "destination";

	@Override
	public String getName() {
		return TYPE_NAME;
	}

	@Override
	public Object convertFormValueToModelValue(String propertyValue) {
	    // Check if containingUnit exists
	    if(propertyValue != null) {
	      ContainingUnit containingUnit = containingUnitService.getContainingUnit(new Integer(propertyValue));	      
	      if(containingUnit == null) {
	        throw new ActivitiException("ContainingUnit Type " + propertyValue + " does not exist");
	      }
	      return propertyValue;
	    }
	    return null;
	}

	@Override
	public String convertModelValueToFormValue(Object modelValue) {
	    return (String) modelValue;
	}

}
