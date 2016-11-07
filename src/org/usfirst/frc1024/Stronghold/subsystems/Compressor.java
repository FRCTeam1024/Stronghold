package org.usfirst.frc1024.Stronghold.subsystems;
import org.usfirst.frc1024.Stronghold.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Compressor extends Subsystem {
    private final edu.wpi.first.wpilibj.Compressor compressor = RobotMap.compressorCompressor;
    public void initDefaultCommand() {
    }
    
    public void setPower(boolean on){
    	if(on)
    		compressor.start();
    	else
    		compressor.stop();
    }
}

