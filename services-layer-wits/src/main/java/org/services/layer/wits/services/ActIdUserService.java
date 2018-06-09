package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.ActIdUser;

public interface ActIdUserService {
	public List<ActIdUser> listActIdUser();
	public ActIdUser getActIdUser(String id);

}
