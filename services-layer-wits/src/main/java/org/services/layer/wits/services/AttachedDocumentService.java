package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.AttachedDocument;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.form.utility.AttachmentWits;

public interface AttachedDocumentService {
	public int addAttachedDocument(AttachedDocument attachedDocument );
	public List<AttachedDocument> listAttachedDocument();
	public void removeAttachedDocument(int id);
	public AttachedDocument getAttachedDocument(int id);
	public void updateAttachedDocument(AttachedDocument attachedDocument);

}
