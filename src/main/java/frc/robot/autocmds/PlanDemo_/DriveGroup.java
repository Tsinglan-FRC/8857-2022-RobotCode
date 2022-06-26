package frc.robot.autocmds.PlanDemo_;

import frc.robot.Toolkit.TimedCommand;
import frc.robot.Toolkit.TimedCommandGroup;

import frc.robot.autocmds.PlanDemo_.DriveCmd.*;
import frc.robot.subsystems.DriveSystem;

public class DriveGroup extends TimedCommandGroup{
    private final DriveSystem sys;
    public DriveGroup(DriveSystem _sys){
        sys = _sys;
        
        TimedCommand[] tc = {
            new TimedCommand(2, new Command0(sys)),
            new TimedCommand(3,new Command1(sys))
        };

        timedCommands = tc;
    }


    TimedCommand[] timedCommands = null;

    @Override
    public TimedCommand[] getCommands(){
        return timedCommands;
    }

    @Override
    public TimedCommand getCommand(int index){
        return timedCommands[index];
    }

    @Override
    public int getLength(){
        return timedCommands.length;
    }
}