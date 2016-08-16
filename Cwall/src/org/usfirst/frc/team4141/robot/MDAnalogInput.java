package org.usfirst.frc.team4141.robot;

import java.util.Hashtable;


import edu.wpi.first.wpilibj.AnalogInput;


public class MDAnalogInput extends AnalogInput implements Telemetry {

	private Type type = Type.analog;
	private String name;
	
	public MDAnalogInput(int channel, String name) {
		super(channel);
		this.name = name;
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
		sb.append("\", \"channel\":");
		sb.append(Integer.toString(getChannel()));
		sb.append(", \"value\":");
		sb.append(Double.toString(getVoltage()));
		sb.append("}");
		return sb.toString();
	}
	
	@Override
	public void read(Hashtable<String, Telemetry> sensors) {
		if(!sensors.containsKey(getName()))
			sensors.put(getName(),this);
	}

}
