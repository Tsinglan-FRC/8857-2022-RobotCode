// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class ArcadeDrive extends CommandBase {
  /*
   * private final DriveSystem m_drivesystem;
   * private final Double speedFunction, turnFunction;
   * private final boolean lowspeedFuntion;
   * 
   * public ArcadeDrive(DriveSystem m_drive, double speed, double turn, boolean
   * lowspeed) {
   * m_drivesystem = m_drive;
   * speedFunction = speed;
   * turnFunction = turn;
   * lowspeedFuntion = lowspeed;
   * addRequirements(m_drive);
   * // Use addRequirements() here to declare subsystem dependencies.
   * }
   */

  private final DriveSystem driveSubsystem;
  private final Supplier<Double> SpeedFunction;
  private final Supplier<Double> turnFunction;
  private final Supplier<Boolean> LowspeedFunction;

  public ArcadeDrive(
    DriveSystem _driveSubsystem, //
    Supplier<Double> _SpeedFunction, 
    Supplier<Double> _turnFunction, 
    Supplier<Boolean> _LowspeedFunction) {

    SpeedFunction = _SpeedFunction;
    LowspeedFunction = _LowspeedFunction;
    turnFunction = _turnFunction;
    driveSubsystem = _driveSubsystem;

    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    /*driveSubsystem.setDrivePID(Telepid.kP, Telepid.kI, Telepid.kD, Telepid.kF, Telepid.kIZone, Telepid.Maxout);
    driveSubsystem.setBrake(true);*/
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double speed = SpeedFunction.get();
    double turn = turnFunction.get();
    boolean lowspeed = LowspeedFunction.get();

    if (Math.abs(speed) < 0.1){
      speed = 0;
    }
    if (Math.abs(turn) < 0.1){
      turn = 0;
    }

    driveSubsystem.arcade(speed, turn, lowspeed);
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.setBrake(true);
    driveSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
