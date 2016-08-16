package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.subsystems.ArmSystem;


public class UnfurlCommand extends MDCommand {
	ArmSystem sys;
	

	public UnfurlCommand(MDRobotBase robot) {
		super(robot,"UnfurlCommand");
		 requires(getRobot().getSubsystems().get("ArmSystem")); 
		 sys = (ArmSystem) getRobot().getSubsystems().get("ArmSystem");
    }

	private double start;


    boolean isInitialized = false;
    @Override
	protected void execute() {
//    	if(!isInitialized){
//    		start = Timer.getFPGATimestamp();
////    		sys.releaseLift();
//    		isInitialized = true;
//    	}
//    	double time = Timer.getFPGATimestamp() - start;
//    	double delay = ConfigManager.get(sys, "delay").getDouble();
//    	if(time>delay){
//    		sys.unfurl();
//    	}
    }

//    protected boolean isFinished() {
//    	return sys.isUnfurled();
//    }

    @Override
	protected void end() {
    	super.end();
    	sys.unfurlStop();
    	isInitialized = false;
    }
}
