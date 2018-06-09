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



/**
 * @author Mennea Giuseppe
 */
public interface Messages {
  
  // General
  static final String APP_TITLE = "app.title";
  
  static final String CONFIRMATION_DIALOG_DEFAULT_TITLE = "confirmation.dialog.default.title";
  static final String CONFIRMATION_DIALOG_YES = "confirmation.dialog.yes";
  static final String CONFIRMATION_DIALOG_NO = "confirmation.dialog.no";
  static final String BUTTON_OK = "button.ok";
  static final String BUTTON_CREATE = "button.create";
  static final String BUTTON_CANCEL = "button.cancel";
  static final String BUTTON_ADD = "button.add";
  static final String BUTTON_DELETE = "button.delete";
  static final String BUTTON_EDIT = "button.edit";

  static final String UNCAUGHT_EXCEPTION = "uncaught.exception";
  static final String CONFIRMATION_ON_BUTTON_CANCEL = "confirmation.on.button.cancel";
  static final String CONFIRMATION_ON_LEAVE_PAGE = "confirmation.on.leave.page";
  static final String CONFIRMATION_DATA_SAVED = "confirmation.data.saved";
  static final String DEFAULT_DATA_FORMAT_EXCEPTION = "default.data.format.exception";

  // Navigation
  static final String NAVIGATION_ERROR_NOT_INVOLVED_TITLE = "navigation.error.not.involved.title";
  static final String NAVIGATION_ERROR_NOT_INVOLVED = "navigation.error.not.involved";
  
  // Login
  static final String LOGIN_USERNAME = "login.username";
  static final String LOGIN_PASSWORD = "login.password";
  static final String LOGIN_BUTTON = "login.button";
  static final String LOGIN_FAILED_HEADER = "login.failed.header";
  static final String LOGIN_FAILED_INVALID = "login.failed.invalid";
  
  // Header
  static final String HEADER_SEARCHBOX = "header.searchbox";
  static final String HEADER_LOGOUT = "header.logout";
  
  // Footer
  static final String FOOTER_MESSAGE = "footer.message";

  // Main menu
  static final String MAIN_MENU_TASKS = "main.menu.tasks";
  static final String MAIN_MENU_PROCESS = "main.menu.process";
  static final String MAIN_MENU_MANAGEMENT = "main.menu.management";
  static final String MAIN_MENU_REPORTS = "main.menu.reports";
  static final String MAIN_MENU_VIEW = "main.menu.view";
  static final String MAIN_MENU_GROUPS = "main.menu.groups";
  
  // Password
  static final String PASSWORD_CHANGE = "password.change";
  static final String PASSWORD_CHANGE_INPUT_REQUIRED = "password.change.input.required";
  static final String PASSWORD_CHANGE_INPUT_MATCH = "password.change.input.match";
  static final String PASSWORD_CHANGED_NOTIFICATION = "password.changed.notification";
  
  // Forms
  static final String FORM_USER_NO_USER_SELECTED = "form.user.no.user.selected";
  static final String FORM_USER_SELECT = "form.user.select";
  static final String FORM_FIELD_REQUIRED = "form.field.required";
  static final String TYPE_NOT_SUPPORTED = "type.not.supported";
  
  // Profile
  static final String PROFILE_ABOUT = "profile.about";
  static final String PROFILE_NAME = "profile.name";
  static final String PROFILE_FIRST_NAME = "profile.firstname";
  static final String PROFILE_LAST_NAME = "profile.lastname";
  static final String PROFILE_JOBTITLE = "profile.jobtitle";
  static final String PROFILE_BIRTHDATE = "profile.birthdate";
  static final String PROFILE_LOCATION = "profile.location";
  static final String PROFILE_CONTACT = "profile.contact";
  static final String PROFILE_EMAIL = "profile.email";
  static final String PROFILE_PHONE = "profile.phone";;
  static final String PROFILE_TWITTER = "profile.twitter";
  static final String PROFILE_SKYPE = "profile.skype";
  static final String PROFILE_ACCOUNTS = "profile.accounts";
  static final String PROFILE_SHOW = "profile.show";
  static final String PROFILE_EDIT = "profile.edit";
  static final String PROFILE_SAVE = "profile.save";
  static final String PROFILE_CHANGE_PICTURE = "profile.change.picture";
  static final String PROFILE_ACCOUNT_USER_NAME = "profile.account.username";
  static final String PROFILE_ACCOUNT_PASWORD = "profile.account.password";
  static final String PROFILE_DELETE_ACCOUNT_TITLE = "profile.delete.account.title";
  static final String PROFILE_DELETE_ACCOUNT_DESCRIPTION = "profile.delete.account.description";
  static final String PROFILE_ADD_ACCOUNT = "profile.add.account";
  static final String PROFILE_ACCOUNT_IMAP_DESCRIPTION = "profile.account.imap.description";
  static final String PROFILE_ACCOUNT_IMAP = "profile.account.imap";
  static final String PROFILE_ACCOUNT_ALFRESCO = "profile.account.alfresco";
  static final String PROFILE_NEW_PASSWORD = "profile.new.password";
  static final String PROFILE_CONFIRM_PASSWORD = "profile.confirm.password";
  
  // Imap
  static final String IMAP_SERVER = "imap.server";
  static final String IMAP_PORT = "imap.port";
  static final String IMAP_SSL = "imap.ssl";
  static final String IMAP_USERNAME = "imap.username";
  static final String IMAP_PASSWORD = "imap.password";
  static final String IMAP_DESCRIPTION = "imap.description";
  
  //Alfresco
  static final String ALFRESCO_SERVER = "alfresco.server";
  static final String ALFRESCO_USERNAME = "alfresco.username";
  static final String ALFRESCO_PASSWORD = "alfresco.password";
  static final String ALFRESCO_DESCRIPTION = "alfresco.description";
  
  // Case
  static final String TASK_CREATE_NEW = "task.create.new";
  static final String TASK_NEW = "task.new";
  static final String TASK_NAME_REQUIRED = "task.name.required";

  // Task menu
  static final String TASK_MENU_TASKS = "task.menu.tasks";
  static final String TASK_MENU_INBOX = "task.menu.inbox";
  static final String TASK_MENU_QUEUED = "task.menu.queued";
  static final String TASK_MENU_INVOLVED = "task.menu.involved";
  static final String TASK_MENU_ARCHIVED = "task.menu.archived";
  
  // Task details
  static final String TASK_ID = "task.id";
  static final String TASK_NAME = "task.name";
  static final String TASK_DESCRIPTION = "task.description";
  static final String TASK_NO_DESCRIPTION = "task.no.description";
  static final String TASK_OWNER = "task.owner";
  static final String TASK_OWNER_TRANSFER = "task.owner.transfer";
  static final String TASK_NO_OWNER = "task.no.owner";
  static final String TASK_ASSIGNEE = "task.assignee";
  static final String TASK_NO_ASSIGNEE = "task.no.assignee";
  static final String TASK_ASSIGNEE_REASSIGN = "task.assignee.reassign";
  static final String TASK_INVOLVED_REMOVE = "task.involved.remove";
  static final String TASK_INVOLVED_REMOVE_CONFIRMATION_TITLE = "task.involved.remove.confirmation.title";
  static final String TASK_INVOLVED_REMOVE_CONFIRMATION_DESCRIPTION = "task.involved.remove.confirmation.description";
  static final String TASK_CREATED_SHORT = "task.created.short";
  static final String TASK_DUEDATE = "task.duedate";
  static final String TASK_DUEDATE_UNKNOWN = "task.duedate.unknown";
  static final String TASK_DUEDATE_SHORT = "task.duedate.short";
  static final String TASK_COMPLETE = "task.complete";
  static final String TASK_COMPLETED = "task.task.completed";
  static final String TASK_RESET_FORM = "task.form.reset";
  static final String TASK_ADD_COMMENT = "task.comment.add";
  static final String TASK_COMMENT_POPUP_HEADER = "task.comment.popup.header";
  static final String TASK_CREATE_TIME = "task.create.time";
  static final String TASK_COMPLETE_TIME = "task.complete.time";
  static final String TASK_DURATION = "task.duration";
  static final String TASK_PRIORITY = "task.priority";
  static final String TASK_PRIORITY_LOW = "task.priority.low";
  static final String TASK_PRIORITY_MEDIUM = "task.priority.medium";
  static final String TASK_PRIORITY_HIGH = "task.priority.high";
  static final String TASK_NOT_FINISHED_YET = "task.not.finished.yet";
  static final String TASK_PART_OF_PROCESS = "task.part.of.process";
  static final String TASK_SUBTASK_OF_PARENT_TASK = "task.subtask.of.parent.task";
  static final String TASK_JUMP_TO_PROCESS = "task.jump.to.process";
  static final String TASK_CLAIM_FAILED = "task.claim.failed";
  static final String TASK_CLAIM_SUCCESS = "task.claim.success";
  static final String TASK_CLAIM = "task.claim";
  static final String TASK_RELATED_CONTENT = "task.related.content";
  static final String TASK_NO_RELATED_CONTENT = "task.no.related.content";
  static final String TASK_PEOPLE = "task.people";
  static final String TASK_FORM_HELP = "task.form.help";
  static final String TASK_SUBTASKS = "task.subtasks";
  static final String TASK_NO_SUBTASKS = "task.no.subtasks";
  static final String TASK_CONFIRM_DELETE_SUBTASK = "task.confirm.delete.subtask";
  static final String TASK_ITALIAN = "task.italian";
  static final String TASK_COUNT = "task.count";
  static final String TASK_CURRENT = "task.current";
  
  // Task roles
  static final String TASK_ROLE_CONTRIBUTOR = "task.role.contributor";
  static final String TASK_ROLE_MANAGER = "task.role.manager";
  static final String TASK_ROLE_SPONSOR = "task.role.sponsor";
  static final String TASK_ROLE_IMPLEMENTER = "task.role.implementer";
        
  // Events
  static final String EVENT_ADD_USER_LINK = "event.add.user.link";
  static final String EVENT_DELETE_USER_LINK = "event.delete.user.link";
  static final String EVENT_ADD_GROUP_LINK = "event.add.group.link";
  static final String EVENT_DELETE_GROUP_LINK = "event.delete.group.link";
  static final String EVENT_ADD_ATTACHMENT = "event.add.attachment";
  static final String EVENT_DELETE_ATTACHMENT = "event.delete.attachment";
  static final String EVENT_COMMENT = "event.comment";
  static final String EVENT_DEFAULT = "event.default";
  static final String EVENT_TITLE = "event.title";
  
  // Process menu
  static final String PROCESS_MENU_MY_INSTANCES = "process.menu.my.instances";
  static final String PROCESS_MENU_DEPLOYED_DEFINITIONS = "process.menu.deployed.definitions";
  static final String PROCESS_MENU_EDITOR_DEFINITIONS = "process.menu.editor.definitions";
  static final String PROCESS_MENU_INSTANCES = "process.menu.instances";
  
  // Process page
  static final String PROCESS_CATEGORY = "process.category";
  static final String PROCESS_VERSION = "process.version"; 
  static final String PROCESS_DEPLOY_TIME = "process.deploy.time";
  static final String PROCESS_HEADER_DIAGRAM = "process.header.diagram";
  static final String PROCESS_NO_DIAGRAM = "process.no.diagram";
  static final String PROCESS_HEADER_SUSPENSION_STATE = "process.header.suspension.state";
  static final String PROCESS_SCHEDULED_SUSPEND = "process.scheduled.suspend";
  static final String PROCESS_SCHEDULED_ACTIVATE = "process.scheduled.activate";
  static final String PROCESS_START = "process.start";
  static final String PROCESS_EDIT = "process.edit";
  static final String PROCESS_COPY = "process.copy";
  static final String PROCESS_NEW = "process.new";
  static final String PROCESS_IMPORT = "process.import";
  static final String PROCESS_DELETE = "process.delete";
  static final String PROCESS_CANCEL = "process.cancel";
  static final String PROCESS_DEPLOY = "process.deploy";
  static final String PROCESS_ACTIVATE = "process.activate";
  static final String PROCESS_ACTIVATE_POPUP = "process.activate.popup";
  static final String PROCESS_ACTIVATE_POPUP_TIME_DESCRIPTION = "process.activate.popup.time.description";
  static final String PROCESS_ACTIVATE_POPUP_INCLUDE_PROCESS_INSTANCES_DESCRIPTION = "process.activate.popup.process.instances.description";
  static final String PROCESS_SUSPEND = "process.suspend";
  static final String PROCESS_SUSPEND_POPUP = "process.suspend.popup";
  static final String PROCESS_SUSPEND_POPUP_TIME_DESCRIPTION = "process.suspend.popup.time.description";
  static final String PROCESS_SUSPEND_POPUP_TIME_NOW = "process.suspend.popup.time.now";
  static final String PROCESS_SUSPEND_POPUP_TIME_DATE = "process.suspend.popup.time.date";
  static final String PROCESS_SUSPEND_POPUP_INCLUDE_PROCESS_INSTANCES_DESCRIPTION = "process.suspend.popup.process.instances.description";
  static final String PROCESS_TOXML_FAILED = "process.toxml.failed";
  static final String PROCESS_CONVERT = "process.convert";
  static final String PROCESS_EXPORT = "process.export";
  static final String PROCESS_INSTANCE_DELETE = "process.instance.delete";
  static final String PROCESS_INSTANCE_DELETE_POPUP_TITLE = "process.instance.delete.popup.title";
  static final String PROCESS_INSTANCE_CANCEL_POPUP_TITLE = "process.instance.cancel.popup.title";
  static final String PROCESS_INSTANCE_DELETE_POPUP_DESCRIPTION = "process.instance.delete.popup.description";
  static final String PROCESS_INSTANCE_CANCEL_POPUP_DESCRIPTION = "process.instance.cancel.popup.description";
  static final String PROCESS_START_TIME = "process.start.time";
  static final String PROCESS_STARTED_NOTIFICATION = "process.started.notification";
  static final String PROCESS_INSTANCE_STARTED_ON = "process.instance.started.on";
  static final String PROCESS_INSTANCE_STARTED = "process.instance.started";
  static final String PROCESS_INSTANCE_HEADER_TASKS = "process.instance.header.tasks";
  static final String PROCESS_INSTANCE_NO_TASKS = "process.instance.no.tasks";
  static final String PROCESS_INSTANCE_HEADER_VARIABLES = "process.instance.header.variables";
  static final String PROCESS_INSTANCE_NO_VARIABLES = "process.instance.no.variables";
  static final String PROCESS_INSTANCES = "process.instances";
  static final String PROCESS_NO_INSTANCES = "process.no.instances";
  static final String PROCESS_ACTION_VIEW = "process.action.view";
  static final String PROCESS_INSTANCE_ID = "process.instance.id";
  static final String PROCESS_INSTANCE_NAME = "process.instance.name";
  static final String PROCESS_INSTANCE_BUSINESSKEY = "process.instance.businesskey";
  static final String PROCESS_INSTANCE_ACTIONS = "process.instance.actions";
  static final String PROCESS_INSTANCE_VARIABLE_NAME = "process.instance.variable.name";
  static final String PROCESS_INSTANCE_VARIABLE_VALUE = "process.instance.variable.value";
  static final String PROCESS_CONVERT_POPUP_CAPTION = "process.convert.popup.caption";
  static final String PROCESS_CONVERT_POPUP_MESSAGE = "process.convert.popup.message";
  static final String PROCESS_CONVERT_POPUP_CONVERT_BUTTON = "process.convert.popup.convert.button";
  static final String PROCESS_NEW_POPUP_CAPTION = "process.new.popup.caption";
  static final String PROCESS_NEW_POPUP_CREATE_BUTTON = "process.new.popup.create.button";
  static final String PROCESS_COPY_POPUP_CAPTION = "process.copy.popup.caption";
  static final String PROCESS_DELETE_POPUP_CAPTION = "process.delete.popup.caption";
  static final String PROCESS_DELETE_POPUP_MESSAGE = "process.delete.popup.message";
  static final String PROCESS_DELETE_POPUP_DELETE_BUTTON = "process.delete.popup.delete.button";
  
  // Management menu
  static final String MGMT_MENU_DATABASE = "management.menu.database";
  static final String MGMT_MENU_DEPLOYMENTS = "management.menu.deployments";
  static final String MGMT_MENU_ACTIVE_PROCESS_DEFINITIONS = "management.menu.active.processdefinitions";
  static final String MGMT_MENU_SUSPENDED_PROCESS_DEFINITIONS = "management.menu.suspended.processdefinitions";
  static final String MGMT_MENU_JOBS = "management.menu.jobs";
  static final String MGMT_MENU_DEPLOYMENTS_SHOW_ALL = "management.menu.deployments.show.all";
  static final String MGMT_MENU_DEPLOYMENTS_UPLOAD = "management.menu.deployments.upload";
  static final String MGMT_MENU_USERS = "management.menu.users";
  static final String MGMT_MENU_GROUPS = "management.menu.groups";
  static final String MGMT_MENU_ADMINISTRATION = "management.menu.admin";
  static final String MGMT_MENU_STATIC_TABLES = "management.menu.static.tables";
  static final String MGMT_MENU_DYNAMIC_TABLES = "management.menu.dynamic.tables";
  static final String MGMT_MENU_STATIC_TABLES_BIS = "management.menu.static.tables.bis";
  static final String MGMT_MENU_DYNAMIC_TABLES_BIS = "management.menu.dynamic.tables.bis";
  
  // Job page
  static final String JOB_EXECUTE = "job.execute";
  static final String JOB_DELETE = "job.delete";
  static final String JOB_DELETED = "job.deleted";
  static final String JOB_HEADER_EXECUTION = "job.header.execution";
  static final String JOB_RETRIES = "job.retries";
  static final String JOB_NO_RETRIES = "job.no.retries";
  static final String JOB_DEFAULT_NAME = "job.default.name";
  static final String JOB_TIMER = "job.timer";
  static final String JOB_MESSAGE = "job.message";
  static final String JOB_DUEDATE = "job.duedate";
  static final String JOB_NO_DUEDATE = "job.no.dudedate";
  static final String JOB_ERROR = "job.error";
  static final String JOB_NOT_EXECUTED = "job.not.executed";
  static final String JOB_SUSPEND_PROCESSDEFINITION = "job.suspend.processdefinition";
  static final String JOB_ACTIVATE_PROCESSDEFINITION = "job.activate.processdefinition";
  
  // Deployment page
  static final String DEPLOYMENT_DELETE = "deployment.delete";
  static final String DEPLOYMENT_CREATE_TIME = "deployment.create.time";
  static final String DEPLOYMENT_HEADER_DEFINITIONS = "deployment.header.definitions";
  static final String DEPLOYMENT_HEADER_RESOURCES = "deployment.header.resources";
  static final String DEPLOYMENT_UPLOAD = "deployment.upload";
  static final String DEPLOYMENT_UPLOAD_DESCRIPTION = "deployment.upload.description";
  static final String DEPLOYMENT_UPLOAD_FAILED = "deployment.upload.failed";
  static final String DEPLOYMENT_UPLOAD_INVALID_FILE = "deployment.upload.invalid.file";
  static final String DEPLOYMENT_UPLOAD_INVALID_FILE_EXPLANATION = "deployment.upload.invalid.file.explanation";
  static final String DEPLOYMENT_UPLOAD_SERVER_ERROR = "deployment.upload.server.error";
  static final String DEPLOYMENT_DEPLOY_TIME = "deployment.deploy.time";
  static final String DEPLOYMENT_NO_NAME = "deployment.no.name";
  static final String DEPLOYMENT_NO_INSTANCES = "deployment.no.instances";
  static final String DEPLOYMENT_DELETE_POPUP_CAPTION = "deployment.delete.popup.caption";
  static final String DEPLOYMENT_DELETE_POPUP_WARNING = "deployment.delete.popup.warning";
  static final String DEPLOYMENT_DELETE_POPUP_DELETE_BUTTON = "deployment.delete.popup.delete.button";
  
  // Import to model workspace
  static final String MODEL_IMPORT = "model.import";
  static final String MODEL_IMPORT_DESCRIPTION = "model.import.description";
  static final String MODEL_IMPORT_FAILED = "model.import.failed";
  static final String MODEL_IMPORT_INVALID_FILE = "model.import.invalid.file";
  static final String MODEL_IMPORT_INVALID_FILE_EXPLANATION = "model.import.invalid.file.explanation";
  static final String MODEL_IMPORT_INVALID_BPMNDI = "model.import.invalid.bpmndi";
  static final String MODEL_IMPORT_INVALID_BPMNDI_EXPLANATION = "model.import.invalid.bpmndi.explanation";
  static final String MODEL_IMPORT_INVALID_BPMN_EXPLANATION = "model.import.invalid.bpmn.explanation";
  
  static final String MODEL_ACTION = "model.action";
  
  // Database page
  static final String DATABASE_NO_ROWS = "database.no.rows";
  
  // User page
  static final String USER_HEADER_DETAILS = "user.header.details";
  static final String USER_HEADER_GROUPS = "user.header.groups";
  static final String USER_ID = "user.id";
  static final String USER_ID_REQUIRED = "user.id.required";
  static final String USER_ID_UNIQUE = "user.id.unique";
  static final String USER_FIRSTNAME = "user.firstname";
  static final String USER_LASTNAME = "user.lastname";
  static final String USER_EMAIL = "user.email";
  static final String USER_PASSWORD = "user.password";
  static final String USER_PASSWORD_REQUIRED = "user.password.required";
  static final String USER_PASSWORD_MIN_LENGTH = "user.password.min.lenth";
  static final String USER_RESET_PASSWORD = "user.reset.password";
  static final String USER_CREATE = "user.create";
  static final String USER_EDIT = "user.edit";
  static final String USER_DELETE = "user.delete";
  static final String USER_SAVE = "user.save";
  static final String USER_NO_PICTURE = "user.no.picture";
  static final String USER_NO_GROUPS = "user.no.groups";
  static final String USER_CONFIRM_DELETE = "user.confirm.delete";
  static final String USER_CONFIRM_DELETE_GROUP = "user.confirm.delete.group";
  static final String USER_SELECT_GROUPS = "user.select.groups";
  static final String USER_SELECT_GROUPS_POPUP = "user.select.groups.popup";
  static final String USER_ENABLING_START_DATE = "user.enabling.start.date";
  static final String USER_ENABLING_END_DATE   = "user.enabling.end.date";
  static final String USER_ROLE_ADMINISTRATOR  = "user.role.administrator";
  static final String USER_ROLE_GUEST          = "user.role.guest";
  static final String USER_ROLE_SUPERVISOR     = "user.role.supervisor";
  static final String USER_ROLE_OPERATOR       = "user.role.operator";
  static final String USER_ROLE_RESPONSIBLE    = "user.role.responsible";
  static final String USER_NOT_IN_ACTIVITI     = "user.not.in.wits";
  

  // Group page
  static final String GROUP_HEADER_DETAILS = "group.header.details";
  static final String GROUP_HEADER_USERS = "group.header.users";
  static final String GROUP_CREATE = "group.create";
  static final String GROUP_ID = "group.id";
  static final String GROUP_NAME = "group.name";
  static final String GROUP_TYPE = "group.type";
  static final String GROUP_CONFIRM_DELETE = "group.confirm.delete";
  static final String GROUP_ID_REQUIRED = "group.id.required";
  static final String GROUP_ID_UNIQUE = "group.id.unique";
  static final String GROUP_NO_MEMBERS = "group.no.members";
  static final String GROUP_SELECT_MEMBERS = "group.select.members";
  static final String GROUP_DELETE = "group.delete";
  static final String GROUP_NUMBER_MEMBERS = "group.number.members";
  static final String GROUP_DESCRIPTION = "group.description";
  static final String GROUP_OWNER = "group.owner";
  static final String GROUP_TYPE_UNIQUE = "group.type.unique";
  
  // Running process instances page
  static final String ADMIN_MENU_RUNNING = "admin.menu.running";
  static final String ADMIN_MENU_COMPLETED = "admin.menu.completed";
  static final String ADMIN_MENU_DATABASE = "admin.menu.database";
  static final String ADMIN_RUNNING_TITLE = "admin.running.title";
  static final String ADMIN_RUNNING_NONE_FOUND = "admin.running.none.found";
  static final String ADMIN_COMPLETED_TITLE = "admin.completed.title";
  static final String ADMIN_COMPLETED_NONE_FOUND = "admin.completed.none.found";
  static final String ADMIN_DEFINITIONS = "admin.definitions";
  static final String ADMIN_NR_INSTANCES = "admin.nr.instances";
  static final String ADMIN_STARTED_BY = "admin.started.by";
  static final String ADMIN_START_ACTIVITY = "admin.start.activity";
  static final String ADMIN_FINISHED = "admin.finished";
  
  // Database settings page
  static final String DATABASE_TITLE = "database.title";
  static final String DATABASE_TYPE = "database.type";
  static final String DATABASE_UPDATE = "database.update";
  static final String DATABASE_CONFIG_TYPE = "database.config.type";
  static final String DATABASE_JNDI = "database.jndi";
  static final String DATABASE_DATASOURCE_CLASS = "database.datasource.class";
  static final String DATABASE_DATASOURCE_URL = "database.datasource.url";
  static final String DATABASE_JDBC_URL = "database.jdbc.url";
  
  // Upload
  static final String UPLOAD_SELECT = "upload.select";
  static final String UPLOAD_DROP = "upload.drop";
  static final String UPLOAD_FAILED = "upload.failed";
  static final String UPLOAD_LIMIT = "upload.limit";
  static final String UPLOAD_INVALID_MIMETYPE = "upload.invalid.mimetype";
  static final String UPLOAD_DOCUMENT_TYPE    = "upload.document.type";
  

  // Related content
  static final String RELATED_CONTENT_ADD = "related.content.add";
  static final String RELATED_CONTENT_NAME = "related.content.name";
  static final String RELATED_CONTENT_NAME_REQUIRED = "related.content.name.required";
  static final String RELATED_CONTENT_DESCRIPTION = "related.content.description";
  static final String RELATED_CONTENT_CREATE = "related.content.create";
  static final String RELATED_CONTENT_DOCUMENT_TYPE_REQUIRED = "related.content.document.type.required";
  
  static final String RELATED_CONTENT_TYPE_URL = "related.content.type.url";
  static final String RELATED_CONTENT_TYPE_URL_URL = "related.content.type.url.url";
  static final String RELATED_CONTENT_TYPE_URL_URL_REQUIRED = "related.content.type.url.url.required";;
  static final String RELATED_CONTENT_TYPE_URL_HELP = "related.content.type.url.help";
  
  static final String RELATED_CONTENT_TYPE_FILE = "related.content.type.file";
  static final String RELATED_CONTENT_TYPE_FILE_HELP = "related.content.type.file.help";
  static final String RELATED_CONTENT_TYPE_FILE_UPLOADED = "related.content.type.file.uploaded";
  static final String RELATED_CONTENT_TYPE_FILE_REQUIRED = "related.content.type.file.required";
  static final String RELATED_CONTENT_CONFIRM_DELETE = "related.content.confirm.delete";
  static final String RELATED_CONTENT_SHOW_FULL_SIZE = "related.content.show.full.size";
  
  static final String RELATED_CONTENT_TYPE_EMAIL = "related.content.type.email";
  
  // People involvement
  static final String PEOPLE_SEARCH = "people.search";
  static final String PEOPLE_INVOLVE_POPUP_CAPTION = "people.involve.popup.caption";
  static final String PEOPLE_SELECT_MYSELF = "people.select.myself";

  static final String TASK_AUTHORISATION_ERROR_TITLE = "task.authorisation.error.title";
  static final String TASK_AUTHORISATION_MEMBERSHIP_ERROR = "task.authorisation.membership.error";
  static final String TASK_AUTHORISATION_INBOX_ERROR = "task.authorisation.inbox.error";

  static final String EMAIL_SUBJECT = "email.subject";
  static final String EMAIL_SENT_DATE = "email.sent.date";
  static final String EMAIL_RECEIVED_DATE = "email.received.date";
  static final String EMAIL_HTML_CONTENT = "email.html.content";
  static final String EMAIL_RECIPIENTS = "email.recipients";

  // Time formatting
  static final String TIME_UNIT_MOMENTS = "time.unit.moments";
  static final String TIME_UNIT_PAST = "time.unit.past";
  static final String TIME_UNIT_FUTURE = "time.unit.future";
  
  static final String TIME_UNIT_MINUTE = "time.unit.minute";
  static final String TIME_UNIT_MINUTES = "time.unit.minutes";
  static final String TIME_UNIT_HOUR = "time.unit.hour";
  static final String TIME_UNIT_HOURS = "time.unit.hours";
  static final String TIME_UNIT_DAY = "time.unit.day";
  static final String TIME_UNIT_DAYS = "time.unit.days";
  static final String TIME_UNIT_WEEK = "time.unit.week";
  static final String TIME_UNIT_WEEKS = "time.unit.weeks";
  static final String TIME_UNIT_MONTH = "time.unit.month";
  static final String TIME_UNIT_MONTHS = "time.unit.months";
  static final String TIME_UNIT_YEAR = "time.unit.year";
  static final String TIME_UNIT_YEARS = "time.unit.years";
  static final String TIME_UNIT_JUST_NOW = "time.unit.just.now";
  
  static final String OPERATION_NOT_ALLOWED    = "operation.not.allowed";

  // workflow wiew
  static final String MAIN_MENU_WORKFLOW  = "main.menu.workflow";

  // task to do page
  static final String COMBO_PROCESS_TYPE          = "workflow.view.combo.process.type";
  static final String COMBO_PROCESS_STEP          = "workflow.view.combo.process.step";
  static final String COMBO_NO_SELECTED           = "workflow.view.combo.no.selected";
  static final String WRONG_TASK_LIST_SELECTED    = "workflow.view.wrong.task.list.selected";
  // ROLES
  static final String USER_WITH_ROLE_INCORRECT    = "user.with.role.incorrect";
  static final String USER_WITH_NO_ROLE           = "user.with.no.role";
  static final String CHOOSE_ROLES                = "choose.roles";
  static final String LIST_ROLES                  = "list.roles";
  static final String NO_ROLES_CHOSE              = "no.roles.chose";
  static final String USER_ROLE                   = "user.role";
  
  // RWMO Process
  static final String RWMO_PRINT_BARCODE_WAMAT_OBJECT   = "process.rwmo.print.barcode.wamat.object";
  static final String RWMO_NUMBER_MAX_REQUESTS_POSSIBLE = "process.rwmo.number.max.requests.possible";
  static final String RWMO_WAMAT_OBJECT_CODE            = "process.rwmo.wamat.object.code";
  static final String RWMO_CONTAINING_UNIT_CODE         = "process.rwmo.containing.unit.code";
  static final String RWMO_PROCESS_CONTAINER_TYPE  = "process.rwmo.container.type";
  static final String RWMO_USER_REQUESTING         = "process.rwmo.user.requesting";
  static final String RWMO_RECIPIENT               = "process.rwmo.recipient";
  static final String RWMO_QUANTITY                = "process.rwmo.quantity";
  static final String RWMO_POSITION                = "process.rwmo.position";
  static final String RWMO_BARCODE_PRINTED         = "process.rwmo.barcode.printed";
  static final String RWMO_BARCODE_PRINTED_YES     = "process.rwmo.barcode.printed.yes";
  static final String RWMO_BARCODE_PRINTED_NO      = "process.rwmo.barcode.printed.no";
  static final String RWMO_BARCODE_COMBO_BOX_LABEL = "process.rwmo.barcode.combo.box.label";
  static final String RWMO_BARCODE_DELIVERY_DATE   = "process.rwmo.barcode.delivery.date";
  static final String RWMO_PRINT_BARCODE           = "process.rwmo.print.barcode";
  static final String RWMO_BARCODE_PRINTED_CORRECTLY         = "process.rwmo.barcode.printed.corretly";
  static final String RWMO_NO_TABLE_ROW_SELECTED             = "process.rwmo.no.table.row.selected";
  static final String RWMO_BARCODE_READING_TEXT_AREA_LABEL   = "process.rwmo.barcode.reading.text.area.label";
  static final String RWMO_BARCODE_ERRORS_TEXT_AREA_LABEL    = "process.rwmo.barcode.errors.text.area.label";
  static final String RWMO_BARCODE_READING_BUTTON_SEEK_LABEL = "process.rwmo.barcode.reading.button.seek.label";
  static final String RWMO_BARCODE_READING_BUTTON_DELETE_LABEL = "process.rwmo.barcode.reading.button.delete.label";
  static final String RWMO_BARCODE_READING_BUTTON_ADD_LABEL    = "process.rwmo.barcode.reading.button.add.label";
  static final String RWMO_BARCODE_READING_BUTTON_SAVE_LABEL   = "process.rwmo.barcode.reading.button.save.label";
  static final String RWMO_BARCODE_READING_BUTTON_PROCESS      = "process.rwmo.barcode.reading.button.process";
  static final String RWMO_CLEAR_FORM_TEXT_BUTTON              = "process.rwmo.clear.form.text.button";
  static final String RWMO_WAMAT_OBJECT_CANCEL                 = "process.rwmo.wamat.object.cancel";
  static final String RWMO_BARCODE_READING_OBJECT              = "process.rwmo.barcode.reading.object";
  static final String RWMO_BARCODE_READING_OBJECT_TYPE         = "process.rwmo.barcode.reading.object.type";
  static final String RWMO_BARCODE_READING_STATUS              = "process.rwmo.barcode.reading.status";
  static final String RWMO_BARCODE_READING_STATUS_DESCRIPTION  = "process.rwmo.barcode.reading.status.description";
  static final String RWMO_RECIPIENT_TYPE                      = "process.rwmo.recipient.type";
  static final String RWMO_RECIPIENT_TYPE_MOVABLE              = "process.rwmo.recipient.type.mobile";
  static final String RWMO_RECIPIENT_TYPE_IMMOVABLE            = "process.rwmo.recipient.type.immobile";
  static final String RWMO_RECIPIENT_CONTAINER_UNIT            = "process.rwmo.container.unit";
  static final String RWMO_RECIPIENT_CONTAINER_UNIT_PACKAGED   = "process.rwmo.container.unit.packaged";
  static final String RWMO_RECIPIENT_CONTAINER_UNIT_UNPACKAGED = "process.rwmo.container.unit.unpackaged";
  static final String RWMO_RECIPIENT_WAMAT_OBJECT              = "process.rwmo.wamat.object";
  static final String RWMO_RECIPIENT_CONTAINER_UNIT_NOT_FOUND  = "process.rwmo.recipient.container.unit.not.found";
  static final String RWMO_OBJECT_CONTAINER_UNIT_NOT_FOUND     = "process.rwmo.object.container.unit.not.found";
  static final String RWMO_OBJECT_NOT_FOUND                    = "process.rwmo.oggetto.not.found";
  static final String RWMO_STATUS_ERROR                        = "process.rwmo.status.error";
  static final String RWMO_STATUS_WARNING                      = "process.rwmo.status.warning";
  static final String PROCESS_NUMBER_FORMAT_EXCEPTION          = "process.number.format.exception";
  static final String RWMO_COORDINATE_BOUND                    = "process.rwmo.coordinate.bound";
  static final String WAMAT_OBJECT_PACKAGED                    = "wamat.object.packaged";
  static final String WAMAT_OBJECT_UNPACKAGED                  = "wamat.object.unpackaged";
  static final String RWMO_BARCODE_READING_OBJECT_MOVED        = "process.rwmo.barcode.reading.object.moved";
  static final String RWMO_BARCODE_READING_ACTIVE_BUTTON       = "process.rwmo.barcode.reading.active.button";
  static final String RWMO_BARCODE_READING_NO_ACTIVE_BUTTON    = "process.rwmo.barcode.reading.no.active.button";
  static final String RWMO_TABLE_ROW_DELETE_POPUP_TITLE        = "process.rwmo.table.row.delete.popup.title";
  static final String RWMO_TABLE_ROW_DELETE_POPUP_DESCRIPTION  = "process.rwmo.table.row.delete.popup.description";
  static final String RWMO_BARCODE_PRINTED_EVENT               = "process.rwmo.barcode.printed.event";
  static final String RWMO_STARTPAGE_OPTIONGROUP_CU_REQUESTED  = "process.rwmo.startpage.optiongroup.containing.requested";
  static final String RWMO_STARTPAGE_OPTIONGROUP_CU_EXISTING   = "process.rwmo.startpage.optiongroup.containing.existing";
  static final String RWMO_STARTPAGE_OPTIONGROUP_NO_CU         = "process.rwmo.startpage.optiongroup.no.containing";
  static final String RWMO_STARTPAGE_OPTIONGROUP_OVERPACK      = "process.rwmo.startpage.optiongroup.overpack";  
  static final String RWMO_STARTPAGE_NO_OWNER                  = "process.rwmo.startpage.no.owner";
  static final String RWMO_BARCODE_NO_PRINTED_WARNING          = "process.rwmo.barcode.no.printed.warning";
  static final String RWMO_WMP_WAC_NOT_CONSIDERED              = "process.rwmo.wmp.wac.not.considered";
  static final String RWMO_BARCODE_SELECTED_HAVE_TO_BE_ALL_OVERPACK_OR_ALL_NOT_OVERPACK              
  = "process.rwmo.barcode.selected.have.to.be.all.overpack.or.all.not.overpack";
  
  // Static view wamatObject
  static final String VIEW_STATIC_LIST_WAMAT_OBJECT_ID         = "list.wamat.objects.id";
  static final String VIEW_STATIC_LIST_WAMAT_OBJECT_CODE       = "list.wamat.objects.code";
  static final String VIEW_STATIC_LIST_WAMAT_OBJECT_MATERIAL   = "list.wamat.objects.material";
  static final String VIEW_STATIC_LIST_WAMAT_OBJECT_OWNER      = "list.wamat.objects.owner";
  static final String VIEW_STATIC_LIST_WAMAT_OBJECT_STATE      = "list.wamat.objects.state";
  static final String VIEW_STATIC_SEARCH_WAMAT_OBJECT_PROCESS  = "search.wamat.objects.process";
  static final String VIEW_STATIC_SEARCH_WAMAT_OBJECT_WAC      = "search.wamat.objects.wac";
  static final String VIEW_STATIC_SEARCH_CONT_UNIT_IMMOVABLE   = "search.containing.unit.immovable";
  static final String VIEW_STATIC_SEARCH_WAMAT_OBJECT_ID       = "search.wamat.object.id";
  static final String VIEW_STATIC_SEARCH_IS_IN_WORKFLOW        = "search.is.in.workflow";
  static final String VIEW_STATIC_SEARCH_APPLY_FILTERS         = "search.apply.filters";
  static final String VIEW_STATIC_SEARCH_IMMOVABLE_SELECTED    = "search.wamat.objects.immovable.selected";
  static final String VIEW_STATIC_SEARCH_MATERIAL_SELECTED     = "search.wamat.objects.material.selected";
  static final String VIEW_STATIC_CLEAR_APPLY_FILTERS          = "search.clear.filters";
  static final String VIEW_STATIC_BUTTON_SHOW_FILTERS          = "button.show.filters";
  static final String VIEW_STATIC_TAB_SHEET_DESCRIPTION        = "tab.sheet.description";
  static final String VIEW_STATIC_TAB_SHEET_ITEM               = "tab.sheet.item";
  static final String VIEW_STATIC_TAB_SHEET_MISURE             = "tab.sheet.misure";
  static final String VIEW_STATIC_TAB_SHEET_EVENTS             = "tab.sheet.events";
  static final String VIEW_STATIC_TAB_SHEET_ATTACHED           = "tab.sheet.attached";
  static final String VIEW_STATIC_TAB_SHEET_HISTORY            = "tab.sheet.history";
  static final String VIEW_STATIC_TAB_SHEET_WORKFLOW           = "tab.sheet.workflow";
  static final String VIEW_STATIC_TAB_SHEET_OTHER              = "tab.sheet.other";
  static final String VIEW_STATIC_WAMAT_PROCESS_GENERATION     = "wamat.object.process.generation";
  static final String VIEW_STATIC_WAMAT_FIRST_CU_IMMOVABLE     = "wamat.object.first.cu.immovable";
  static final String VIEW_STATIC_WAMAT_PROCESS_LOCATION       = "wamat.object.process.location";
  static final String VIEW_STATIC_WAMAT_PROCESS_NAME           = "wamat.object.process.name";
  static final String VIEW_STATIC_WAMAT_DATE_GENERATION        = "wamat.object.date.generation";
  static final String VIEW_STATIC_WAMAT_NET_MASS               = "wamat.object.net.mass";
  static final String VIEW_STATIC_WAMAT_FILLING_LEVEL          = "wamat.object.filling.level";
  static final String VIEW_STATIC_WAMAT_HOMOGENEOUS_LOT        = "wamat.object.homogeneous.lot";
  static final String VIEW_STATIC_WAMAT_HOMOGENEOUS_GROUP      = "wamat.object.homogeneous.group";
  static final String VIEW_STATIC_WAMAT_PROCESS_FINAL          = "wamat.object.process.final";
  static final String VIEW_STATIC_WAMAT_PROCESSING_DATE        = "wamat.object.processing.date";
  static final String VIEW_STATIC_WAMAT_INVENTORY              = "wamat.object.inventory";
  static final String VIEW_STATIC_WAMAT_LIST_WAC               = "wamat.object.list.wac";
  static final String VIEW_STATIC_WAMAT_HIERARCHY_CONT_UNIT    = "wamat.object.hierarchy.containing.unit";
  static final String VIEW_STATIC_WAMAT_TYPE_CONT_UNIT         = "wamat.object.type.containing.unit";
  static final String VIEW_STATIC_WAMAT_INVENTORY_EXTERN       = "wamat.object.inventory.extern";
  static final String VIEW_STATIC_WAMAT_COMPLIANCE_WAC         = "wamat.object.compliance.wac";
  static final String VIEW_STATIC_WAMAT_COMPLIANCE_WAC_REQ     = "wamat.object.compliance.wac.requested";
  static final String VIEW_STATIC_WAMAT_COMPLIANCE_WAC_DECLARED     = "wamat.object.compliance.wac.declared";
  static final String VIEW_STATIC_WAMAT_POSITIONX              = "wamat.object.positionx";
  static final String VIEW_STATIC_WAMAT_POSITIONY              = "wamat.object.positiony";
  static final String VIEW_STATIC_WAMAT_POSITIONZ              = "wamat.object.positionz";
  static final String VIEW_STATIC_WAMAT_NEXT                   = "wamat.object.next";
  static final String VIEW_STATIC_WAMAT_PREVIOUS               = "wamat.object.previous";
  static final String VIEW_STATIC_WAMAT_PRINT_SCHEDULE         = "wamat.object.print.schedule";
  static final String VIEW_STATIC_WAMAT_SHOW_DETAILS           = "wamat.object.show.details";
  static final String VIEW_STATIC_WAMAT_LIST_ITEMS             = "wamat.object.list.items";
  static final String VIEW_STATIC_WAMAT_TABLE_FIELD_NUMBER     = "wamat.object.table.field.number";
  static final String VIEW_STATIC_WAMAT_TABLE_FIELD_DATE       = "wamat.object.table.field.date";
  static final String VIEW_STATIC_WAMAT_TABLE_FIELD_QUANTITY   = "wamat.object.table.field.quantity";
  static final String VIEW_STATIC_WAMAT_TABLE_RIF_SIMPLE       = "wamat.object.table.field.rif.simple";
  static final String VIEW_STATIC_WAMAT_TABLE_PROCESS_INSTANCE = "wamat.object.table.field.instance.process";
  static final String VIEW_STATIC_WAMAT_TABLE_ATTACHED         = "wamat.object.table.field.document.attached";
  static final String VIEW_STATIC_WAMAT_WORKFLOW_INITIATOR     = "wamat.object.workflow.initiator";
  static final String VIEW_STATIC_SEARCH_LEFT_INTERVAL         = "wamat.object.filter.left.interval";
  static final String VIEW_STATIC_SEARCH_RIGHT_INTERVAL        = "wamat.object.filter.right.interval";
  static final String VIEW_STATIC_SEARCH_CHECKS_BOUNDS_INTERVAL = "wamat.object.filter.ckeck.bounds.interval";
  
  
  /* Multi Tab Vista Statica Oggetti */
  static final String VIEW_STATIC_WAMAT_NOT_FOUND              = "wamat.object.table.wamat.not.found";
  static final String VIEW_STATIC_TABLE_ATTACHED_NO_WAMAT      = "wamat.object.table.field.document.attached.no.wamat";
  static final String VIEW_STATIC_WAMAT_LIST_MISURE            = "wamat.object.list.misure";
  static final String VIEW_STATIC_WAMAT_LIST_EVENTS            = "wamat.object.list.events";
  static final String VIEW_STATIC_WAMAT_LIST_HISTORY            = "wamat.object.list.history";
  static final String VIEW_STATIC_WAMAT_LIST_EVENTS_USER       = "wamat.object.list.events.user";
  static final String VIEW_STATIC_WAMAT_LIST_EVENTS_W_I        = "wamat.object.list.events.workflow.instance";
  static final String VIEW_STATIC_WAMAT_LIST_ATTACHED          = "wamat.object.list.attached";
  static final String VIEW_STATIC_WAMAT_LIST_ATTACHED_REFER    = "wamat.object.list.attached.refer";
  static final String VIEW_STATIC_WAMAT_LIST_FIELD             = "wamat.object.list.field";
  static final String VIEW_STATIC_WAMAT_LIST_VALUE             = "wamat.object.list.value";
  static final String VIEW_STATIC_WAMAT_LIST_WORKFLOW_ACTIVE   = "wamat.object.list.workflow.active";
  static final String VIEW_STATIC_WAMAT_MODIFY_TYPE            = "wamat.object.modify.type";
  static final String VIEW_STATIC_WAMAT_OTHER_INFORMATION      = "wamat.object.other.information";
  /* workflow Set Location Object */
  
  static final String SOL_NO_BARCODE_READ                      = "process.sol.no.barcode.read";
  static final String SOL_TABLE_BARCODE_INCORRECT              = "process.sol.table.barcode.incorrect";
  static final String SOL_OBJECT_LOCALIZED                     = "process.sol.object.localized";
  
  /* workflow Processing wamat Object */

  static final String PWMO_PRELIMINARY_INFORMATION             = "process.pwmo.preliminary.information";
  static final String PWMO_WMP                                 = "process.pwmo.wmp";
  static final String PWMO_TEXT_HELP                           = "process.pwmo.text.help";
  static final String PWMO_START_DATE                          = "process.pwmo.start.date";
  static final String PWMO_END_DATE                            = "process.pwmo.end.date";
  static final String PWMO_PROCESS_NAME                        = "process.pwmo.name.process";
  static final String PWMO_SELECT_WAMAT_INPUT_PROCESS          = "process.pwmo.select.wamat.input.process";
  static final String PWMO_NO_WAMAT_INPUT_PROCESS              = "process.pwmo.select.no.wamat.input.process";
  static final String PWMO_LIST_WAMAT_INPUT_PROCESS            = "process.pwmo.list.wamat.input.process";
  static final String PWMO_WAMAT_SEARCH                        = "process.pwmo.wamat.search";
  static final String PWMO_WAMAT_INPUT_PROCES                  = "process.pwmo.wamat.input";
  static final String PWMO_WAMAT_CONFIRM_DELETE                = "process.pwmo.wamat.confirm.delete";
  static final String PWMO_WAMAT_DELETE_WAMAT_INPUT            = "process.pwmo.delete.wamat.input";
  static final String PWMO_INSTANCE_PROCESS                    = "process.pwmo.instance.process";
  static final String PWMO_INSTANCE_PROCESS_ATTACHMENTS        = "process.pwmo.instance.process.attachments";
  static final String PWMO_LIST_WAMAT_OBJECTS_INPUT            = "process.pwmo.list.wamat.objects.input";
  static final String PWMO_LIST_ITEMS_PRODUCED                 = "process.pwmo.list.items.produced";
  static final String PWMO_ADD_ITEM                            = "process.pwmo.list.add.item";
  static final String PWMO_NUMBER_ITEM                         = "process.pwmo.list.number.item";
  static final String PWMO_DELETE_ITEM                         = "process.pwmo.list.delete.item";
  static final String PWMO_DUPLICATE_ITEM                      = "process.pwmo.duplicate.item";
  static final String PWMO_ITEM_CONFIRM_DELETE                 = "process.pwmo.item.confirm.delete";
  static final String PWMO_ITEM_UPDATE                         = "process.pwmo.item.update";
  static final String PWMO_NO_RELATED_ITEM                     = "process.pwmo.no.related.item";
  static final String PWMO_INSTANCE_PROCESS_DELETE             = "process.pwmo.instance.process.delete";
  static final String PWMO_CONFIRM_INSTANCE_PROCESS_DELETE     = "process.pwmo.process.instance.confirm.delete";
  static final String PWMO_NO_ITEM_DELETE                      = "process.pwmo.no.item.delete";
  static final String PWMO_NO_ISTANCE_PROCESS_DELETE           = "process.pwmo.no.process.instance.delete";
  static final String PWMO_NO_ENDDATE_AFTER_STARDATE           = "process.pwmo.enddate.after.end.date";
  static final String PWMO_NAME_INSTANCE_PWMO_ALREADY_PRESENT  = "process.pwmo.name.instance.pwmo.already.present";
  
  // Workflow Characterization CWMO
  static final String CWMO_SCHEDULE_DETAILS                    = "process.cwmo.schedule";
  static final String CWMO_CLOSING_ITEMIZING_DATE              = "process.cwmo.closing.itemizing.date";
  static final String CWMO_WARNING_CLOSURE_WAMAT               = "process.cwmo.warning.closure.wamat";
  static final String CWMO_CHECK_CLOSING_ITEMIZING_DATE        = "process.cwmo.ckeck.closing.itemizing.date";
  static final String CWMO_NO_ITEMS                            = "process.cwmo.no.items";
  static final String CWMO_UNIT_MISURE                         = "process.cwmo.unit.misure";
  static final String CWMO_WAMAT_ATTACHMENTS                   = "process.cwmo.wamat.attachments";
  static final String CWMO_WARNING_CLOSURE_CHARACTERIZATION    = "process.cwmo.warning.closure.characterization";
  static final String CWMO_WAMAT_MAIN_FUTURES                  = "process.cwmo.main.futures";
  static final String CWMO_WAMAT_FUTURE                        = "process.cwmo.future";
  static final String CWMO_CHECK_RP_MEASUREMENT_DATE           = "process.cwmo.ckeck.rp.measurement.date";
  static final String CWMO_INSERT_NET_MASS                     = "process.cwmo.insert.net.mass";
  static final String CWMO_MEASUREMENT_PROTOCOL                = "process.cwmo.measurement.protocol";
  static final String CWMO_NET_WEIGTH_MEASURED                 = "process.cwmo.net.weigth.measured";
  static final String CWMO_WAMAT_MAIN_OTHERS_FUTURES           = "process.cwmo.main.others.futures";
  static final String CWMO_WAMAT_OTHERS_FUTURES_CONFIRM_DELETE = "process.cwmo.others.futures.confirm.delete";
  static final String CWMO_WAMAT_ADD_FUTURES                   = "process.cwmo.add.futures";
  static final String CWMO_WARNING_ON_BUTTON_ADD_DELETE        = "process.cwmo.warning.on.button.add.delete";
  static final String CWMO_INVALID_VALUE_BOOLEAN               = "process.cwmo.invalid.value.boolean";
  static final String CWMO_GO_BACK_SCHEDULING                  = "process.cwmo.go.back.scheduling";
  static final String CWMO_GO_BACK_CHARACTERIZATION            = "process.cwmo.go.back.characterization";
  static final String CWMO_SUSPEND_CHARACTERIZATION            = "process.cwmo.suspend.characterization";
  static final String CWMO_COMMENT                             = "process.cwmo.comment";
  static final String CWMO_COMMENT_MAX_SIZE                    = "process.cwmo.comment.max.size";
  static final String CWMO_NAME_HOMOGENEOUSLOT_ALREADY_PRESENT = "process.cwmo.name.homogeneouslot.already.present";
  
  /*
   * Validation
   */
  static final String VALUE_HAS_BE_INT                         = "value.has.be.int";
  static final String VALUE_HAS_BE_BETWEEN_RANGE               = "value.has.be.between.range";
  static final String VALUE_IS_NOT_INTEGER                     = "value.is.not.int";
  
  /*
   * Web app version-branch-revision
   * 
   * 
   */
  static final String WITS_VERSION_BRANCH_REVISION             = "webapp.version.revision";

  static final String WIEW_STATIC_WORKFLOW_TASK_CURRENT_DATE   = "view.static.workflow.task.current.create.date";
  static final String WIEW_STATIC_WORKFLOW_DESCRIPTION_RADIO   = "view.static.workflow.description.radioproctection";
  
}
