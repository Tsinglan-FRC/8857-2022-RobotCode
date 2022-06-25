// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.SolenoidConstants;
import frc.robot.Toolkit.TKTalonFX;
import frc.robot.Toolkit.TKTalonFX.PIDType;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class LiftSystem extends SubsystemBase{
  private TalonFX lift_L = new TalonFX(MotorConstants.liftLeftID);
  private TalonFX lift_R = new TalonFX(MotorConstants.liftRightID);
  private Solenoid liftSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, SolenoidConstants.liftChannel);


  public LiftSystem() {


    lift_L.setInverted(false);
    lift_L.setSensorPhase(false);
    lift_R.setInverted(true);
    lift_R.setSensorPhase(true);

    
    TKTalonFX.configMotor(lift_L,PIDType.Liftpid);
    TKTalonFX.configMotor(lift_R,PIDType.Liftpid);
    setBrake(true);
    setLiftPositionzero();
  }

  @Override
  public void periodic() {
    
    SmartDashboard.putNumber("LiftPosition", getLiftPosition());
  }

  public void setBrake(boolean brake) {
    if (brake == true) {
      lift_L.setNeutralMode(NeutralMode.Brake);
      lift_R.setNeutralMode(NeutralMode.Brake);
    } else {
      lift_L.setNeutralMode(NeutralMode.Coast);
      lift_R.setNeutralMode(NeutralMode.Coast);
    }
  }

  public void setliftSoleniod(boolean SW) {

    if (SW == true) {
      liftSolenoid.set(false);

    } else {
      liftSolenoid.set(true);

    }
  }

  public void setLiftPositionzero() {
    lift_L.setSelectedSensorPosition(0);
    lift_R.setSelectedSensorPosition(0);
  }

  public void setLiftPower(double power) {
    power = power*-1;
    lift_L.set(ControlMode.PercentOutput, power);
    lift_R.set(ControlMode.PercentOutput, power);

  }

  public void setLiftPosition(double CapstanRound) {
    lift_L.set(ControlMode.Position, CapstanRound / MotorConstants.LiftgearRatio * 2048);
    lift_R.set(ControlMode.Position, CapstanRound / MotorConstants.LiftgearRatio * 2048);
  }

  public double getLiftPosition() {
    return lift_L.getSelectedSensorPosition();

  }

}
