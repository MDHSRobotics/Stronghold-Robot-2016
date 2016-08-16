package org.usfirst.frc.team4141.robot.notifications;

public class RobotStateNotification extends RobotNotification {
	
	public enum RobotState{
		RobotInit,
		DisabledInit,
		DisabledPeriodic,
		AutonomousInit,
		AutonomousPeriodic,
		TeleopInit,
		TeleopPeriodic,
		TestInit,
		TestPeriodic
	}
	public RobotStateNotification(RobotState state,boolean display,boolean record, boolean console) {
		super("RobotStateNotification",display,record,console);
		this.state = state;
	}
	public RobotStateNotification(RobotState state,boolean display) {
		this(state,display,true,false);
	}
	private RobotState state;
	@Override
	protected void addJSONPayload() {
		if(sb.length()>0){
			sb.append(", ");
		}
		sb.append("\"fpgaTime\":");
		sb.append(getFpgaTime());
		if(state!=null){
			sb.append(", \"state\":\"");
			sb.append(state.toString());
			sb.append("\"");
		}
	}
}
