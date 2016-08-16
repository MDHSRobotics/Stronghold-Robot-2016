package org.usfirst.frc.team4141.robot;

import java.util.ArrayList;
import java.util.List;

public class DistanceComputedAnalog extends ComputedAnalog {
	List<Double> priorValues;
	double sum;
	private ConfigItem tolerance;
	private ConfigItem windowSize;

	public DistanceComputedAnalog(String name, ConfigItem tolerance, ConfigItem windowSize) {
		super(name);
		priorValues = new ArrayList<Double>();
		this.tolerance = tolerance;
		this.windowSize = windowSize;
	}
	public void update(double distance1, double distance2){
		double distance = 0;	
//		System.out.printf("distance1=%f, distance2=%f, average=%f\n",distance1,distance2,(getAverage()));
		if(priorValues.size()<windowSize.getInt()){
			distance = (distance1+distance2)/2;
			priorValues.add(distance);
			setValue(distance);
			sum+=distance;
//			System.out.println("priming distance with "+distance);
			
			return;
		}
		while(priorValues.size()>windowSize.getInt()){
			sum-=priorValues.remove(0).doubleValue();
		}
		if(!isOK(distance1) && !isOK(distance2)){
			distance = (distance1+distance2)/2;
		}
		else if(isOK(distance1) && isOK(distance2)){
			distance = (distance1+distance2)/2;
		}
		else if(!isOK(distance1)){
			distance = distance2;
		}
		else{
			distance = distance1;
		}
		sum-=priorValues.remove(0).doubleValue();
		distance = (int) distance;
		sum+=distance;
		priorValues.add(distance);
//		System.out.println("updating distance to "+(sum/priorValues.size()));
		setValue((int)sum/priorValues.size());
	}
		
	private boolean isOK(double distance) {
		double average = getAverage();
		return Math.abs(distance-average)<=average*tolerance.getDouble();
	}
	
	private double getAverage(){
		return sum/priorValues.size();
	}
	public void update(MD_IMU_Component[] distances) {
		if(distances!=null && distances.length==2){
			update(distances[0].getValue(),distances[1].getValue());
		}
	}
}
