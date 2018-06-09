package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.GroupSt;

public interface FacadeGroupDetailPanelService {
	public List<GroupSt> listGroupStForResponsible(String idUser);

}
