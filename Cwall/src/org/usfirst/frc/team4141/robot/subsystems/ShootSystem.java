package org.usfirst.frc.team4141.robot.subsystems;

import org.usfirst.frc.team4141.robot.ComputedAnalog;
import org.usfirst.frc.team4141.robot.ConfigManager;
import org.usfirst.frc.team4141.robot.MDAnalogInput;
import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.RobotMap;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;


/**
 *
 */
public class ShootSystem extends MDSubsystem {

	boolean haveBall = false;
	
	public void setHasBall(boolean hasBall){
		haveBall= hasBall;
	}
	public boolean hasBall(){
		return haveBall || ballLoadedSwitch.get();
	}

	SpeedController shootRightMotor = RobotMap.shootRightMotor;
    SpeedController shootLeftMotor = RobotMap.shootLeftMotor;

    SpeedController pivotmotor = RobotMap.pivotmotor;
    DigitalInput pivotloadswitch = RobotMap.pivotloadswitch;
    DigitalInput pivotshootswitch = RobotMap.pivotshootswitch;
//    DigitalInput hookSwitch = RobotMap.hookSwitch;
    DigitalInput ballLoadedSwitch = RobotMap.ballLoadedswitch;
    Solenoid feedBall = RobotMap.feedBallSolenoid;
    MDAnalogInput cannonEncoder = RobotMap.cannonEncoder;
 


	public ShootSystem(MDRobotBase robot) {
		super(robot);
		feedBall.set(false);
	}	

	public void raise() {
		double pivotSpeedUp = ConfigManager.getItem(this, "pivotSpeedUp").getDouble();
		pivotmotor.set(pivotSpeedUp);
	}
	int i=0;
	int span = 30;
	double drop = 0.50;
	public void lower() {
		if (i<span) i++;
		double pivotSpeedDown = ConfigManager.getItem(this, "pivotSpeedDown").getDouble();
		pivotmotor.set(-(pivotSpeedDown*(1-(drop*(i/span)))));
	}
	public void stop() {
		pivotmotor.set(0);
	}	
	public boolean isAtShootPosition(){
		return !RobotMap.pivotshootswitch.get();  //may need to remove the not (!) depending on switch polarity
	}
	public double getCannonPosition() {
//		System.out.printf("raw encoder output = %f\n",cannonEncoder.getVoltage());
		return cannonEncoder.getVoltage();
	}
	public boolean isAtLoadPosition(){
		boolean loaded =  !RobotMap.pivotloadswitch.get();
		return loaded;
	}
	public void load(){
		double loadSpeed = ConfigManager.getItem(this, "loadSpeed").getDouble();
		shootLeftMotor.set(loadSpeed);
		shootRightMotor.set(-loadSpeed);
	}
	public void spinUp(){
		double s = ConfigManager.getItem(this, "shootSpeed").getDouble();
		if (!this.isAtLoadPosition()) s=ConfigManager.getItem(this, "lowGoalShootSpeed").getDouble();
		shootLeftMotor.set(-s);
		shootRightMotor.set(s);
	}
	public void shoot(){
		feedBall.set(true);
		haveBall = false;
	}
	public void stopShootMotors(){
		shootLeftMotor.set(0);
		shootRightMotor.set(0);
		feedBall.set(false);
	}

	double priorPosition = 0.0;
	double currentPosition = 0.0;
	double change = 0.0;
//	double angularDistance = 0.0;
	public void updateCannonPosition() {
		//if (isAtLoadPosition()){
		//	angularDistance = 0;
		//}
		ComputedAnalog angularDistance = getRobot().getVariable("cannonPosition");
		if(isAtLoadPosition()){
//			System.out.println("resetting cannon position");
			angularDistance.setValue(0);
			return;
		}
		double encoderCrossoverThreshold = ConfigManager.getItem(this, "encoderCrossoverThreshold").getDouble();
		currentPosition =  getCannonPosition();
		change = currentPosition-priorPosition; //multiply by negative 1 if encoder polarity is reversed
//		System.out.printf("change=%f, %f>%f=%b\n", change, Math.abs(change),encoderCrossoverThreshold, Math.abs(change)>encoderCrossoverThreshold);
		if (Math.abs(change)>encoderCrossoverThreshold){
//			System.out.println("threshold detected");
			if (priorPosition > currentPosition){
		//going up
//				System.out.println("going up");
				change = (5 - priorPosition) + (currentPosition - 0); 
			}
			else {
			//going down	
//				System.out.println("going down");
			change = (priorPosition - 0) + (5 - currentPosition);
			}
			
		}
		angularDistance.setValue(angularDistance.getValue()+change);
//		System.out.printf("change = %f\t priorPosition = %f\t currentPosition = %f\t distance = %f\t switch = %b\n", change, priorPosition, currentPosition, angularDistance.getValue(), isAtLoadPosition());
		priorPosition = currentPosition;
	}
}



