package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.PathCommand;
import org.firstinspires.ftc.teamcode.drive.constants.FConstants;
import org.firstinspires.ftc.teamcode.drive.constants.LConstants;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

@Config
@Autonomous(name="SAMPLE Auto", group="Auto")
public class Auto extends CommandOpMode {
    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
        telemetry.log().setCapacity(8);

        DriveSubsystem drive = new DriveSubsystem(new Follower(hardwareMap, FConstants.class, LConstants.class), new Pose(), telemetry);

        // set with your own target poses and headings.

        Pose start = new Pose();
        Pose end = new Pose();
        PathChain path1 = drive.pathBuilder()
                .addBezierLine(new Point(start), new Point(end))
                .setLinearHeadingInterpolation(start.getHeading(), end.getHeading())
                .build();

        SequentialCommandGroup routine = new SequentialCommandGroup(
            new PathCommand(drive, path1)
        );
        schedule(routine);
    }
}
