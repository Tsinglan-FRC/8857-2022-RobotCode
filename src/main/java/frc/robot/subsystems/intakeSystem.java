// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Toolkit.TKTalonFX;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.SolenoidConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class intakeSystem extends SubsystemBase implements TKTalonFX{
  // private TalonFX leftMaster = new TalonFX(MotorConstants.LeftmasterID);
  private final TalonFX intake;
  private final Solenoid intakeSolenoid;
  // private TalonFX upBall = new TalonFX(MotorConstants.upBallID);
  // private TalonFX rightMaster = new TalonFX(MotorConstants.RightmasterID);
  // private TalonFX rightFollower = new TalonFX(MotorConstants.RightfollowerID);
  // private TalonSRX mSrx = new TalonSRX(0);
  // private PigeonIMU m_pigeon = new PigeonIMU(mSrx);
  // private PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();


  public intakeSystem() {
    intake = new TalonFX(MotorConstants.intakeID);
    intakeSolenoid = new Solenoid(
      PneumaticsModuleType.CTREPCM, 
      SolenoidConstants.intakeChannel);

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

  /*public void setIntakeForward(double power) {
    intake.set(ControlMode.PercentOutput, power);
  }*/

  public void setIntake(boolean SW, double power) {
    if (SW == true) {
      intakeSolenoid.set(true);
      intake.set(ControlMode.PercentOutput, power);
    } else {
      intakeSolenoid.set(false);
      intake.set(ControlMode.PercentOutput, 0.0);
    }
  }

  /*public void setBrake(boolean brake){
    if(brake){
      intake.setNeutralMode(NeutralMode.Brake);
    }
    else{
      intake.setNeutralMode(NeutralMode.Coast);
    }
  }*/
}
