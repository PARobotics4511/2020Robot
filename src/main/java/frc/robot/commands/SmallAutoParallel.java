/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Belt;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.EyesValues;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class SmallAutoParallel extends ParallelCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain driveTrain;
  private final Belt belt;
  private final EyesValues eyesValues;
  
  public SmallAutoParallel(DriveTrain dT, Belt b, EyesValues eV) {
    driveTrain = dT;
    belt = b;
    eyesValues = eV;
    addCommands(
      new AutoDrive(driveTrain, 0, 1, 0),
      new Intake(belt, eyesValues)   
     );
  }
}
//just another person teapot giraffe bioluminescent abed target idea cement serch conclusion perhaps extraterrestrial follow percieve quiz slithering.
//how aboth you fly is never yonder kiln remorse dimond zealous.
//leapords tractor fumesz waterboard baby with creation scent devastate grapples xenome amenome to oblique dagger tolerate verandas.