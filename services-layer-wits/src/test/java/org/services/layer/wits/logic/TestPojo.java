package org.services.layer.wits.logic;

import java.awt.Color;

import org.persistence.layer.wits.form.WamatObject;
import org.services.layer.wits.services.WamatObjectService;
import org.services.layer.wits.services.aop.MethodLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class TestPojo {
	   
	    private WamatObject wamatObject;

	    //@FieldLog(write=false)
	    private String firstName;

	    //@FieldLog(read=false)
	    private String lastName; 

	    //@FieldLog(prefix="Don't be color-blind: ")
	    private Color favoriteColor; 

	    //@FieldLog(suffix=". Dad gum! that's old!!")
	    private int age;

	    public TestPojo() {
	        super();
	    }

	    @MethodLog
	    public String getFirstName() {
	        return firstName;
	    }

	    @MethodLog
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    @MethodLog
	    public String getLastName() {
	        return lastName;
	    }

	    @MethodLog
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    @MethodLog
	    public Color getFavoriteColor() {
	        return favoriteColor;
	    }

	    @MethodLog
	    public void setFavoriteColor(Color favoriteColor) {
	        this.favoriteColor = favoriteColor;
	    }

	    @MethodLog
	    public int getAge() {
	        return age;
	    }

	    @MethodLog
	    public void setAge(int age) {
	        this.age = age;
	    }

		public WamatObject getWamatObject() {
			return wamatObject;
		}

	    @MethodLog
		public void setWamatObject(WamatObject wamatObject) {
			this.wamatObject = wamatObject;
		} 
}
