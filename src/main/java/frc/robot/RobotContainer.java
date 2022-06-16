package frc.robot;

// Import subsystems
import frc.robot.subsystems.DriveSystem; 
import frc.robot.subsystems.LiftSystem;
import frc.robot.subsystems.PneumaticSystem;
import frc.robot.subsystems.TurrentSystem;
//import frc.robot.subsystems.VisionSystem;
import frc.robot.subsystems.intakeSystem;
//import frc.robot.subsystems.upAndShootSystem;

import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.I_Will;
import frc.robot.commands.Intakecomm;
import frc.robot.commands.LiftComm;
import frc.robot.commands.PCM_Conctrl;
import frc.robot.commands.TurrentCmd;
//import frc.robot.commands.UpAndShootComm;
//import frc.robot.commands.VisionCmd;
import frc.robot.joysticks.DriveStick;
import frc.robot.joysticks.OperateStick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RobotContainer {
    private final DriveSystem m_driveSubsystem = new DriveSystem();
    //private final upAndShootSystem m_upAndShootSystem = new upAndShootSystem();
    private final PneumaticSystem m_PneumaticSystem = new PneumaticSystem(); // 气动系统
    private final intakeSystem m_IntakeSystem = new intakeSystem();
    private final LiftSystem m_LiftSystem = new LiftSystem();
    //private final VisionSystem m_VisionSystem = new VisionSystem();
    private final TurrentSystem m_TurrentSystem = new TurrentSystem();

    private final DriveStick joystick1 = new DriveStick();
    private final OperateStick joystick2 = new OperateStick();

    public RobotContainer() {
        m_driveSubsystem.setDefaultCommand(new ArcadeDrive(
			m_driveSubsystem, // 定义手柄
	            
			() -> joystick1.getSpeed(),
			() -> joystick1.getTurn(),
			() -> joystick1.isInLowSpeed()
		));

        m_IntakeSystem.setDefaultCommand(new Intakecomm(
            m_IntakeSystem,
                        
        	() -> joystick2.shallPutOut(),
            () -> joystick2.getIntakeStatus()
		));

        /*m_upAndShootSystem.setDefaultCommand(new UpAndShootComm(
			m_upAndShootSystem,
                
			() -> joystick2.shallShootBallForward(),
            () -> joystick2.shallMoveBallUp()
		));*/

        m_PneumaticSystem.setDefaultCommand(new PCM_Conctrl(
			m_PneumaticSystem,
	            
			() -> joystick1.getCompressorStatus()
		));

        m_LiftSystem.setDefaultCommand(new LiftComm(
			m_LiftSystem,
                
			() -> joystick1.getRawAxis(2),
            () -> joystick1.getRawAxis(3),
            () -> joystick1.getRawButton(2)
		));

        /*m_VisionSystem.setDefaultCommand(new VisionCmd(
            m_upAndShootSystem,
            m_VisionSystem,

            () -> joystick2.getHorizontal(),
            () -> joystick2.isFire()       
        ));*/

        m_TurrentSystem.setDefaultCommand(new TurrentCmd(
            m_TurrentSystem, 
            
            () -> joystick2.shallShootBallForward(), 
            () -> joystick2.shallMoveBallUp(), 
            () -> joystick2.getHorizontal(), 
            () -> joystick2.isFire()));
    }

    public Command getAutonomousCommand() {
        return new I_Will(m_driveSubsystem);
    }

	/*public Command getTeleopCommand(){
		return new SequentialCommandGroup(
			m_driveSubsystem.getDefaultCommand(),
			m_IntakeSystem.getDefaultCommand(),
			m_LiftSystem.getDefaultCommand(),
			m_PneumaticSystem.getDefaultCommand(),
			m_TurrentSystem.getDefaultCommand()
		);
	}*/

    /*public Command getTeleopCommand(){
        return new SequentialCommandGroup(
            new TurrentCmd(
                m_TurrentSystem, 
                
                () -> joystick2.shallShootBallForward(), 
                () -> joystick2.shallMoveBallUp(), 
                () -> joystick2.getHorizontal(), 
                () -> joystick2.isFire()
            ),
            new LiftComm(
                m_LiftSystem,
                    
                () -> joystick1.getRawAxis(2),
                () -> joystick1.getRawAxis(3),
                () -> joystick1.getRawButton(2)
            ),
            new PCM_Conctrl(
                m_PneumaticSystem,
                    
                () -> joystick1.getCompressorStatus()
            ),
            new Intakecomm(
                m_IntakeSystem,
                            
                () -> joystick2.shallPutOut(),
                () -> joystick2.getIntakeStatus()
            ),
            new ArcadeDrive(
                m_driveSubsystem, // 定义手柄
                    
                () -> joystick1.getSpeed(),
                () -> joystick1.getTurn(),
                () -> joystick1.isInLowSpeed()
            )
        );
    }*/
}
