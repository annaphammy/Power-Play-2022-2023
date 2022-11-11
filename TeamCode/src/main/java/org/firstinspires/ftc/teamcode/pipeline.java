package org.firstinspires.ftc.teamcode;

import androidx.annotation.VisibleForTesting;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.o
import org.opencv.core.Scalar;


@Disabled
public class pipeline extends OpenCvPipeline {
    public enum GreenImageParking{

        LEFT,
        CENTER,
        RIGHT

    }

    public final Scalar BLUE = new Scalar(0, 0, 255);
    public final Scalar GREEN = new Scalar(0, 255, 0);

}