package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.AttachedDocument;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.form.utility.AttachmentWits;

public interface AttachedDocumentDAO {
	public int addAttachedDocument(AttachedDocument attachedDocument );
	public List<AttachedDocument> listAttachedDocument();
	public void removeAttachedDocument(int id);
	public AttachedDocument getAttachedDocument(int id);
	public void updateAttachedDocument(AttachedDocument attachedDocument);

}
