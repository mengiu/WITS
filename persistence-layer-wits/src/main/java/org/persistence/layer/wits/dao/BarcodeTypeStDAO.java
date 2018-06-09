package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.BarcodeTypeSt;

public interface BarcodeTypeStDAO {
	public int addBarcodeTypeSt(BarcodeTypeSt barcode );
	public List<BarcodeTypeSt> listBarcodeTypeSt();
	public void removeBarcodeTypeSt(int id);
	public BarcodeTypeSt getBarcodeTypeSt(int id);
	public void updateBarcodeTypeSt(BarcodeTypeSt barcode);
}
