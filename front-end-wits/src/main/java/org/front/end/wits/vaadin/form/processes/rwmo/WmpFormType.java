package org.front.end.wits.vaadin.form.processes.rwmo;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.form.AbstractFormType;
import org.persistence.layer.wits.form.WasteManagementPlanSt;
import org.services.layer.wits.services.WasteManagementPlanStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class WmpFormType extends AbstractFormType {
    @Autowired
	@Qualifier("wasteManagementPlanStService")
    WasteManagementPlanStService wasteManagementPlanStService;
    
	public static final String TYPE_NAME = "wmp";

	public String getName() {
		return TYPE_NAME;
	}

	@Override
	public Object convertFormValueToModelValue(String propertyValue) {
	    // Check if WMP exists
	    if(propertyValue != null) {
	      WasteManagementPlanSt wmp = wasteManagementPlanStService.getWasteManagementPlanSt(new Integer(propertyValue));	      
	      if(wmp == null) {
	        throw new ActivitiException("Waste Management Plan " + propertyValue + " does not exist");
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
