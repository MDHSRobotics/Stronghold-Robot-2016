package org.usfirst.frc.team4141.robot;

import java.util.Hashtable;

public class ComputedAnalog implements Telemetry {

	private String name;
	private Type type = Type.analog;
	private double value;
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public ComputedAnalog(String name) {
		this.name=name;
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
	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"type\":\"");
		sb.append(type.toString());
		sb.append("\", \"name\":\"");
		sb.append(name);
		sb.append("\", \"value\":");
		sb.append(getValue());
		sb.append("}");
		return sb.toString();
	}

	@Override
	public void read(Hashtable<String, Telemetry> sensors) {
		if(!sensors.containsKey(getName()))
			sensors.put(getName(),this);
	}

}
