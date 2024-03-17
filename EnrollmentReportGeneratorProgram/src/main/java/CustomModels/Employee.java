package CustomModels;

/**
 *  @author     Nathaniel Merck
 *  @date       2.18.24
 *  @desc       This class is used for defining the structure for employee records.
 */

public class Employee {
    private int employer;
    
    private String name;

    private int totalEnrolled;
    
    private int spousesEnrolled;

    private int childrenEnrolled;

    // constructor needed for Jackson deserialization
    public Employee() {}

    // Constructor with parameters
    public Employee(int employer, 
                    String name, 
                    int totalEnrolled, 
                    int spousesEnrolled, 
                    int childrenEnrolled) {
        this.employer = employer;
        this.name = name;
        this.totalEnrolled = totalEnrolled;
        this.spousesEnrolled = spousesEnrolled;
        this.childrenEnrolled = childrenEnrolled;
    }

    // getters
    public int getEmployer() {
        return employer;
    }
    public String getName() {
        return name;
    }
    public int getTotalEnrolled() {
        return totalEnrolled;
    }
    public int getSpousesEnrolled() {
        return spousesEnrolled;
    }
    public int getChildrenEnrolled() {
        return childrenEnrolled;
    }
    
    // setters
    public void setEmployer (int employer) {
        this.employer = employer;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTotalEnrolled(int totalEnrolled) {
        this.totalEnrolled = totalEnrolled;
    }
    public void setSpousesEnrolled(int spousesEnrolled) {
        this.spousesEnrolled = spousesEnrolled;
    }
    public void setChildrenEnrolled(int childrenEnrolled) {
        this.childrenEnrolled = childrenEnrolled;
    }
}
