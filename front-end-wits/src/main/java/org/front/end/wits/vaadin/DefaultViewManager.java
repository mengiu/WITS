/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.front.end.wits.vaadin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.front.end.wits.vaadin.editor.ui.EditorProcessDefinitionPage;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.front.end.wits.vaadin.ui.AbstractTablePage;
import org.front.end.wits.vaadin.ui.MainWindow;
import org.front.end.wits.vaadin.ui.management.ManagementMenuBar;
import org.front.end.wits.vaadin.explorer.ui.management.admin.AdministrationPage;
import org.front.end.wits.vaadin.ui.management.db.DatabasePage;
import org.front.end.wits.vaadin.ui.management.dynamictables.DynamicsTablesPage;
import org.front.end.wits.vaadin.ui.management.dynamictables.DynamicsTablesPageBis;
import org.front.end.wits.vaadin.explorer.ui.management.deployment.DeploymentPage;
import org.front.end.wits.vaadin.ui.management.identity.GroupPage;
import org.front.end.wits.vaadin.ui.management.identity.UserPage;
import org.front.end.wits.vaadin.ui.management.statictables.StaticsTablesPage;
import org.front.end.wits.vaadin.ui.management.statictables.StaticsTablesPageBis;
import org.front.end.wits.vaadin.explorer.ui.management.job.JobPage;
import org.front.end.wits.vaadin.explorer.ui.management.processdefinition.ActiveProcessDefinitionPage;
import org.front.end.wits.vaadin.explorer.ui.management.processdefinition.SuspendedProcessDefinitionPage;
import org.front.end.wits.vaadin.identity.LoggedInUser;
import org.front.end.wits.vaadin.ui.view.dbstatic.groups.ManagementGroupMenuBar;
import org.front.end.wits.vaadin.ui.view.dbstatic.users.WitsUserPage;
import org.front.end.wits.vaadin.ui.view.dbstatic.wamatobjects.StaticViewMenuBar;
import org.front.end.wits.vaadin.ui.view.dbstatic.wamatobjects.WamatObjectsPage;
import org.front.end.wits.vaadin.ui.view.workflow.process.MyProcessInstancesPage;
import org.front.end.wits.vaadin.ui.view.workflow.process.ProcessDefinitionPage;
import org.front.end.wits.vaadin.ui.view.workflow.process.ProcessMenuBar;
import org.front.end.wits.vaadin.ui.profile.ProfilePopupWindow;
import org.front.end.wits.vaadin.ui.view.workflow.task.ArchivedPage;
import org.front.end.wits.vaadin.ui.view.workflow.task.InboxPage;
import org.front.end.wits.vaadin.ui.view.workflow.task.InvolvedPage;
import org.front.end.wits.vaadin.ui.view.workflow.task.QueuedPage;
import org.front.end.wits.vaadin.ui.view.workflow.task.TaskMenuBar;
import org.front.end.wits.vaadin.ui.view.workflow.task.TasksPage;
import org.front.end.wits.vaadin.ui.view.workflow.WorkflowViewMenuBar;
import org.front.end.wits.vaadin.ui.view.workflow.task.TaskToDoPage;
import org.services.layer.wits.services.WitsUserStService;

import com.vaadin.ui.Window;


/**
 * @author Mennea Giuseppe
 */
public class DefaultViewManager implements ViewManager {


	protected AbstractTablePage currentPage;
	protected WitsUserStService witsUserStService;

	//@Autowired
	protected MainWindow mainWindow;
	protected TaskService taskService;
	protected HistoryService historyService;
	protected IdentityService identityService;

	public DefaultViewManager(
			WitsUserStService witsUserStService) {
		this.taskService = ProcessEngines.getDefaultProcessEngine().getTaskService();
		this.historyService = ProcessEngines.getDefaultProcessEngine().getHistoryService();
		this.identityService = ProcessEngines.getDefaultProcessEngine().getIdentityService();
		this.witsUserStService = witsUserStService;
	}

	public void showLoginPage() {
		if(!mainWindow.isShowingLoginPage()) {
			mainWindow.showLoginPage();      
		}
	}
	public void showLogoutPage() {
		if(!mainWindow.isShowingLogoutPage()) {
			mainWindow.showLogoutPage();      
		}
	}

	public void showDefaultPage() {
		mainWindow.showDefaultContent();
		showInboxPage();
	}

	public void showPopupWindow(Window window) {
		mainWindow.addWindow(window);
	}

	public void showDefaultPageWITS() {
		mainWindow.showDefaultContent();
		showWamatObjectsPage();
	}
	// Tasks 

	/**
	 * Generic method which will figure out to which
	 * task page must be jumped, based on the task data.
	 * 
	 * Note that, if possible, it is always more
	 * performant to use the more specific showXXXPage() methods.
	 */
	public void showTaskPage(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String loggedInUserId = WitsApp.get().getLoggedInUser().getId();

		if (task == null) {
			// If no runtime task exists, our only hope is the archive page
			boolean isOwner = historyService.createHistoricTaskInstanceQuery()
					.taskId(taskId).taskOwner(loggedInUserId).count() == 1;
			if (isOwner) {
				showArchivedPage(taskId);
			} else {
				showNavigationError(taskId);
			}
		} else if (loggedInUserId.equals(task.getOwner())) {
			showTasksPage(taskId);
		} else if (loggedInUserId.equals(task.getAssignee())) {
			showInboxPage(taskId);
		} else if (taskService.createTaskQuery().taskInvolvedUser(loggedInUserId).count() == 1) {
			showInvolvedPage(taskId);
		} else {
			// queued
			List<String> groupIds = getGroupIds(loggedInUserId);
			List<IdentityLink> identityLinks = taskService.getIdentityLinksForTask(task.getId());
			Iterator<IdentityLink> identityLinkIterator = identityLinks.iterator();

			boolean pageFound = false;
			while (!pageFound && identityLinkIterator.hasNext()) {
				IdentityLink identityLink = identityLinkIterator.next();
				if (identityLink.getGroupId() != null && groupIds.contains(identityLink.getGroupId())) {
					showQueuedPage(identityLink.getGroupId(), task.getId());
					pageFound = true;
				}
			}

			// We've tried hard enough, the user now gets a notification. He deserves it.
			if (!pageFound) {
				showNavigationError(taskId);
			}
		}
	}

	protected List<String> getGroupIds(String userId) {
		List<String> groupIds = new ArrayList<String>();
		List<Group> groups = identityService.createGroupQuery().groupMember(userId).list();
		for (Group group : groups) {
			groupIds.add(group.getId());
		}

		return groupIds;
	}

	protected void showNavigationError(String taskId) {
		WitsApp.get().getNotificationManager().showErrorNotification(
				Messages.NAVIGATION_ERROR_NOT_INVOLVED_TITLE, 
				WitsApp.get().getI18nManager().getMessage(Messages.NAVIGATION_ERROR_NOT_INVOLVED, taskId));
	}
	// Wits Views
	public void showTaskToDoPage() {
		switchView(new TaskToDoPage(), 
				ViewManager.MAIN_NAVIGATION_WORKFLOW , WorkflowViewMenuBar.ENTRY_TASKS );
	}

	// Task
	public void showTasksPage() {
		switchView(new TasksPage(), ViewManager.MAIN_NAVIGATION_TASK, TaskMenuBar.ENTRY_TASKS);
	}

	public void showTasksPage(String taskId) {
		switchView(new TasksPage(taskId), ViewManager.MAIN_NAVIGATION_TASK, TaskMenuBar.ENTRY_TASKS);
	}

	public void showInboxPage() {
		switchView(new InboxPage(), ViewManager.MAIN_NAVIGATION_TASK, TaskMenuBar.ENTRY_INBOX);
	}

	public void showInboxPage(String taskId) {
		switchView(new InboxPage(taskId), ViewManager.MAIN_NAVIGATION_TASK, TaskMenuBar.ENTRY_INBOX);
	}

	public void showQueuedPage(String groupId) {
		switchView(new QueuedPage(groupId), ViewManager.MAIN_NAVIGATION_TASK, TaskMenuBar.ENTRY_QUEUED);
	}

	public void showQueuedPage(String groupId, String taskId) {
		switchView(new QueuedPage(groupId, taskId), ViewManager.MAIN_NAVIGATION_TASK, TaskMenuBar.ENTRY_QUEUED);
	}

	public void showInvolvedPage() {
		switchView(new InvolvedPage(), ViewManager.MAIN_NAVIGATION_TASK, TaskMenuBar.ENTRY_INVOLVED);
	}

	public void showInvolvedPage(String taskId) {
		switchView(new InvolvedPage(taskId), ViewManager.MAIN_NAVIGATION_TASK, TaskMenuBar.ENTRY_INVOLVED);
	}

	public void showArchivedPage() {
		switchView(new ArchivedPage(), ViewManager.MAIN_NAVIGATION_TASK, TaskMenuBar.ENTRY_ARCHIVED);
	}

	public void showArchivedPage(String taskId) {
		switchView(new ArchivedPage(taskId), ViewManager.MAIN_NAVIGATION_TASK, TaskMenuBar.ENTRY_ARCHIVED);
	}

	// Process

	public void showDeployedProcessDefinitionPage() {
		switchView(new ProcessDefinitionPage(witsUserStService), ViewManager.MAIN_NAVIGATION_WORKFLOW, WorkflowViewMenuBar.DEPLOYED_PROCESS_DEFINITIONS);
	}

	public void showDeployedProcessDefinitionPage(String processDefinitionId) {
		switchView(new ProcessDefinitionPage(processDefinitionId,witsUserStService), ViewManager.MAIN_NAVIGATION_WORKFLOW, WorkflowViewMenuBar.DEPLOYED_PROCESS_DEFINITIONS);
	}

	public void showEditorProcessDefinitionPage() {
		switchView(new EditorProcessDefinitionPage(), ViewManager.MAIN_NAVIGATION_PROCESS, ProcessMenuBar.EDITOR_PROCESS_DEFINITIONS);
	}

	public void showEditorProcessDefinitionPage(String modelId) {
		switchView(new EditorProcessDefinitionPage(modelId), ViewManager.MAIN_NAVIGATION_PROCESS, ProcessMenuBar.EDITOR_PROCESS_DEFINITIONS);
	}

	public void showMyProcessInstancesPage() {
		switchView(new MyProcessInstancesPage(), ViewManager.MAIN_NAVIGATION_WORKFLOW, WorkflowViewMenuBar.ENTRY_MY_PROCESS_INSTANCES);
	}

	public void showMyProcessInstancesPage(String processDefinitionId) {
		switchView(new MyProcessInstancesPage(processDefinitionId), ViewManager.MAIN_NAVIGATION_WORKFLOW, WorkflowViewMenuBar.ENTRY_MY_PROCESS_INSTANCES);
	}

	// Management

	public void showDatabasePage() {
		switchView(new DatabasePage(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_DATABASE);
	}

	public void showDatabasePage(String tableName) {
		switchView(new DatabasePage(tableName), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_DATABASE);
	}

	public void showDeploymentPage() {
		switchView(new DeploymentPage(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_DEPLOYMENTS);
	}

	public void showDeploymentPage(String deploymentId) {
		switchView(new DeploymentPage(deploymentId), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_DEPLOYMENTS);
	}

	public void showActiveProcessDefinitionsPage() {
		switchView(new ActiveProcessDefinitionPage(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_ACTIVE_PROCESS_DEFINITIONS);
	}

	public void showActiveProcessDefinitionsPage(String processDefinitionId) {
		switchView(new ActiveProcessDefinitionPage(processDefinitionId), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_ACTIVE_PROCESS_DEFINITIONS);
	}

	public void showSuspendedProcessDefinitionsPage() {
		switchView(new SuspendedProcessDefinitionPage(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_SUSPENDED_PROCESS_DEFINITIONS);
	}

	public void showSuspendedProcessDefinitionsPage(String processDefinitionId) {
		switchView(new SuspendedProcessDefinitionPage(processDefinitionId), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_SUSPENDED_PROCESS_DEFINITIONS);
	}

	public void showJobPage() {
		switchView(new JobPage(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_JOBS);
	}

	public void showJobPage(String jobId) {
		switchView(new JobPage(jobId), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_JOBS);
	}

	public void showUserPage() {
		switchView(new UserPage(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_USERS);
	}

	public void showUserPage(String userId) {
		switchView(new UserPage(userId), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_USERS);
	}

	public void showGroupPage() {
		final LoggedInUser loggedInUser = WitsApp.get().getLoggedInUser();
        if (loggedInUser.isAdmin())
		 switchView(new GroupPage(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_GROUPS);
        if (loggedInUser.isResponsible())
		 switchView(new GroupPage(), ViewManager.MAIN_NAVIGATION_GROUP, ManagementGroupMenuBar.ENTRY_GROUPS);
	}

	public void showGroupPage(String groupId) {
		final LoggedInUser loggedInUser = WitsApp.get().getLoggedInUser();
        if (loggedInUser.isAdmin())
    	 switchView(new GroupPage(groupId), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_GROUPS);
        if (loggedInUser.isResponsible())
		 switchView(new GroupPage(groupId), ViewManager.MAIN_NAVIGATION_GROUP, ManagementGroupMenuBar.ENTRY_GROUPS);
	}

	public void showProcessInstancePage() {
		throw new UnsupportedOperationException(); // Only for alfresco admin app
	}

	public void showProcessInstancePage(String processInstanceId) {
		throw new UnsupportedOperationException(); // Only for alfresco admin app
	}

	public void showAdministrationPage() {
		switchView(new AdministrationPage(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_ADMIN);
	}

	public void showAdministrationPage(String managementId) {
		switchView(new AdministrationPage(managementId), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_ADMIN);
	}

	public void showStaticsTablesPage() {
		switchView(new StaticsTablesPage(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_STATIC_TABLES);
	}

	public void showStaticsTablesPageBis() {
		switchView(new StaticsTablesPageBis(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_STATIC_TABLES_BIS);
	}

	public void showDynamicsTablesPage() {
		switchView(new DynamicsTablesPage(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_DYNAMIC_TABLES);
	}

	public void showDynamicsTablesPageBis() {
		switchView(new DynamicsTablesPageBis(), ViewManager.MAIN_NAVIGATION_MANAGE, ManagementMenuBar.ENTRY_DYNAMIC_TABLES_BIS);
	}
	// Profile

	public void showProfilePopup(String userId) {
		showPopupWindow(new ProfilePopupWindow(userId));
	}

	// Helper

	protected void switchView(AbstractTablePage page, String mainMenuActive, String subMenuActive) {
		currentPage = page;
		mainWindow.setMainNavigation(mainMenuActive);
		mainWindow.switchView(page);
		if (page.getToolBar()!=null)
		 page.getToolBar().setActiveEntry(subMenuActive); // Must be set AFTER adding page to window (toolbar will be created in atach())
	}

	public AbstractTablePage getCurrentPage() {
		return currentPage;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void showWamatObjectsPage() {
		switchView(new WamatObjectsPage() , ViewManager.MAIN_NAVIGATION_VIEW, StaticViewMenuBar.ENTRY_WAMAT_OBJECTS );
	}

	@Override
	public void showWamatObjectsPage(Integer wamatObjectId) {
		switchView(new WamatObjectsPage(wamatObjectId), ViewManager.MAIN_NAVIGATION_VIEW, StaticViewMenuBar.ENTRY_WAMAT_OBJECTS );
	}

	@Override
	public void showWitsUsersPage() {
		switchView(new WitsUserPage() , ViewManager.MAIN_NAVIGATION_VIEW, StaticViewMenuBar.ENTRY_USERS);
		
	}



}
