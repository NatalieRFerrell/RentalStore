package Constants;

import java.util.HashMap;
import java.util.Map;

import static Constants.ToolType.*;
import static Constants.ToolCode.*;

/*
 * In a more robust system these predefined values would be loaded from an external document.
 * Absent knowledge of intended deployment pattern this is more of a simple placeholder for demo purposes
 */
public class ToolRegistry {
    private static final Map<ToolType, Double> rentalValues = new HashMap<>();
    private static final Map<ToolCode, ToolType> toolCodeToType = new HashMap<>();
    private static final Map<ToolCode, String> toolCodeToBrand = new HashMap<>();
    private static final Map<ToolType, int[]> toolTypetoChareDays = new HashMap<>();

    static{
        rentalValues.put(CHAINSAW, 1.49);
        rentalValues.put(LADDER, 1.99);
        rentalValues.put(JACKHAMMER, 2.99);

        toolCodeToType.put(CHNS, CHAINSAW);
        toolCodeToType.put(LADW, LADDER);
        toolCodeToType.put(JAKD, JACKHAMMER);
        toolCodeToType.put(JAKR, JACKHAMMER);

        toolCodeToBrand.put(CHNS, "Stihl");
        toolCodeToBrand.put(LADW, "Werner");
        toolCodeToBrand.put(JAKD, "DeWalt");
        toolCodeToBrand.put(JAKR, "Ridgid");

        /*
         * array format is [weekdays, weekends, holidays] int array was chosen to streamline math checks.
         * 1 for charge on that day type, 0 for no charge on that day type.
         */
        toolTypetoChareDays.put(CHAINSAW, new int[]{1, 0, 1});
        toolTypetoChareDays.put(LADDER, new int[]{1, 1, 0});
        toolTypetoChareDays.put(JACKHAMMER, new int[]{1, 0, 0});


    }

    public static ToolType getToolType(ToolCode code){
        return toolCodeToType.get(code);
    }

    public static String getToolBrand(ToolCode code){
        return toolCodeToBrand.get(code);
    }

    public static int[] getToolBillingDays(ToolType type){
        return toolTypetoChareDays.get(type);
    }

    public static double getToolRentalRate(ToolType type){
        return rentalValues.get(type);
    }
}
