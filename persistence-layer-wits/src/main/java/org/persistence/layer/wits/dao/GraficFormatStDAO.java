package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.GraficFormatSt;

public interface GraficFormatStDAO {
	public int addGraficFormatSt(GraficFormatSt gfst );
	public List<GraficFormatSt> listGraficFormatSt();
	public void removeGraficFormatSt(int id);
	public GraficFormatSt getGraficFormatSt(int id);
	public void updateGraficFormatSt(GraficFormatSt gfst );
	public GraficFormatSt getGraficFormatSt(String format);
	public GraficFormatSt getGraficFormatStForExtention(String extention);
}
