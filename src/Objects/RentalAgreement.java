package Objects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Date;
import java.nio.file.Paths;

import Constants.ToolRegistry;
import Utilities.StoreUtils;

public class RentalAgreement {
    //specified values
    private Tool tool;
    private Date checkoutDate;
    private Integer rentalTerm;
    private Integer discount;

    //derived values
    private Date dueDate;
    private Integer chargeDays;
    private Double subTotal;
    private Double discountAmount;
    private Double finalTotal;

    //args array for printout, see toString method
    private final Object[] args;

    public RentalAgreement(Tool tool, Date checkoutDate, int rentalTerm, int discount){
        this.tool = tool;
        this.checkoutDate = checkoutDate;
        this.rentalTerm = rentalTerm;
        this.discount = discount;
        setDerivedValues();

        //args doesn't need to be reset when resetting values using setters because it depends only on references
        this.args = new Object[]{
                this.tool.getToolCode(),
                this.tool.getToolType(),
                this.tool.getBrand(),
                this.rentalTerm,
                this.checkoutDate,
                dueDate,
                ToolRegistry.getToolRentalRate(this.tool.getToolType()),
                chargeDays,
                subTotal,
                this.discount,
                discountAmount,
                finalTotal
                };
    }
    private void setDerivedValues(){
        this.dueDate = StoreUtils.getDueDate(checkoutDate, rentalTerm);
        this.chargeDays = StoreUtils.getChargeDays(tool.getToolType(), checkoutDate, rentalTerm);
        this.subTotal = ToolRegistry.getToolRentalRate(tool.getToolType()) * chargeDays;
        //the discount is represented by a whole number, but adjustment is done after the rounding is to nearest cent
        this.discountAmount = (double)Math.round(subTotal * discount)/100;
        this.finalTotal = subTotal - discountAmount;
    };

    @Override
    public String toString(){
        String printout = "Something went wrong in template file read";
        try {
            Path path = Paths.get(System.getProperty("user.dir"), "src", "Resources", "RentalAgreementPrintoutTemplate.txt");
            printout = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return MessageFormat.format(printout, this.args);
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
        setDerivedValues();
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
        setDerivedValues();
    }

    public int getRentalTerm() {
        return rentalTerm;
    }

    public void setRentalTerm(int rentalTerm) {
        this.rentalTerm = rentalTerm;
        setDerivedValues();
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
        setDerivedValues();
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalTotal() {
        return finalTotal;
    }
}
