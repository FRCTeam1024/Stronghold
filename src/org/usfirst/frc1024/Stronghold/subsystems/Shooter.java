package org.usfirst.frc1024.Stronghold.subsystems;
import org.usfirst.frc1024.Stronghold.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
    private final Solenoid one = RobotMap.launcherOne;
    private final Solenoid two = RobotMap.launcherTwo;
    public void initDefaultCommand() {
    }
    public void up(){
    	one.set(true);
    	two.set(true);
    }
    
    public void down(){
    	one.set(false);
    	two.set(false);
    }
    public void shoot(){
    	up();
    	Timer.delay(0.5);
    	down();
    }
}
    			
    			
    			
    			
    			