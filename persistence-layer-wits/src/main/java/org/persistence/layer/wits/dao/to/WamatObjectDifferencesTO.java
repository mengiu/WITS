package org.persistence.layer.wits.dao.to;

import java.io.Serializable;

import org.persistence.layer.wits.form.WamatObject;

public interface WamatObjectDifferencesTO extends Serializable {
	public WamatObject getBefore();
	public WamatObject getAfter();
	public WamatObject getEntitywithChanging();
}
