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
package org.front.end.wits.vaadin.identity;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.persistence.layer.wits.dao.to.GenericRole;
import org.persistence.layer.wits.enumusertypes.RoleStType;
import org.persistence.layer.wits.form.WitsUserSt;
import org.services.layer.wits.services.WitsUser2RoleGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Wrapper around a {@link User}, containing the data of a logged in user
 * and adding data such as security roles, etc.
 * 
 * @author Mennea Giuseppe
 */
public class LoggedInUserImpl implements LoggedInUser {

	private static final long serialVersionUID = 1L;

	protected User user;
	protected String password;
	protected String alternativeId;
	protected static Logger logger = LoggerFactory.getLogger(LoggedInUserImpl.class);

	protected GenericRole genericRole;

	protected boolean isResponsible;
	protected boolean isSupervisor;
	protected boolean isOperator;
	protected boolean isGuest;
	protected boolean isAdmin;
	protected WitsUserSt witsUserSt;
	protected WitsUser2RoleGroupService witsUser2RoleGroupService;
	protected List<Group> securityRoles = new ArrayList<Group>();
	protected List<Group> groups = new ArrayList<Group>();

	public LoggedInUserImpl(User user, String password , 
			WitsUser2RoleGroupService witsUser2RoleGroupService ) {
		this.user = user;
		this.password = password;
		resetRoles();
		this.witsUser2RoleGroupService = witsUser2RoleGroupService;
	}
	public String getId() {
		if(user != null) {
			return user.getId();
		}
		return alternativeId;
	}
	public String getFirstName() {
		if(user != null) {
			return user.getFirstName();
		}
		return null;
	}
	public String getLastName() {
		if(user != null) {
			return user.getLastName();
		}
		return null;
	}
	public String getFullName() {
		if(user != null) {
			return getFirstName() + " " + getLastName();
		}
		return null;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		user.setPassword(password);
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public void addSecurityRoleGroup(Group securityRoleGroup) {
		securityRoles.add(securityRoleGroup);
	}
	public List<Group> getSecurityRoles() {
		return securityRoles;
	}
	public void addGroup(Group group) {
		groups.add(group);
	}
	@Override
	public GenericRole getGenericRole() {
		return this.genericRole;
	}
	public void setGenericRole(GenericRole genericRole) {
		this.genericRole = genericRole;
	}
	@Override
	public boolean isResponsible() {
		return this.isResponsible;
	}
	@Override
	public boolean isSupervisor() {
		return this.isSupervisor;
	}
	@Override
	public boolean isOperator() {
		return this.isOperator;
	}
	@Override
	public boolean isGuest() {
		return this.isGuest;
	}
	public void setResponsible(boolean isResponsible) {
		this.isResponsible = isResponsible;
	}
	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}
	public void setOperator(boolean isOperator) {
		this.isOperator = isOperator;
	}
	public void setGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}
	@Override
	public User getUser() {
		return user;
	}
    protected void resetRoles()
    {
		this.isResponsible = false;
		this.isSupervisor = false;
		this.isOperator = false;
		this.isGuest = false;
		this.isAdmin = false;
    }
	@Override
	public void initializeLoggedInUser()
	{
		resetRoles();
		if (genericRole!=null && genericRole.getWitsUser2RoleGroupId()!=null)
		{
			switch (RoleStType.valueOf(witsUser2RoleGroupService.getWitsRoleName(genericRole.getWitsUser2RoleGroupId())))
			{
			case RESPONSABILE :
			{
				setResponsible(true);
				break;
			}
			case SUPERVISORE :
			{
				setSupervisor(true);
				break;
			}
			case OPERATORE :
			{
				setOperator(true);
				break;
			}
			case OSPITE :
			{
				setGuest(true);
				break;
			}
			case AMMINISTRATORE :
			{
				setAdmin(true);
				break;
			}
			default :
			{
				setGuest(true);
				logger.error("Ruolo non trovato per utente : " + user.getId() + " assunto default guest!");
				break; 
			}
			}
		}
		else
		{
			logger.error("Ruolo non trovato per utente : " + user.getId() + " !");
		}
	}
	public WitsUserSt getWitsUserSt() {
		return witsUserSt;
	}
	public void setWitsUserSt(WitsUserSt witsUserSt) {
		this.witsUserSt = witsUserSt;
	}

}
