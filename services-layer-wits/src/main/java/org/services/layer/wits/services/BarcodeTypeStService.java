package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.BarcodeTypeSt;

public interface BarcodeTypeStService {
	public int addBarcodeTypeSt(BarcodeTypeSt barcode );
	public List<BarcodeTypeSt> listBarcodeTypeSt();
	public void removeBarcodeTypeSt(int id);
	public BarcodeTypeSt getBarcodeTypeSt(int id);
	public void updateBarcodeTypeSt(BarcodeTypeSt barcode);

}
