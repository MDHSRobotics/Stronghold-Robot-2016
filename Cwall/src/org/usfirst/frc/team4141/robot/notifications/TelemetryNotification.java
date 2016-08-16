package org.usfirst.frc.team4141.robot.notifications;

import org.usfirst.frc.team4141.robot.Telemetry;

public class TelemetryNotification extends RobotNotification {
	
	private Telemetry[] readings;

	public TelemetryNotification(Telemetry[] readings,boolean display,boolean record,boolean console) {
		super("telemetryNotification",display,record,console);
		this.readings = readings;
	}
	public TelemetryNotification(Telemetry[] readings,boolean display) {
		this(readings,display,true,false);
	}
	

	@Override
	protected void addJSONPayload() {
		if(sb.length()>0){
			sb.append(", ");
		}
		sb.append("\"fpgaTime\":");
		sb.append(getFpgaTime());
		sb.append(", \"sensors\":[");
		boolean isFirst = true;
		for(Telemetry reading:readings){
			if(isFirst) isFirst = false;
			else sb.append(",");
			sb.append(reading.toJSON());
		}
		sb.append("]");	}

}
