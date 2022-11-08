// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Demo extends SubsystemBase {
  private CANSparkMax neo = new CANSparkMax(3, MotorType.kBrushless);
  private CANSparkMax cimSpark = new CANSparkMax(4, MotorType.kBrushed);

  private TalonFX falcon = new TalonFX(5);

  private TalonSRX cimTalonSRX = new TalonSRX(6);


  /** Creates a new Demo. */
  public Demo() {
    //Cims don't have built in encoders
    cimSpark.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 1400);


    //Encoder Plugs into motor controller
    cimTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
