// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LiftSystem;

public class LiftComm extends CommandBase {
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
  private final LiftSystem liftSubsystem;
  private final Supplier<Double> liftForward; 
  private final Supplier<Double> liftBackward;
  private final Supplier<Boolean> liftPneumatic;

  public LiftComm(LiftSystem liftSubsystem, Supplier<Double> liftForward, Supplier<Double> liftBackward,
      Supplier<Boolean> liftPneumatic) {
    this.liftForward = liftForward;
    this.liftBackward = liftBackward;
    this.liftSubsystem = liftSubsystem;
    this.liftPneumatic = liftPneumatic;
    addRequirements(liftSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forward = liftForward.get();
    double backward = liftBackward.get();
    boolean Pneumatic = liftPneumatic.get();

    if (Math.abs(forward) < 0.05){
      forward = 0;
    }
    if (Math.abs(backward) < 0.05){
      backward = 0;
    }

    if (forward > backward) {
      if (liftSubsystem.getLiftPosition() < 1000000) {
        liftSubsystem.setLiftPower(forward * 0.5);
        System.out.println(liftSubsystem.getLiftPosition());
      } 
      else{
        liftSubsystem.setLiftPower(0);
      }
    } 
    else if (forward < backward) {
      if (liftSubsystem.getLiftPosition() > -1000000) {
        liftSubsystem.setLiftPower(-backward * 0.5);
        System.out.println(liftSubsystem.getLiftPosition());
      } 
      else{
        liftSubsystem.setLiftPower(0);
      }
    } 
    else{
      liftSubsystem.setLiftPower(0);
    }

    liftSubsystem.setliftSoleniod(Pneumatic);

    // arcade(speed, turn, lowspeed);
  }

  @Override
  public void end(boolean interrupted) {
    liftSubsystem.setBrake(true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
