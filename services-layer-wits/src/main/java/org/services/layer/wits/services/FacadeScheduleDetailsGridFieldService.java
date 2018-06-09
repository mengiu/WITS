package org.services.layer.wits.services;

import java.util.List;
import java.util.Map;

import org.persistence.layer.wits.form.QuantityUnitSt;
import org.persistence.layer.wits.form.WacSt;
import org.persistence.layer.wits.form.WamatObject;

import com.vaadin.ui.Component;

public interface FacadeScheduleDetailsGridFieldService {
 public boolean saveSchedulingDetails(WacSt wacSt, WamatObject wamatObject, 
		 Map<String, List<Component>> cachedComponent, String delimitator);
}
