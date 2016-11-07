package org.usfirst.frc1024.Stronghold.subsystems;

import org.usfirst.frc1024.Stronghold.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Scaler extends Subsystem {
	
	/*private final CANTalon rightWinch = RobotMap.scalerRightWinch;
	private final CANTalon leftWinch = RobotMap.scalerLeftWinch;
	private final Servo releaser = RobotMap.scalerReleaser;
	private final Solenoid hookLauncher = RobotMap.scalerHookLauncher;
    */
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /*public void release(){
    	releaser.set(1); //check this I'm not certain it will go the correct way
    }
    
    public void launchHook(){
    	hookLauncher.set(true);    	
    }
    
    public void rScale(double speed){
    	rightWinch.set(speed);
    }
    
    public void lScale(double speed){
    	leftWinch.set(-speed);
    }
    */
}