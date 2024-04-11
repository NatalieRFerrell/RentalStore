package Objects;

import Constants.ToolCode;
import Exceptions.DiscountRangeException;
import Exceptions.RentalPeriodException;
import Utilities.StoreUtils;

import java.util.Date;

/**
 * Class to handle the checking out of tools. Externalized to its own class rather than just being in Main
 * for compartmentalization of the RentalStore into a hypothetical larger whole
 */
public class Register {
    public static RentalAgreement checkout(ToolCode tool, Date checkout, int rentalTerm, int discount)
            throws DiscountRangeException, RentalPeriodException {
        if(rentalTerm < 1){
            throw new RentalPeriodException(rentalTerm);
        }
        if(discount < 0 || discount > 100){
            throw new DiscountRangeException(discount);
        }
        return new RentalAgreement(StoreUtils.getToolFromCode(tool), checkout, rentalTerm, discount);
    }
}
