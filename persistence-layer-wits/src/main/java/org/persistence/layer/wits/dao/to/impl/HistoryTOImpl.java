package org.persistence.layer.wits.dao.to.impl;

import java.util.Date;

import javax.persistence.Table;

import org.persistence.layer.wits.dao.to.HistoryTO;
import org.persistence.layer.wits.form.WamatObject;

public class HistoryTOImpl implements HistoryTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Date date;
	protected String refer;
	protected String field;
	protected String value;
	protected String user;
	protected String workflow;
	
	public HistoryTOImpl(String user, Date date, 
			String workflow )
	{
		this.user = user;
		this.workflow = workflow;
		this.date = date;
	}
	@Override
	public Date getDate() {
		return date;
	}
	@Override
	public String getRefer() {
		return refer;
	}
	public void setRefer(String refer) {
 		this.refer = refer;
	}
	@Override
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	@Override
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String getUser() {
		return user;
	}
	@Override
	public String getWorkflow() {
		return workflow;
	}

}
