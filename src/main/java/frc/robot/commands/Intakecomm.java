// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
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
  private final Supplier<Boolean> slowUp;
  private final Supplier<Boolean> slowDown;
  private final Supplier<Boolean> seperateBalls;

  private final Toggle intakeToggle = new Toggle();

  private final Timer timer = new Timer();
  private Boolean doing = false;
  private Boolean done = false;

  //private boolean togglePressed = false;

  /** Creates a new Intakecomm. */
  public Intakecomm(
    intakeSystem _intakeSystem, 
    Supplier<Boolean> _putOut, 
    Supplier<Boolean> _intakeStatus,
    Supplier<Boolean> _getOut,
    Supplier<Boolean> _slowUp,
    Supplier<Boolean> _slowDown,
    Supplier<Boolean> _seperateBalls) {
    // Use addRequirements() here to declare subsystem dependencies.
    // m_IntakeSysten = mIntakeSysten;
    intakeSystem = _intakeSystem;
    putOut = _putOut;
    intakeStatus = _intakeStatus;
    getOut = _getOut;
    slowUp = _slowUp;
    slowDown = _slowDown;
    seperateBalls = _seperateBalls;

    addRequirements(intakeSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //intakeToggle.press(true);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // boolean Com= IntakeStandby.get();
    // System.out.println(Com);

    boolean putOutGet = putOut.get();
    boolean getOutGet = getOut.get();

    boolean intakeStatusGet = intakeStatus.get();

    boolean slowUpGet = slowUp.get();
    boolean slowDownGet = slowDown.get();

    boolean seperateBallsGet = seperateBalls.get();
		
    if(doing){
      if(timer.get()<=IntakeConstants.doing_stage1){
        intakeSystem.moveBallDown(IntakeConstants.slowDownpower);
      }
      else if(timer.get()<=IntakeConstants.doing_stage2){
        intakeSystem.moveBallUP(IntakeConstants.slowUppower);
      }
      else{
        doing = false;
        done = true;
        timer.stop();
        timer.reset();
      }
    }
    else if(putOutGet == true){
			intakeSystem.moveBallUP(IntakeConstants.power_MoveBallUp);
      //intakeSystem.setIntake(intakeStatusGet, MotorConstants.intakeSpeedTruePower);
      intakeSystem.moveBallIn(IntakeConstants.power_MoveBallIn);
		}
		else if(getOutGet == true){
			intakeSystem.moveBallDown(IntakeConstants.power_MoveBallDown);
      //intakeSystem.setIntake(intakeStatusGet, MotorConstants.intakeSpeedFalsePower);
      intakeSystem.moveBallOut(IntakeConstants.power_MoveBallOut);
		}
    else if(slowUpGet && !slowDownGet){
      intakeSystem.moveBallUP(IntakeConstants.slowUppower);
    }
    else if(slowDownGet && !slowUpGet){
      intakeSystem.moveBallDown(IntakeConstants.slowDownpower);
    }
    else if(seperateBallsGet && !done){
      doing = true;
      timer.start();
    }
		else{
			intakeSystem.moveBallUP(0);
      //intakeSystem.setIntake(intakeStatusGet,0);
      intakeSystem.moveBallIn(0);
		}

    intakeToggle.press(intakeStatusGet);
    intakeSystem.intakeSet(intakeToggle.get());
    
    if(seperateBallsGet==false && doing==false){
      done = false;
    }
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
