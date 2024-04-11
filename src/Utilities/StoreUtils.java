package Utilities;

import Constants.ToolCode;
import Constants.ToolRegistry;
import Constants.ToolType;
import Objects.Tool;
import java.util.Calendar;
import java.util.Date;

public class StoreUtils {

    public static Tool getToolFromCode(ToolCode code){
        return new Tool(code, ToolRegistry.getToolType(code), ToolRegistry.getToolBrand(code));
    }

    /**
     * @param checkout date of checkout
     * @param rentalTerm number of days to be checked out
     * @return the corresponding dueDate for the tool to be returned
     */
    public static Date getDueDate(Date checkout, int rentalTerm){
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkout);
        cal.add(Calendar.DAY_OF_YEAR, rentalTerm);
        return cal.getTime();
    }

    /**
     * Currently only used in private scope, but there is no need to restrict pure utility functions
     * @param checkout Date of checkout
     * @param rentalTerm number of days to be checked out
     * @return array containing the number of [weekdays, weekends, holidays]
     */
    public static int[] typesOfDaysInTerm(Date checkout, int rentalTerm){
        int[] days = new int[]{0, 0, 0};
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkout);
        for(int i = 0;i < rentalTerm;i++){
            //advance calendar one day
            cal.roll(Calendar.DAY_OF_YEAR, 1);
            //easiest check first, weekends can never be holidays
            if(cal.get(Calendar.DAY_OF_WEEK) == (Calendar.SATURDAY)
                || cal.get(Calendar.DAY_OF_WEEK) == (Calendar.SUNDAY)){
                days[1]++;
            }else{
                //weekdays need to be checked for holidays
                if(isHoliday(cal)){
                    days[2]++;
                }else{
                    days[0]++;
                }
            }

        }
        return days;
    }

    private static boolean isHoliday(Calendar cal){
        boolean isHoliday = false;
        //check for labor day, first Monday in Septemeber
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
            &&  cal.get(Calendar.MONTH) == Calendar.SEPTEMBER
            && cal.get(Calendar.DAY_OF_MONTH) <= 7){
            isHoliday = true;
        }
        //check for 4th of July, then 3rd (if Friday) and 5th (if Monday)
        //this 4th of July check depends on this function not being called for weekends.
        if(cal.get(Calendar.MONTH) == Calendar.JULY
                && cal.get(Calendar.DAY_OF_MONTH) == 4){
            isHoliday = true;
        }
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
                &&  cal.get(Calendar.MONTH) == Calendar.JULY
                && cal.get(Calendar.DAY_OF_MONTH) == 3){
            isHoliday = true;

        }
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                &&  cal.get(Calendar.MONTH) == Calendar.JULY
                && cal.get(Calendar.DAY_OF_MONTH) == 5){
            isHoliday = true;

        }
        return isHoliday;
    }

    /**
     * Provides the number of days during the rental term that are billable
     * @param type ToolType to be looked up against
     * @param checkout Date of checkout
     * @param rentalTerm number of days to be checked out
     * @return number of billable days
     */
    public static int getChargeDays(ToolType type, Date checkout, int rentalTerm){
        int sum = 0;
        int[] billableDays = ToolRegistry.getToolBillingDays(type);
        int[] dayTypes = typesOfDaysInTerm(checkout, rentalTerm);
        for(int i = 0;i<dayTypes.length;i++){
            sum += dayTypes[i] * billableDays[i];
        }
        return sum;
    }
}
