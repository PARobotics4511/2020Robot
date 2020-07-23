/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private TalonSRX mShoot; //Right Talon is the Master
  private TalonSRX sShoot;  //Left Talon is the Slave
  private VictorSPX waterWheel;
  private int mTalonIdx = 7;
  private int sTalonIdx = 8;
  private int waterWheelIdx = 5;
  private int timeOut = 30; //Timeout in ms
  private double FeedForewardInput = 0.43; //Value was 0.53 at Lake Superior Competition
  private double shotTargetVel = ((112818 * FeedForewardInput) - 2389); //Peak is approx 125000 units per 100ms
  private double shootKf = (FeedForewardInput * 1023) / shotTargetVel;
  private double shootKp = 0.03; //Proportional PID Value
  private double shootKd = 1.0; //Derivative PID Value
  private double shootKi = 0.0; //Integral PID Value //DO NOT ADJUST!
  
  public Shooter(){
    sShoot = new TalonSRX(sTalonIdx);
    mShoot = new TalonSRX(mTalonIdx);
    waterWheel = new VictorSPX(waterWheelIdx);
  }

  public void shooterPrep(){
    sShoot.configFactoryDefault();
    mShoot.configFactoryDefault();
    mShoot.setInverted(true);
    sShoot.follow(mShoot);
    sShoot.setInverted(InvertType.OpposeMaster);
    mShoot.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,timeOut);
    mShoot.setSensorPhase(true); //Check sensor direction vs motor direction
    mShoot.configNominalOutputForward(0, timeOut);
    mShoot.configNominalOutputReverse(0, timeOut);
    mShoot.configPeakOutputForward(1, timeOut);
    mShoot.configPeakOutputReverse(-1, timeOut);
    mShoot.config_kF(0, shootKf, timeOut);
    mShoot.config_kP(0, shootKp, timeOut);
    mShoot.config_kD(0, shootKd, timeOut);
    mShoot.config_kI(0, shootKi, timeOut);
  }

  public void shooterOn(){
    mShoot.set(ControlMode.Velocity, shotTargetVel);
    System.out.println("Master Velocity: " + mShoot.getSelectedSensorVelocity());
  }

  public boolean speedAchieved(){
    boolean achieved = false;
    double error = Math.abs(mShoot.getClosedLoopError(0));
    //System.out.println("Error is: " + error);
    if(error < Math.abs(0.025 * shotTargetVel)){
      achieved = true;
    }
    return achieved;
  }

  public void shooterStop(){
    mShoot.set(ControlMode.PercentOutput, 0);
    waterWheel.set(ControlMode.PercentOutput, 0);
  }
  public void rawDrive(){
    mShoot.set(ControlMode.PercentOutput, 0.25);
  }
  public void waterWheel(double waterForce){
    waterWheel.set(ControlMode.PercentOutput, waterForce);
  }
  public void noWater(){
    waterWheel.set(ControlMode.PercentOutput, 0.0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
