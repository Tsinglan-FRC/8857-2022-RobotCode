// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Toolkit.Toggle;
import frc.robot.subsystems.intakeSystem;

public class Intakecomm extends CommandBase {
  // IntakeSysten m_IntakeSysten;
  private final intakeSystem intakeSystem;
  private final Supplier<Boolean> putOut;
  private final Supplier<Boolean> intakeStatus;
  private final Supplier<Boolean> getOut;
  private final Supplier<Double> slowUp;
  private final Supplier<Double> slowDown;

  private final Toggle intakeToggle = new Toggle();

  //private boolean togglePressed = false;

  /** Creates a new Intakecomm. */
  public Intakecomm(
    intakeSystem _intakeSystem, 
    Supplier<Boolean> _putOut, 
    Supplier<Boolean> _intakeStatus,
    Supplier<Boolean> _getOut,
    Supplier<Double> _slowUp,
    Supplier<Double> _slowDown) {
    // Use addRequirements() here to declare subsystem dependencies.
    // m_IntakeSysten = mIntakeSysten;
    intakeSystem = _intakeSystem;
    putOut = _putOut;
    intakeStatus = _intakeStatus;
    getOut = _getOut;
    slowUp = _slowUp;
    slowDown = _slowDown;

    addRequirements(intakeSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // boolean Com= IntakeStandby.get();
    // System.out.println(Com);

    boolean putOutGet = putOut.get();
    boolean getOutGet = getOut.get();

    boolean intakeStatusGet = intakeStatus.get();

    double slowUpGet = slowUp.get();
    double slowDownGet = slowDown.get();


		if(putOutGet == true){
			intakeSystem.moveBallUP(IntakeConstants.power_MoveBallUp);
      //intakeSystem.setIntake(intakeStatusGet, MotorConstants.intakeSpeedTruePower);
      intakeSystem.moveBallIn(IntakeConstants.power_MoveBallIn);
		}
		else if(getOutGet == true){
			intakeSystem.moveBallDown(IntakeConstants.power_MoveBallDown);
      //intakeSystem.setIntake(intakeStatusGet, MotorConstants.intakeSpeedFalsePower);
      intakeSystem.moveBallOut(IntakeConstants.power_MoveBallOut);
		}
    else if(slowUpGet>IntakeConstants.slowMovementDeadzone && slowDownGet<=IntakeConstants.slowMovementDeadzone){
      intakeSystem.moveBallUP(IntakeConstants.slowUppower);
    }
    else if(slowDownGet>IntakeConstants.slowMovementDeadzone && slowUpGet<=IntakeConstants.slowMovementDeadzone){
      intakeSystem.moveBallDown(IntakeConstants.slowDownpower);
    }
		else{
			intakeSystem.moveBallUP(0);
      //intakeSystem.setIntake(intakeStatusGet,0);
      intakeSystem.moveBallIn(0);
		}

    intakeToggle.press(intakeStatusGet);
    intakeSystem.intakeSet(intakeToggle.get());
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //intakeSystem.setBrake(false);
    //intakeSystem.setIntake(false,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
