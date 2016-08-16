
package org.usfirst.frc.team4141.robot;

import java.util.Map;

import org.eclipse.jetty.websocket.api.Session;
import org.usfirst.frc.team4141.robot.commands.ArcadeDriveCommand;
import org.usfirst.frc.team4141.robot.commands.AutoBackwardCommand;
import org.usfirst.frc.team4141.robot.commands.AutoCrossLowCommand;
import org.usfirst.frc.team4141.robot.commands.AutoDrivePauseCommand;
import org.usfirst.frc.team4141.robot.commands.AutoFowardCommand;
import org.usfirst.frc.team4141.robot.commands.GetBallCommand;
import org.usfirst.frc.team4141.robot.commands.GotBallCommand;

import org.usfirst.frc.team4141.robot.commands.LoadingCommand;
import org.usfirst.frc.team4141.robot.commands.LowerCannonCommand;
import org.usfirst.frc.team4141.robot.commands.MDCommand;
import org.usfirst.frc.team4141.robot.commands.RaiseCannonCommand;
import org.usfirst.frc.team4141.robot.commands.ShootCommand;

import org.usfirst.frc.team4141.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4141.robot.subsystems.MDSubsystem;
import org.usfirst.frc.team4141.robot.subsystems.ShootSystem;


import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends MDRobotBase {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	private static boolean enableLiveWindow = true;
	private static boolean enableSmartDashboard = false;
	private boolean rumbling = false;
	private double rumblestart;
	CameraServer server;
	
	public Robot(){
		super(enableLiveWindow,enableSmartDashboard);
		try{
			USBCamera camera =new USBCamera("cam0");
			camera.setFPS(10);
			camera.setSize(192, 144);
	        server = CameraServer.getInstance();
	        server.setQuality(25);
	        //the camera name (ex "cam0") can be found through the roborio web interface
	        server.startAutomaticCapture(camera);
		}
		catch(VisionException e){
//			log(Level.ERROR,"Robot()","error instantiating camera: "+e.getMessage());
			System.err.println("error instantiating camera: "+e.getMessage());
		}
    }
	
//	@Override
//	public void teleopPeriodic() {
//		super.teleopPeriodic();
//		double now = Timer.getFPGATimestamp();
//		
//		if (!rumbling && getSubsystems().get("ShootSystem").isRumbling()) {
//			rumblestart = Timer.getFPGATimestamp();
//			rumbling = true;
//		}
//		
//		if (rumbling && now-rumblestart > 1.0) {
//			getSubsystems().get("ShootSystem").stopRumbling();
//			rumbling = false;
			
//		}
		
//	}
	

	@Override
	protected void mdRobotInit() {
		RobotMap.dualDistanceSensor = new DualDistanceSensor(0x41);
		
		//add Subsystems here
//		MDSubsystem sys = new ArmSystem(this);
//		//configure configItems for ArmSystem
//		ConfigManager.put(sys, new DoubleConfigItem("unfurlSpeed",0.0,1.0,0.2));
//		ConfigManager.put(sys, new DoubleConfigItem("liftSpeed",0.0,1.0,0.33));
				
//		add("ArmSystem", sys);
		
		//configure telemetry observations here
		RobotMap.pivotloadswitch.read(getSensors());
		RobotMap.pivotshootswitch.read(getSensors());
		RobotMap.ballLoadedswitch.read(getSensors());
		RobotMap.cannonEncoder.read(getSensors());
		
		RobotMap.IMU.read(getSensors());
		RobotMap.builtInAccelerometer.read(getSensors());
		RobotMap.dualDistanceSensor.read(getSensors());
		
				
		MDSubsystem sys = new DriveSystem(this);
		ConfigManager.put(sys, new DoubleConfigItem("tolerance",0.0,0.35,0.35));
		ConfigManager.put(sys, new IntegerConfigItem("windowsize",0,50,3));
		ConfigManager.put(sys, new DoubleConfigItem("drive_a",0.0,1.0,1.0));
		ConfigManager.put(sys, new DoubleConfigItem("drive_b",0.0,1.0,1.0));
		ConfigManager.put(sys, new DoubleConfigItem("drive_c",0.0,1.0,1.0));
		DistanceComputedAnalog distance = new DistanceComputedAnalog("distance", ConfigManager.getItem(sys, "tolerance"), ConfigManager.getItem(sys, "windowsize"));
		ConfigManager.put(sys, getVariable(distance));
		ConfigManager.put(sys, getSensors().get("distance0"));
		ConfigManager.put(sys, getSensors().get("distance1"));
//		ConfigManager.put(sys, getSensors().get("AngleZ"));
		
//		ConfigManager.put(sys, getSensors().get("Angle"));		
//		ConfigManager.put(sys, getSensors().get("AccelX"));
//		ConfigManager.put(sys, getSensors().get("AccelY"));
//		ConfigManager.put(sys, getSensors().get("AccelZ"));
//		ConfigManager.put(sys, getSensors().get("AngleX"));
//		ConfigManager.put(sys, getSensors().get("AngleY"));
//		ConfigManager.put(sys, getSensors().get("MagX"));
//		ConfigManager.put(sys, getSensors().get("MagY"));
//		ConfigManager.put(sys, getSensors().get("MagZ"));
//		ConfigManager.put(sys, getSensors().get("Rate"));
//		ConfigManager.put(sys, getSensors().get("RateX"));
//		ConfigManager.put(sys, getSensors().get("RateY"));
//		ConfigManager.put(sys, getSensors().get("RateZ"));
//		ConfigManager.put(sys, getSensors().get("Rio_AccelX"));
//		ConfigManager.put(sys, getSensors().get("Rio_AccelY"));
//		ConfigManager.put(sys, getSensors().get("Rio_AccelZ"));

		add("DriveSystem", sys);
		
		sys = new ShootSystem(this);
		ConfigManager.put(sys, new DoubleConfigItem("pivotSpeedUp",     0.0,1.0,0.6));
		ConfigManager.put(sys, new DoubleConfigItem("pivotSpeedDown",   0.0,1.0,0.5));
		ConfigManager.put(sys, new DoubleConfigItem("shootSpeed",       0.0,1.0,1.0));
		ConfigManager.put(sys, new DoubleConfigItem("loadSpeed",        0.0,1.0,0.75));
		ConfigManager.put(sys, new DoubleConfigItem("lowGoalShootSpeed",0.0,1.0,0.6));
		ConfigManager.put(sys, new DoubleConfigItem("encoderCrossoverThreshold",0.0,5.0,2.5));
		ConfigManager.put(sys, getSensors().get(RobotMap.cannonEncoder.getName()));
		ConfigManager.put(sys, getSensors().get(RobotMap.pivotloadswitch.getName()));
		ConfigManager.put(sys, getVariable("cannonPosition"));
		
//00		ConfigManager.put(sys, getSensors().get(RobotMap.pivotshootswitch.getName()));
//		ConfigManager.put(sys, getSensors().get(RobotMap.ballLoadedswitch.getName()));
		
		add("ShootSystem", sys);
		
		//add commands here
		MDCommand command =  new AutoFowardCommand(this);
		//configure configItems for AutoForwardCommand
		ConfigManager.put(command, new DoubleConfigItem("duration",0.0,15.0,6.0));
		ConfigManager.put(command, new DoubleConfigItem("accelerationDuration",0.0,7.5,1.2));
		ConfigManager.put(command, new DoubleConfigItem("breakingDuration",0.0,7.5,1.2));
		ConfigManager.put(command, new DoubleConfigItem("speed",0.0,1.0,0.5));
		ConfigManager.put(command, new DoubleConfigItem("Kp",0.00,0.30,0.03));
		ConfigManager.put(command, getSensors().get("AngleZ"));
		ConfigManager.put(command, getVariable("correction"));
//		ConfigManager.put(command, getVariable("setAngle"));

		command.setAttribute("isAutonomous", "true");
		add("AutoForwardCommand",command);
		
		add("AutoBackwardCommand", new AutoBackwardCommand(this));
		add("AutoDrivePauseCommand", new AutoDrivePauseCommand(this));
		add("ArcadeDriveCommand", new ArcadeDriveCommand(this));
		add("RaiseCannonCommand", new RaiseCannonCommand(this));
		add("LowerCannonCommand", new LowerCannonCommand(this));
		add("LoadingCommand", new LoadingCommand(this));
		add("ShootCommand", new ShootCommand(this));
		add("GotBallCommand", new GotBallCommand(this));
		
//		command =  new UnfurlCommand(this);
//		ConfigManager.put(command, new DoubleConfigItem("delay",0.0,3.0,0.5));
//		add("UnfurlCommand", command);
//		add("LiftCommand", new LiftCommand(this));
//		add("HookedCommand", new HookedCommand(this));

		//add command groups here
		add("AutoCrossLowCommand", new AutoCrossLowCommand(this));
		add("GetBallCommand", new GetBallCommand(this));
//		add("TowerScaleCommand", new TowerScaleCommand(this));

		autonomousCommand=getCommands().get("AutoFowardCommand");

		//add SmartDashboard Command buttons here
		if(isSmartDashboardEnabled()){
//			SmartDashboard.putData("Auto Foward", getCommands().get("AutoFowardCommand"));
//			SmartDashboard.putData("Auto Backward", getCommands().get("AutoBackwardCommand"));
			SmartDashboard.putData("Auto Cross Low", getCommands().get("AutoCrossLowCommand"));
			SmartDashboard.putData("Get Ball", getCommands().get("GetBallCommand"));
			SmartDashboard.putData("Got Ball Trigger", getCommands().get("GotBallCommand"));
			SmartDashboard.putData("Shoot", getCommands().get("ShootCommand"));
//			SmartDashboard.putData("Unfurl", getCommands().get("UnfurlCommand"));
//			SmartDashboard.putData("Lift", getCommands().get("LiftCommand"));
//			SmartDashboard.putData("Hook", getCommands().get("HookedCommand"));
//			SmartDashboard.putData("Scale Tower", getCommands().get("TowerScaleCommand"));
		}
		
		//configure joystick
		getOi().add("LowerCannonButton", new JoystickButton(getOi().getJoystick(), 5));
		getOi().getButtons().get("LowerCannonButton").whileHeld(getCommands().get("LowerCannonCommand"));

		getOi().add("RaiseCannonButton", new JoystickButton(getOi().getJoystick(), 6));
		getOi().getButtons().get("RaiseCannonButton").whileHeld(getCommands().get("RaiseCannonCommand"));

		getOi().add("LoadBallButton", new JoystickButton(getOi().getJoystick(), 2));
		getOi().getButtons().get("LoadBallButton").whenPressed(getCommands().get("LoadingCommand"));
		//getOi().getButtons().get("LoadBallButton").whenPressed(new GetBallCommand(this));

		getOi().add("GotBallButton", new JoystickButton(getOi().getJoystick(), 3));
		getOi().getButtons().get("GotBallButton").whenPressed(getCommands().get("GotBallCommand"));

		getOi().add("ShootButton", new JoystickButton(getOi().getJoystick(), 1));
		getOi().getButtons().get("ShootButton").whenPressed(getCommands().get("ShootCommand"));

		getOi().add("UnfurlButton", new JoystickButton(getOi().getJoystick(), 4));
		getOi().getButtons().get("UnfurlButton").whenPressed(getCommands().get("UnfurlCommand"));


	}


	private void add(String name, CommandGroup cmdGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnect(Session session) {
		super.onConnect(session);
	}
	@Override
	public void onClose(Session session, int closeCode, String closeReason) {
		System.out.printf("disconnected code[%d] reason:%s",closeCode,closeReason);
	}
	@Override
	public void onError(Session session, Throwable err) {
		System.err.println("error: "+err.getMessage());
	}
	@Override
	public void onText(Session session, String event) {
		boolean isCommand = false;
		boolean isSystem = false;
		
		Map map= decode(event);
		if(map==null) return;

		String eventType=null;
		
		
		String key = "eventType";
		if(map.containsKey(key)){		
			eventType = (String) map.get(key);
			
		}
		if(eventType!=null){
			if(eventType.equals("configItemNotification")){
				String commandName=null;
				String systemName=null;
				String itemName=null;
				String value=null;
				key = "name";
				if(map.containsKey(key)){
					itemName = (String) map.get(key);
					
				}
				key = "systemName";
				if(map.containsKey(key)){
					systemName=(String) map.get(key);
					
					isSystem = true;
				}
				key = "commandName";
				if(map.containsKey(key)){
					commandName=(String) map.get(key);
					
					isCommand = true;
				}
				key = "value";
				if(map.containsKey(key)){
					value = (String) map.get(key);
					
				}
				if(itemName!=null && value!=null){
					ConfigItem item = null;
					if(isCommand && commandName!=null){
						item = ConfigManager.getItem(getCommands().get(commandName), itemName);
					}
					if(isSystem && systemName!=null){
						item = ConfigManager.getItem(getSubsystems().get(systemName), itemName);
					} 
					if(item!=null){
						switch(item.getType()){
						case doubleNumber:
							try{
								Double d = Double.valueOf(value);
								if(d!=null){
									item.setValue(d);
								}
							}
							catch(NumberFormatException nfe){							
							}						
							break;
						case integer:
							try{
								Integer i = Integer.valueOf(value);
								if(i!=null){
									item.setValue(i);
								}
							}
							catch(NumberFormatException nfe){							
							}	
							break;
						case string:
							item.setValue(value);
							break;
						}
					}
				}				
			}
		}

	}
	@Override
	public void onBinary(Session session, byte[] bytes, int arg2, int arg3) {
		// do nothing.  not supported yet
	}
	

	
	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		super.teleopPeriodic();
		MDSubsystem sys = getSubsystems().get("ShootSystem");
		((ShootSystem)sys).updateCannonPosition();
		sys = getSubsystems().get("DriveSystem");
		((DriveSystem)sys).updateDistance();
	}
	
	//@Override

	int timer = 0;
	int timesUp = 300;    // timesUp/50 = seconds
	
	public void autonomousPeriodic() {
	
		if(timer < timesUp){
			RobotMap.rightFrontMotor.set(.5);
			RobotMap.rightRearMotor.set(.5);
			RobotMap.leftFrontMotor.set(.5);
			RobotMap.leftRearMotor.set(.5);
			timer++;
		}else {
			RobotMap.rightFrontMotor.set(0);
			RobotMap.rightRearMotor.set(0);
			RobotMap.leftFrontMotor.set(0);
			RobotMap.leftRearMotor.set(0);
		
			
		}
/*		super.autonomousPeriodic();
		MDSubsystem sys = getSubsystems().get("ShootSystem");
		((ShootSystem)sys).updateCannonPosition();
		sys = getSubsystems().get("DriveSystem");
		((DriveSystem)sys).updateDistance();
*/		
		
	}
	
}
