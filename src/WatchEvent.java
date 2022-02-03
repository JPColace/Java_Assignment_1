import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WatchEvent {
    private LinkedHashMap<String,Demographics> demos;
    private MonthlyOfferings monthlyOfferings;
    private HashMap<StreamingService,Integer> viewsByService = new HashMap<>();
    private int monthTimeStamp;
    private int yearTimeStamp;

    public WatchEvent(MonthlyOfferings monthlyOfferings){
        demos = new LinkedHashMap<>();
        this.monthlyOfferings = monthlyOfferings;
        monthTimeStamp = 10;
        yearTimeStamp = 2020;
    }

    public Demographics createDemo(String demoShortName, String demoLongName,
                                   int demoAccounts){
        //create the instance of Studio
        Demographics demographics = new Demographics(demoShortName,demoLongName,demoAccounts);

        // Add to linked hash map
        demos.put(demoShortName,demographics);

        return demographics;
    }

    public String getDemoInfo(String demoShortName){
        return demos.get(demoShortName).toString();
    }

    public void eventViewer(String demoShortName, int percentageViewed, String streamingShortName,
                            String eventName, int eventYear) {

        // Get streaming service, event, and demographic based on user's input
        StreamingService stream = monthlyOfferings.getStreamingService(streamingShortName);
        Event event = monthlyOfferings.getEvent(eventName);
        Demographics demo = demos.get(demoShortName);

        // If the service has not been used by this demo, put it in the map
        if (!viewsByService.containsKey(stream))
            viewsByService.put(stream, 0);

        // Get the number of users currently assigned to this streaming service,
        // so how many of these users have paid for the month
        int currentViewers = viewsByService.get(stream);
        // Calculate number of viewers for this newest event
        int newViewers = (demo.getDemoAccounts() * percentageViewed) / 100;
        int subscriptionFee = stream.getSubscriptionFee();
        int ppvViewingPrice = event.getPpvViewingPrice();

        // If it's a movie and the number of new viewers are higher than what's currently
        // in map, update the number of viewers for this service, and calculate how much
        // additional costs
        if (event.getEventType().equals("movie") &&  (newViewers > currentViewers)) {
            viewsByService.put(stream, newViewers);
            demo.addStreamingCosts(subscriptionFee * (newViewers - currentViewers));
            stream.addRevenue(subscriptionFee * (newViewers - currentViewers));
        }
        // If it's PPV, they pay the PPV price every time
        else{
            demo.addStreamingCosts(ppvViewingPrice * newViewers);
            stream.addRevenue(ppvViewingPrice * newViewers);
        }
    }

    public void demosNextMonth() {
        monthTimeStamp += 1;


        if (monthTimeStamp > 12) {
            monthTimeStamp = 1;
            yearTimeStamp += 1;
        }

        // Call nextMonthRevenue method when user advances to next month
        for (Map.Entry<String, Demographics> entry : demos.entrySet()) {
            entry.getValue().nextMonthRevenue();
        }
    }


}
