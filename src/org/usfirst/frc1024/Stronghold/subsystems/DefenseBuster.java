package org.usfirst.frc1024.Stronghold.subsystems;
import org.usfirst.frc1024.Stronghold.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DefenseBuster extends Subsystem {
    private final DoubleSolenoid arm = RobotMap.defenseBusterArm;
    public void initDefaultCommand() {
    }
    public void up(){
    	arm.set(DoubleSolenoid.Value.kForward);
    }
    public void down(){
    	arm.set(DoubleSolenoid.Value.kReverse);
    }
}

