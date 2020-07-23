/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
 
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.EyesValues;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Intake extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Belt belt;
  private final EyesValues eyesValues;
  private double eyesNumbers;
  
  public Intake(Belt b, EyesValues eV) {
    belt = b;
    eyesValues = eV;
  }

  @Override
  public void initialize() {
    belt.GeorgeDrop();
  }

  @Override
  public void execute() {
    
    eyesNumbers = eyesValues.GetEyesNumbers();
    System.out.println("Eyes Numbers: " + eyesNumbers);
    if (eyesNumbers == 5){
      belt.IntakeStop();
    }
    else{
      belt.Intake();
    }
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
