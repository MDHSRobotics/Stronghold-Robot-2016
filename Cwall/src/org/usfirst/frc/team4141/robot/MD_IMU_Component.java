package org.usfirst.frc.team4141.robot;

import java.util.Hashtable;

public class MD_IMU_Component implements Telemetry {

	private String name;
	private double value;
	private Type type;

	public MD_IMU_Component(String name, double value){
		this.name = name;
		this.value = value;
		this.type = Type.analog;
	}


	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}


	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public String toJSON(){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"type\":\"");
		sb.append(type.toString());
		sb.append("\", \"name\":\"");
		sb.append(name);
		sb.append("\", \"value\":");
		sb.append(Double.toString(getValue()));
		sb.append("}");
		return sb.toString();
	}
	@Override
	public void read(Hashtable<String, Telemetry> sensors) {
		if(!sensors.containsKey(getName()))
			sensors.put(getName(),this);
	}
}
