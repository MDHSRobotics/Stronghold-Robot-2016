package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.subsystems.ShootSystem;

public class LowerCannonCommand extends MDCommand {
	ShootSystem sys;

	public LowerCannonCommand(MDRobotBase robot) {
		super(robot,"LowerCannonCommand");
		requires(getRobot().getSubsystems().get("ShootSystem")); 
		sys=(ShootSystem)(getRobot().getSubsystems().get("ShootSystem"));

	}
	boolean initialized = false;
	@Override
	protected void execute() {
		super.execute();
		if(!initialized){
			initialized = true;
		}
		sys.lower();
	}
    @Override
	protected void end() {
    	super.end();
    	sys.stop();
    	initialized = false;
    }


    @Override
	protected boolean isFinished() {
    	return sys.isAtLoadPosition();
    }

}
