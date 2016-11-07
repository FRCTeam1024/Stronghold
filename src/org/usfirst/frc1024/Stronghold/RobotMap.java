package org.usfirst.frc1024.Stronghold;


import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class RobotMap {
    public static Solenoid drivetrainShifter;
    public static CANTalon drivetrainFrontLeft;
    public static CANTalon drivetrainFrontRight;
    public static CANTalon drivetrainBackLeft;
    public static CANTalon drivetrainBackRight;
    public static CANTalon drivetrainMidLeft;
    public static CANTalon drivetrainMidRight;
    public static Encoder drivetrainLeftEncoder;
    public static Encoder drivetrainRightEncoder;
    public static AnalogGyro drivetrainGyro;
    public static DoubleSolenoid defenseBusterArm;
    public static DigitalInput defenseBusterArmUp;
    public static DigitalInput defenseBusterArmDown;
    public static AnalogPotentiometer defenseBusterArmPosition;
    public static CANTalon intakeLeftMotor;
    public static CANTalon intakeRightMotor;
    public static DoubleSolenoid intakeArm;
    public static Solenoid launcherOne;
    public static Solenoid launcherTwo;
    /*public static Solenoid launcherThree;
    public static Solenoid launcherFour;
    */
    public static Solenoid launcherPressureAdjust;
    public static Compressor compressorCompressor;
    public static I2C pixyi2c;
    public static DigitalOutput pixyPower;
    public static Relay photonCannonPower;
    //public static AHRS navx;
    //public static AnalogInput servoIn;
    //public static REVDigitBoard autoChooser;



    public static void init() { //reorganized and moved all of the LiveWindow stuff to the bottom
        //Motors
        drivetrainFrontLeft = new CANTalon(0);
        drivetrainFrontRight = new CANTalon(1);
        drivetrainBackLeft = new CANTalon(2);
        drivetrainBackRight = new CANTalon(3); //for some reason we skipped 4 and 5? Maybe cursed???
        intakeLeftMotor = new CANTalon(6);
        intakeRightMotor = new CANTalon(7);
        //Sensors
        drivetrainLeftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        drivetrainRightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        drivetrainGyro = new AnalogGyro(1);
        defenseBusterArmUp = new DigitalInput(4); //don't think these were implemented
        defenseBusterArmDown = new DigitalInput(5); //see above
        defenseBusterArmPosition = new AnalogPotentiometer(0, 1.0, 0.0); //see above
        //Pneumatics
        compressorCompressor = new Compressor(0);
        defenseBusterArm = new DoubleSolenoid(0, 0, 1);
        intakeArm = new DoubleSolenoid(0, 2, 3);
        drivetrainShifter = new Solenoid(0, 4);
        launcherOne = new Solenoid(0, 5);
        launcherTwo = new Solenoid(0, 6);
        //Pixy and Aiming
        pixyi2c = new I2C(Port.kOnboard, 0x54);
        pixyPower = new DigitalOutput(6);
        photonCannonPower = new Relay(1);
        //Livewindow stuff
        LiveWindow.addActuator("Drivetrain", "FrontLeft", drivetrainFrontLeft);
        LiveWindow.addActuator("Drivetrain", "FrontRight", drivetrainFrontRight);
        LiveWindow.addActuator("Drivetrain", "BackLeft", drivetrainBackLeft);
        LiveWindow.addActuator("Drivetrain", "BackRight", drivetrainBackRight);
        LiveWindow.addActuator("Drivetrain", "Shifter", drivetrainShifter);
        
        LiveWindow.addSensor("Drivetrain", "LeftEncoder", drivetrainLeftEncoder);
        LiveWindow.addSensor("Drivetrain", "RightEncoder", drivetrainRightEncoder);
        
        LiveWindow.addActuator("DefenseBuster", "Arm", defenseBusterArm);
        
        LiveWindow.addSensor("DefenseBuster", "ArmUp", defenseBusterArmUp);
        LiveWindow.addSensor("DefenseBuster", "ArmDown", defenseBusterArmDown);
        LiveWindow.addSensor("DefenseBuster", "ArmPosition", defenseBusterArmPosition);
        
        LiveWindow.addActuator("Intake", "LeftMotor", intakeLeftMotor);
        LiveWindow.addActuator("Intake", "RightMotor", intakeRightMotor);
        LiveWindow.addActuator("Intake", "Arm", intakeArm);
        
        LiveWindow.addActuator("Launcher", "One", launcherOne);
        LiveWindow.addActuator("Launcher", "Two", launcherTwo);
        
        LiveWindow.addActuator("SPIKE",  "photonCannon", photonCannonPower);
    }
}
