package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.Wmo2ActHiProcinst;
import org.persistence.layer.wits.form.Wmo2ActHiProcinstId;

public interface Wmo2ActHiProcinstDAO {
	public Wmo2ActHiProcinstId addWmo2ActHiProcinst(Wmo2ActHiProcinst wmo2AhPi );
	public List<Wmo2ActHiProcinst> listWmo2ActHiProcinst();
	public void removeWmo2ActHiProcinst(Wmo2ActHiProcinstId id);
	public Wmo2ActHiProcinst getWmo2ActHiProcinst(Wmo2ActHiProcinstId id);
	public void updateWmo2ActHiProcinst(Wmo2ActHiProcinst wmo2AhPi);

}
