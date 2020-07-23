/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.EyesValues;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class HoldFirstBall extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter shooter;
  private final EyesValues eyesValues;
  private int wheelState;
  private double waterBack;
  private double pauseTime;
  private Timer timer;

  public HoldFirstBall(Shooter s, EyesValues eV) {
    shooter = s;
    eyesValues = eV;
    pauseTime = 0.75;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    addRequirements(eyesValues);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    wheelState = 0;  //0 is off, 1 is running
    waterBack = -0.175;
    timer = new Timer();


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.shooterStop();
    if(eyesValues.GetEyesNumbers() == 0 && wheelState == 0)
    {
      wheelState = 1;
    }
    else if(eyesValues.GetFirstEye() && wheelState == 1)
    {
      wheelState = 0;
      timer.start();
    }

    if(wheelState == 0 && timer.get() > pauseTime)
    {
      shooter.noWater();
      timer.stop();
      timer.reset();
    }

    else if(wheelState == 1)
    {
      shooter.waterWheel(waterBack);
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
