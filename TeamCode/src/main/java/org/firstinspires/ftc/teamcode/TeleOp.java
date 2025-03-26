package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.drive.constants.FConstants;
import org.firstinspires.ftc.teamcode.drive.constants.LConstants;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

@Config
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "SAMPLE TeleOp", group = "TeleOp")
public class TeleOp extends CommandOpMode {

    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
        telemetry.log().setCapacity(8);

        GamepadEx driver1 = new GamepadEx(gamepad1);
        GamepadEx driver2 = new GamepadEx(gamepad2);

        DriveSubsystem drive = new DriveSubsystem(new Follower(hardwareMap, FConstants.class, LConstants.class), new Pose(), telemetry);
        drive.setDefaultCommand(
                new DriveCommand(
                        drive,
                        () -> -driver1.getLeftX(),
                        () -> -driver1.getLeftY(),
                        () -> -driver1.getRightX(),
                        true
                ));

        schedule(new RunCommand(() -> {
            telemetry.update();
        }));
    }
}
