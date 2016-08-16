package org.usfirst.frc.team4141.robot.commands;


import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.subsystems.DriveSystem;


public class ArcadeDriveCommand extends MDCommand {
	DriveSystem sys;
	public ArcadeDriveCommand(MDRobotBase robot) {
		super(robot,"ArcadeDriveCommand");
		requires(robot.getSubsystems().get("DriveSystem"));
		sys = ((DriveSystem)getRobot().getSubsystems().get("DriveSystem"));
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    	sys.arcadeDrive(getRobot().getOi().getJoystick());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	super.end();
    	sys.stop();
    }

}
