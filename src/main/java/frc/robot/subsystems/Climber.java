/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

public class Climber extends SubsystemBase {
  WPI_TalonSRX ascender;
  WPI_TalonSRX mLiftoff;
  WPI_TalonSRX sLiftoff;
  int ascenderIdx = 11;
  int mLiftoffIdx = 10; //Left Side
  int sLiftoffIdx = 9; //Right Side
  int ascenderPos = 0;
  double aSpeed = 0.5;
  double speed = 0.75;

  public Climber() {
    ascender = new WPI_TalonSRX(ascenderIdx);
    mLiftoff = new WPI_TalonSRX(mLiftoffIdx);
    sLiftoff = new WPI_TalonSRX(sLiftoffIdx);
    mLiftoff.setInverted(true); //set true to climb (correct ratchet direction)
    sLiftoff.setInverted(false); //set false to climb (correct ratchet direction)
    ascender.setSelectedSensorPosition(0,0,30);
  }

  @Override
  public void periodic() {
  }

  public void Ascend(){
    //if (ascenderPos <= 587500){
      ascender.set(ControlMode.PercentOutput, aSpeed);
    //}
    //else {
      //ascender.set(ControlMode.PercentOutput, 0);
    //}
    ascenderPos = ascender.getSelectedSensorPosition();
    System.out.println("Ascender: " + ascenderPos);
  }
  public void Descend(){
    //if (ascenderPos >= 0){
      ascender.set(ControlMode.PercentOutput, (-1 * aSpeed));
    //}
    //else{
      //ascender.set(ControlMode.PercentOutput, 0);
    //}
    ascenderPos = ascender.getSelectedSensorPosition();
    System.out.println("Ascender: " + ascenderPos);
  }
  public void ClimbStop(){
    ascender.set(ControlMode.PercentOutput, 0);
  }
  public void LiftoffStopLeft(){
    mLiftoff.set(ControlMode.PercentOutput, 0);
  }
  public void LiftoffStopRight(){
    sLiftoff.set(ControlMode.PercentOutput, 0);
  }
  public void LiftoffClimbLeft(){
    mLiftoff.set(ControlMode.PercentOutput, speed);
  }
  public void LiftoffClimbRight(){
    sLiftoff.set(ControlMode.PercentOutput, speed);
  }
  public void LiftoffLeftReverse(){
    mLiftoff.set(ControlMode.PercentOutput,(-1 * 0.5 * speed));
  }
  public void LiftoffRightReverse(){
    sLiftoff.set(ControlMode.PercentOutput,(-1 * 0.5 * speed));
  }
}
