// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.MotorConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class intakeSystem extends SubsystemBase {
  // private TalonFX leftMaster = new TalonFX(MotorConstants.LeftmasterID);
  private TalonFX intake = new TalonFX(MotorConstants.intakeID);
  private DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
  // private TalonFX upBall = new TalonFX(MotorConstants.upBallID);
  // private TalonFX rightMaster = new TalonFX(MotorConstants.RightmasterID);
  // private TalonFX rightFollower = new TalonFX(MotorConstants.RightfollowerID);
  // private TalonSRX mSrx = new TalonSRX(0);
  // private PigeonIMU m_pigeon = new PigeonIMU(mSrx);
  // private PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();

  public intakeSystem() {
    // m_pigeon.getGeneralStatus(genStatus);
    intake.setInverted(true);
    intake.setSensorPhase(true);
    // upBall.setInverted(true);
    // upBall.setSensorPhase(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("intake speed Value", intake.getMotorOutputPercent());
    SmartDashboard.putNumber("intake Current", intake.getSupplyCurrent());
    // SmartDashboard.putNumber("Left Drive Position Value",
    // leftMaster.getSelectedSensorPosition());
    // SmartDashboard.putNumber("Left2 Drive speed Value",
    // leftFollower.getSelectedSensorVelocity());
    // SmartDashboard.putNumber("Left2 Drive Power Value",
    // leftFollower.getMotorOutputPercent());
    // SmartDashboard.putNumber("Left2 Drive Position Value",
    // leftFollower.getSelectedSensorPosition());
    // This method will be called once per scheduler run
  }

  public void setBrake(boolean brake) {
    if (brake == true) {
      intake.setNeutralMode(NeutralMode.Brake);
    } else {
      intake.setNeutralMode(NeutralMode.Coast);
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
    // motor.configPeakOutputForward(maxout);
    // motor.configPeakOutputReverse(-maxout);
    motor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, MotorConstants.kCurrentLimitAmps,
        MotorConstants.kCurrentLimitAmps, MotorConstants.kCurrentLimitDelay));
    motor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, MotorConstants.kCurrentLimitAmps,
        MotorConstants.kCurrentLimitAmps, MotorConstants.kCurrentLimitDelay));
  }

  public void setDrivePID(double P, double I, double D, double F, double Izone, double maxout) {
    configMotor(intake, P, I, D, F, Izone, maxout);
    // configMotor(upBall, P, I, D, F, Izone, maxout);
  }

  public void setIntakeForward(double power) {
    intake.set(ControlMode.PercentOutput, power);
  }

  public void setIntake(boolean SW, double power) {
    if (SW == true) {
      intakeSolenoid.set(Value.kReverse);
      intake.set(ControlMode.PercentOutput, power);
    } else {
      intakeSolenoid.set(Value.kForward);
      intake.set(ControlMode.PercentOutput, 0.0);
    }
  }
}
