package JSONPayloads;

// import the jackson annotation properties defined for JSON type reference and manipulation
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  @author     Nathaniel Merck
 *  @date       2.18.24
 *  @desc       This class is used for defining the structure for enrollment records.
 *              This is what we are expecting in the retrieval of the JSON payload. 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnrollmentRecord {
    // these properties match the JSON data that we are expecting to retrieve
    @JsonProperty("employer")
    private int employer;
    
    @JsonProperty("employee")
    private String employee;
    
    @JsonProperty("product")
    private String product;
    
    @JsonProperty("is_enrolled")
    private boolean isEnrolled;
    
    @JsonProperty("spouses_enrolled")
    private int spousesEnrolled;
    
    @JsonProperty("children_enrolled")
    private int childrenEnrolled;

    // empty constructor
    public EnrollmentRecord() {}

    // getters
    public int getEmployer() {
        return employer;
    }
    public String getEmployee() {
        return employee;
    }
    public String getProduct() {
        return product;
    }
    public boolean getIsEnrolled() {
        return isEnrolled;
    }
    public int getSpousesEnrolled() {
        return spousesEnrolled;
    }
    public int getChildrenEnrolled() {
        return childrenEnrolled;
    }
    
    // setters
    public void setEmployer(int employer) {
        this.employer = employer;
    }
    public void setEmployee(String employee) {
        this.employee = employee;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public void setIsEnrolled(boolean isEnrolled) {
        this.isEnrolled = isEnrolled;
    }
    public void setSpousesEnrolled(int spousesEnrolled) {
        this.spousesEnrolled = spousesEnrolled;
    }
    public void setChildrenEnrolled(int childrenEnrolled) {
        this.childrenEnrolled = childrenEnrolled;
    }
}

