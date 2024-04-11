package Exceptions;

public class RentalPeriodException extends RuntimeException {
    public RentalPeriodException(int rentalPeriod){
        super("The discount must be a whole number greater than 0. "+rentalPeriod+" is out of range");
    }
}
