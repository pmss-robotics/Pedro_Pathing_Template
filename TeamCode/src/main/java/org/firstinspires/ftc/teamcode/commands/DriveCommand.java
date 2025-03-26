package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.MathFunctions;
import com.pedropathing.util.PIDFController;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

/**
 * The default command of {@link DriveSubsystem}
 */
@Config
public class DriveCommand extends CommandBase {
    private final DriveSubsystem drive;
    private final DoubleSupplier lx, ly, rx;
    private final boolean isFieldCentric;

    public DriveCommand(DriveSubsystem drive, DoubleSupplier lx, DoubleSupplier ly, DoubleSupplier rx, boolean isFieldCentric) {
        this.drive = drive;
        this.lx = lx;
        this.ly = ly;
        this.rx = rx;
        this.isFieldCentric = isFieldCentric;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.follower.startTeleopDrive();
    }
    @Override
    public void execute() {
        drive.follower.setTeleOpMovementVectors(ly.getAsDouble(), lx.getAsDouble(), rx.getAsDouble(), !isFieldCentric);
    }
}
