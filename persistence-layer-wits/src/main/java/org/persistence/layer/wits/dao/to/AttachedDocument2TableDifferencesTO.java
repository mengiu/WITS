package org.persistence.layer.wits.dao.to;

import java.io.Serializable;

import org.persistence.layer.wits.form.AttachedDocument2Table;

public interface AttachedDocument2TableDifferencesTO extends Serializable {
	public AttachedDocument2Table getBefore();
	public AttachedDocument2Table getAfter();
	public AttachedDocument2Table getEntitywithChanging();

}
