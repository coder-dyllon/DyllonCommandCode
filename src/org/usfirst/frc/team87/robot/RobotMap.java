/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	
	//////////////
	////Motors////
	//////////////
	public static final int LEFTFRONTMOTOR = 0;
	public static final int LEFTREARMOTOR = 1;
	public static final int RIGHTFRONTMOTOR = 2;
	public static final int RIGHTREARMOTOR = 3;
	
	public static final int LEFTSNOWMOTOR= 4;
	public static final int RIGHTSNOWMOTOR= 5;
	
	public static int LINEARLIFTMOTORLEFT= 0;
	public static int LINEARLIFTMOTORRIGHT= 1;
	
	// Potential usage
	public static int DRIVEMOTORS[] = {0, 1, 2, 3};
	
	///////////////////
	////Controllers////
	///////////////////
	public static int JOYSTICK = 0;
	public static int GAMEPAD = 1;
	
	///////////
	////POV////
	///////////
	public static int UP = 0;
	public static int RIGHT = 90;
	public static int  DOWN= 180;
	public static int LEFT = 270;
	
	////////////////////////////
	////Triggers and Analogs////
	////////////////////////////
	public static int LEFTTRIGGER = 3;
	public static int RIGHTTRIGGER = 4;
	public static int LEFTANALOG;
	public static int RIGHTANALOG;
	/////////////
	////LED's////
	/////////////
	public static int LEDONE;
	public static int LEDTWO;
	
	///////////////////
	////Positioning////
	///////////////////
	public static String sides[] = {"Red", "Blue"};
	public static String positioning[]  = {"Left", "Middle", "Right"};
	
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	public static int rangefinderPort = 10;
	public static int rangefinderModule = 11;
}
