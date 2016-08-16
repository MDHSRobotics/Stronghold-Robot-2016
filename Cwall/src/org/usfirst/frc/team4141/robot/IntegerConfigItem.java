package org.usfirst.frc.team4141.robot;

import org.usfirst.frc.team4141.robot.ConfigManager.Type;

public class IntegerConfigItem implements ConfigItem {
	private Integer min;
	private Integer max;
	private Integer value;
	private ConfigManager.Type type;
	private String name;
	
	public IntegerConfigItem(String name, Integer min, Integer max, Integer value){
		this.name = name;
		this.min = min;
		this.max = max;
		this.value = value;
		this.type = ConfigManager.Type.integer;	
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
		return min;
	}

	@Override
	public Object getMax() {
		return max;
	}

	@Override
	public void setMin(Object min) {
		if(min instanceof Integer){
			this.min = (Integer)min;
		}
	}

	@Override
	public void setMax(Object max) {
		if(max instanceof Integer){
			this.max = (Integer)max;
		}
	}

	@Override
	public void setValue(Object value) {
		if(value instanceof Integer){
			this.value = (Integer)value;
			System.out.printf("setting %s to %d\n",name,value);
		}
	}


	@Override
	public int getInt() {
		return value.intValue();
	}

	@Override
	public double getDouble() {
		return value.intValue();
	}

	@Override
	public String getString() {
		return value.toString();
	}

}
