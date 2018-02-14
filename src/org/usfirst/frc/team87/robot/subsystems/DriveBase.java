package org.usfirst.frc.team87.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 *
 */
public class DriveBase extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private DifferentialDrive diabloDrive;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private SpeedControllerGroup leftSpeedController;
	private SpeedControllerGroup rightSpeedController;
	

	Talon leftFrontTalon = new Talon(RobotMap.LEFTFRONTMOTOR);
	Talon leftRearTalon = new Talon(RobotMap.LEFTREARMOTOR);
	Talon rightFrontTalon = new Talon(RobotMap.RIGHTFRONTMOTOR);
	Talon rightRearTalon = new Talon(RobotMap.RIGHTREARMOTOR);
	
	public DriveBase() {

		//Talon ts[] = new Talon(3);
		
    	leftSpeedController = new SpeedControllerGroup(leftFrontTalon, leftRearTalon);
    	rightSpeedController = new SpeedControllerGroup(rightFrontTalon, rightRearTalon);
    	leftFrontTalon.enableDeadbandElimination(true);
    	
    	//diabloDrive = new RobotDrive(leftFrontTalon, leftRearTalon, rightFrontTalon, rightRearTalon);
		
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    		
    }
    
	public void drive() {
		
	}
}

