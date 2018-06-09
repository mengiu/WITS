package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.GroupTypeSt;

public interface GroupTypeStDAO {
	public int addGroupTypeSt(GroupTypeSt wg );
	public List<GroupTypeSt> listGroupTypeSt();
	public void removeGroupTypeSt(int id);
	public GroupTypeSt getGroupTypeSt(int id);
	public void updateGroupTypeSt(GroupTypeSt wg );

}
