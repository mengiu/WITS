package org.services.layer.wits.services;


import org.persistence.layer.wits.form.AttachedDocument;
import org.persistence.layer.wits.form.utility.AttachmentWits;
import org.persistence.layer.wits.util.AttachToGenericTable;

public interface FacadeAttachedDocumentService {
  AttachedDocument attachDocumentToReferentTableAndId(AttachedDocument attachedDocument , 
		 AttachToGenericTable attachToGenericTable );
  void applyValuesToAttachmentForGenericTable(AttachmentWits attachment ,
			String attachmentDescription , String attachmentName);
  void removeAttachedDocument(Integer idAttach , AttachToGenericTable attachToGenericTable );
}
