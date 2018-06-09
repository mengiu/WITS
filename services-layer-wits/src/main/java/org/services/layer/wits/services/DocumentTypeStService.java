package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.DocumentTypeSt;

public interface DocumentTypeStService {
	public int addDocumentTypeSt(DocumentTypeSt documentTypeSt );
	public List<DocumentTypeSt> listDocumentTypeSt();
	public void removeDocumentTypeSt(int id);
	public DocumentTypeSt getDocumentTypeSt(int id);
	public void updateDocumentTypeSt(DocumentTypeSt documentTypeSt);
	public DocumentTypeSt getDocumentTypeSt(String docType);

}
