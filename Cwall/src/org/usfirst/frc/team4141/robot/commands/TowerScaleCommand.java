package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TowerScaleCommand extends CommandGroup {

	public TowerScaleCommand(MDRobotBase robot) {
		addSequential(new UnfurlCommand(robot));
		addSequential(new HookedCommand(robot));
		addSequential(new LiftCommand(robot));
		}
}
