/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {
  private final DriveTrain driveTrain;
  private double multiplier = 0.8;
  private DoubleSupplier lX;
  private DoubleSupplier lY;
  private DoubleSupplier rX;

  public Drive(DriveTrain dT, DoubleSupplier lx, DoubleSupplier ly, DoubleSupplier rx) {
    driveTrain = dT;
    addRequirements(driveTrain);
    lX = lx;
    lY = ly;
    rX = rx;
  }
  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    driveTrain.RobotDrive(lX.getAsDouble() * multiplier,-1 * lY.getAsDouble() * multiplier, rX.getAsDouble() * multiplier);
  }
}
