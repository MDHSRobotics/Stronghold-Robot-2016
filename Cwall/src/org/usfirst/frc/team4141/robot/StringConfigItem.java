package org.usfirst.frc.team4141.robot;

import org.usfirst.frc.team4141.robot.ConfigManager.Type;

import team4141.eventmanager.NotImplementedException;


public class StringConfigItem implements ConfigItem {
	private String value;
	private ConfigManager.Type type;
	private String name;
	
	public StringConfigItem(String name, String value){
		this.name = name;
		this.value = value;
		this.type = ConfigManager.Type.string;	
	}	

	@Override
	public String getName() {
		return name;
	}


	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getMin() {
		throw new NotImplementedException();
	}

	@Override
	public Object getMax() {
		throw new NotImplementedException();
	}

	@Override
	public void setMin(Object min) {
		throw new NotImplementedException();
	}

	@Override
	public void setMax(Object max) {
		throw new NotImplementedException();
	}

	@Override
	public void setValue(Object value) {
		if(value instanceof String){
			this.value = (String)value;
			System.out.printf("setting %s to %s\n",name,value);
		}
	}


	@Override
	public int getInt() {
		throw new NotImplementedException();
	}

	@Override
	public double getDouble() {
		throw new NotImplementedException();
	}

	@Override
	public String getString() {
		return value;
	}

}
