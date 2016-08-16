package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.subsystems.ArmSystem;

public class LiftCommand extends MDCommand {
	ArmSystem sys;
	
	public LiftCommand(MDRobotBase robot) {
		super(robot,"LiftCommand");
		 requires(getRobot().getSubsystems().get("ArmSystem")); 
		 sys = (ArmSystem) getRobot().getSubsystems().get("ArmSystem");
    }

//    protected void execute() {
//   		sys.lift();
//    }
//
//    protected boolean isFinished() {
//    	return sys.isDoneLifting();
//    }
//
//    protected void end() {
//    	super.end();
//    	sys.liftStop();
//    }
}
