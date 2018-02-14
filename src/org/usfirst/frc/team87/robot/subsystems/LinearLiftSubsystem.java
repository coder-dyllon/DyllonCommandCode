package org.usfirst.frc.team87.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
//-----
import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team87.robot.RobotMap;



/**
 *
 */
public class LinearLiftSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Talon  leftMotor = new Talon(RobotMap.linearMotorLeft);
	Talon rightMotor = new Talon(RobotMap.linearMotorRight);
	
	public LinearLiftSubsystem() {
		
		
		
	}
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

