package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Timer;

public class AutoDrivePauseCommand extends MDCommand {
	double start;//start time
	double duration=0.2;
	double speed=0;
	DriveSystem sys;

	public AutoDrivePauseCommand(MDRobotBase robot) {
		super(robot,"AutoDrivePauseCommand");
		requires(getRobot().getSubsystems().get("DriveSystem")); 
		sys=(DriveSystem)(getRobot().getSubsystems().get("DriveSystem"));		
	}
	@Override
	protected void execute() {
		sys.move(speed);
    }
    @Override
	protected void end() {
    	super.end();
    	sys.move(0);
    }
    @Override
	protected void initialize() {
    	start=Timer.getFPGATimestamp();
    }
    @Override
	protected boolean isFinished() {
    	return Timer.getFPGATimestamp()>=start+duration;
    }

}
