package org.usfirst.frc1024.Stronghold.subsystems;
import org.usfirst.frc1024.Stronghold.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
    private final CANTalon leftMotor = RobotMap.intakeLeftMotor;
    private final CANTalon rightMotor = RobotMap.intakeRightMotor;
    private final DoubleSolenoid arm = RobotMap.intakeArm;
    
    public void initDefaultCommand() {
    }
    public void up(){
    	arm.set(DoubleSolenoid.Value.kForward);
    }
    public void down(){
    	arm.set(DoubleSolenoid.Value.kReverse);
    }
    public void roll(double speed){
    	leftMotor.set(speed);
    	rightMotor.set(-speed);
    }
}

