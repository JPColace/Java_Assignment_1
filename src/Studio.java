import java.util.LinkedHashMap;

public class Studio {
    private String studioShortName;
    private String studioLongName;
    private int currentRevenue;
    private int previousRevenue;
    private int totalRevenue;

    public Studio(String studioShortName, String studioLongName) {
        this.studioShortName = studioShortName;
        this.studioLongName = studioLongName;
        previousRevenue = 0;
        currentRevenue = 0;
        totalRevenue = 0;
    }

    public String getStudioShortName() {
        return studioShortName;
    }

    public void setStudioShortName(String studioShortName) {
        this.studioShortName = studioShortName;
    }

    public String getStudioLongName() {
        return studioLongName;
    }

    public void setStudioLongName(String studioLongName) {
        this.studioLongName = studioLongName;
    }

    public void addLicenseFee(int licenseFee) {
        currentRevenue += licenseFee;
    }

    public void nextMonthRevenue() {
        previousRevenue = currentRevenue;
        totalRevenue += previousRevenue;
        currentRevenue = 0;
    }

    @Override
    public String toString() {
        return "studio," + this.studioShortName + "," + this.studioLongName + "\n" +
                "current_period," + this.currentRevenue + "\n" +
                "previous_period," + this.previousRevenue + "\n" +
                "total," + this.totalRevenue;
    }
}
