package org.usfirst.frc.team4141.robot.subsystems;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.Logger.Level;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class MDSubsystem extends Subsystem {
	private String name;
	
	public MDSubsystem(MDRobotBase robot) {
		super();
		this.robot=robot;
		
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private MDRobotBase robot;

	public MDRobotBase getRobot() {
		return robot;
	}
	public void log(String methodName, String message) {
		getRobot().log(this.getClass().getName()+"."+methodName+"()", message);
		
	}
	public void log(Level level, String methodName, String message) {
		getRobot().log(level,this.getClass().getName()+"."+methodName+"()", message);
		
	}

	@Override
	protected void initDefaultCommand() {
		

	}

}
