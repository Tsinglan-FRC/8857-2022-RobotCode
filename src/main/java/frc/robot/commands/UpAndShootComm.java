// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.MotorConstants.Telepid;
import frc.robot.subsystems.upAndShootSystem;

public class UpAndShootComm extends CommandBase {
  // IntakeSysten m_IntakeSysten;
  private final upAndShootSystem uPAndShootSystem;
  private final Supplier<Boolean> shootBallForward, shootBallBackward, upBallForward, upBallBackward;

  /** Creates a new Intakecomm. */
  public UpAndShootComm(upAndShootSystem uPAndShootSystem, Supplier<Boolean> shootBallForward,
      Supplier<Boolean> shootBallBackward, Supplier<Boolean> upBallForward, Supplier<Boolean> upBallBackward) {
    // Use addRequirements() here to declare subsystem dependencies.
    // m_IntakeSysten = mIntakeSysten;
    this.uPAndShootSystem = uPAndShootSystem;
    this.shootBallForward = shootBallForward;
    this.shootBallBackward = shootBallBackward;
    this.upBallForward = upBallForward;
    this.upBallBackward = upBallBackward;
    addRequirements(uPAndShootSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    uPAndShootSystem.setDrivePID(Telepid.kP, Telepid.kI, Telepid.kD, Telepid.kF, Telepid.kIZone, Telepid.Maxout);
    uPAndShootSystem.setBrake(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // boolean Com= IntakeStandby.get();

    // m_PneumaticSystem.setCompressorclosedloop(Compressorstart.get());
    // m_PneumaticSystem.setIntake(O_Intake.get(), IntakeStandby.get());
    boolean shootBallForwardStatus = shootBallForward.get();
    boolean shootBallBackwardStatus = shootBallBackward.get();
    boolean upBallForwardStatus = upBallForward.get();
    boolean upBallBackwardStatus = upBallBackward.get();
    // System.out.println(shootBallForwardStatus);
    if (shootBallForwardStatus == true)
      uPAndShootSystem.setshootForward(0.5);
    else {
      if (shootBallBackwardStatus == true)
        uPAndShootSystem.setshootBackward(0.5);
      else
        uPAndShootSystem.setshootForward(0.0);
    }

    if (upBallForwardStatus == true)
      uPAndShootSystem.setupBallForward(0.5);
    else {
      if (upBallBackwardStatus == true)
        uPAndShootSystem.setupBallBackward(0.5);
      else
        uPAndShootSystem.setupBallForward(0.0);
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
