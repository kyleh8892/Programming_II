// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  private CANSparkMax arm;
  private boolean armUp = false;
  private double lastBurstTime = 0;
  public static double armTimeUp = 0.5;
  public static double armTimeDown = 0.35;
  public static  double armHoldUp = 0.08;
  public static  double armHoldDown = 0.13;
  public static  double armTravel = 0.5;


  /** Creates a new Arm. */
  public Arm() {
    arm = new CANSparkMax(5, MotorType.kBrushless);
    arm.setIdleMode(IdleMode.kBrake);
    arm.getEncoder().setPosition(0);
    arm.getPIDController().setP(.05);
    arm.getPIDController().setD(.001);
  }


  public double getArmPosition(){
    return arm.getEncoder().getPosition();
  }

  public void setArmPosition(double pos){
    arm.getPIDController().setReference(pos, ControlType.kPosition);
  }

  public double getLastBurstTime() {
    return lastBurstTime;
  }

  public void setLastBurstTime(double lastBurstTime) {
    this.lastBurstTime = lastBurstTime;
  }

  public void setArmSpeed(double speed){
    arm.set(speed);
  }

  public void setArmUp(boolean state){
    armUp = state;
  }

  public boolean getArmUp(){
    return armUp;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Arm Encoder", arm.getEncoder().getPosition());
    SmartDashboard.putNumber("Arm Power", arm.getOutputCurrent());
    
  }
}
