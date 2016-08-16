package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.robot.MDRobotBase;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCrossLowCommand extends CommandGroup {

	public AutoCrossLowCommand(MDRobotBase robot) {
//		addSequential(new RaiseCannonCommand(robot));
		addSequential(new AutoFowardCommand(robot));
//		addSequential(new AutoDrivePauseCommand(robot));
//		addSequential(new AutoBackwardCommand(robot));
		
	}
}
