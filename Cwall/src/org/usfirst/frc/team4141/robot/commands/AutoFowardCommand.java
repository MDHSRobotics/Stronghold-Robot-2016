package org.usfirst.frc.team4141.robot.commands;

//import org.usfirst.frc.team4141.robot.ComputedAnalog;
//import org.usfirst.frc.team4141.robot.ConfigManager;
import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Timer;

public class AutoFowardCommand extends MDCommand {
	//private 
	 double start;//start time
	 double duration=6.0;
	 double accelerationDuration = duration*0.2;
	 double breakingDuration = duration*0.2;
	 double speed=0.5;
	 double Kp = 0.02;
	 double setAngle;
	 boolean initialized = false;
	 DriveSystem sys;
	 
	public AutoFowardCommand(MDRobotBase robot) {
		super(robot,"AutoForwardCommand");
		requires(getRobot().getSubsystems().get("DriveSystem")); 
		sys=(DriveSystem)(getRobot().getSubsystems().get("DriveSystem"));
	}
	@Override
	protected void execute() {
		super.execute();
//		ComputedAnalog setAngle= getRobot().getVariable("setAngle");
		 
		if(!initialized){
//			System.out.println("initializing AutoForward");
//			sys.gyroReset();
			
			
//			setAngle.setValue(sys.getAngle());
//			System.out.println("set angle = "+setAngle.getValue());
	    	start=Timer.getFPGATimestamp();
	    	setAngle=sys.getAngle();
			initialized = true;
		}
		else{
//			double duration = ConfigManager.getItem(this, "duration").getDouble();
//			double speed = ConfigManager.getItem(this, "speed").getDouble();
//			double accelerationDuration = ConfigManager.getItem(this, "accelerationDuration").getDouble();
//			double breakingDuration = ConfigManager.getItem(this, "breakingDuration").getDouble();
//			double Kp = ConfigManager.getItem(this, "Kp").getDouble();
//			ComputedAnalog correction = getRobot().getVariable("correction");
//			
//			
////			String data = sys.getIMUData();
////			System.out.println(data);
//			double angle = setAngle.getValue() - sys.getAngle();
////			System.out.printf("angle=%f\n",angle);
////			System.out.printf("kp=%f\n",Kp);
//			correction.setValue(angle*Kp);
////			System.out.printf("correction=%f\n",correction.getValue());
			double time = Timer.getFPGATimestamp()-start;
			if(time < accelerationDuration){
				double s = speed - speed * (Math.cos(Math.PI*time/(2*accelerationDuration)));
				sys.move(s,setAngle);
			}
			else if(time > (duration - breakingDuration)){
				double s = speed - speed * (Math.sin(Math.PI*(time-(duration-breakingDuration))/(2*breakingDuration)));
				sys.move(s,setAngle);
			}
			else{
				sys.move(speed,setAngle);
			}
		}
    }
	
    @Override
	protected void end() {
    	super.end();
    	sys.move(0);
//    	initialized= false;
//    	System.out.println("reset initialized flag");
    }


    @Override
	protected boolean isFinished() {
//    	double duration = ConfigManager.getItem(this, "duration").getDouble();
    	return Timer.getFPGATimestamp()>=start+duration;
    }

//    @Override
//    protected void interrupted() {
//    	super.interrupted();
//    	System.out.println("interrupted");
//    }
}
