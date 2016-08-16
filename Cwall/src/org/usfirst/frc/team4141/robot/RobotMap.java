package org.usfirst.frc.team4141.robot;


import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static SpeedController rightFrontMotor;
    public static SpeedController rightRearMotor;
    public static SpeedController leftFrontMotor;
    public static SpeedController leftRearMotor;
    public static SpeedController shootRightMotor;
    public static SpeedController shootLeftMotor;
//    public static SpeedController liftFrontMotor;
//    public static SpeedController liftRearMotor;
    public static SpeedController pivotmotor;

//    public static MDDigitalInput unfurledswitch;
//    public static MDDigitalInput hookSwitch;
    public static MDDigitalInput ballLoadedswitch;
    public static MDDigitalInput pivotloadswitch;
    public static MDDigitalInput pivotshootswitch;
//    public static MDDigitalInput liftSwitch;
    
    public static MDAnalogInput cannonEncoder;
    

    public static Solenoid feedBallSolenoid;
//    public static Solenoid liftReleaseSolenoid;
//    public static Solenoid liftLockSolenoid;

    public static MD_IMU IMU;
    public static MD_BuiltInAccelerometer  builtInAccelerometer;

    public static RobotDrive robotDrive;
    
	public static DualDistanceSensor dualDistanceSensor;

    private static void configureLiveWindow(){
    	//LiveWindow is the test console when the robot is in Test Mode
        LiveWindow.addActuator("Drive System", "Right Front Motor", (Talon) rightFrontMotor);
        LiveWindow.addActuator("Drive System", "Right Rear Motor", (Talon) rightRearMotor);
        LiveWindow.addActuator("Drive System", "Left Front Motor", (Talon) leftFrontMotor);
        LiveWindow.addActuator("Drive System", "Left Rear Motor", (Talon) leftRearMotor);
//        LiveWindow.addActuator("Arm System", "Lift Front Motor", (Victor) liftFrontMotor);
//        LiveWindow.addActuator("Arm System", "Lift Rear Motor", (Victor) liftRearMotor);
        LiveWindow.addActuator("Shoot System", "Shoot Right Motor", (Talon) shootRightMotor);
        LiveWindow.addActuator("Shoot System", "Shoot Left Motor", (Talon) shootLeftMotor);
        LiveWindow.addActuator("Shoot System", "Pivot Motor", (Talon) pivotmotor); 
        LiveWindow.addSensor("Pivot Switches", "Pivot Load Switch", pivotloadswitch);
        LiveWindow.addSensor("Pivot Switches", "Pivot Shoot Switch", pivotshootswitch);
//        LiveWindow.addSensor("Arm Switches", "Unfurled Switch", unfurledswitch);
//        LiveWindow.addSensor("Arm Switches", "Lift Switch", liftSwitch);
//        LiveWindow.addSensor("Arm Switches", "Hool Switch", hookSwitch);
//        LiveWindow.addSensor("Arm Switches", "Ball Loaded Switch", ballLoadedswitch);
//        LiveWindow.addActuator("pneumatics", "feedBall", feedBallSolenoid);
//        LiveWindow.addActuator("pneumatics", "liftRelease", liftReleaseSolenoid);
//        LiveWindow.addActuator("pneumatics", "liftLock", liftLockSolenoid);

//        LiveWindow.addSensor("IMU", "IMU", IMU);
        LiveWindow.addSensor("Shoot System", "cannonEncoder", cannonEncoder);
       // LiveWindow.setEnabled(true);
     //   LiveWindow.run();
    }
    
    public static void init(boolean enableLiveWindow) {


    	
    	//drive motors
        rightFrontMotor = new Talon(0);
        rightRearMotor = new Talon(1);
        leftFrontMotor = new Talon(2);
        leftRearMotor = new Talon(3);

        //lift motors
//        liftFrontMotor = new Victor(0);
//        liftRearMotor = new Victor(1);
        
        //shoot motors
        shootRightMotor = new Talon(6);
        shootLeftMotor = new Talon(7);
        
        //pivot motor
        pivotmotor = new Talon (8);
        
        //switches
        pivotloadswitch = new MDDigitalInput(0,"pivotloadswitch");
        pivotshootswitch = new MDDigitalInput(1,"pivotshootswitch");
//        unfurledswitch = new MDDigitalInput(2,"unfurledswitch");
//        liftSwitch = new MDDigitalInput(3,"liftSwitch");
//        hookSwitch = new MDDigitalInput(4,"hookSwitch");
        ballLoadedswitch = new MDDigitalInput(5,"ballLoadedswitch");
        

        cannonEncoder = new MDAnalogInput(0, "cannonEncoder"); 
        //solenoids
        feedBallSolenoid = new Solenoid(0,1);
//        liftReleaseSolenoid = new Solenoid(0,0);
//        liftLockSolenoid = new Solenoid(0,2);

        //other
        IMU = new MD_IMU();
        builtInAccelerometer = new MD_BuiltInAccelerometer();
        robotDrive = new RobotDrive(leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor);
        
        
        configureLiveWindow();
        
        
	}

}
