// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.MotorConstants;

// 定义电机
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class upAndShootSystem extends SubsystemBase {
  // private TalonFX leftMaster = new TalonFX(MotorConstants.LeftmasterID);
  private TalonFX shootBall_L = new TalonFX(MotorConstants.shootBallLeftID); //一左一右两个射球
  private TalonFX shootBall_R = new TalonFX(MotorConstants.shootBallRightID); //一左一右两个射球
  // private TalonFX upBall = new TalonFX(MotorConstants.upBallID); // 暂未更新

  public upAndShootSystem() {
    // m_pigeon.getGeneralStatus(genStatus);
    shootBall_L.setInverted(true);// 设置反转
    shootBall_L.setSensorPhase(true); // 设置传感器状态
    shootBall_R.setInverted(false);// 设置不反转（需要测试）
    shootBall_R.setSensorPhase(true); // 设置传感器状态
    // upBall.setInverted(true);
    // upBall.setSensorPhase(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("intake speed Value Left", shootBall_L.getMotorOutputPercent());
    SmartDashboard.putNumber("intake Current Left", shootBall_L.getSupplyCurrent());
    SmartDashboard.putNumber("intake speed Value Right", shootBall_R.getMotorOutputPercent());
    SmartDashboard.putNumber("intake Current Right", shootBall_R.getSupplyCurrent());

  }

  public void setBrake(boolean brake) {
    if (brake == true) {
      shootBall_L.setNeutralMode(NeutralMode.Brake); // 设置刹车
      shootBall_R.setNeutralMode(NeutralMode.Brake); // 设置刹车
    } else {
      shootBall_L.setNeutralMode(NeutralMode.Coast); 
      shootBall_R.setNeutralMode(NeutralMode.Coast);
    }
    // if (brake == true) {
    //   upBall.setNeutralMode(NeutralMode.Brake);
    // } else {
    //   upBall.setNeutralMode(NeutralMode.Coast);
    // }
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
    configMotor(shootBall_L, P, I, D, F, Izone, maxout);
    // configMotor(upBall, P, I, D, F, Izone, maxout);
  }

  public void setshootForward(double power) {
    shootBall_L.set(ControlMode.PercentOutput, power);
    shootBall_R.set(ControlMode.PercentOutput, power);
  }

  public void setshootBackward(double power) {
    shootBall_L.set(ControlMode.PercentOutput, power * -1);
    shootBall_R.set(ControlMode.PercentOutput, power * -1);
  }

  // public void setupBallForward(double power) {
  //   upBall.set(ControlMode.PercentOutput, power);
  // }

  // public void setupBallBackward(double power) {
  //   upBall.set(ControlMode.PercentOutput, power * -1);
  // }
}
