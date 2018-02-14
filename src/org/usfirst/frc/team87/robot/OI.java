/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);


	// If errors, maybe set them to public?
	static Talon LeftFrontMotor;
	static Talon leftRearMotor;
	static Talon rightFrontMotor;
	static Talon rightRearMotor;
	
	static Spark leftSnowBlowerMotor;
	static Spark rightSnowBlowerMotor;
	
	static Joystick joystick;
	static Joystick gamepad;

	static Timer timer;
	
	
	//JoystickButton leftTrigger = new JoystickButton(joystick, RobotMap.leftTrigger);
	//JoystickButton rightTrigger = new JoystickButton(joystick, RobotMap.rightTrigger);
	
	public OI() {
		
		// Variables
		LeftFrontMotor = new Talon(RobotMap.LEFTFRONTMOTOR);
		leftRearMotor = new Talon(RobotMap.LEFTREARMOTOR);
		rightFrontMotor = new Talon(RobotMap.RIGHTFRONTMOTOR);
		rightRearMotor = new Talon(RobotMap.RIGHTREARMOTOR);
		
		leftSnowBlowerMotor = new Spark(RobotMap.LINEARLIFTMOTORLEFT);
		rightSnowBlowerMotor = new Spark(RobotMap.LINEARLIFTMOTORRIGHT);
		
		joystick = new Joystick(RobotMap.JOYSTICK);
		gamepad = new Joystick(RobotMap.GAMEPAD);
		
		int dood = gamepad.getPOV();
		
	}
	
	
	// Don't know if you have to make them static or not --\-_-/--
	public void drive(double speedValue) {	
		
		if(gamepad.getRawButton(RobotMap.LEFTTRIGGER)) {
			//Motor Movement For Function
			// If getting speed error do (speedValue * 0.5)
			LeftFrontMotor.setSpeed(0.5 * speedValue);
			leftRearMotor.setSpeed(0.5 * speedValue);	
		} else if (gamepad.getRawButton(RobotMap.RIGHTTRIGGER)) {
			//Motor Movement For Function
			rightFrontMotor.setSpeed(0.5 * speedValue);
			rightRearMotor.setSpeed(0.5 * speedValue);
		}
	}
	
	public void robotMotorStop() {
		
		LeftFrontMotor.stopMotor();
		leftRearMotor.stopMotor();
		
		rightFrontMotor.stopMotor();
		rightRearMotor.stopMotor();
	}
	
	public void winch(double speedValue) {
		
		leftSnowBlowerMotor.set(1.0);
		rightSnowBlowerMotor.set(1.0);
		
		/*
		if(gamepad.getRawButton(RobotMap.LEFTANALOG)){
			leftSnowBlowerMotor.setSpeed(0.5 * speedValue);
			rightSnowBlowerMotor.setSpeed(0.5 * speedValue);
			LeftFrontMotor.setSpeed(0.75 *- speedValue);
			
		} else if(gamepad.getRawButton(RobotMap.RIGHTANALOG)){
			
		}
		*/
	}
	
	public void restartStartTimer() {
		timer.reset();
		timer.start();
	}
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
