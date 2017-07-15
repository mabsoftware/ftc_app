package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class SensorPackage {
    private LinearOpMode opMode;
    private ModernRoboticsI2cGyro driveGyro;

    public SensorPackage(LinearOpMode opMode) {
        this.opMode = opMode;
        this.driveGyro =  (ModernRoboticsI2cGyro) this.opMode.hardwareMap.gyroSensor.get("drive_gyro");

        this.driveGyro.calibrate();
        while (this.driveGyro.isCalibrating() && !opMode.isStopRequested()) {
            this.opMode.sleep(50);
            this.opMode.idle();
        }
        this.driveGyro.resetZAxisIntegrator();
    }

    public int getHeading() {
        return this.driveGyro.getHeading();
    }
}
