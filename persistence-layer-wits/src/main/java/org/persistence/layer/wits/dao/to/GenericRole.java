package org.persistence.layer.wits.dao.to;

import java.io.Serializable;

import org.persistence.layer.wits.form.WitsUser2RoleGroupId;


public interface GenericRole  extends Serializable{
    public String getRole_description();
	public void setRole_description(String role_description);
	public WitsUser2RoleGroupId getWitsUser2RoleGroupId();
	public void setWitsUser2RoleGroupId(WitsUser2RoleGroupId witsUser2RoleGroupId);
}
