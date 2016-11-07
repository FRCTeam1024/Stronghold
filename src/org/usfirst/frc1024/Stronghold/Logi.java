package org.usfirst.frc1024.Stronghold;

public class Logi extends edu.wpi.first.wpilibj.Joystick{
	public Logi(int port) {
		super(port);
	}
	
	public boolean getButtonA(){
		return getRawButton(2);
	}
	public boolean getButtonB(){
		return getRawButton(3);
	}
	public boolean getButtonX(){
		return getRawButton(1);
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
	public boolean getButtonLT(){
		return getRawButton(7);
	}
	public boolean getButtonRT(){
		return getRawButton(8);
	}
	public boolean getButtonBack(){
		return getRawButton(9);
	}
	public boolean getButtonStart(){
		return getRawButton(10);
	}
	public boolean getButtonLS(){
		return getRawButton(11);
	}
	public boolean getButtonRS(){
		return getRawButton(12);
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
		}
		return 0;
	}
}