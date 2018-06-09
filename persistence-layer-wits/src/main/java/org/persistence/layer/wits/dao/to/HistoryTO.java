package org.persistence.layer.wits.dao.to;

import java.io.Serializable;
import java.util.Date;

public interface HistoryTO extends Serializable{
	public Date getDate();
	public String getRefer();
	public String getField();
	public String getValue();
	public String getUser();
	public String getWorkflow();
}
