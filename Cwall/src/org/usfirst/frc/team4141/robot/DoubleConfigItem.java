package org.usfirst.frc.team4141.robot;

import org.usfirst.frc.team4141.robot.ConfigManager.Type;

public class DoubleConfigItem implements ConfigItem {
	private Double min;
	private Double max;
	private Double value;
	private ConfigManager.Type type;
	private String name;
	
	
	public DoubleConfigItem(String name, Double min, Double max, Double value){
		this.name = name;
		this.min = min;
		this.max = max;
		this.value = value;
		this.type = ConfigManager.Type.doubleNumber;	
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
		if(min instanceof Double){
			this.min = (Double)min;
		}
	}

	@Override
	public void setMax(Object max) {
		if(max instanceof Double){
			this.max = (Double)max;
		}
	}

	@Override
	public void setValue(Object value) {
		if(value instanceof Double){
			this.value = (Double)value;
			System.out.printf("setting %s to %f\n",name,value);
		}
	}


	@Override
	public int getInt() {
		return value.intValue();
	}

	@Override
	public double getDouble() {
		return value.doubleValue();
	}

	@Override
	public String getString() {
		return value.toString();
	}

}
