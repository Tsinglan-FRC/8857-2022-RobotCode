// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;

public class PneumaticSystem extends SubsystemBase {
  private Compressor m_pressor = new Compressor(PneumaticsModuleType.CTREPCM);

  /** Creates a new Pneumatic. */
  public PneumaticSystem() {
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("enabled", m_pressor.enabled());
    SmartDashboard.putBoolean("getPressureSwitchValue", m_pressor.getPressureSwitchValue());
    SmartDashboard.putNumber("getCurrent", m_pressor.getCurrent());

  }

  public void setCompressorclosedloop(boolean start) {
    if (start == true)
      m_pressor.enableDigital();
    else
      m_pressor.disable();
  }

}
