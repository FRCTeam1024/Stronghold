package org.usfirst.frc1024.Stronghold.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1024.Stronghold.Robot;

public class Drive extends Command {
    public Drive() {
        requires(Robot.drivetrain);
    }
    protected void initialize() {
    }
    protected void execute() {
    	Robot.drivetrain.driveMotors(Robot.oi.lJoy.getRawAxis(1), Robot.oi.rJoy.getRawAxis(1));
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    }
    protected void interrupted() {
    }
}
