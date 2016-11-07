package org.usfirst.frc1024.Stronghold.commands;
import org.usfirst.frc1024.Stronghold.Robot;
import org.usfirst.frc1024.Stronghold.RobotMap;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.RobotState;

public class StateAndWorldsAuto extends Command {
	int autoSelected;
	int position;
	boolean isDone = false;
	int autoDelay = 0;
	char xPosition = 0;

	public StateAndWorldsAuto(int autoSelected, int position) {
		this.autoSelected = autoSelected;
		this.position = position;
		//this.autoDelay = autoDelay;
	}
	protected void initialize() {
	}
	//I am so sorry for this long comment :(
	/*
	 * protected void execute() { while (!isDone) { if (position != 0) { switch
	 * (autoSelected) { case 0: // default (2-pointer) Timer.delay(delayTime);
	 * Robot.drivetrain.driveInches(36, -0.5); break; case 1: case 5: // Low bar
	 * & Port Timer.delay(delayTime); Robot.drivetrain.gyro.reset();
	 * Robot.defenseBuster.down(); Timer.delay(8);
	 * Robot.drivetrain.driveMotors(-0.5, -0.5); Timer.delay(3.5);
	 * Robot.drivetrain.driveMotors(0, 0); break; case 2: case 6:// Shovel de
	 * Fries Timer.delay(delayTime); Robot.drivetrain.gyro.reset();
	 * Robot.defenseBuster.down(); Timer.delay(1);
	 * Robot.drivetrain.driveMotors(-0.25, -0.25); Timer.delay(3.5);
	 * Robot.drivetrain.driveMotors(0, 0); break; case 3: case 7: // Rock Wall &
	 * Moat Timer.delay(delayTime); Robot.drivetrain.driveMotors(-0.75, -0.75);
	 * Timer.delay(3); Robot.drivetrain.driveMotors(0, 0); break; case 4: case
	 * 8:// RT and Ramp Timer.delay(delayTime); Robot.drivetrain.gyro.reset();
	 * Timer.delay(.5); Robot.drivetrain.driveMotors(-0.75, -0.75);
	 * Timer.delay(2.5); Robot.drivetrain.driveMotors(0, 0); break; } if
	 * (autoSelected > 4) { char xPosition = 0; switch (position) { case 1: //
	 * drive forward some and turn right xPosition = 0;
	 * Robot.drivetrain.driveMotors(-0.5, -0.5); Timer.delay(2);
	 * Robot.drivetrain.driveMotors(.5, -0.5); break; case 2: // drive forward
	 * some and turn right xPosition = 0; Robot.drivetrain.driveMotors(-0.5, 0);
	 * Timer.delay(1); Robot.drivetrain.driveMotors(-0.5, -0.5);
	 * Timer.delay(1.5); Robot.drivetrain.driveMotors(0.5, -0.5); break; case 3:
	 * // turn right xPosition = 0; Robot.drivetrain.driveMotors(0, 0.3); break;
	 * case 4: // turn left xPosition = 500; Robot.drivetrain.driveMotors(0.5,
	 * -0.5); break; case 5: // turn left xPosition = 500;
	 * Robot.drivetrain.driveMotors(0.3, 0); break; } int loopCounter = 0; while
	 * ((xPosition < 140 || xPosition > 160) && loopCounter != 1) { if(xPosition
	 * > 140 && xPosition < 160){ loopCounter++; }else{ loopCounter = 0; }
	 * if(xPosition > 160){ Robot.drivetrain.driveMotors(0, 0.3); }else
	 * if(xPosition < 140){ Robot.drivetrain.driveMotors(0.3, 0); }else
	 * if(loopCounter == 1){ Robot.drivetrain.driveMotors(0, 0); } byte[]
	 * pixyValues = new byte[64]; pixyValues[0] = (byte) 0b01010101;
	 * pixyValues[1] = (byte) 0b10101010;
	 * 
	 * RobotMap.pixyi2c.readOnly(pixyValues, 64); if (pixyValues != null) { int
	 * i = 0; while (!(pixyValues[i] == 85 && pixyValues[i + 1] == -86) && i <
	 * 50) { i++; } i++; if (i > 50) i = 49; while (!(pixyValues[i] == 85 &&
	 * pixyValues[i + 1] == -86) && i < 50) { i++; } xPosition = (char)
	 * (((pixyValues[i + 7] & 0xff) << 8) | (pixyValues[i + 6] & 0xff));
	 * if(xPosition >300){ xPosition = 300; }else if(xPosition < 50){ xPosition
	 * = 50; } char yPosition = (char) ((pixyValues[i + 9] & 0xff << 8) |
	 * pixyValues[i + 8] & 0xff); char width = (char) ((pixyValues[i + 11] &
	 * 0xff << 8) | pixyValues[i + 10] & 0xff); char height = (char)
	 * ((pixyValues[i + 13] & 0xff << 8) | pixyValues[i + 12] & 0xff);
	 * SmartDashboard.putNumber("xPosition", xPosition);
	 * SmartDashboard.putNumber("yPosition", yPosition);
	 * SmartDashboard.putNumber("width", width);
	 * SmartDashboard.putNumber("height", height);
	 * //Robot.autoChooser.display("" + xPosition); } }
	 * Robot.drivetrain.driveMotors(0, 0); Robot.intake.down();
	 * Robot.defenseBuster.down(); Timer.delay(2); Robot.shooter.shoot();
	 * Robot.intake.up(); isDone = true; } else { Timer.delay(delayTime);
	 * Robot.drivetrain.driveInches(36, -0.5); isDone = true; } } } }
	 */

	protected void execute() {
		RobotMap.photonCannonPower.set(Relay.Value.kOff);
		int pos = 0;
		int[] xPositionArray = new int[10];
		int maxPos = 1;
		int aveCount = 0;
		int samples = 0;
		int xPositionAve = 0;
		int turnDir = 0; // negative is left turn. positive is right turn. zero
							// is stop.
		int lowerBound = 120;
		int upperBound = 140;
		if ((position == 0 || autoSelected == 0) && !isDone) {
			isDone = true;
			Timer.delay(autoDelay);
			Robot.drivetrain.driveInches(36, -0.5);
		} else if (!isDone) {
			Timer.delay(autoDelay);
			switch (autoSelected) {
			case 1:
			case 5: // Low bar & Port
				Robot.drivetrain.gyro.reset();
				Robot.defenseBuster.down();
				Timer.delay(1);
				Robot.drivetrain.driveMotors(-0.5, -0.5);
				Timer.delay(3.5);
				Robot.drivetrain.driveMotors(0, 0);
				break;
			case 2:
			case 6:// Shovel de Fries
				/*Timer.delay(delayTime);
				Robot.drivetrain.gyro.reset();
				Robot.defenseBuster.down();
				Timer.delay(1);
				Robot.drivetrain.driveMotors(-0.25, -0.25);
				Timer.delay(3.5);
				Robot.drivetrain.driveMotors(0, 0);
				break;*/
				
				// New code written by Mark on 4/18
				Robot.drivetrain.driveMotors(-0.35, -0.35);
				Timer.delay(1.5);
				Robot.drivetrain.driveMotors(0, 0);
				Robot.defenseBuster.down();
				Timer.delay(1.5);
				Robot.drivetrain.driveMotors(0.2, 0.2);
				Timer.delay(.4);
				Robot.drivetrain.driveMotors(-0.5, -0.5);
				Timer.delay(2.5);
				Robot.drivetrain.driveMotors(0, 0);
				break;
			case 3:
			case 7: // Rock Wall & Moat
				Robot.drivetrain.driveMotors(-0.75, -0.75);
				Timer.delay(3.5);
				Robot.drivetrain.driveMotors(0, 0);
				break;
			case 4:
			case 8:// RT and Ramp
				Robot.drivetrain.gyro.reset();
				Timer.delay(.5);
				Robot.drivetrain.driveMotors(-0.75, -0.75);
				Timer.delay(2.5);
				Robot.drivetrain.driveMotors(0, 0);
				break;
			}
			if (autoSelected > 4) {
				switch (position) {
				case 1: // drive forward a lot then turn right
					
				case 2: // drive forward some and turn right
					xPosition = 0;
					Robot.drivetrain.driveMotors(-0.3, -0.3);
					Timer.delay(2);
					Robot.drivetrain.driveMotors(0, 0);
					turnDir = 1;
					lowerBound = 165;
					upperBound = 175;
					break;
				case 3: // turn right
					//150
					xPosition = 0;
					turnDir = 1;
					lowerBound = 150;
					upperBound = 160;
					break;
				case 4: // turn left
					//160
					xPosition = 300;
					turnDir = -1;
					lowerBound = 155;
					upperBound = 165;
					break;
				case 5: // turn left
					xPosition = 300;
					Robot.drivetrain.driveMotors(0.4, 0);
					Timer.delay(0);
					Robot.drivetrain.driveMotors(0, 0);
					turnDir = -1;
					lowerBound = 190;
					upperBound = 210;
					break;
				}
				boolean target = false;
				while (!target && !Robot.oi.logi.getButtonBack()) {
					if (turnDir > 0) { // Set pivot direction (positive = right,
										// negative =
										// left, zero = stop)
						Robot.drivetrain.driveMotors(0, 0.3);
					} else if (turnDir < 0) {
						Robot.drivetrain.driveMotors(0.3, 0);
					} else {
						Robot.drivetrain.driveMotors(0, 0);
					}
					byte[] pixyValues = new byte[64];
					pixyValues[0] = (byte) 0b01010101;
					pixyValues[1] = (byte) 0b10101010;

					RobotMap.pixyi2c.readOnly(pixyValues, 64); // get camera
																// frame
																// data
					if (pixyValues != null) {// parse camera frame data
						int i = 0;
						while ((!(pixyValues[i] == 85 && pixyValues[i + 1] == -86) && i < 50)
								&& !Robot.oi.logi.getButtonBack() && RobotState.isAutonomous()) {
							i++;
						}
						i++;
						if (i > 50 && !Robot.oi.logi.getButtonBack())
							i = 49;
						while ((!(pixyValues[i] == 85 && pixyValues[i + 1] == -86) && i < 50)
								&& !Robot.oi.logi.getButtonBack() && RobotState.isAutonomous()) {
							i++;
						}

						// Calculate xPosition using running average
						xPosition = (char) (((pixyValues[i + 7] & 0xff) << 8) | (pixyValues[i + 6] & 0xff));
						char yPosition = (char) ((pixyValues[i + 9] & 0xff << 8) | pixyValues[i + 8] & 0xff);
						char width = (char) ((pixyValues[i + 11] & 0xff << 8) | pixyValues[i + 10] & 0xff);
						char height = (char) ((pixyValues[i + 13] & 0xff << 8) | pixyValues[i + 12] & 0xff);
						DriverStation.reportError("xposition: " + xPosition, true);
						if (xPosition < 300 && xPosition > 1 && (width > 20 && width < 45)) {
							xPositionArray[pos] = xPosition;
							pos++;
							samples++;
							if (pos > maxPos) {
								pos = 0;
							}
						}
						xPositionAve = 0;
						for (aveCount = 0; aveCount <= maxPos; aveCount++) {
							xPositionAve += xPositionArray[aveCount];
						}
						xPositionAve = xPositionAve / (maxPos + 1);

						if ((xPositionAve > lowerBound && xPositionAve < upperBound)) { // Determine
							// if we
							// have a
							// target
							target = true;
						}
						if (samples > maxPos) { // Don't
																					// adjust
																					// turn
																					// direction
																					// until
							// there is a full average
							if (xPositionAve < lowerBound) {
								turnDir = -1;
							} else if (xPositionAve > upperBound) {
								turnDir = 1;
							} else {
								turnDir = 0;
							}
							samples = maxPos + 1; // Don't let samples grow
													// uncontrollably
						}

						// char yPosition = (char) ((pixyValues[i + 9] & 0xff <<
						// 8)
						// | pixyValues[i + 8] & 0xff);
						// char width = (char) ((pixyValues[i + 11] & 0xff << 8)
						// |
						// pixyValues[i + 10] & 0xff);
						// char height = (char) ((pixyValues[i + 13] & 0xff <<
						// 8) |
						// pixyValues[i + 12] & 0xff);
						// SmartDashboard.putNumber("xPosition", xPosition);
						// SmartDashboard.putNumber("yPosition", yPosition);
						// SmartDashboard.putNumber("width", width);
						// SmartDashboard.putNumber("height", height);
					}
				}
				if (!Robot.oi.logi.getButtonBack() && RobotState.isAutonomous() && autoSelected > 4) {
					Robot.drivetrain.driveMotors(0, 0);
					Robot.intake.down();
					Robot.defenseBuster.down();
					Timer.delay(2);
					Robot.shooter.shoot();
				}
			}
		}
		isDone = true;
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
	}
	protected void interrupted() {
	}
}
