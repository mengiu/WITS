package org.persistence.layer.wits.dao.to.impl;

import org.persistence.layer.wits.dao.to.GenericRole;
import org.persistence.layer.wits.form.WitsUser2RoleGroupId;

public class GenericRoleImpl implements GenericRole {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    protected String role_description;
    protected WitsUser2RoleGroupId witsUser2RoleGroupId;
    public GenericRoleImpl()
    {
    	
    }
    public String getRole_description() {
		return role_description;
	}
	public void setRole_description(String role_description) {
		this.role_description = role_description;
	}
	public WitsUser2RoleGroupId getWitsUser2RoleGroupId() {
		return witsUser2RoleGroupId;
	}
	public void setWitsUser2RoleGroupId(WitsUser2RoleGroupId witsUser2RoleGroupId) {
		this.witsUser2RoleGroupId = witsUser2RoleGroupId;
	}
    
}
