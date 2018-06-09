package org.front.end.wits.vaadin.form.processes.rwmo;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.form.AbstractFormType;
import org.persistence.layer.wits.form.ContainingUnitTypeSt;
import org.services.layer.wits.services.ContainingUnitTypeStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Mennea Giuseppe
 */
public class GstFormType extends AbstractFormType {
    @Autowired
	@Qualifier("containingUnitTypeStService")
    ContainingUnitTypeStService containingUnitTypeStService;
    
	public static final String TYPE_NAME = "gst";

	public String getName() {
		return TYPE_NAME;
	}

	@Override
	public Object convertFormValueToModelValue(String propertyValue) {
	    // Check if gst exists
	    if(propertyValue != null) {
	      ContainingUnitTypeSt gst = containingUnitTypeStService.getContainingUnitTypeSt(new Integer(propertyValue));	      
	      if(gst == null) {
	        throw new ActivitiException("Geometry Type " + propertyValue + " does not exist");
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
