package org.front.end.wits.vaadin.form.processes.rwmo;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.form.AbstractFormType;
import org.persistence.layer.wits.form.OwnerSt;
import org.services.layer.wits.services.OwnerStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class OwnerFormType extends AbstractFormType {
    @Autowired
	@Qualifier("ownerStService")
    OwnerStService ownerStService;
    
	public static final String TYPE_NAME = "owner";

	@Override
	public String getName() {
		return TYPE_NAME;
	}

	@Override
	public Object convertFormValueToModelValue(String propertyValue) {
	    // Check if gst exists
	    if(propertyValue != null) {
	      OwnerSt own = ownerStService.getOwnerSt(new Integer(propertyValue));	      
	      if(own == null) {
	        throw new ActivitiException("Owner Type " + propertyValue + " does not exist");
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
