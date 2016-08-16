package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.subsystems.ShootSystem;

public class RaiseCannonCommand extends MDCommand {
	ShootSystem sys;

	public RaiseCannonCommand(MDRobotBase robot) {
		super(robot,"RaiseCannonCommand");
		requires(getRobot().getSubsystems().get("ShootSystem")); 
		sys=(ShootSystem)(getRobot().getSubsystems().get("ShootSystem"));

	}

	@Override
	protected void execute() {
		super.execute();
//		log("execute","isatshoot = "+sys.isAtShootPosition());
		sys.raise();
		
	}
    @Override
	protected void end() {
    	super.end();
    	sys.stop();
    }

    @Override
	protected boolean isFinished() {
    	return sys.isAtShootPosition();
    }
}
