package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.GroupTypeSt;
import org.persistence.layer.wits.form.OwnerSt;
import org.persistence.layer.wits.form.RoleSt;
import org.persistence.layer.wits.form.WitsUser2RoleGroup;
import org.persistence.layer.wits.form.WitsUser2RoleGroupId;
import org.persistence.layer.wits.form.WitsUserSt;

public interface WitsUser2RoleGroupDAO {
	public WitsUser2RoleGroupId addWitsUser2RoleGroup(WitsUser2RoleGroup wurg );
	public List<WitsUser2RoleGroup> listWitsUser2RoleGroup();
	public void removeWitsUser2RoleGroup(WitsUser2RoleGroupId id);
	public WitsUser2RoleGroup getWitsUser2RoleGroup(WitsUser2RoleGroupId id);
	public String getWitsRoleName(WitsUser2RoleGroupId id);
	public void updateWitsUser2RoleGroup(WitsUser2RoleGroup wurg );
	public List<WitsUser2RoleGroup> listWitsUser2RoleGroup(WitsUserSt user);
	public List<WitsUser2RoleGroup> listWitsUser2RoleGroup(RoleSt role, GroupTypeSt witsGroup);
	public List<WitsUser2RoleGroup> listWitsUser2RoleGroup(RoleSt role, GroupTypeSt witsGroup , OwnerSt ownerSt);
	public List<WitsUser2RoleGroup> listWitsUser2RoleGroup(RoleSt role, GroupSt witsInstanceGroup);
	public String getWitsUserName(WitsUser2RoleGroupId id);
	public List<WitsUser2RoleGroup> listWitsUser2RoleGroup(GroupSt groupSt);
	public WitsUser2RoleGroup getWitsUser2RoleGroup(RoleSt role,
			GroupTypeSt witsGroup, WitsUserSt witsUserSt);
	public WitsUser2RoleGroup getWitsUser2RoleGroup(GroupSt groupSt,WitsUserSt witsUserSt,
			RoleSt role);
	public List<WitsUser2RoleGroup> listWitsUser2RoleGroup(WitsUserSt witsUserSt, RoleSt role);
	public GroupSt getGroupSt(WitsUser2RoleGroupId id);
	
}
