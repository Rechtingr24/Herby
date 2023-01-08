package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.MecanumDriveSubsystem;
import frc.robot.Constants.DriveConstants;

public class MecanumDriveCommand extends CommandBase {
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final CommandXboxController controller;
    boolean triangle2 = false;

    public MecanumDriveCommand(MecanumDriveSubsystem mecanumDriveSubsystem, CommandXboxController controller) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.controller = controller;

        addRequirements(this.mecanumDriveSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double rightStickX = -controller.getRightX();
        double leftStickY = controller.getLeftY();
        double leftStickX = -controller.getLeftX();
        double rightTrig = controller.getRightTriggerAxis();
        double leftTrig = controller.getLeftTriggerAxis();

        double rawRot = rightTrig-leftTrig;

        boolean triangle = controller.y().getAsBoolean();

        double desiredForwardSpeed = leftStickY * DriveConstants.MAX_ROTATION_MS;
        double desiredSideSpeed = 0;
        double desiredRotationSpeed = rightStickX * DriveConstants.RADIAN_ROTATION;

        if (Math.abs(leftStickX) >= .4) {
            desiredSideSpeed = leftStickX * DriveConstants.MAX_ROTATION_MS;
            desiredForwardSpeed = 0;
        }

        if (triangle) {
            triangle2 = true;
        }

        if (!triangle && triangle2) {
            desiredSideSpeed = 0;
            desiredForwardSpeed = leftStickY * DriveConstants.MAX_ROTATION_MS;
        }

        mecanumDriveSubsystem.chasisToWheelSpeeds(desiredForwardSpeed, desiredSideSpeed, desiredRotationSpeed);

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run
        // execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.chasisToWheelSpeeds(0, 0, 0);
    }
}
