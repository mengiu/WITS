package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.ActGeBytearray;

public interface ActGeBytearrayService {
	public List<ActGeBytearray> listActGeBytearray();
	public ActGeBytearray getActGeBytearray(int id);
	public ActGeBytearray getActGeBytearrayRule(String nameRules);
	public byte[] getActGeBytearrayRuleByte(String nameRules);

}
