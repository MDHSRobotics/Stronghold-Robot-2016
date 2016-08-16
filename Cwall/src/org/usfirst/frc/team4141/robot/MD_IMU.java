package org.usfirst.frc.team4141.robot;

import java.util.Hashtable;

import com.analog.adis16448.frc.ADIS16448_IMU;

public class MD_IMU extends ADIS16448_IMU {
	MD_IMU_Component[] readings = new MD_IMU_Component[24];
	
	public MD_IMU(){
		super();
		int i=0;
		readings[i++]=new MD_IMU_Component("AccelX", getAccelX());
		readings[i++]=new MD_IMU_Component("AccelY", getAccelY());
		readings[i++]=new MD_IMU_Component("AccelZ", getAccelZ());
		readings[i++]=new MD_IMU_Component("Angle", getAngle());
		readings[i++]=new MD_IMU_Component("AngleX", getAngleX());
		readings[i++]=new MD_IMU_Component("AngleY", getAngleY());
		readings[i++]=new MD_IMU_Component("AngleZ", getAngleZ());
		readings[i++]=new MD_IMU_Component("MagX", getMagX());
		readings[i++]=new MD_IMU_Component("MagY", getMagY());
		readings[i++]=new MD_IMU_Component("MagZ", getMagZ());
		readings[i++]=new MD_IMU_Component("Pitch", getPitch());
		readings[i++]=new MD_IMU_Component("Yaw", getYaw());
		readings[i++]=new MD_IMU_Component("Roll", getRoll());
		readings[i++]=new MD_IMU_Component("QuaternionW", getQuaternionW());
		readings[i++]=new MD_IMU_Component("QuaternionX", getQuaternionX());
		readings[i++]=new MD_IMU_Component("QuaternionY", getQuaternionY());
		readings[i++]=new MD_IMU_Component("QuaternionZ", getQuaternionZ());
		readings[i++]=new MD_IMU_Component("Rate", getRate());
		readings[i++]=new MD_IMU_Component("RateX", getRateX());
		readings[i++]=new MD_IMU_Component("RateY", getRateY());
		readings[i++]=new MD_IMU_Component("RateZ", getRateZ());
		readings[i++]=new MD_IMU_Component("Temperature", getTemperature());
		readings[i++]=new MD_IMU_Component("BarometricPressure", getBarometricPressure());
		readings[i++]=new MD_IMU_Component("LastSampleTime", getLastSampleTime());
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
		readings[i++].setValue(getAccelX());
		readings[i++].setValue(getAccelY());
		readings[i++].setValue(getAccelZ());
		readings[i++].setValue(getAngle());
		readings[i++].setValue(getAngleX());
		readings[i++].setValue(getAngleY());
		readings[i++].setValue(getAngleZ());
		readings[i++].setValue(getMagX());
		readings[i++].setValue(getMagY());
		readings[i++].setValue(getMagZ());
		readings[i++].setValue(getPitch());
		readings[i++].setValue(getYaw());
		readings[i++].setValue(getRoll());
		readings[i++].setValue(getQuaternionW());
		readings[i++].setValue(getQuaternionX());
		readings[i++].setValue(getQuaternionY());
		readings[i++].setValue(getQuaternionZ());
		readings[i++].setValue(getRate());
		readings[i++].setValue(getRateX());
		readings[i++].setValue(getRateY());
		readings[i++].setValue(getRateZ());
		readings[i++].setValue(getTemperature());
		readings[i++].setValue(getBarometricPressure());
		readings[i++].setValue(getLastSampleTime());
	}
}
