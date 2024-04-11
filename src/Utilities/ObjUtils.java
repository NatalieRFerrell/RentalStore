package Utilities;

/**
 * convenience class I prefer to have to help standardize certain tasks across the program.
 * Typically populated with things like null guards, empty string checks, comparators, and exception handling
 * any often repeated check or non-business logic function that can occupy multiple lines or add complexity
 */
public class ObjUtils {
    public static boolean isStringNullorEmpty(String s){
        return s == null || s.isEmpty();
    }
    public static boolean areObjectsEqual(Object one, Object two){
        boolean areEqual = one == two;
        if(!areEqual){
            if(one != null && two != null){
                areEqual = one.equals(two);
            }
        }

        return areEqual;
    }
}
