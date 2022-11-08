// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveJoystick extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drive m_Drive;
  private DoubleSupplier left, right;
  private SlewRateLimiter leftLimiter, rightLimiter;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveJoystick(Drive subsystem, DoubleSupplier left, DoubleSupplier right) {
    m_Drive = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    this.left = left;
    this.right = right;

    //Limits rate of change to 5 units per second
    leftLimiter = new SlewRateLimiter(3.5);
    rightLimiter = new SlewRateLimiter(3.5);
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Slew rate limiter on drive controls
    m_Drive.tankDrive(leftLimiter.calculate(left.getAsDouble()), rightLimiter.calculate(right.getAsDouble()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
