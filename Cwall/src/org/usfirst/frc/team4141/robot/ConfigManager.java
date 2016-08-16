package org.usfirst.frc.team4141.robot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import org.usfirst.frc.team4141.robot.commands.MDCommand;
import org.usfirst.frc.team4141.robot.subsystems.MDSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ConfigManager {
	private static  ConfigManager instance;
	private static ConfigManager  getInstance(){
		if (instance==null){
			instance = new ConfigManager();
		}
		return instance;
	}
	private Hashtable<Object, Hashtable<String, ConfigItem>> commandConfigItems;
	private Hashtable<Object, Hashtable<String, ConfigItem>> subsystemsConfigItems;
	private Hashtable<Object, Hashtable<String, Telemetry>> commandTelemetry;
	private Hashtable<Object, Hashtable<String, Telemetry>> subsystemsTelemetry;

	public enum Type {
		integer,
		doubleNumber,
		string
	}
	private ConfigManager() {
		commandConfigItems = new Hashtable<Object,Hashtable<String,ConfigItem>>();
		subsystemsConfigItems = new Hashtable<Object,Hashtable<String,ConfigItem>>();
		commandTelemetry = new Hashtable<Object,Hashtable<String,Telemetry>>();
		subsystemsTelemetry = new Hashtable<Object,Hashtable<String,Telemetry>>();
	}
	public Hashtable<Object,Hashtable<String,ConfigItem>> getCommandConfigItems(){
		return commandConfigItems;
	}
	public Hashtable<Object,Hashtable<String,ConfigItem>> getSubsystemsConfigItems(){
		return subsystemsConfigItems;
	}
	public Hashtable<Object,Hashtable<String,Telemetry>> getCommandTelemetry(){
		return commandTelemetry;
	}
	public Hashtable<Object,Hashtable<String,Telemetry>> getSubsystemsTelemetry(){
		return subsystemsTelemetry;
	}	
	public static void put(MDCommand command,ConfigItem configItem){
		String commandName = command.getName();
		if(!getInstance().getCommandConfigItems().containsKey(commandName)){
			getInstance().getCommandConfigItems().put(commandName, new Hashtable<String,ConfigItem>());
		}
		getInstance().getCommandConfigItems().get(commandName).put(configItem.getName(),configItem);
	}
	public static void put(MDCommand command,Telemetry telemetry){
		String commandName = command.getName();
		if(!getInstance().getCommandTelemetry().containsKey(commandName)){
			getInstance().getCommandTelemetry().put(commandName, new Hashtable<String,Telemetry>());
		}
		getInstance().getCommandTelemetry().get(commandName).put(telemetry.getName(),telemetry);
	}	
	public static void put(MDSubsystem system,ConfigItem configItem){
		if(!getInstance().getSubsystemsConfigItems().containsKey(system)){
			getInstance().getSubsystemsConfigItems().put(system, new Hashtable<String,ConfigItem>());
		}
		getInstance().getSubsystemsConfigItems().get(system).put(configItem.getName(),configItem);
	}
	public static void put(MDSubsystem system,Telemetry telemetry){
		if(!getInstance().getSubsystemsTelemetry().containsKey(system)){
			getInstance().getSubsystemsTelemetry().put(system, new Hashtable<String,Telemetry>());
		}
		getInstance().getSubsystemsTelemetry().get(system).put(telemetry.getName(),telemetry);
	}
	
	
	public static ConfigItem getItem(Command command, String configItemName){
		String commandName = command.getName();
		if(getInstance().getCommandConfigItems().containsKey(commandName) &&
				getInstance().getCommandConfigItems().get(commandName).containsKey(configItemName)){
			return getInstance().getCommandConfigItems().get(commandName).get(configItemName);
		}
		return null;
	}
	public static Telemetry getTelemetry(Command command, String telemetryName){
		String commandName = command.getName();
		if(getInstance().getCommandTelemetry().containsKey(commandName) &&
				getInstance().getCommandTelemetry().get(commandName).containsKey(telemetryName)){
			return getInstance().getCommandTelemetry().get(commandName).get(telemetryName);
		}
		return null;
	}
	public static ConfigItem getItem(MDSubsystem system, String configItemName){
		if(getInstance().getSubsystemsConfigItems().containsKey(system) &&
				getInstance().getSubsystemsConfigItems().get(system).containsKey(configItemName)){
			return getInstance().getSubsystemsConfigItems().get(system).get(configItemName);
		}
		return null;
	}
	public static Telemetry getTelemetry(MDSubsystem system, String telemetryName){
		if(getInstance().getSubsystemsTelemetry().containsKey(system) &&
				getInstance().getSubsystemsTelemetry().get(system).containsKey(telemetryName)){
			return getInstance().getSubsystemsTelemetry().get(system).get(telemetryName);
		}
		return null;
	}

	public static Collection<ConfigItem> getItems(String commandName) {
		if(getInstance().getCommandConfigItems().containsKey(commandName)){
			return getInstance().getCommandConfigItems().get(commandName).values();
		}
		return new ArrayList<ConfigItem>();
	}	
	public static Collection<Telemetry> getTelemetry(String commandName) {
		if(getInstance().getCommandTelemetry().containsKey(commandName)){
			return getInstance().getCommandTelemetry().get(commandName).values();
		}
		return new ArrayList<Telemetry>();
	}	
	public static Collection<ConfigItem> getItems(MDSubsystem system){
		if(getInstance().getSubsystemsConfigItems().containsKey(system)){
			return getInstance().getSubsystemsConfigItems().get(system).values();
		}
		return new ArrayList<ConfigItem>();
	}
	public static Collection<Telemetry> getTelemetry(MDSubsystem system){
		if(getInstance().getSubsystemsTelemetry().containsKey(system)){
			return getInstance().getSubsystemsTelemetry().get(system).values();
		}
		return new ArrayList<Telemetry>();
	}
	
	
	public static boolean hasItems() {
		return !(getInstance().commandConfigItems.isEmpty() && getInstance().subsystemsConfigItems.isEmpty() &&
				getInstance().commandTelemetry.isEmpty() && getInstance().subsystemsTelemetry.isEmpty());
	}
}
