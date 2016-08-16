package org.usfirst.frc.team4141.robot.notifications;


public class BallLoadedNotification extends RobotNotification {
	//{"eventType": "ballLoadedNotification", "messageId":8, "timestamp": 1456619634747, "ballStatus":true}
	
	private boolean ballStatus;

	public boolean getBallStatus() {
		return ballStatus;
	}


	public BallLoadedNotification(boolean ballLoaded, boolean display, boolean record, boolean console) {
		super("ballLoadedNotification",display,record,console);
		this.ballStatus = ballLoaded;
	}
	public BallLoadedNotification(boolean ballLoaded, boolean display) {
		this(ballLoaded,display,true,false);
		this.ballStatus = ballLoaded;
	}	

	@Override
	protected void addJSONPayload() {
		if(sb.length()>0){
			sb.append(", ");
		}
		sb.append("\"fpgaTime\":");
		sb.append(getFpgaTime());
		sb.append(", \"ballStatus\":");
		sb.append(getBallStatus());
	}

}
