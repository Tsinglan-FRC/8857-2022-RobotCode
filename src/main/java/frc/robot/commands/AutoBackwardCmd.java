// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.MotorConstants.Movepid;
import frc.robot.subsystems.DriveSystem;

public class AutoBackwardCmd extends CommandBase {
  DriveSystem m_drivesystem;
  double distance;

  /** Creates a new gostraight. */
  public AutoBackwardCmd(DriveSystem p_drivesystem, double Distance) {
    m_drivesystem = p_drivesystem;
    distance = Distance;
    addRequirements(m_drivesystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // m_drivesystem.setLeftPosition();
    m_drivesystem.setDrivePID(Movepid.kP, Movepid.kI, Movepid.kD, Movepid.kF, Movepid.kIZone, Movepid.Maxout);
    m_drivesystem.setBrake(true);
    System.out.println("sec type");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    move(distance);
    System.out.println(m_drivesystem.getLeftMeter());
    // if(Math.abs(m_drivesystem.getLeftMeter())-Math.abs(distance)<0.1)
    // isFinished();
  }

  public void move(double meter) {
    m_drivesystem.setLeftgoMmeter(meter);
    m_drivesystem.setRightgoMmeter(meter);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivesystem.setBrake(true);
    System.out.println("stop2");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(m_drivesystem.getLeftMeter()) + 0.1 > Math.abs(distance))
      return true;
    else
      return false;
  }
}
