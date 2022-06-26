package frc.robot.autocmds.PlanDemo_;

import frc.robot.Toolkit.TimedCommand;
import frc.robot.Toolkit.TimedCommandGroup;
import frc.robot.autocmds.PlanDemo_.PneumaticCmd.*;
import frc.robot.subsystems.PneumaticSystem;

public class PneumaticGroup extends TimedCommandGroup{
    private final PneumaticSystem sys;
    public PneumaticGroup(PneumaticSystem _sys){
        sys = _sys;

        TimedCommand[] tc = {
            new TimedCommand(15,new Command0(sys))
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