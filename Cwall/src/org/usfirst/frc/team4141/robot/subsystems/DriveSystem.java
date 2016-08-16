package org.usfirst.frc.team4141.robot.subsystems;

import java.util.Date;

import org.usfirst.frc.team4141.robot.ConfigManager;
import org.usfirst.frc.team4141.robot.DistanceComputedAnalog;
import org.usfirst.frc.team4141.robot.DualDistanceSensor;
import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.RobotMap;
import org.usfirst.frc.team4141.robot.commands.ArcadeDriveCommand;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;


/**
 *
 */
public class DriveSystem extends MDSubsystem {
    

     private ADIS16448_IMU IMU=RobotMap.IMU;
     
     private DualDistanceSensor sensor = RobotMap.dualDistanceSensor;
//    private double currentSpeed = 0;
     
	 public class TankDriveSetting{
		 private double left;
		 private double right;
		 public TankDriveSetting(double left, double right){
			 this.left = left;
			 this.right = right;
		}    
		public double getLeft(){return left;}
		public double getRight(){return right;}
	 }

    public DriveSystem(MDRobotBase robot) {
    	super(robot);
		move(0);    	
    }

    Date lastReading = null;
    long readingPeriod = 150;  //read every 300 millis
    boolean writeH = true;
    public void updateDistance(){
    	Date now = new Date();
    	if(lastReading == null || now.getTime() > (lastReading.getTime()+readingPeriod)){
        	sensor.setStatusLed(writeH);
        	lastReading = now;
        	((DistanceComputedAnalog)ConfigManager.getTelemetry(this, "distance")).update(sensor.read(getRobot().getSensors()));
        	writeH=!writeH;
    	}
    }

	@Override
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeDriveCommand(getRobot()));
    }

//	private double accelerateGain = 0.01;
	public void move(double speed) {
		if(speed == 0) {stop();return;}
//		currentSpeed = currentSpeed + (speed - currentSpeed)*accelerateGain;
    	TankDriveSetting settings = toTankDrive(speed,0); 
    	robotDrive.tankDrive(settings.getLeft(), settings.getRight());
	}
	
	public TankDriveSetting toTankDrive(double forward, double rotate){
		//swap them...
		double temp = forward;
		forward = rotate;
		rotate = temp;
		if (forward>=0) {
			if (rotate>=0){
				//Q1
				return new TankDriveSetting(L(forward,rotate),R(forward,rotate));
			}
			else{
				//Q2
				return new TankDriveSetting(R(forward,-rotate),L(forward,-rotate));
			}
		}
		else{
			if (rotate>=0){
				//Q4
				return new TankDriveSetting(-R(-forward, rotate),-L(-forward,rotate));
			}
			else{
				//Q3
				return new TankDriveSetting(-L(-forward, -rotate),-R(-forward,-rotate));
			}
		}
	}

	private double R(double forward, double rotate) {
		double a = ConfigManager.getItem(this, "drive_a").getDouble();
		double b = ConfigManager.getItem(this, "drive_b").getDouble();	
		return forward - b*rotate + forward*rotate*(b-a-1);
	}

	private double L(double forward, double rotate) {
		double b = ConfigManager.getItem(this, "drive_b").getDouble();	
		return forward+b*rotate*(1-forward);
	}
	  RobotDrive robotDrive = RobotMap.robotDrive; 
	  
	  public void arcadeDrive(Joystick joystick) {
 		  double c = ConfigManager.getItem(this, "drive_c").getDouble();	

		  double rightTriggerValue = joystick.getRawAxis(3);
		  double leftTriggerValue = -joystick.getRawAxis(2);
		  double forward = rightTriggerValue+leftTriggerValue;
    	  double rotate = joystick.getRawAxis(0);
    
    	forward = (Math.abs(rotate)*(-(1.0-c))+1.0)*forward;
//    	System.out.println("forward = "+forward);
    	TankDriveSetting settings = toTankDrive(forward,rotate); 

    	robotDrive.tankDrive(settings.getLeft(), settings.getRight());
    }
public void stop(){
	robotDrive.tankDrive(0,0);
//	currentSpeed = 0;
	
}


public double getAngle() {
	return IMU.getAngleZ();
}

public void move(double speed, double angle) {
	if(speed == 0) {stop();return;}
	TankDriveSetting settings = toTankDrive(speed,angle); 
	robotDrive.tankDrive(settings.getLeft(), settings.getRight());
}




public void gyroReset() {
	IMU.reset();
}


}


