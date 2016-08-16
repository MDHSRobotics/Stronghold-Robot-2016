package org.usfirst.frc.team4141.robot.notifications;

import java.util.Collection;


import org.usfirst.frc.team4141.robot.ConfigItem;
import org.usfirst.frc.team4141.robot.ConfigManager;
import org.usfirst.frc.team4141.robot.ConfigManager.Type;
import org.usfirst.frc.team4141.robot.MDRobotBase;
import org.usfirst.frc.team4141.robot.Telemetry;
import org.usfirst.frc.team4141.robot.commands.MDCommand;
import org.usfirst.frc.team4141.robot.subsystems.MDSubsystem;

import edu.wpi.first.wpilibj.command.Command;




public class RobotConfigurationNotification extends RobotNotification {
	
	
	private MDRobotBase robot;
	public RobotConfigurationNotification(MDRobotBase robot,boolean display) {
		this(robot, display,true, false);
	}
	public RobotConfigurationNotification(MDRobotBase robot, boolean display,boolean record,boolean broadcast) {
		super("RobotConfigurationNotification", display,record, broadcast);
		this.robot = robot;
	}

	
	@Override
	protected void addJSONPayload() {
		if(sb.length()>0){
			sb.append(", ");
		}
		sb.append("\"fpgaTime\":");
		sb.append(getFpgaTime());
		Object[] commandNames = robot.getCommands().keySet().toArray();
		Object[] systems = robot.getSubsystems().values().toArray();
		
		if(commandNames.length>0){
			sb.append(", \"commands\":[");
			boolean firstWritten = false;
			for(int k=0;k<commandNames.length;k++){
				String commandName = (String)commandNames[k];
				Command command = robot.getCommands().get(commandName);
				Collection<ConfigItem> items = ConfigManager.getItems(commandName);
				Collection<Telemetry> telemetry = ConfigManager.getTelemetry(commandName);
				Collection<String> paramNames = null;
				if(command instanceof MDCommand){
					paramNames = ((MDCommand)command).getAttributes().keySet();
				}
				
				if(items!=null && items.size()>0 || telemetry!=null && telemetry.size()>0){
					if(firstWritten) sb.append(',');
					else firstWritten = true;
					sb.append("{\"command\":\"");
					sb.append(commandName);
					sb.append("\"");
					if(items!=null && items.size()>0){
						sb.append(", \"items\":[");
						appendItems(ConfigManager.getItems(commandName));
						sb.append("]");
					}
					if(telemetry!=null && telemetry.size()>0){
						sb.append(", \"telemetry\":[");
						appendTelemetry(ConfigManager.getTelemetry(commandName));
						sb.append("]");
					}
					if(paramNames!=null && paramNames.size()>0){
						sb.append(", \"params\":[");
						boolean firstParamWritten = false;
						for(String key : paramNames ){
							if(firstParamWritten) sb.append(",");
							else firstParamWritten = true;
							sb.append(String.format("{\"%s\":\"%s\"}", key,((MDCommand)command).getAttribute(key)));
						}
						sb.append("]");
					}
					sb.append("}");
				}
				
			}
			sb.append("]");
		}
		if(systems.length>0){
			sb.append(", \"subsystems\":[");
			boolean firstWritten = false;
			for(int k=0;k<systems.length;k++){
				Collection<ConfigItem> items = ConfigManager.getItems((MDSubsystem) systems[k]);
				Collection<Telemetry> telemetry = ConfigManager.getTelemetry((MDSubsystem) systems[k]);
				if(items!=null && items.size()>0 || telemetry!=null && telemetry.size()>0){
					if(firstWritten) sb.append(',');
					else firstWritten = true;
					MDSubsystem sys = (MDSubsystem) systems[k];
					sb.append("{\"subsystem\":\"");
					sb.append(sys.getName());
					sb.append("\"");
					if(items!=null && items.size()>0){
						sb.append(", \"items\":[");
						appendItems(ConfigManager.getItems(sys));
						sb.append("]");
					}
					if(telemetry!=null && telemetry.size()>0){
						sb.append(", \"telemetry\":[");
						appendTelemetry(ConfigManager.getTelemetry(sys));
						sb.append("]");
					}
					sb.append("}");
				}
			}
			sb.append("]");
		}
	}
	private void appendTelemetry(Collection<Telemetry> telemetry) {
		boolean first = true;
		for(Telemetry item : telemetry){
			if(first) first = false;
			else sb.append(',');
			sb.append(item.toJSON());
		}
	}

	private void appendItems(Collection<ConfigItem> items){
		boolean first = true;
		for(ConfigItem item : items){
			if(first) first = false;
			else sb.append(',');
			append(item);
		}
	}
	private void append(ConfigItem item) {
		sb.append("{\"name\":\"");
		sb.append(item.getName());
		sb.append("\", \"type\":\"");
		sb.append(item.getType());
		sb.append("\"");

		if(item.getType() == Type.integer || item.getType() == Type.doubleNumber){
			sb.append(",\"value\":");
			sb.append(item.getValue());
			if(item.getMin()!=null){
				sb.append(",\"min\":");
				sb.append(item.getMin());
			}
			if(item.getMax()!=null){
				sb.append(",\"max\":");
				sb.append(item.getMax());
			}
		}
		else{
			sb.append(",\"value\":\"");
			sb.append(item.getValue());
			sb.append("\"");
		}
		sb.append("}");
		
	}


}
