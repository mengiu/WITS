package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.dao.to.GenericRole;
import org.persistence.layer.wits.form.WitsUser2RoleGroupId;

public interface FacadeIdentityService {
  public List<GenericRole> getListUserRoles(String userId);
  public GenericRole getUserRoles(WitsUser2RoleGroupId witsUser2RoleGroupId);
}
