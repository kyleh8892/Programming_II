// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.concurrent.BlockingQueue;

import com.ctre.phoenix.sensors.Pigeon2;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {

  private static CANSparkMax leftFront, leftBack, rightFront, rightBack;
  private static DifferentialDrive drive;

  private static ADXRS450_Gyro gryo = new ADXRS450_Gyro();
  /** Creates a new DriveSubsystem. */
  public Drive() {
    leftFront = new CANSparkMax(1, MotorType.kBrushed);
    leftFront.setIdleMode(IdleMode.kBrake);
    leftBack = new CANSparkMax(2, MotorType.kBrushed);
    leftBack.setIdleMode(IdleMode.kBrake);

    rightFront = new CANSparkMax(3, MotorType.kBrushed);
    rightFront.setIdleMode(IdleMode.kBrake);
    rightBack = new CANSparkMax(4, MotorType.kBrushed);
    rightBack.setIdleMode(IdleMode.kBrake);

    Encoder encoder = new Encoder(0, 1);//Blue into 0 and yellow into 1
    
    MotorControllerGroup leftMotors = new MotorControllerGroup(leftFront, leftBack);
    MotorControllerGroup rightMotors = new MotorControllerGroup(rightFront, rightBack);

    //Automatically squares inputs for finer control
    drive = new DifferentialDrive(leftMotors, rightMotors);


    //Deadbands
    drive.setDeadband(.05);
  }

  public void tankDrive(double left, double right){
    drive.tankDrive(-left, -right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
