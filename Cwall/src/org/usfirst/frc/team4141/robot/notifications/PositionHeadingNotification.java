package org.usfirst.frc.team4141.robot.notifications;


public class PositionHeadingNotification extends RobotNotification {
	//{"eventType": "PositionHeadingNotification", "messageId":2, "timestamp": 1456619634747, "position":{"x" : 12, "y" : 280}, "heading":0 }
	
	private Double heading;
	private Double x;
	private Double y;

	public Double getHeading() {
		return heading;
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public PositionHeadingNotification(Double heading, boolean display) {
		this(heading,null,null,display);
	}
	
	public PositionHeadingNotification(Double x, Double y, boolean display) {
		this(null,x,y,display);
	}
	
	public PositionHeadingNotification(Double heading, Double x, Double y, boolean display) {
		this(heading,x,y,display,true,false);
	}
	public PositionHeadingNotification(Double heading, Double x, Double y, boolean display, boolean record, boolean console) {
		super("PositionHeadingNotification",display,record,console);
		this.heading = heading;
		this.x = x;
		this.y = y;
	}
	
	@Override
	protected void addJSONPayload() {
		if(sb.length()>0){
			sb.append(", ");
		}
		sb.append("\"fpgaTime\":");
		sb.append(getFpgaTime());
		
		if(heading!=null){
			sb.append(", \"heading\":");
			sb.append(heading);
		}
		if(x!=null && y!=null){
			sb.append(", \"position\":{\"x\":");
			sb.append(x);
			sb.append(", \"y\":");
			sb.append(y);
			sb.append("}");
		}
				
	}

}
