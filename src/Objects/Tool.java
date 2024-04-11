package Objects;

import Constants.ToolCode;
import Constants.ToolType;
import Utilities.ObjUtils;

/*
 * I am working on the assumption that we are not working in Java 14+ as most production code is still 11 or earlier.
 * Because of this I am manually creating the Objects.Tool POJO rather than making a Record
 */
public class Tool {

    private ToolCode toolCode;
    private ToolType toolType;
    private String brand;

    public Tool(ToolCode code, ToolType type, String brand){
        this.toolCode = code;
        this.toolType = type;
        this.brand = brand;
    }

    // Setter and Getter methods
    public void setToolCode(ToolCode toolCode) { this.toolCode = toolCode; }
    public ToolCode getToolCode() { return toolCode; }

    public void setToolType(ToolType type) { this.toolType = type; }
    public ToolType getToolType() { return toolType; }

    public void setBrand(String brand) { this.brand = brand; }
    public String getBrand() { return brand; }

    //mimics intended printout pattern for rental agreement
    public String toString(){
        return "Objects.Tool code: "+toolCode+"/nTool type: "+toolType;
    }

    // Overriding hashCode and equals methods as best practice
    @Override public int hashCode()
    {
        int prime = 13, result = 1;
        result = result * prime + brand.hashCode();
        result = result * prime + toolCode.hashCode();
        result = result * prime + toolType.hashCode();

        return result;
    }

    @Override public boolean equals(Object obj)
    {
        //standard quick checks
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Tool other = (Tool)obj;

        return ObjUtils.areObjectsEqual(this.brand, other.getBrand()) &&
                ObjUtils.areObjectsEqual(this.toolCode, other.getToolCode()) &&
                ObjUtils.areObjectsEqual(this.toolType, other.getToolType());
    }

}
