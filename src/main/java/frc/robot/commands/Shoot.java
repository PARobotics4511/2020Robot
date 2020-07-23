/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.EyesValues;
import frc.robot.subsystems.Shooter;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class Shoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter shooter;
  private final EyesValues eyesValues;
  private DoubleSupplier wWheelPwr;
  private double waterForce;
  private Timer shootTime;
  private double startTime;
  private double deltaT = 0.25;
  private int shootState; //0 is Pause, 1 is Ready to Shoot

  public Shoot(Shooter s, EyesValues eV) {
    shooter = s;
    addRequirements(shooter);
    eyesValues = eV;
    //wWheelPwr = z;
  }
  @Override
  public void initialize() {
    shooter.shooterPrep();
    shootTime = new Timer();
    shootTime.start();
    startTime = shootTime.get(); 
    shootState = 0;
  }

  @Override
  public void execute() {

    shooter.shooterOn();
    boolean speedAchieved = shooter.speedAchieved();
    System.out.println("Shooter Speed Reached: " + speedAchieved);
    int eyeNumbers = eyesValues.GetEyesNumbers();

    if(speedAchieved && (shootTime.get() > (startTime + deltaT)) && eyeNumbers > -1 && eyeNumbers != 6 && shootState == 0)
    {
      shootState = 1; 
      shootTime.stop();
      shootTime.reset();
    }
    else if(shootState == 1 && !eyesValues.GetFirstEye()){
      shootState = 0;
      shootTime.start();
      startTime = shootTime.get();
    }


    if(shootState == 1){
      if(eyeNumbers >= 4 && eyeNumbers != 6){
        waterForce = 0.19;
      }
      else if (eyeNumbers < 4 && eyeNumbers != -2){
        waterForce = 0.15;
      }
      else if (shootState == 0){
        shooter.waterWheel(0);
      }
        shooter.waterWheel(waterForce);
      }
    }
   
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted){
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished(){
    return eyesValues.GetEyesNumbers() == 0;
  }
}
