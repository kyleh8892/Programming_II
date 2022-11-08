// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.DPad.Direction;
import frc.robot.commands.ArmPID;
import frc.robot.commands.DriveJoystick;
import frc.robot.commands.Auto.LowGoalBackup;
import frc.robot.commands.Auto.Taxi;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drive drive = new Drive();
  private final Arm arm = new Arm();
  private final Intake intake = new Intake();


  private final Joystick driverController = new Joystick(0);


  private final JoystickButton driverTriangle = new JoystickButton(driverController, 4);
  private final JoystickButton driverSquare = new JoystickButton(driverController, 1);

  private final JoystickButton driverLeftBumper = new JoystickButton(driverController, 5);
  private final JoystickButton driverRightBumper = new JoystickButton(driverController, 6);

  public DPad driverUp = new DPad(driverController, Direction.UP);
  //public DPad getRight(){return new DPad(driverController, Direction.RIGHT);}
  public DPad driverDown = new DPad(driverController, Direction.DOWN);
  //public DPad getLeft(){return new DPad(driverController, Direction.LEFT);}

  private SendableChooser<Command> m_chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Put the chooser on the dashboard
    SmartDashboard.putData(m_chooser);

    //Add auto modes to chooser
    m_chooser.addOption("Taxt", new Taxi(drive, arm));
    m_chooser.addOption("Low Goal and Backup  ", new LowGoalBackup(drive, intake, arm));

    //Configure default commands
    drive.setDefaultCommand(new DriveJoystick(drive, () -> driverController.getRawAxis(1), () -> driverController.getRawAxis(5)));
    
    SmartDashboard.putBoolean("Auto", true);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    driverTriangle.whenActive(new ArmPID(arm,13));
    driverSquare.whenActive(new ArmPID(arm,-3));

    driverRightBumper.whenActive(new InstantCommand(() -> intake.setIntakeSpeed(.7), intake));
    driverRightBumper.whenInactive(new InstantCommand(() -> intake.setIntakeSpeed(0), intake));


    driverLeftBumper.whenActive(new InstantCommand(() -> intake.setIntakeSpeed(-.7), intake));
    driverLeftBumper.whenInactive(new InstantCommand(() -> intake.setIntakeSpeed(0), intake));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}
