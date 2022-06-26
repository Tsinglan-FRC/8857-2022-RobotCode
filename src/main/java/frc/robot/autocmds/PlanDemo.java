package frc.robot.autocmds;

import frc.robot.Toolkit.TimedCommandGroup;
import frc.robot.Toolkit.TimedCommandHandle;
import frc.robot.autocmds.PlanDemo_.*;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.LiftSystem;
import frc.robot.subsystems.PneumaticSystem;
import frc.robot.subsystems.TurrentSystem;
import frc.robot.subsystems.intakeSystem;

public class PlanDemo extends TimedCommandHandle{
    private final DriveSystem driveSys;
    private final intakeSystem intakeSys;
    private final LiftSystem liftSys;
    private final PneumaticSystem pcmSys;
    private final TurrentSystem turrentSys;

    public PlanDemo(
        DriveSystem _driveSys,
        intakeSystem _intakeSys,
        LiftSystem _liftSys,
        PneumaticSystem _pcmSys,
        TurrentSystem _turrentSys
    ){
        driveSys = _driveSys;
        intakeSys = _intakeSys;
        liftSys = _liftSys;
        pcmSys = _pcmSys;
        turrentSys = _turrentSys;

        drive = new DriveGroup(driveSys);
        intake = new IntakeGroup(intakeSys);
        lift = new LiftGroup(liftSys);
        pcm = new PneumaticGroup(pcmSys);
        turrent = new TurrentGroup(turrentSys);
    }

    private TimedCommandGroup drive;
    private TimedCommandGroup intake;
    private TimedCommandGroup lift;
    private TimedCommandGroup pcm;
    private TimedCommandGroup turrent;

    @Override
    public TimedCommandGroup getDriveGroup(){return drive;}
    @Override
    public TimedCommandGroup getIntakeGroup(){return intake;}
    @Override
    public TimedCommandGroup getLiftGroup(){return lift;}
    @Override
    public TimedCommandGroup getPneumaticGroup(){return pcm;}
    @Override
    public TimedCommandGroup getTurrentGroup(){return turrent;}
}