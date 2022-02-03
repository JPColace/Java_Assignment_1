public class Demographics {
    private String demoShortName;
    private String demoLongName;
    private int demoAccounts;
    private int currentRevenue;
    private int previousRevenue;
    private int totalRevenue;

    public Demographics(String demoShortName, String demoLongName, int demoAccounts){
        this.demoShortName = demoShortName;
        this.demoLongName = demoLongName;
        this.demoAccounts = demoAccounts;
    }

    public String getDemoShortName() {
        return demoShortName;
    }

    public void setDemoShortName(String demoShortName) {
        this.demoShortName = demoShortName;
    }

    public String getDemoLongName() {
        return demoLongName;
    }

    public void setDemoLongName(String demoLongName) {
        this.demoLongName = demoLongName;
    }

    public int getDemoAccounts() {
        return demoAccounts;
    }

    public void setDemoAccounts(int demoAccounts) {
        this.demoAccounts = demoAccounts;
    }

    public void addStreamingCosts(int subscriptionFees) {
        currentRevenue += subscriptionFees;
    }

    public void nextMonthRevenue() {
        previousRevenue = currentRevenue;
        totalRevenue += previousRevenue;
        currentRevenue = 0;
    }

    @Override
    public String toString(){
        return "demo," + this.demoShortName + "," + this.demoLongName + "\n" +
                "size," + this.demoAccounts + "\n" +
                "current_period," + this.currentRevenue + "\n" +
                "previous_period," + this.previousRevenue + "\n" +
                "total," + this.totalRevenue;
    }
}
