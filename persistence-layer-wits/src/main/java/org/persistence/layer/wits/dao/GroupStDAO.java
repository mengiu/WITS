package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.GroupSt;
import org.persistence.layer.wits.form.GroupTypeSt;
import org.persistence.layer.wits.form.OwnerSt;
import org.persistence.layer.wits.form.RoleSt;


public interface GroupStDAO {
	public int addGroupSt(GroupSt wig );
	public List<GroupSt> listGroupSt();
	public void removeGroupSt(int id);
	public GroupSt getGroupSt(int id);
	public void updateGroupSt(GroupSt wig );
	public GroupSt getGroupSt(GroupTypeSt groupTypeSt);
	public GroupSt getGroupSt(GroupTypeSt groupTypeSt, OwnerSt ownerSt);
}
