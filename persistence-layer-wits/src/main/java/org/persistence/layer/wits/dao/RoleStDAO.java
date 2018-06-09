package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.RoleSt;

public interface RoleStDAO {
	public int addRoleSt(RoleSt role );
	public List<RoleSt> listRoleSt();
	public void removeRoleSt(int id);
	public RoleSt getRoleSt(int id);
	public void updateRoleSt(RoleSt role );
	public RoleSt getRoleSt(String name );

}
