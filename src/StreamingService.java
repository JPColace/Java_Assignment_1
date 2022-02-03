public class StreamingService {
    private String streamShortName;
    private String streamLongName;
    private int subscriptionFee;
    private int currentRevenue;
    private int previousRevenue;
    private int totalRevenue;
    private int licenseFeesPaid;

    public StreamingService(String streamShortName,String streamLongName,
                            int subscriptionFee){
        this.streamShortName = streamShortName;
        this.streamLongName = streamLongName;
        this.subscriptionFee = subscriptionFee;
    }

    public void setStreamShortName(String streamShortName){
        this.streamShortName = streamLongName;
    }

    public String getStreamShortName(){
        return streamShortName;
    }

    public void setStreamLongName(String streamLongName){
        this.streamLongName = streamLongName;
    }

    public String getStreamLongName(){
        return streamLongName;
    }

    public void setSubscriptionFee(int subscriptionFee){
        this.subscriptionFee = subscriptionFee;
    }

    public int getSubscriptionFee(){
        return subscriptionFee;
    }

    public void nextMonthRevenue(){
        previousRevenue = currentRevenue;
        totalRevenue += previousRevenue;
        currentRevenue = 0;
    }

    public void addRevenue(int revenuePaid){
        currentRevenue += revenuePaid;
    }

    public void addLicenseFeesPaid(int licenseFee){
        licenseFeesPaid += licenseFee;
    }

    @Override
    public String toString(){
        return "stream," + this.streamShortName + "," + this.streamLongName + "\n" +
                "subscription," + this.subscriptionFee + "\n" +
                "current_period," + this.currentRevenue + "\n" +
                "previous_period," + this.previousRevenue + "\n" +
                "total," + this.totalRevenue + "\n" +
                "licensing," + this.licenseFeesPaid;
    }
}
