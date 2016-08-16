package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Timer;

public class AutoBackwardCommand extends MDCommand {
	double start;//start time
	double duration=7;
	double accelerationDuration = duration*0.4;
	double breakingDuration = duration*0.4;
	double speed=0;//for backward -0.8
	boolean initialized = false;
	DriveSystem sys;
	

	public AutoBackwardCommand(MDRobotBase robot) {
		super(robot,"AutoBackwardCommand");
		requires(getRobot().getSubsystems().get("DriveSystem")); 
		sys=(DriveSystem)(getRobot().getSubsystems().get("DriveSystem"));
	}

	@Override
	protected void execute() {
		super.execute();
		if(!initialized){
	    	start=Timer.getFPGATimestamp();
			initialized = true;
		}	
		else{
			double time = Timer.getFPGATimestamp()-start;
			if(time < accelerationDuration){
				double s = speed - speed * (Math.cos(Math.PI*time/(2*accelerationDuration)));
				sys.move(s);
			}
			else if(time > (duration - breakingDuration)){
				double s = speed - speed * (Math.sin(Math.PI*(time-(duration-breakingDuration))/(2*breakingDuration)));
				sys.move(s);
			}
			else{
				sys.move(speed);
			}
		}
    }

	@Override
	protected void end() {
    	super.end();
    	sys.move(0);
    	initialized = false;
    }


    @Override
	protected boolean isFinished() {
    	return Timer.getFPGATimestamp()>=start+duration;
    }

}
