// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.MotorConstants.Telepid;
import frc.robot.subsystems.intakeSystem;

public class Intakecomm extends CommandBase {
  // IntakeSysten m_IntakeSysten;
  private final intakeSystem iNtakeSystem;
  private final Supplier<Boolean> intakeSpeed, intakeStatus;
  boolean togglePressed = false;

  /** Creates a new Intakecomm. */
  public Intakecomm(intakeSystem iNtakeSystem, Supplier<Boolean> intakeSpeed, Supplier<Boolean> intakeStatus) {
    // Use addRequirements() here to declare subsystem dependencies.
    // m_IntakeSysten = mIntakeSysten;
    this.iNtakeSystem = iNtakeSystem;
    this.intakeSpeed = intakeSpeed;
    this.intakeStatus = intakeStatus;
    addRequirements(iNtakeSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    iNtakeSystem.setDrivePID(Telepid.kP, Telepid.kI, Telepid.kD, Telepid.kF, Telepid.kIZone, Telepid.Maxout);
    iNtakeSystem.setBrake(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // boolean Com= IntakeStandby.get();
    // System.out.println(Com);
    double FBW = 0.0;
    if (intakeSpeed.get())
      FBW = 0.2;
    else
      FBW = -0.2;
    iNtakeSystem.setIntake(intakeStatus.get(), FBW);
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
