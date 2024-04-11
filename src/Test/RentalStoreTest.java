package Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Constants.ToolCode;
import Exceptions.DiscountRangeException;
import Exceptions.RentalPeriodException;
import Objects.Register;
import Objects.RentalAgreement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class RentalStoreTests {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

    @Test
    @DisplayName("Test for rental time exception")
    void rentalRangeExceptionTest() throws ParseException {
        assertThrows(RentalPeriodException.class, () -> Register.checkout(
                ToolCode.JAKR, dateFormat.parse("09/03/15"), -5, 0));
    }

    @Test
    @DisplayName("Test for negative discount")
    void discountRangeExceptionTest() throws ParseException {
        assertThrows(DiscountRangeException.class, () -> Register.checkout(
                ToolCode.JAKR, dateFormat.parse("09/03/15"), 5, -1));
    }

    @Test
    @DisplayName("Provided test data set 1")
    void providedTest1() throws ParseException {
        assertThrows(DiscountRangeException.class, () -> Register.checkout(
                ToolCode.JAKR, dateFormat.parse("09/03/15"), 5, 101));
    }

    @Test
    @DisplayName("Provided test data set 2")
    void providedTest2() throws ParseException, IOException {

        Path path = Paths.get(System.getProperty("user.dir"), "src", "Resources", "JUnitTestResult2.txt");
        String actual = Files.readString(path);

        RentalAgreement agreement = Register.checkout(
                ToolCode.LADW, dateFormat.parse("07/02/20"), 3, 10);
        assertEquals(agreement.toString(), actual);
    }

    @Test
    @DisplayName("Provided test data set 3")
    void providedTest3() throws ParseException, IOException {

        Path path = Paths.get(System.getProperty("user.dir"), "src", "Resources", "JUnitTestResult3.txt");
        String actual = Files.readString(path);

        RentalAgreement agreement = Register.checkout(
                ToolCode.CHNS, dateFormat.parse("07/02/15"), 5, 25);
        assertEquals(agreement.toString(), actual);
    }

    @Test
    @DisplayName("Provided test data set 4")
    void providedTest4() throws ParseException, IOException {

        Path path = Paths.get(System.getProperty("user.dir"), "src", "Resources", "JUnitTestResult4.txt");
        String actual = Files.readString(path);

        RentalAgreement agreement = Register.checkout(
                ToolCode.JAKD, dateFormat.parse("09/03/15"), 6, 0);
        assertEquals(agreement.toString(), actual);
    }

    @Test
    @DisplayName("Provided test data set 5")
    void providedTest5() throws ParseException, IOException {

        Path path = Paths.get(System.getProperty("user.dir"), "src", "Resources", "JUnitTestResult5.txt");
        String actual = Files.readString(path);

        RentalAgreement agreement = Register.checkout(
                ToolCode.JAKR, dateFormat.parse("07/02/15"), 9, 0);
        assertEquals(agreement.toString(), actual);
    }

    @Test
    @DisplayName("Provided test data set 6")
    void providedTest6() throws ParseException, IOException {

        Path path = Paths.get(System.getProperty("user.dir"), "src", "Resources", "JUnitTestResult6.txt");
        String actual = Files.readString(path);

        RentalAgreement agreement = Register.checkout(
                ToolCode.JAKR, dateFormat.parse("07/02/20"), 4, 50);
        assertEquals(agreement.toString(), actual);
    }

    @Test
    @DisplayName("Test Holiday cases for July 4th")
    void testHolidayCase4th() throws ParseException, IOException {

        Path path = Paths.get(System.getProperty("user.dir"), "src", "Resources", "JUnitTestResult7.txt");
        String actual = Files.readString(path);

        RentalAgreement agreement = Register.checkout(
                ToolCode.JAKR, dateFormat.parse("07/02/16"), 4, 0);
        assertEquals(agreement.toString(), actual);
    }

    @Test
    @DisplayName("Test Holiday cases for July 5th")
    void testHolidayCase5th() throws ParseException, IOException {

        Path path = Paths.get(System.getProperty("user.dir"), "src", "Resources", "JUnitTestResult8.txt");
        String actual = Files.readString(path);

        RentalAgreement agreement = Register.checkout(
                ToolCode.JAKR, dateFormat.parse("07/02/21"), 4, 0);
        assertEquals(agreement.toString(), actual);
    }


}