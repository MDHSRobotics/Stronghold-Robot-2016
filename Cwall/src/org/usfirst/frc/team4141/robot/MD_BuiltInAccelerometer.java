package org.usfirst.frc.team4141.robot;

import java.util.Hashtable;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class MD_BuiltInAccelerometer extends BuiltInAccelerometer {
	MD_IMU_Component[] readings = new MD_IMU_Component[3];
	
	public MD_BuiltInAccelerometer(){
		super();
		int i=0;
		readings[i++]=new MD_IMU_Component("Rio_AccelX", getX());
		readings[i++]=new MD_IMU_Component("Rio_AccelY", getY());
		readings[i++]=new MD_IMU_Component("Rio_AccelZ", getZ());
	}

	public void read(Hashtable<String, Telemetry> sensors){
		update();
		for(int i=0;i<readings.length;i++){
			if(!sensors.containsKey(readings[i].getName())){
				sensors.put(readings[i].getName(), readings[i]);
			}
		}
	}

	private void update() {
		int i=0;
		readings[i++].setValue(getX());
		readings[i++].setValue(getY());
		readings[i++].setValue(getZ());
	}
}
