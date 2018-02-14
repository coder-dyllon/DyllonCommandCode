/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team87.robot.subsystems.DriveBase;
// SmartDashboard Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.usfirst.frc.team87.robot.commands.ExampleCommand;
import org.usfirst.frc.team87.robot.subsystems.ExampleSubsystem;


// Vision Imports
import org.opencv.core.Mat;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import org.opencv.imgproc.Imgproc;
import edu.wpi.first.wpilibj.CameraServer;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//Timer Imports
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AnalogTrigger;

//Pneumatics Imports
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	public static OI oi;
	
	public static Joystick gamepad;
	public static Joystick joystick;
	
	
	//Timer timer;
	
	//AnalogTrigger leftTrigger = new AnalogTrigger(RobotMap.leftTrigger);
	//AnalogTrigger rightTrigger = new AnalogTrigger(RobotMap.rightTrigger);
	
	
	// Motor Controllers and Drivetrains
	Spark leftFrontMotorSpark = new Spark(RobotMap.LEFTFRONTMOTOR);
	Spark leftRearMotorSpark = new Spark(RobotMap.LEFTREARMOTOR);
	Spark rightFrontMotorSpark = new Spark(RobotMap.RIGHTFRONTMOTOR);
	Spark rightRearMotorSpark = new Spark(RobotMap.RIGHTREARMOTOR);
	
	SpeedController leftSpeedController;
	SpeedController rightSpeedController;
	
	// Robot Drive
	//DifferentialDrive theRobotDrive = new DifferentialDrive(leftSpeedControl, rightSpeedControl);
	
	
	Command autonomousCommand;
	SendableChooser<Command> sendChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		sendChooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", sendChooser);
		
		// Deadband Elimination
		leftFrontMotorSpark.enableDeadbandElimination(true);
		leftRearMotorSpark.enableDeadbandElimination(true);
		rightFrontMotorSpark.enableDeadbandElimination(true);
		rightRearMotorSpark.enableDeadbandElimination(true);
		
		joystick = new Joystick(RobotMap.JOYSTICK);
		gamepad = new Joystick(RobotMap.GAMEPAD);
		
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = sendChooser.getSelected();
		
		oi.restartStartTimer();
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {

		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		
		oi.restartStartTimer();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {		
		
		oi.drive(gamepad.getRawAxis(RobotMap.LEFTTRIGGER));
		oi.drive(gamepad.getRawAxis(RobotMap.RIGHTTRIGGER));
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	// Vision
    private static void cameraInit() {
        new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);

            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Feed", 640, 480);

            Mat source = new Mat();
            Mat output = new Mat();

            while (!Thread.interrupted()) {
                cvSink.grabFrame(source);
                outputStream.putFrame(output);
            }
        }).start();
    }
}
