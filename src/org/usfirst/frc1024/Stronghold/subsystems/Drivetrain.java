package org.usfirst.frc1024.Stronghold.subsystems;
import org.usfirst.frc1024.Stronghold.Robot;
import com.ctre.*;
import org.usfirst.frc1024.Stronghold.RobotMap;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	private final Solenoid shifter = RobotMap.drivetrainShifter;
	private final static CANTalon frontLeft = RobotMap.drivetrainFrontLeft;
	private final static CANTalon frontRight = RobotMap.drivetrainFrontRight;
	private final static CANTalon backLeft = RobotMap.drivetrainBackLeft;
	private final static CANTalon backRight = RobotMap.drivetrainBackRight;
	public final Encoder leftEncoder = RobotMap.drivetrainLeftEncoder;
	public final Encoder rightEncoder = RobotMap.drivetrainRightEncoder;
	public final AnalogGyro gyro = RobotMap.drivetrainGyro;
	
	public void initDefaultCommand() {
	}
	public void shiftDown() {
		shifter.set(true);
	}
	public void shiftUp() {
		shifter.set(false);
	}
	public void driveMotors(double lSpeed, double rSpeed) {
		frontLeft.set(-lSpeed);
		backLeft.set(-lSpeed);
		frontRight.set(rSpeed);
		backRight.set(rSpeed);
	}
	
	public void driveInches(double distance, double speed){
		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.rightEncoder.reset();
		driveMotors(speed, speed);
		if(distance > 0){
			while (((((Robot.drivetrain.leftEncoder.getDistance() + Robot.drivetrain.rightEncoder.getDistance()) / 2)
					/ 250) * (6 * Math.PI)) <= distance){
			}
		}
		if(distance < 0){
			while (((((Robot.drivetrain.leftEncoder.getDistance() + Robot.drivetrain.rightEncoder.getDistance()) / 2)
					/ 250) * (6 * Math.PI)) >= distance){
			}
		}
		driveMotors(0, 0);
	}
	
	public void driveStraightInches(double distance, double speed){
		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.rightEncoder.reset();
		if(distance > 0){
			while (((((Robot.drivetrain.leftEncoder.getDistance() + Robot.drivetrain.rightEncoder.getDistance()) / 2)
					/ 250) * (6 * Math.PI)) <= distance){
				driveMotors(speed - gyro.getAngle() / 10, speed + gyro.getAngle() / 10);
			}
		}
		if(distance < 0){
			while (((((Robot.drivetrain.leftEncoder.getDistance() + Robot.drivetrain.rightEncoder.getDistance()) / 2)
					/ 250) * (6 * Math.PI)) >= distance){
				driveMotors(speed - gyro.getAngle() / 10, speed + gyro.getAngle() / 10);
			}
		}
		driveMotors(0, 0);
	}
}