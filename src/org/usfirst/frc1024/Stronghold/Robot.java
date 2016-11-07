package org.usfirst.frc1024.Stronghold;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1024.Stronghold.commands.*;
import org.usfirst.frc1024.Stronghold.subsystems.*;


public class Robot extends IterativeRobot {
	Command autonomousCommand;	

	public static OI oi;
	public static Drivetrain drivetrain;
	public static DefenseBuster defenseBuster;
	public static Intake intake;
	public static Shooter shooter;
	public static Compressor compressor;
	public static double[] testArray = { 4, 8, 15, 16, 23, 42 };
	public static boolean goingStraight = false;
	public static int straightConstant = 1;
	public int autoSelected;
	public int position;
	public static REVDigitBoard autoChooser;
	public int autoDelay;
	public double potOffset = 0;
	
	
	public void robotInit() {
		
		RobotMap.init();
		RobotMap.pixyPower.set(true);
		drivetrain = new Drivetrain();
		defenseBuster = new DefenseBuster();
		intake = new Intake();
		shooter = new Shooter();
		compressor = new Compressor();
		autoChooser = new REVDigitBoard();

		//DO NOT MOVE UNDER ANY CIRCUMSTANCE!!!
		oi = new OI();
	}

	public void disabledInit() {
		autoChooser.display("1024");
		potOffset = autoChooser.pot.getAverageVoltage();
		Timer.delay(2);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		printPixyStuff();
		
		if(!autoChooser.getButtonA()){
			autoSelected++;
			Timer.delay(.2);
			while(!autoChooser.getButtonA());
			if(autoSelected > 9) autoSelected = 0;
		}
		if(!autoChooser.getButtonB()){
			position++;
			Timer.delay(.2);
			while(!autoChooser.getButtonB());
			if(position > 5) position = 0;
		}
		/*
		autoDelay = (int) ((autoChooser.getPot()-.25) * 440);
		autoDelay = (int) ((autoChooser.pot.getAverageVoltage()- potOffset) * 440);
		if (autoDelay > 9) autoDelay = 9;
		if (autoDelay < 0) autoDelay = 0;
		
		if(autoSelected < 10 && position < 10){
			autoChooser.display("0" + position + "0" + autoSelected);
		}else if(autoSelected < 10 && position >= 10){
			autoChooser.display(""+ position + "0"+ autoSelected);
		}else if(autoSelected >= 10 && position < 10){
			autoChooser.display("0" + position + autoSelected);
		}else if(autoSelected >= 10 && position >= 10){
			autoChooser.display(""+ autoSelected + position);
		}else{
			autoChooser.display("NULL");
		}
		autonomousCommand = new StateAndWorldsAuto(autoSelected, position);
		*/
		
		if(autoSelected < 10){
			autoChooser.display("" + autoDelay + position + "0" + autoSelected);
		}else if(autoSelected >= 10){
			autoChooser.display("" + autoDelay + position + autoSelected);
		}else{
			autoChooser.display("NULL");
		}
		autonomousCommand = new StateAndWorldsAuto(autoSelected, position);
	}
	public void autonomousInit() {
		autonomousCommand.start();
	}
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		drivetrain.gyro.reset();
		drivetrain.rightEncoder.reset();
		drivetrain.leftEncoder.reset();
	}
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboardPrints();
		turnCannonOn();
		RobotMap.photonCannonPower.set(Relay.Value.kForward);

		SmartDashboard.putNumber("thing", autoChooser.pot.getAverageVoltage());

		
		printPixyStuff();
		if(oi.logi.getButtonStart()){
			turnCannonOn();
		}
		if (oi.logi.getButtonLT() && !oi.logi.getButtonBack()) {
			intake.down();
		}
		if (oi.logi.getButtonLB() && !oi.logi.getButtonBack()) {
			intake.up();
		}
		if (oi.logi.getAxis("lefty") > 0.10 && !oi.logi.getButtonBack()) {
			intake.roll(.8);
		} else if (oi.logi.getAxis("lefty") < -0.10 && oi.logi.getAxis("lefty") > -0.85 && !oi.logi.getButtonBack()) {
			intake.roll(-.2);
		}else if(oi.logi.getAxis("lefty") < -0.85 && !oi.logi.getButtonBack()){
			intake.roll(-.8);
		} else {
			intake.roll(0);
		}
		if (oi.logi.getButtonA() == true && !oi.logi.getButtonBack()) {
			defenseBuster.down();
		}
		if (oi.logi.getButtonX() == true && !oi.logi.getButtonBack()) {
			defenseBuster.up();
		}
		if (oi.logi.getButtonRT() && !oi.logi.getButtonBack()) {
			shooter.up();
		} else {
			shooter.down();
		}
		if (oi.lJoy.getRawButton(1) || oi.rJoy.getRawButton(1) || oi.lJoy.getRawButton(7) || oi.rJoy.getRawButton(10)) {
			Robot.drivetrain.shiftDown();
		}
		if (oi.lJoy.getRawButton(2) || oi.rJoy.getRawButton(2) || oi.lJoy.getRawButton(8) || oi.rJoy.getRawButton(11)) {
			Robot.drivetrain.shiftUp();
		}

		if (((oi.rJoy.getRawButton(3) || oi.lJoy.getRawButton(3)) && !goingStraight) && !oi.logi.getButtonBack()) {
			drivetrain.gyro.reset();
			goingStraight = true;
		}
		if (((oi.rJoy.getRawButton(3) || oi.lJoy.getRawButton(3)) && goingStraight) && !oi.logi.getButtonBack()) {
			if (drivetrain.gyro.getAngle() < 0) {
				drivetrain.driveMotors(0.5 - ((drivetrain.gyro.getAngle() % 360) / straightConstant),
						0.5 + ((drivetrain.gyro.getAngle() % 360) / straightConstant));
			} else if (drivetrain.gyro.getAngle() > 0) {
				drivetrain.driveMotors(0.5 + ((drivetrain.gyro.getAngle() % 360) / straightConstant),
						0.5 - ((drivetrain.gyro.getAngle() % 360) / straightConstant));
			}
		}
		drivetrain.driveMotors(oi.lJoy.getY(), oi.rJoy.getY());
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
	
	//This method puts updated values on SmartDashboard
	public static void SmartDashboardPrints() {
		SmartDashboard.putNumber("rEnconder", drivetrain.rightEncoder.getDistance());
		SmartDashboard.putNumber("lEncoder", drivetrain.leftEncoder.getDistance());
		SmartDashboard.putNumber("rEncoder Inches", (drivetrain.rightEncoder.getDistance() / 250) * (6 * Math.PI));
		SmartDashboard.putNumber("lEncoder Inches", (drivetrain.leftEncoder.getDistance() / 250) * (6 * Math.PI));
		SmartDashboard.putNumber("rEncoder Feet", (drivetrain.rightEncoder.getDistance() / 250) * (6 * Math.PI) / 12);
		SmartDashboard.putNumber("lEncoder Feet", (drivetrain.leftEncoder.getDistance() / 250) * (6 * Math.PI) / 12);
	}

	public static void printPixyStuff(){
		byte[] pixyValues = new byte[64];
		pixyValues[0] = (byte) 0b01010101;
		pixyValues[1] = (byte) 0b10101010;

		RobotMap.pixyi2c.readOnly(pixyValues, 64);
		if (pixyValues != null) {
			int i = 0;
			while (!(pixyValues[i] == 85 && pixyValues[i + 1] == -86) && i < 50) {
				i++;
			}
			i++;
			if (i > 50)
				i = 49;
			while (!(pixyValues[i] == 85 && pixyValues[i + 1] == -86) && i < 50) {
				i++;
			}
			char xPosition = (char) (((pixyValues[i + 7] & 0xff) << 8) | (pixyValues[i + 6] & 0xff));
			char yPosition = (char) ((pixyValues[i + 9] & 0xff << 8) | pixyValues[i + 8] & 0xff);
			char width = (char) ((pixyValues[i + 11] & 0xff << 8) | pixyValues[i + 10] & 0xff);
			char height = (char) ((pixyValues[i + 13] & 0xff << 8) | pixyValues[i + 12] & 0xff);
			SmartDashboard.putNumber("xPosition", xPosition);
			SmartDashboard.putNumber("yPosition", yPosition);
			SmartDashboard.putNumber("width", width);
			SmartDashboard.putNumber("height", height);
			SmartDashboard.putNumber("Raw 5", pixyValues[5]);
		}
	}
	
	public static void pixyLEDS(){
		byte[] switchLED = new byte[5];
		switchLED[0] = 0x00;
		switchLED[1] = (byte) 0xfd;
		switchLED[2] = (byte) 255; //255
		switchLED[3] = (byte) 0; //0
		switchLED[4] = (byte) 0; //0
		RobotMap.pixyi2c.writeBulk(switchLED);
	}
	public static void turnCannonOn(){
		RobotMap.photonCannonPower.set(Relay.Value.kForward);
	}
	public static void turnCannonOff(){
		RobotMap.photonCannonPower.set(Relay.Value.kOff);
		
	}
	
}