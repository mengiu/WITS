package org.services.layer.wits.services;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.RoleSt;
import org.persistence.layer.wits.form.WamatObject;

public interface FacadeDefaultBarcodeService {
	List<WamatObject> getBarcodeQuery(TaskService taskService,List<String> listTaskId);
	WamatObjectService getWamatObjectService();
    int getCount(TaskService taskService, List<String> listTaskId);
    List<WamatObject> getListWamatObjecttoReadByGroup(GroupSt groupSt, RoleSt role,
    		HistoryService historyService, RuntimeService runtimeService);
    		
}
