import java.util.Scanner;

/* Product - Motor */
abstract class Motor {

    String model;
    long power;

    public Motor(String model, long power) {
        this.model = model;
        this.power = power;
    }

    @Override
    public String toString() {
        return "motor={model:" + model + ",power:" + power + "}";
    }
}

class PneumaticMotor extends Motor {
    PneumaticMotor(String model, long motor) {
        super(model, motor);
        System.out.println(toString());
    }
}

class HydraulicMotor extends Motor {
    HydraulicMotor(String model, long motor) {
        super(model, motor);
        System.out.println(toString());
    }
}

class ElectricMotor extends Motor {
    ElectricMotor(String model, long motor) {
        super(model, motor);
        System.out.println(toString());
    }
}

class WarpDrive extends Motor {
    WarpDrive(String model, long motor) {
        super(model, motor);
        System.out.println(toString());
    }
}

class MotorFactory {

    /**
     * It returns an initialized motor according to the specified type by the first character:
     * 'P' or 'p' - pneumatic, 'H' or 'h' - hydraulic, 'E' or 'e' - electric, 'W' or 'w' - warp.
     */
    public static Motor make(char type, String model, long power) {
        if (type == 'P' || type == 'p') {
            System.out.print("Pneumatic ");
            return new PneumaticMotor(model, power);
        } else if (type == 'H' || type == 'h') {
            System.out.print("Hydraulic ");
            return new HydraulicMotor(model, power);
        } else if (type == 'E' || type == 'e') {
            System.out.print("Electric ");
            return new ElectricMotor(model, power);
        } else if (type == 'W' || type == 'w') {
            System.out.print("Warp ");
            return new WarpDrive(model, power);
        } else return null;
    }
}

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char type = scanner.next().charAt(0);     
        final String model = scanner.next();
        final long power = scanner.nextLong();
        MotorFactory motor = new MotorFactory();
        motor.make(type, model, power);

        scanner.close();
    }
}