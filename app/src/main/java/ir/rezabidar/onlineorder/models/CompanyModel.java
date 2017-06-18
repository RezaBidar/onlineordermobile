package ir.rezabidar.onlineorder.models;

/**
 * Created by ReZaBiDaR on 7/21/2016.
 */
public class CompanyModel {

    private int companyId ;
    private String companyName;
    private int visitorId;
    private String visitorName;

    @Override
    public String toString() {
        return companyName + " - " + visitorName;
    }

    public CompanyModel(){}

    public CompanyModel(String companyName, String visitorName) {
        this.companyName = companyName;
        this.visitorName = visitorName;
    }

    public CompanyModel(int companyId, String companyName, int visitorId, String visitorName) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.visitorId = visitorId;
        this.visitorName = visitorName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }
}
