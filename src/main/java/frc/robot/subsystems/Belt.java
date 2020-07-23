/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Belt extends SubsystemBase {
  VictorSPX asteroid;
  int asteroidIdx = 6;
  Servo george;
  int georgeIdx = 0;
  double georgeAngle = 0.75;

  public Belt() {
   asteroid = new VictorSPX(asteroidIdx);
   george = new Servo(georgeIdx);
  }

  public void Intake(){
    asteroid.set(ControlMode.PercentOutput, 1);
  }
  public void IntakeReverse(){
    asteroid.set(ControlMode.PercentOutput, -1);
  }
  public void IntakeStop(){
    asteroid.set(ControlMode.PercentOutput, 0);
  }
  public void GeorgeDrop(){
    george.set(georgeAngle);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
