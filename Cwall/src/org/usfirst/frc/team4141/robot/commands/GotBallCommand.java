package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.subsystems.ShootSystem;


public class GotBallCommand extends MDCommand {
	ShootSystem sys;
	public GotBallCommand(MDRobotBase robot) {
		super(robot,"GotBallCommand");
//		requires(getRobot().getSubsystems().get("ShootSystem")); 
		sys = (ShootSystem)(getRobot().getSubsystems().get("ShootSystem"));
	}

	@Override
	protected void execute() {
		super.execute();
		sys.setHasBall(true);
	}		

    @Override
	protected boolean isFinished() {
    	return true;
	}
}
