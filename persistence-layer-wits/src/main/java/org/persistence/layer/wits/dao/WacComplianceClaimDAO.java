package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.WacComplianceClaim;
import org.persistence.layer.wits.form.WamatObject;

public interface WacComplianceClaimDAO {
	public int addWacComplianceClaim(WacComplianceClaim wcc );
	public List<WacComplianceClaim> listWacComplianceClaim();
	public void removeWacComplianceClaim(int id);
	public WacComplianceClaim getWacComplianceClaim(int id);
	public void updateWacComplianceClaim(WacComplianceClaim wcc);
	public List<WacComplianceClaim> listWacComplianceClaim(WamatObject wamatObject);

}
