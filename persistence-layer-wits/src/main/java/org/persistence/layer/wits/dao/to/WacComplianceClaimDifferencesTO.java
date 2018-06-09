package org.persistence.layer.wits.dao.to;

import java.io.Serializable;
import org.persistence.layer.wits.form.WacComplianceClaim;

public interface WacComplianceClaimDifferencesTO extends Serializable{
	public WacComplianceClaim getBefore();
	public WacComplianceClaim getAfter();
	public WacComplianceClaim getWacComplianceClaimBeforeChanging();	
}
