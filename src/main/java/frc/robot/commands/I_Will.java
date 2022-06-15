/*
 * The name of the class is chosen from a song with the same name.
 * It is a good song and if anyone see this, you may go take a try.
 * What I hear is not the original one but a guitar cover version.
 * Platform is NetEase Music, written by DongNi Fingerstyle.
 * Hope you enjoy.
 */



package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;
import frc.robot.Constants.MotorConstants.Telepid;

public class I_Will extends CommandBase{
    private final DriveSystem m_DriveSystem;

    private final Timer m_Timer;

    public I_Will(DriveSystem _DriveSystem){
        m_DriveSystem=_DriveSystem;

        m_Timer = new Timer();
        m_Timer.start();

        addRequirements(m_DriveSystem);
    }
    
    @Override
    public void initialize() {
      m_DriveSystem.setDrivePID(Telepid.kP, Telepid.kI, Telepid.kD, Telepid.kF, Telepid.kIZone, Telepid.Maxout);
      m_DriveSystem.setBrake(true);
    }

    @Override
    public void execute(){
        if(m_Timer.get()<10){
            m_DriveSystem.arcade(0.3, 0, false);
        }
    }
    
    @Override
    public void end(boolean interrupted) {
      m_DriveSystem.setBrake(true);
      m_DriveSystem.arcade(0,0, false);
    }

    @Override
    public boolean isFinished() {
      return false;
    }
}
