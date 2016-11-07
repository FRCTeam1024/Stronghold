package org.usfirst.frc1024.Stronghold;

public class xBox extends edu.wpi.first.wpilibj.Joystick{
	public xBox(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean getButtonA(){
		return getRawButton(1);
	}
	public boolean getButtonB(){
		return getRawButton(2);
	}
	public boolean getButtonX(){
		return getRawButton(3);
	}
	public boolean getButtonY(){
		return getRawButton(4);
	}
	public boolean getButtonLB(){
		return getRawButton(5);
	}
	public boolean getButtonRB(){
		return getRawButton(6);
	}
	public boolean getButtonBack(){
		return getRawButton(7);
	}
	public boolean getButtonStart(){
		return getRawButton(8);
	}
	public boolean getButtonLS(){
		return getRawButton(9);
	}
	public boolean getButtonRS(){
		return getRawButton(10);
	}

	public double getAxis(String axis) {
		axis = axis.toLowerCase();
		switch (axis) {
		case "leftx":
			return getRawAxis(0);
		case "lefty":
			return getRawAxis(1);
		case "rightx":
			return getRawAxis(4);
		case "righty":
			return getRawAxis(5);
		case "lefttrigger":
			return getRawAxis(2);
		case "righttrigger":
			return getRawAxis(3);
		}
		return 0;
	}
}