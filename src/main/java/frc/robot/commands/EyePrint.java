/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.EyesValues;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class EyePrint extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final EyesValues eyesValues ;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public EyePrint(EyesValues eV) {
    eyesValues = eV;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(eyesValues);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Eye 1: " + eyesValues.GetFirstEye());
    System.out.println("Eye 2: " + eyesValues.GetSecondEye());
    System.out.println("Eye 3: " + eyesValues.GetThirdEye());
    System.out.println("Eye 4: " + eyesValues.GetFourthEye());
    System.out.println("Eye 5: " + eyesValues.GetFifthEye());
    System.out.println("Eyes Number: " + eyesValues.GetEyesNumbers());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
