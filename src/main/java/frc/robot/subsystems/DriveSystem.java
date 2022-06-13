// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.MotorConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class DriveSystem extends SubsystemBase {
  // private TalonFX leftMaster = new TalonFX(DriveConstants.LeftmasterID);
  private TalonFX leftMaster = new TalonFX(MotorConstants.LeftmasterID);
  private TalonFX leftFollower = new TalonFX(MotorConstants.LeftfollowerID);
  private TalonFX rightMaster = new TalonFX(MotorConstants.RightmasterID);
  private TalonFX rightFollower = new TalonFX(MotorConstants.RightfollowerID);
  // private TalonFX rightMaster = new TalonFX(DriveConstants.RightmasterID);
  // private TalonFX rightFollower = new TalonFX(DriveConstants.RightfollowerID);
  // private TalonSRX mSrx = new TalonSRX(0);
  // private PigeonIMU m_pigeon = new PigeonIMU(mSrx);
  // private PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();

  public DriveSystem() {
    // m_pigeon.getGeneralStatus(genStatus);
    leftMaster.setInverted(true);
    leftMaster.setSensorPhase(true);
    leftFollower.setInverted(true);
    leftFollower.setSensorPhase(true);
    rightMaster.setInverted(true);
    rightMaster.setSensorPhase(true);
    rightFollower.setInverted(true);
    rightFollower.setSensorPhase(true);

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Left Drive speed Value", leftMaster.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Left Drive Power Value", leftMaster.getMotorOutputPercent());
    SmartDashboard.putNumber("Left Drive Position Value", leftMaster.getSelectedSensorPosition());
    SmartDashboard.putNumber("Left2 Drive speed Value", leftFollower.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Left2 Drive Power Value", leftFollower.getMotorOutputPercent());
    SmartDashboard.putNumber("Left2 Drive Position Value", leftFollower.getSelectedSensorPosition());
  }

  public void setIMUzero() {
    // m_pigeon.setYaw(0, 10);
    // m_pigeon.setAccumZAngle(0, 10);
  }

  public double getAngle() {
    double[] ypr = new double[3];

    // m_pigeon.getYawPitchRoll(ypr);
    return ypr[0];

  }

  public void setBrake(boolean brake) {
    if (brake == true) {
      leftMaster.setNeutralMode(NeutralMode.Brake);
      leftFollower.setNeutralMode(NeutralMode.Brake);
      rightMaster.setNeutralMode(NeutralMode.Brake);
      rightFollower.setNeutralMode(NeutralMode.Brake);
    } else {
      leftMaster.setNeutralMode(NeutralMode.Coast);
      leftFollower.setNeutralMode(NeutralMode.Coast);
      rightMaster.setNeutralMode(NeutralMode.Coast);
      rightFollower.setNeutralMode(NeutralMode.Coast);
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
    configMotor(leftMaster, P, I, D, F, Izone, maxout);
    configMotor(leftFollower, P, I, D, F, Izone, maxout);
    configMotor(rightMaster, P, I, D, F, Izone, maxout);
    configMotor(rightFollower, P, I, D, F, Izone, maxout);

  }

  public void setLeftPosition() {
    leftMaster.setSelectedSensorPosition(0);
    leftFollower.setSelectedSensorPosition(0);
  }

  public void setRightPosition() {
    rightMaster.setSelectedSensorPosition(0);
    rightFollower.setSelectedSensorPosition(0);
  }

  public void setLeftSpeed(double speed) {
    leftMaster.set(TalonFXControlMode.Velocity, speed * MotorConstants.EncoderToRPM);
    leftFollower.set(TalonFXControlMode.Velocity, speed * MotorConstants.EncoderToRPM);
  }

  public void setRightSpeed(double speed) {
    rightMaster.set(TalonFXControlMode.Velocity, -speed * MotorConstants.EncoderToRPM);
    rightFollower.set(TalonFXControlMode.Velocity, -speed * MotorConstants.EncoderToRPM);
  }

  public void setLeftPower(double power) {
    leftMaster.set(ControlMode.PercentOutput, power);
    leftFollower.set(ControlMode.PercentOutput, power);

  }

  public void setRightPower(double power) {
    rightMaster.set(ControlMode.PercentOutput, power);
    rightFollower.set(ControlMode.PercentOutput, power);

  }

  public void setLeftgoMmeter(double meter) {
    leftMaster.set(TalonFXControlMode.Position, meter / (MotorConstants.Encode2Meter * MotorConstants.DrivegearRatio));
    leftFollower.set(TalonFXControlMode.Position,
        meter / (MotorConstants.Encode2Meter * MotorConstants.DrivegearRatio));

  }

  public void setRightgoMmeter(double meter) {
    rightMaster.set(TalonFXControlMode.Position, meter / (MotorConstants.Encode2Meter * MotorConstants.DrivegearRatio));
    rightFollower.set(TalonFXControlMode.Position,
        meter / (MotorConstants.Encode2Meter * MotorConstants.DrivegearRatio));
  }

  public double getleftspeed() {
    return leftMaster.getSelectedSensorVelocity() * 600 / 2048;

  }

  public double getrightspeed() {
    return rightMaster.getSelectedSensorVelocity() / 2048 * 600;

  }

  public double getLeftMeter() {
    return leftMaster.getSelectedSensorPosition() * MotorConstants.Encode2Meter * MotorConstants.DrivegearRatio;
  }

  public double getRightMeter() {
    return rightMaster.getSelectedSensorPosition() * MotorConstants.Encode2Meter * MotorConstants.DrivegearRatio;
  }

  public void turn(double power) {
    setLeftPower(-power);
    setRightPower(power);
  }
}
