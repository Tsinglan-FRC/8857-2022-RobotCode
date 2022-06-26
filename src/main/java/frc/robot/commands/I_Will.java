/*
 * The name of the class is chosen from a song with the same name.
 * It is a good song and if anyone see this, you may go take a try.
 * What I hear is not the original one but a guitar cover version.
 * Platform is NetEase Music, written by DongNi Fingerstyle.
 * Hope you enjoy.
 */



package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Toolkit;
import frc.robot.Toolkit.TimedCommand;
import frc.robot.Toolkit.TimedCommandHandle;
import frc.robot.autocmds.PlanDemo;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.LiftSystem;
import frc.robot.subsystems.PneumaticSystem;
import frc.robot.subsystems.TurrentSystem;
import frc.robot.subsystems.intakeSystem;

public class I_Will extends CommandBase{
    private final DriveSystem drive;
    private final intakeSystem intake;
    private final LiftSystem lift;
    private final PneumaticSystem pcm;
    private final TurrentSystem turrent;

    private final Timer m_Timer;

    private final TimedCommandHandle handle;


    private TimedCommand nowDriveCmd = null;
    private TimedCommand nowIntakeCmd = null;
    private TimedCommand nowLiftCmd = null;
    private TimedCommand nowPcmCmd = null;
    private TimedCommand nowTurrentCmd = null;

    public I_Will(
        DriveSystem d,
        intakeSystem i,
        LiftSystem l,
        PneumaticSystem p,
        TurrentSystem t
    ){
        m_Timer = new Timer();

        drive = d;
        intake = i;
        lift = l;
        pcm = p;
        turrent = t;

        handle = new PlanDemo(
            drive,
            intake,
            lift,
            pcm,
            turrent
        );

        addRequirements(drive);
        addRequirements(intake);
        addRequirements(lift);
        addRequirements(pcm);
        addRequirements(turrent);
    }
    
    @Override
    public void initialize() {
        m_Timer.stop();
        m_Timer.reset();
        m_Timer.start();
    }

    @Override
    public void execute(){
        SmartDashboard.putNumber("m_Timer : ", m_Timer.get());
        SmartDashboard.putNumber("Cancel : ",Toolkit.TimedCommand.count);
        if(nowDriveCmd!=null) SmartDashboard.putNumber("nowDriveCmd.getEndTime() : ", nowDriveCmd.getEndTime());

        TimedCommand[] driveCmds = handle.getDriveGroup().getCommands();
        TimedCommand[] intakeCmds = handle.getIntakeGroup().getCommands();
        TimedCommand[] liftCmds = handle.getLiftGroup().getCommands();
        TimedCommand[] pcmCmds = handle.getPneumaticGroup().getCommands();
        TimedCommand[] turrentCmds = handle.getTurrentGroup().getCommands();

        double time = m_Timer.get();

        if(driveCmds!=null){
            if(time <= driveCmds[0].getEndTime()){
                if(nowDriveCmd!=driveCmds[0]){
                    if(nowDriveCmd!=null) nowDriveCmd.cancel();
                    nowDriveCmd = driveCmds[0];
                    nowDriveCmd.schedule();
                }
            }
            else{
                for(int i=1;i<driveCmds.length;i++){
                    if(time <= driveCmds[i].getEndTime() && time > driveCmds[i-1].getEndTime()){
                        if(nowDriveCmd!=driveCmds[i]){
                            if(nowDriveCmd!=null) nowDriveCmd.cancel();
                            nowDriveCmd = driveCmds[i];
                            nowDriveCmd.schedule();
                        }
                    }
                }
            }
            
            if(nowDriveCmd!=null) if(nowDriveCmd.getEndTime() < time) {
                nowDriveCmd.cancel();
            }
        }

        if(intakeCmds!=null){
            if(time <= intakeCmds[0].getEndTime()){
                if(nowIntakeCmd!=intakeCmds[0]){
                    if(nowIntakeCmd!=null) nowIntakeCmd.cancel();
                    nowIntakeCmd = intakeCmds[0];
                    nowIntakeCmd.schedule();
                }
            }
            else{
                for(int i=1;i<intakeCmds.length;i++){
                    if(time <= intakeCmds[i].getEndTime() && time > intakeCmds[i-1].getEndTime()){
                        if(nowIntakeCmd!=intakeCmds[i]){
                            if(nowIntakeCmd!=null) nowIntakeCmd.cancel();
                            nowIntakeCmd = intakeCmds[i];
                            nowIntakeCmd.schedule();
                        }
                    }
                }
            }
            
            if(nowIntakeCmd!=null) if(nowIntakeCmd.getEndTime() < time) nowIntakeCmd.cancel();
        }

        if(liftCmds!=null){
            if(time <= liftCmds[0].getEndTime()){
                if(nowLiftCmd!=liftCmds[0]){
                    if(nowLiftCmd!=null) nowLiftCmd.getCommand().cancel();
                    nowLiftCmd = liftCmds[0];
                    nowLiftCmd.schedule();
                }
            }
            else{
                for(int i=1;i<liftCmds.length;i++){
                    if(time <= liftCmds[i].getEndTime() && time > liftCmds[i-1].getEndTime()){
                        if(nowLiftCmd!=liftCmds[i]){
                            if(nowLiftCmd!=null) nowLiftCmd.cancel();
                            nowLiftCmd = liftCmds[i];
                            nowLiftCmd.schedule();
                        }
                    }
                }
            }
            
            if(nowLiftCmd!=null) if(nowLiftCmd.getEndTime() < time) nowLiftCmd.cancel();
        }

        if(pcmCmds!=null){
            if(time <= pcmCmds[0].getEndTime()){
                if(nowPcmCmd!=pcmCmds[0]){
                    if(nowPcmCmd!=null) nowPcmCmd.cancel();
                    nowPcmCmd = pcmCmds[0];
                    nowPcmCmd.schedule();
                }
            }
            else{
                for(int i=1;i<pcmCmds.length;i++){
                    if(time <= pcmCmds[i].getEndTime() && time > pcmCmds[i-1].getEndTime()){
                        if(nowPcmCmd!=pcmCmds[i]){
                            if(nowPcmCmd!=null) nowPcmCmd.cancel();
                            nowPcmCmd = pcmCmds[i];
                            nowPcmCmd.schedule();
                        }
                    }
                }
            }
            
            if(nowPcmCmd!=null) if(nowPcmCmd.getEndTime() < time) nowPcmCmd.cancel();
        }

        if(turrentCmds!=null){
            if(time <= turrentCmds[0].getEndTime()){
                if(nowTurrentCmd!=turrentCmds[0]){
                    if(nowTurrentCmd!=null) nowTurrentCmd.cancel();
                    nowTurrentCmd = turrentCmds[0];
                    nowTurrentCmd.schedule();
                }
            }
            else{
                for(int i=1;i<turrentCmds.length;i++){
                    if(time <= turrentCmds[i].getEndTime() && time > turrentCmds[i-1].getEndTime()){
                        if(nowTurrentCmd!=turrentCmds[i]){
                            if(nowTurrentCmd!=null) nowTurrentCmd.cancel();
                            nowTurrentCmd = turrentCmds[i];
                            nowTurrentCmd.schedule();
                        }
                    }
                }
            }
            
            if(nowTurrentCmd!=null) if(nowTurrentCmd.getEndTime() < time) nowTurrentCmd.cancel();
        }
    }
    
    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
      return false;
    }
}
