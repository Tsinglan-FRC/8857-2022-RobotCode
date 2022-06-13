// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.PneumaticSystem;

public class PCM_Conctrl extends CommandBase {
  // IntakeSysten m_IntakeSysten;
  private final PneumaticSystem m_PneumaticSystem;
  private final Supplier<Boolean> CompressorStatus;
  boolean togglePressed = false;
  boolean defaultvalue = true;

  /** Creates a new Intakecomm. */
  public PCM_Conctrl(PneumaticSystem m_PneumaticSystem, Supplier<Boolean> CompressorStatus) {
    // Use addRequirements() here to declare subsystem dependencies.
    // m_IntakeSysten = mIntakeSysten;
    this.m_PneumaticSystem = m_PneumaticSystem;
    this.CompressorStatus = CompressorStatus;
    addRequirements(m_PneumaticSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // boolean Com= IntakeStandby.get();
    // System.out.println(Com);
    m_PneumaticSystem.setCompressorclosedloop(toggle(CompressorStatus.get()));
    // m_PneumaticSystem.setIntake(intakeStatus.get());
  }

  public boolean toggle(boolean btn) {
    if (btn) {
      if (!togglePressed) {
        defaultvalue = !defaultvalue;
        togglePressed = true;
      }
    } else
      togglePressed = false;
    return defaultvalue;
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
