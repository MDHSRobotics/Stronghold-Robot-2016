package org.usfirst.frc.team4141.robot.subsystems;

import org.usfirst.frc.team4141.robot.MDRobotBase;


/**
 *
 */
public class ArmSystem extends MDSubsystem {
//	Solenoid liftRelease = RobotMap.liftReleaseSolenoid;
//	Solenoid liftLock = RobotMap.liftLockSolenoid;
//
//	DigitalInput unfurledSwitch = RobotMap.unfurledswitch;
//	DigitalInput liftSwitch = RobotMap.liftSwitch;
//	DigitalInput hookSwitch = RobotMap.hookSwitch;
//
//	SpeedController liftFrontMotor = RobotMap.liftFrontMotor;
//	SpeedController liftRearMotor = RobotMap.liftRearMotor;
//	
	
	public ArmSystem(MDRobotBase robot) {
		super(robot);
//		liftRelease.set(false);
//		liftLock.set(false);
	}

//	public boolean isUnfurled(){
//		return unfurledSwitch.get();
		
//	}
	
	public void unfurlStop(){
//		liftFrontMotor.set(0);
//		liftRearMotor.set(0);
//		liftRelease.set(false);
	}

	public void unfurl(){
//		double unfurlSpeed = ConfigManager.get(this, "unfurlSpeed").getDouble();
//		liftFrontMotor.set(unfurlSpeed);
//		liftRearMotor.set(unfurlSpeed);		
	}
	
	boolean isLiftInitialized = false;
//	public void lift(){
//		if(!isLiftInitialized){
//			liftLock.set(false);
//			isLiftInitialized = true;
//		}
//		double liftSpeed = ConfigManager.get(this, "liftSpeed").getDouble();
//		
//		liftFrontMotor.set(liftSpeed);
//		liftRearMotor.set(liftSpeed);
//	}
//	public void liftStop(){
//		liftFrontMotor.set(0);
//		liftRearMotor.set(0);
//		liftLock.set(true);
//		isLiftInitialized = false;
//	}
//	public boolean isDoneLifting(){
//		return liftSwitch.get();
//	}
//	public void releaseLift(){
//		liftRelease.set(true);
//	}
//	public boolean isHooked(){
//		return hookSwitch.get();
//	}
}

