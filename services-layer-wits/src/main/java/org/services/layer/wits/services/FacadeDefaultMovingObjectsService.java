package org.services.layer.wits.services;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.persistence.layer.wits.form.ContainingUnit;
import org.persistence.layer.wits.form.WamatObject;

public interface FacadeDefaultMovingObjectsService {
	WamatObject getMovingObjectsQuery(TaskService taskService,
			RuntimeService runtimeService, String taskId);
	int getCount(TaskService taskService, RuntimeService runtimeService,
			List<String> listTaskId);
	ContainingUnit getMovingCUQuery(
			TaskService taskService, RuntimeService runtimeService,
			String taskId);
}
