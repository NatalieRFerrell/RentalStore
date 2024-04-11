package Exceptions;

public class DiscountRangeException extends RuntimeException {
    public DiscountRangeException(int discount){
        super("The discount must be a whole in the inclusive range of 0 to 100. "+discount+" is out of range");
    }
}
