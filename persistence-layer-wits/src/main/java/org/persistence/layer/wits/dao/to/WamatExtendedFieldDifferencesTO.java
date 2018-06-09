package org.persistence.layer.wits.dao.to;

import java.io.Serializable;
import org.persistence.layer.wits.form.WamatExtendedField;

public interface WamatExtendedFieldDifferencesTO extends Serializable {
	public WamatExtendedField getBefore();
	public WamatExtendedField getAfter();
	public WamatExtendedField getWamatExtendedFieldChanging();
}
