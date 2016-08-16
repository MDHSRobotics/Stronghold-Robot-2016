package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.notifications.BallLoadedNotification;
import org.usfirst.frc.team4141.robot.subsystems.ShootSystem;

import edu.wpi.first.wpilibj.Timer;

public class ShootCommand extends MDCommand {
	ShootSystem sys;
	double spinUpTime = 0.7;
	double afterShotTime = 0.7;
	double start;
	boolean didShoot = false;

	public ShootCommand(MDRobotBase robot) {
		super(robot,"ShootCommand");
		requires(getRobot().getSubsystems().get("ShootSystem")); 
		sys=(ShootSystem)(getRobot().getSubsystems().get("ShootSystem"));
	}
	
	boolean isInitialized = false;
	@Override
	protected void execute() {
		if(!isInitialized){
			start = Timer.getFPGATimestamp();
			didShoot = false;
			isInitialized = true;
		}
		sys.spinUp();
		double time = Timer.getFPGATimestamp() - start;
		if(!didShoot && time > spinUpTime){
			sys.shoot();
			didShoot= true;
			start = Timer.getFPGATimestamp();
		}
		
    }	
    @Override
	protected void end() {
    	super.end();
    	sys.stopShootMotors();
    	isInitialized = false;
    }
    @Override
	protected boolean isFinished() {
    	double time = Timer.getFPGATimestamp() - start;
    	if( didShoot && time > afterShotTime){
    		getRobot().post(new BallLoadedNotification(false,true));
    		return true;
    	}
        return false;
    }
}
