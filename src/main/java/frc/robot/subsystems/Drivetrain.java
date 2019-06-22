/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.DefaultDrivetrain;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  AnalogPotentiometer pot = new AnalogPotentiometer(0);
  CANSparkMax myFirst = new CANSparkMax(1, MotorType.kBrushless);
  CANEncoder myFirstEncoder = myFirst.getEncoder();

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DefaultDrivetrain());
  }

  public void init(){
    myFirst.setEncPosition(0);
    myFirst.setIdleMode(IdleMode.kBrake);
    
    SmartDashboard.putBoolean("Driving?", false);
    //TODO: add current limits -- documentation?!
  }

  public void drive(){
    myFirst.set(Robot.mOI.getJoyY());
    SmartDashboard.putBoolean("Driving?", true);
  }

  public double getMotorOutput() {
    return myFirst.get();
  }

  public double getMotorEncoderPos(){
    return myFirstEncoder.getPosition();
  }

  public double getMotorEncoderVel(){
    return myFirstEncoder.getVelocity();
  }

  public double getExternalPotVal(){
    return pot.get();
  }
}
