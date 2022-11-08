// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drive;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveSpeed extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drive m_Drive;
  private double left, right, seconds;
  private double startTime, currentTime;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveSpeed(Drive subsystem, double left, double right, double seconds) {
    m_Drive = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    this.left = left;
    this.right = right;
    this.seconds = seconds;

    //Limits rate of change to 5 units per second
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
    SmartDashboard.putBoolean("Auto", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Slew rate limiter on drive controls
    m_Drive.tankDrive(left, right);
    currentTime = Timer.getFPGATimestamp();
    SmartDashboard.putBoolean("Auto", true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drive.tankDrive(0, 0);
    SmartDashboard.putBoolean("Auto", false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return currentTime > startTime + seconds;
  }
}
