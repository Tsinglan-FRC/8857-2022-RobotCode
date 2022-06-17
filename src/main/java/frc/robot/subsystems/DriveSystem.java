// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Toolkit.TKTalonFX;
import frc.robot.Toolkit.TKTalonFX.PIDType;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.MotorConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class DriveSystem extends SubsystemBase{
  // private TalonFX leftMaster = new TalonFX(DriveConstants.LeftmasterID);
  private TalonFX leftMaster = new TalonFX(MotorConstants.LeftmasterID); // 定义电机
  private TalonFX leftFollower = new TalonFX(MotorConstants.LeftfollowerID); // 定义电机
  private TalonFX rightMaster = new TalonFX(MotorConstants.RightmasterID); // 定义电机
  private TalonFX rightFollower = new TalonFX(MotorConstants.RightfollowerID); // 定义电机
  // private TalonFX rightMaster = new TalonFX(DriveConstants.RightmasterID);
  // private TalonFX rightFollower = new TalonFX(DriveConstants.RightfollowerID);
  // private TalonSRX mSrx = new TalonSRX(0);
  // private PigeonIMU m_pigeon = new PigeonIMU(mSrx);
  // private PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();

  public DriveSystem() {
    // m_pigeon.getGeneralStatus(genStatus);
    leftMaster.setInverted(true); // 定义马达
    leftMaster.setSensorPhase(true); // 定义马达
    leftFollower.setInverted(true); // 定义马达
    leftFollower.setSensorPhase(true); // 定义马达
    rightMaster.setInverted(true); // 定义马达
    rightMaster.setSensorPhase(true); // 定义马达
    rightFollower.setInverted(true); // 定义马达
    rightFollower.setSensorPhase(true); // 定义马达

    TKTalonFX.configMotor(leftMaster,PIDType.Telepid);
    TKTalonFX.configMotor(leftFollower,PIDType.Telepid);
    TKTalonFX.configMotor(rightMaster,PIDType.Telepid);
    TKTalonFX.configMotor(rightFollower,PIDType.Telepid);

    setBrake(true);
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

  /*public void setIMUzero() {
    // m_pigeon.setYaw(0, 10);
    // m_pigeon.setAccumZAngle(0, 10);
  }*/

  /*public double getAngle() {
    double[] ypr = new double[3];

    // m_pigeon.getYawPitchRoll(ypr);
    return ypr[0];

  }*/

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

  /*public void setDrivePID(double P, double I, double D, double F, double Izone, double maxout) {
    configMotor(leftMaster, P, I, D, F, Izone, maxout);
    configMotor(leftFollower, P, I, D, F, Izone, maxout);
    configMotor(rightMaster, P, I, D, F, Izone, maxout);
    configMotor(rightFollower, P, I, D, F, Izone, maxout);
  }*/

  private double f(double x){
    if(x>0){
      return x*x;
    }
    else{
      return x*x * -1;
    }
  }


  public void arcade(double speed, double turn, boolean lowspeed) {
    if (lowspeed) {
      setLeftSpeed(f(speed) * DriveConstants.speedFactor_slowspeed + f(turn) * DriveConstants.turnFactor_slowspeed);
      setRightSpeed(f(speed) * DriveConstants.speedFactor_slowspeed - f(turn) * DriveConstants.turnFactor_slowspeed);
    }

    else {
      setLeftSpeed(speed * DriveConstants.speedFactor + turn * DriveConstants.turnFacotr);
      setRightSpeed(speed * DriveConstants.speedFactor- turn * DriveConstants.turnFacotr);
    }
  }

  public void stop(){
    arcade(0,0,false);
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
    rightMaster.set(ControlMode.PercentOutput, -power);
    rightFollower.set(ControlMode.PercentOutput, -power);

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
