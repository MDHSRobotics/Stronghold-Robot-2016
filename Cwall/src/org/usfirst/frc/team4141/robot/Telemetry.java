package org.usfirst.frc.team4141.robot;

import java.util.Hashtable;

public interface Telemetry {
	public enum Type{
		analog,  //double
		digital  //boolean
	}
	Type getType();
	String getName();
	String toJSON();
	void read(Hashtable<String, Telemetry> sensors);
}
