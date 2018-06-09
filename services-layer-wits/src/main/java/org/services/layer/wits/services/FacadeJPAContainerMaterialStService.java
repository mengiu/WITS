package org.services.layer.wits.services;


import org.persistence.layer.wits.form.MaterialSt;


public interface FacadeJPAContainerMaterialStService {
  public void removeItem(Integer objectId);
  public void addItem(MaterialSt materialSt);
  public void updateItem(MaterialSt materialSt);
}
