package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.notifications.BallLoadedNotification;
import org.usfirst.frc.team4141.robot.subsystems.ShootSystem;


public class LoadingCommand extends MDCommand {
	ShootSystem sys;
	public LoadingCommand(MDRobotBase robot) {
		super(robot,"LoadingCommand");
		requires(getRobot().getSubsystems().get("ShootSystem")); 
		sys=(ShootSystem)(getRobot().getSubsystems().get("ShootSystem"));
	}
	boolean isFirst = true;
	@Override
	protected void execute() {
		if(isFirst){
			sys.setHasBall(false);
			getRobot().post(new BallLoadedNotification(false,true));
			isFirst = false;
		}
		sys.load();
    }
    @Override
	protected void end() {
    	super.end();
    	sys.stopShootMotors();
    }
    @Override
	protected boolean isFinished() {
    	if(sys.hasBall()){
    		getRobot().post(new BallLoadedNotification(true,true));
    	}
        return sys.hasBall();
    }

}
