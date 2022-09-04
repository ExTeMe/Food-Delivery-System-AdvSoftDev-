package model;

public class RCategory {

    private final int RCat_ID;
    private String rCatName;
    private String rCatDescription;

    public RCategory(int RCat_ID, String rCatName, String rCatDescription) {
        this.RCat_ID = RCat_ID;
        this.rCatName = rCatName;
        this.rCatDescription = rCatDescription;
    }

    public int getRCat_ID() {
        return RCat_ID;
    }

    public String getrCatName() {
        return rCatName;
    }

    public void setrCatName(String rCatName) {
        this.rCatName = rCatName;
    }

    public String getrCatDescription() {
        return rCatDescription;
    }

    public void setrCatDescription(String rCatDescription) {
        this.rCatDescription = rCatDescription;
    }
}
