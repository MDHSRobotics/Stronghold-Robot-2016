package org.usfirst.frc.team4141.robot;


public interface ConfigItem {
	String getName();
	ConfigManager.Type getType();
	Object getValue();
	Object getMin();
	Object getMax();
	void setMin(Object min);
	void setMax(Object max);
	void setValue(Object value);
	int getInt();
	double getDouble();
	String getString();
}
