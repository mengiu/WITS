package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.ActGeBytearray;

public interface ActGeBytearrayDAO {
	public List<ActGeBytearray> listActGeBytearray();
	public ActGeBytearray getActGeBytearray(int id);
	public ActGeBytearray getActGeBytearrayRule(String nameRules);
	public byte[] getActGeBytearrayRuleByte(String nameRules);

}
