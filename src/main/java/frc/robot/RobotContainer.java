package frc.robot;

import frc.robot.Constants.OIConstants; // Import Consts
// Import subsystems
import frc.robot.subsystems.DriveSystem; 
import frc.robot.subsystems.LiftSystem;
import frc.robot.subsystems.PneumaticSystem;
import frc.robot.subsystems.intakeSystem;
import frc.robot.subsystems.upAndShootSystem;

import frc.robot.commands.TankDrive;
import frc.robot.commands.GoStraight;
import frc.robot.commands.Intakecomm;
import frc.robot.commands.LiftComm;
import frc.robot.commands.PCM_Conctrl;
import frc.robot.commands.UpAndShootComm;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.Joystick;

public class RobotContainer {
        private final DriveSystem driveSubsystem = new DriveSystem();
        private final upAndShootSystem m_upAndShootSystem = new upAndShootSystem();
        private final PneumaticSystem m_PneumaticSystem = new PneumaticSystem(); // 气动系统
        private final intakeSystem m_IntakeSystem = new intakeSystem();
        private final LiftSystem m_LiftSystem = new LiftSystem();
        private final Joystick joystick1 = new Joystick(OIConstants.Drive_stickPort);
        private final Joystick joystick2 = new Joystick(OIConstants.Operate_stickPort);

        public RobotContainer() {
                driveSubsystem.setDefaultCommand(new TankDrive(driveSubsystem, // 定义手柄
                                () -> -joystick1.getRawAxis(1),
                                () -> -joystick1.getRawAxis(5)));

                m_IntakeSystem.setDefaultCommand(new Intakecomm(m_IntakeSystem,
                                () -> joystick2.getRawButton(8),
                                () -> joystick2.getRawButton(5)));

                m_upAndShootSystem.setDefaultCommand(new UpAndShootComm(m_upAndShootSystem,
                                () -> joystick2.getRawButton(7),
                                () -> joystick2.getRawButton(6),
                                () -> joystick2.getRawButton(2),
                                () -> joystick2.getRawButton(4)));

                m_PneumaticSystem.setDefaultCommand(new PCM_Conctrl(m_PneumaticSystem,
                                () -> joystick1.getRawButton(8)));

                m_LiftSystem.setDefaultCommand(new LiftComm(m_LiftSystem,
                                () -> joystick1.getRawAxis(2),
                                () -> joystick1.getRawAxis(3),
                                () -> joystick1.getRawButton(2)));
        }

        public Command getAutonomousCommand() {
                return new SequentialCommandGroup(
                                new GoStraight(driveSubsystem, 0.1)
                // new AutoBackwardCmd(driveSubsystem, -10.0)
                );
        }

}
