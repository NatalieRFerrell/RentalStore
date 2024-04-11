import Constants.ToolCode;
import Objects.Register;
import Objects.RentalAgreement;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        RentalAgreement agreement = Register.checkout(ToolCode.LADW, new Date(), 3, 10);
        System.out.println(agreement);

    }
}