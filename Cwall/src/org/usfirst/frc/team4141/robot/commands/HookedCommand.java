package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.subsystems.ShootSystem;


public class HookedCommand extends MDCommand {
	ShootSystem sys;
	public HookedCommand(MDRobotBase robot) {
		super(robot,"HookedCommand");
		requires(getRobot().getSubsystems().get("ShootSystem")); 
		sys = (ShootSystem)(getRobot().getSubsystems().get("ShootSystem"));
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		super.end();
		log("end",getName()+" has ended.");
	}

//    protected boolean isFinished() {
//    	return sys.hooked();
//	}
}
