package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.ActIdUser;

public interface ActIdUserDAO {
	public List<ActIdUser> listActIdUser();
	public ActIdUser getActIdUser(String id);

}
