// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LiftConstants;
import frc.robot.Constants.LiftConstants.FGTB;
import frc.robot.Constants.LiftConstants.FSTB;
import frc.robot.Toolkit.Toggle;
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
  private final Supplier<Boolean> setZero;

  private final Toggle togglePneumatic = new Toggle();

  public LiftComm(LiftSystem liftSubsystem, 
      Supplier<Double> liftForward, 
      Supplier<Double> liftBackward,
      Supplier<Boolean> liftPneumatic,
      Supplier<Boolean> _setZero) {
    this.liftForward = liftForward;
    this.liftBackward = liftBackward;
    this.liftSubsystem = liftSubsystem;
    this.liftPneumatic = liftPneumatic;
    this.setZero = _setZero;
    addRequirements(liftSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    liftSubsystem.setLiftPositionzero();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forward = liftForward.get();
    double backward = liftBackward.get();
    boolean Pneumatic = liftPneumatic.get();
    boolean setZeroGet = setZero.get();

    if (Math.abs(forward) < LiftConstants.joystickDeadZone){
      forward = 0;
    }
    if (Math.abs(backward) < LiftConstants.joystickDeadZone){
      backward = 0;
    }

    if(setZeroGet){
      liftSubsystem.setLiftPosition(0);
    }
    else if (forward > backward) {
      if (liftSubsystem.getLiftPosition() > FGTB.LPosMAX) {
        liftSubsystem.setLiftPower(forward * FGTB.LPowFAC);
      } 
      else{
        liftSubsystem.setLiftPower(0);
      }
    } 
    else if (forward < backward) {
      if (liftSubsystem.getLiftPosition() < FSTB.LPosMIN) {
        liftSubsystem.setLiftPower(-backward * FSTB.LPowFAC);
      } 
      else{
        liftSubsystem.setLiftPower(0);
      }
    } 
    else{
      liftSubsystem.setLiftPower(0);
    }

    togglePneumatic.press(Pneumatic);
    liftSubsystem.setliftSoleniod(togglePneumatic.get());

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
