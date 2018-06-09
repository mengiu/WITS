package org.persistence.layer.wits.dao.to;

import java.io.Serializable;
import org.persistence.layer.wits.form.Item;

public interface ItemDifferencesTO extends Serializable {
	public Item getBefore();
	public Item getAfter();
	public Item getItemwithChanging();
}
