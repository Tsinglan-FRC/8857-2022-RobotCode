// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;
import frc.robot.Toolkit.TKTalonFX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class LiftSystem extends SubsystemBase implements TKTalonFX{
  private TalonFX lift_L = new TalonFX(MotorConstants.liftLeftID);
  private TalonFX lift_R = new TalonFX(MotorConstants.liftRightID);
  private DoubleSolenoid liftSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

  private boolean defaultvalue;
  private boolean togglePressed;

  private boolean toggle(boolean btn) {
    if (btn) {
      if (togglePressed == false) {
        defaultvalue = true;
        togglePressed = true;
      }
      else{
        defaultvalue = false;
        togglePressed = false;
      }
    }
    return defaultvalue;
  }

  public LiftSystem() {
    defaultvalue = true;
    togglePressed = false;


    lift_L.setInverted(false);
    lift_L.setSensorPhase(false);
    lift_R.setInverted(true);
    lift_R.setSensorPhase(true);

    
    configMotor(lift_L,PIDType.Liftpid);
    configMotor(lift_R,PIDType.Liftpid);
    setBrake(true);
    setLiftPositionzero();
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

  public void setliftSoleniod(boolean raw) {
    boolean SW = toggle(raw);


    if (SW == true) {
      liftSolenoid.set(Value.kReverse);

    } else {
      liftSolenoid.set(Value.kForward);

    }
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
