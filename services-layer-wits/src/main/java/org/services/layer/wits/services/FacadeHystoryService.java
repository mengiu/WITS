package org.services.layer.wits.services;

import java.util.Date;
import java.util.List;

import org.persistence.layer.wits.dao.to.AttachedDocument2TableTO;
import org.persistence.layer.wits.dao.to.ItemTO;
import org.persistence.layer.wits.dao.to.WacComplianceClaimTO;
import org.persistence.layer.wits.dao.to.WamatExtendedFieldTO;
import org.persistence.layer.wits.dao.to.WamatObjectTO;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.form.WitsUserSt;

public interface FacadeHystoryService {
	public List<AttachedDocument2TableTO> getListVariationAttachedDocument2TableTO(
			Object entityObject , WitsUserSt witsUserSt , Date dateFrom , Date dateTo);
	public List<WamatObjectTO> getListWamatObjectTO(
			WamatObject wamatObject , WitsUserSt witsUserSt , Date dateFrom , Date dateTo);
	public List<ItemTO> getListItemTO(
			WamatObject wamatObject, WitsUserSt witsUserSt , 
			Date dateFrom , Date dateTo);
	public List<WamatExtendedFieldTO> getListWamatExtendedFieldTO(
			WamatObject wamatObject , WitsUserSt witsUserSt , Date dateFrom , Date dateTo);
	public List<WacComplianceClaimTO> getListWacComplianceClaimTO(
			WamatObject wamatObject , WitsUserSt witsUserSt , Date dateFrom , Date dateTo);

}
