package org.services.layer.wits.services;

import java.util.List;

import org.activiti.engine.TaskService;
import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.RoleSt;

public interface FacadeProcessInstanceDetailPanelService {
	public List<String> listAllCandidateUsersNameForATask(String taskId,TaskService taskService);
	public void deleteUserFromATask(String taskId,TaskService taskService, String userName);
	public String getFirstNameAndLastName(String userName);
	public List<RoleSt> getUserRoles(String userName);
	public List<GroupSt> listAllGroupsNameForATask(String taskId,
			TaskService taskService);

}
