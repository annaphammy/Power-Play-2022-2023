package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;




@Disabled
@Autonomous (name= "Templete")
public class Templete  extends LinearOpMode {


                                 /*     Base    hi osuna           */
                                   public DcMotor frontLeft;
                                   public DcMotor frontRight;
                                   public DcMotor backLeft;
                                   public DcMotor backRight;

                           /*        Mechanism                  */

    // Clawscade = 2 servos  , cascade = motor, hi osuna
                          public  DcMotor casCade;
                          public  Servo claw;


                                     //Examples
       /*
        public DcMotor carousel;
        public DcMotor bucketArm;
        public DcMotor drawerSlide;
        public DcMotor intake;
        */
        /* Declare OpMode members. */
        private ElapsedTime runtime = new ElapsedTime();

        // Math for Drive Train Encoders
        static final double COUNTS_PER_MOTOR_REV = 537.7;
        static final double WHEEL_DIAMETER_INCHES = 3.77953;
        static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * 3.1415);
        static final double DRIVE_SPEED = 0.5;
        static final double TURN_SPEED = 0.6;


static final double casCade_COUNTS_PER_MOTOR_REV = 537.7;
    static final double casCade_DIAMETER_INCHES = 3.77953;
    static final double casCade_COUNTS_PER_INCH = (casCade_COUNTS_PER_MOTOR_REV) / (casCade_DIAMETER_INCHES * 3.1415);


    /* hi osuna, Rest in Peace Dump Truck <3

        // Math for the Drawer Slide/Pulley Encoder
        static final double PULLEY_COUNTS_PER_MOTOR_REV = 384.5;
        static final double SPOOL_DIAMETER_INCHES = 1;
        static final double SPOOL_COUNTS_PER_INCH = (PULLEY_COUNTS_PER_MOTOR_REV) / (SPOOL_DIAMETER_INCHES * 3.1415);
        static final double SPOOL_SPEED = .5;

        // Math for Bucket Encoder
        static final double BUCKET_COUNTS_PER_MOTOR_REV = 537.7;
        static final double GEAR_DIAMETER_INCHES = 1.468504;
        static final double BUCKET_COUNTS_PER_INCH = (BUCKET_COUNTS_PER_MOTOR_REV) / (GEAR_DIAMETER_INCHES * 3.1415);
        static final double BUCKET_SPEED = .75;

        static final double CarouselCW = .675;
        static final double CarouselCCW = -.675;
*/
        @Override
        public void runOpMode() {
            frontLeft = hardwareMap.dcMotor.get("frontLeft");
            frontRight = hardwareMap.dcMotor.get("frontRight");
            backLeft = hardwareMap.dcMotor.get("backLeft");
            backRight = hardwareMap.dcMotor.get("backRight");

            casCade = hardwareMap.dcMotor.get("casCade");
            claw = hardwareMap.servo.get("claw");
           /*
            carousel = hardwareMap.dcMotor.get("carousel");
            bucketArm = hardwareMap.dcMotor.get("bucketArm");
            intake = hardwareMap.dcMotor.get("intake");
            drawerSlide = hardwareMap.dcMotor.get("drawerSlide");


            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            drawerSlide.setDirection(DcMotorSimple.Direction.REVERSE);
            bucketArm.setDirection(DcMotorSimple.Direction.REVERSE);

*/
            // Send telemetry message to signify robot waiting;
            telemetry.addData("Status", "Resetting Encoders");
            telemetry.update();

            casCade.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
           /*
            drawerSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            bucketArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
*/

            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            /*
            drawerSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bucketArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

             */
            /*
             * For some reason the encoders are not working correctly, specifically for the drive train.
             * The solution I found is to reset the encoders after each time the robot moves.
             * So I made a function called: resetDriveTrainEncoders();
             * For example:
             *
             *      //Forward
             *      encoderDrive(DRIVE_SPEED,24, 24, 5.0);
             *      Pause(200);
             *          resetDriveTrainEncoders();
             *
             *      //Backward
             *      encoderDrive(DRIVE_SPEED, 24, 24, 5.0);
             *      Pause(200);
             *          resetDriveTrainEncoders();
             *
             *      //Forward
             *      encoderDrive(DRIVE_SPEED,24, 24, 5.0);
             *      Pause(200);
             *
             * I'm pretty sure you got the jist of it now.
             * It's a lot more complicated and mess but it works.
             */
            waitForStart();
         clawopen(2,2);
         resetDriveTrainEncoders();




            telemetry.addData("Path", "Complete");
            telemetry.update();
        }


        public void encoderDrive(double speed,
                                 double leftInches, double rightInches,
                                 double timeoutS) {
            int newLeftTarget;
            int newRightTarget;

            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                newLeftTarget = frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
                newLeftTarget = backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
                newRightTarget = frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
                newRightTarget = backRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

                frontLeft.setTargetPosition(newLeftTarget);
                backLeft.setTargetPosition(newLeftTarget);
                frontRight.setTargetPosition(newRightTarget);
                backRight.setTargetPosition(newRightTarget);

                // Turn On RUN_TO_POSITION
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // reset the timeout time and start motion.
                runtime.reset();
                frontLeft.setPower(Math.abs(speed));
                backLeft.setPower(Math.abs(speed));
                frontRight.setPower(Math.abs(speed));
                backRight.setPower(Math.abs(speed));

                while (opModeIsActive() && (runtime.seconds() < timeoutS) && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
                    // Display it for the driver.
                    telemetry.addData("Path1", "Running to %7d :%7d");
                    telemetry.addData("Path2", "Running at %7d :%7d",
                            frontLeft.getCurrentPosition(),
                            backLeft.getCurrentPosition());
                    frontRight.getCurrentPosition();
                    backRight.getCurrentPosition();
                    telemetry.update();
                }

                // Stop all motion;
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);

                // Turn off RUN_TO_POSITION
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }

        }

/*
    public void encoderclawopen (double speed, double Inches, double timeoutS) {
        int newSlideTarget;

        if (opModeIsActive()) {
            newSlideTarget = claw.getCurrentPosition() + (int) (Inches * SPOOL_COUNTS_PER_INCH);
            claw.setTargetPosition(newSlideTarget);
            telemetry.addData("TargetPos", "Running to %7d",
                    newSlideTarget);
            telemetry.update();

            claw.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            claw.setPower(Math.abs(speed));


            while (opModeIsActive() && (runtime.seconds() < timeoutS) && claw.isBusy()) {
                telemetry.addData("Path1", "Running to %7d",
                        claw.getCurrentPosition());
                telemetry.addData("TargetPos", "Running to %7d",
                        newSlideTarget);
                telemetry.update();
            }

            telemetry.addData("egg", "si");
            claw.setPower(0);
            telemetry.update();

            claw.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

 */
        public void encoderTurnRight(double speed,
                                     double leftInches, double rightInches,
                                     double timeoutS) {
            int newLeftTarget;
            int newRightTarget;

            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                newLeftTarget = frontLeft.getCurrentPosition() + (int) (-leftInches * COUNTS_PER_INCH);
                newLeftTarget = backLeft.getCurrentPosition() + (int) (-leftInches * COUNTS_PER_INCH);
                newRightTarget = frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
                newRightTarget = backRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

                frontLeft.setTargetPosition(newLeftTarget);
                backLeft.setTargetPosition(newLeftTarget);
                frontRight.setTargetPosition(newRightTarget);
                backRight.setTargetPosition(newRightTarget);

                // Turn On RUN_TO_POSITION
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // reset the timeout time and start motion.
                runtime.reset();
                frontLeft.setPower(speed);
                backLeft.setPower(speed);
                frontRight.setPower(speed);
                backRight.setPower(speed);

                while (opModeIsActive() && (runtime.seconds() < timeoutS) && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
                    // Display it for the driver.
                    telemetry.addData("Path1", "Running to %7d :%7d");
                    telemetry.addData("Path2", "Running at %7d :%7d",
                            frontLeft.getCurrentPosition(),
                            backLeft.getCurrentPosition());
                    frontRight.getCurrentPosition();
                    backRight.getCurrentPosition();
                    telemetry.update();
                }

                // Stop all motion;
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);

                // Turn off RUN_TO_POSITION
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }
        }

        public void encoderTurnLeft(double speed,
                                    double leftInches, double rightInches,
                                    double timeoutS) {
            int newLeftTarget;
            int newRightTarget;

            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                newLeftTarget = frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
                newLeftTarget = backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
                newRightTarget = frontRight.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);
                newRightTarget = backRight.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);

                frontLeft.setTargetPosition(newLeftTarget);
                backLeft.setTargetPosition(newLeftTarget);
                frontRight.setTargetPosition(newRightTarget);
                backRight.setTargetPosition(newRightTarget);

                // Turn On RUN_TO_POSITION
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // reset the timeout time and start motion.
                runtime.reset();
                frontLeft.setPower(speed);
                backLeft.setPower(speed);
                frontRight.setPower(speed);
                backRight.setPower(speed);

                while (opModeIsActive() && (runtime.seconds() < timeoutS) && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
                    // Display it for the driver.
                    telemetry.addData("Path1", "Running to %7d :%7d");
                    telemetry.addData("Path2", "Running at %7d :%7d",
                            frontLeft.getCurrentPosition(),
                            backLeft.getCurrentPosition());
                    frontRight.getCurrentPosition();
                    backRight.getCurrentPosition();
                    telemetry.update();
                }

                // Stop all motion;
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);

                // Turn off RUN_TO_POSITION
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }
        }

        public void encoderStrafeRight(double speed,
                                       double leftInches, double rightInches,
                                       double timeoutS) {
            int posLeftTarget;
            int posRightTarget;
            int negLeftTarget;
            int negRightTarget;

            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                posLeftTarget = frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
                negLeftTarget = backLeft.getCurrentPosition() + (int) (-leftInches * COUNTS_PER_INCH);
                negRightTarget = frontRight.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);
                posRightTarget = backRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

                frontLeft.setTargetPosition(posLeftTarget);
                backLeft.setTargetPosition(negLeftTarget);
                frontRight.setTargetPosition(negRightTarget);
                backRight.setTargetPosition(posRightTarget);

                // Turn On RUN_TO_POSITION
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // reset the timeout time and start motion.
                runtime.reset();
                frontLeft.setPower(speed);
                backLeft.setPower(speed);
                frontRight.setPower(speed);
                backRight.setPower(speed);

                while (opModeIsActive() && (runtime.seconds() < timeoutS) && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
                    // Display it for the driver.
                    telemetry.addData("Path1", "Running to %7d :%7d");
                    telemetry.addData("Path2", "Running at %7d :%7d",
                            frontLeft.getCurrentPosition(),
                            backLeft.getCurrentPosition());
                    frontRight.getCurrentPosition();
                    backRight.getCurrentPosition();
                    telemetry.update();
                }

                // Stop all motion;
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);

                // Turn off RUN_TO_POSITION
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }
        }

        public void encoderStrafeLeft(double speed,
                                      double leftInches, double rightInches,
                                      double timeoutS) {
            int posLeftTarget;
            int posRightTarget;
            int negLeftTarget;
            int negRightTarget;

            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                negLeftTarget = frontLeft.getCurrentPosition() + (int) (-leftInches * COUNTS_PER_INCH);
                posLeftTarget = backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
                posRightTarget = frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
                negRightTarget = backRight.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);

                frontLeft.setTargetPosition(negLeftTarget);
                backLeft.setTargetPosition(posLeftTarget);
                frontRight.setTargetPosition(posRightTarget);
                backRight.setTargetPosition(negRightTarget);

                // Turn On RUN_TO_POSITION
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // reset the timeout time and start motion.
                runtime.reset();
                frontLeft.setPower(speed);
                backLeft.setPower(speed);
                frontRight.setPower(speed);
                backRight.setPower(speed);

                while (opModeIsActive() && (runtime.seconds() < timeoutS) && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
                    // Display it for the driver.
                    telemetry.addData("Path1", "Running to %7d :%7d");
                    telemetry.addData("Path2", "Running at %7d :%7d",
                            frontLeft.getCurrentPosition(),
                            backLeft.getCurrentPosition());
                    frontRight.getCurrentPosition();
                    backRight.getCurrentPosition();
                    telemetry.update();
                }

                // Stop all motion;
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);

                // Turn off RUN_TO_POSITION
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }
        }

        public void Pause(int time) {
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
            sleep(time);
        }

        public void resetDriveTrainEncoders() {
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        public void clawopen(double power, int time) {
        claw.setPosition(-power);
        sleep(time);
    }
    public void clawclose(double power, int time) {
        claw.setPosition(power);
        sleep(time);
    }

    public void StrafeLeft(double power, int time) {
            frontLeft.setPower(-power);
            frontRight.setPower(power);
            backLeft.setPower(power);
            backRight.setPower(-power);
            sleep(time);
        }

        public void StrafeRight(double power, int time) {
            frontRight.setPower(power);
            backRight.setPower(-power);
            frontLeft.setPower(-power);
            backLeft.setPower(power);
            sleep(time);
        }

        public void turnLeft(double power, int time) {
            frontRight.setPower(-power);
            backRight.setPower(-power);
            frontLeft.setPower(power);
            backLeft.setPower(power);
            sleep(time);
        }

        public void goBackward(double power, int time) {
            frontRight.setPower(-power);
            backRight.setPower(-power);
            frontLeft.setPower(-power);
            backLeft.setPower(-power);
            sleep(time);

        }

        public void goForward(double power, int time) {
            frontRight.setPower(power);
            backRight.setPower(power);
            frontLeft.setPower(power);
            backLeft.setPower(power);
            sleep(time);

        }

    }


//hi osuna