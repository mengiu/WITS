package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.GraficFormatSt;

public interface GraficFormatStService {
	public int addGraficFormatSt(GraficFormatSt gfst );
	public List<GraficFormatSt> listGraficFormatSt();
	public void removeGraficFormatSt(int id);
	public GraficFormatSt getGraficFormatSt(int id);
	public void updateGraficFormatSt(GraficFormatSt gfst );
    public GraficFormatSt getGraficFormatSt(String format);
    public GraficFormatSt getGraficFormatStForExtention(String extention);
}
