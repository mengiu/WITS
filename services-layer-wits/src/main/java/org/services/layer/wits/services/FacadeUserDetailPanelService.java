package org.services.layer.wits.services;

import java.util.List;

import org.activiti.engine.identity.User;
import org.persistence.layer.wits.enumusertypes.RoleStType;
import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.WitsUser2RoleGroup;
import org.persistence.layer.wits.form.WitsUserSt;

public interface FacadeUserDetailPanelService {
    void addRoleAdministratorOrGuest(WitsUserSt witsUserSt,List<RoleStType> listRoleStType);
	void addRoleResponsibleAtTheGroupSt(List<WitsUserSt> listWitsUserSt,GroupSt groupSt);
    void addRoleSupervisorOrOperator(List<WitsUserSt> listWitsUserSt,List<RoleStType> listRoleStType,
    		GroupSt groupSt);
	List<WitsUser2RoleGroup> listWitsUser(RoleStType roleStType,
			GroupSt groupSt);
	List<WitsUser2RoleGroup> listWitsUser(GroupSt groupSt);
	void addUserAtTheGroupSt(List<WitsUserSt> listWitsUserSt,
			GroupSt groupSt,
			List<RoleStType> listRoleStType);
    void removeUserWits(User user);
}
