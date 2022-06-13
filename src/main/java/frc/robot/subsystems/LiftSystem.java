// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.MotorConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class LiftSystem extends SubsystemBase {
  private TalonFX lift_L = new TalonFX(MotorConstants.liftLeftID);
  private TalonFX lift_R = new TalonFX(MotorConstants.liftRightID);
  private DoubleSolenoid liftSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

  public LiftSystem() {
    lift_L.setInverted(false);
    lift_L.setSensorPhase(false);
    lift_R.setInverted(true);
    lift_R.setSensorPhase(true);
  }

  @Override
  public void periodic() {

  }

  public void setBrake(boolean brake) {
    if (brake == true) {
      lift_L.setNeutralMode(NeutralMode.Brake);
      lift_R.setNeutralMode(NeutralMode.Brake);
    } else {
      lift_L.setNeutralMode(NeutralMode.Brake);
      lift_R.setNeutralMode(NeutralMode.Brake);
    }
  }

  public void setliftSoleniod(boolean SW) {
    if (SW == true) {
      liftSolenoid.set(Value.kReverse);

    } else {
      liftSolenoid.set(Value.kForward);

    }
  }

  public void configMotor(TalonFX motor, double P, double I, double D, double F, double Izone, double maxout) {
    motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, MotorConstants.kPIDSlot,
        Constants.kCANTimeoutMS);

    motor.config_kP(MotorConstants.kPIDSlot, P, Constants.kCANTimeoutMS);
    motor.config_kI(MotorConstants.kPIDSlot, I, Constants.kCANTimeoutMS);
    motor.config_kD(MotorConstants.kPIDSlot, D, Constants.kCANTimeoutMS);
    motor.config_kF(MotorConstants.kPIDSlot, F, Constants.kCANTimeoutMS);

    motor.config_IntegralZone(MotorConstants.kPIDSlot, Izone, Constants.kCANTimeoutMS);
    motor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, MotorConstants.kCurrentLimitAmps,
        MotorConstants.kCurrentLimitAmps, MotorConstants.kCurrentLimitDelay));
    motor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, MotorConstants.kCurrentLimitAmps,
        MotorConstants.kCurrentLimitAmps, MotorConstants.kCurrentLimitDelay));
  }

  public void setLiftPID(double P, double I, double D, double F, double Izone, double maxout) {
    configMotor(lift_L, P, I, D, F, Izone, maxout);
    configMotor(lift_R, P, I, D, F, Izone, maxout);

  }

  public void setLiftPositionzero() {
    lift_L.setSelectedSensorPosition(0);
    lift_R.setSelectedSensorPosition(0);
  }

  public void setLiftPower(double power) {
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
