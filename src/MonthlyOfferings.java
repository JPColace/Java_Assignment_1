import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

public class MonthlyOfferings {
    private LinkedHashMap<String, Studio> studios;
    private LinkedHashMap<String, StreamingService> streams;
    private LinkedHashMap<String, Event> events;
    private ArrayList<LinkedHashMap<StreamingService,Event>> monthlyOffering;
    private int monthTimeStamp;
    private int yearTimeStamp;

    public MonthlyOfferings() {
        studios = new LinkedHashMap<>();
        streams = new LinkedHashMap<>();
        events = new LinkedHashMap<>();
        monthlyOffering = new ArrayList<LinkedHashMap<StreamingService,Event>>();
        monthTimeStamp = 10;
        yearTimeStamp = 2020;
    }

    public Studio createStudio(String studioShortName, String studioLongName) {
        //create the instance of Studio
        Studio studio = new Studio(studioShortName, studioLongName);

        // Add to linked hashmap
        studios.put(studioShortName, studio);

        return studio;
    }

    public String getStudioInfo(String studioShortName) {
        return studios.get(studioShortName).toString();
    }


    public Event createEvent(String eventType, String eventName, int eventYear, int eventDuration,
                             String studioShortName, int licenseFee) {

        // Create Event
        Event event = new Event(eventType, eventName, eventYear, eventDuration, licenseFee);

        // Get studio based on studio name input by user
        Studio studio = studios.get(studioShortName);

        // Set studio for this event
        event.setStudio(studio);

        // Add to events linked hashmap
        events.put(eventName, event);

        return event;
    }

    public Event getEvent(String eventName) {
        return events.get(eventName);
    }

    // Loop through and grab events based on key, printing with toString()
    public void displayEvents() {
        for (String key : events.keySet()) {
            System.out.println(events.get(key).toString());
        }
    }

    public StreamingService createStream(String streamShortName, String streamLongName, int subscriptionFee) {

        // Create new StreamingService
        StreamingService streamingService = new StreamingService(streamShortName, streamLongName,
                subscriptionFee);

        // Add to streams linked hashmap
        streams.put(streamShortName, streamingService);

        return streamingService;
    }

    // Get streaming service object based on streamShortName
    public StreamingService getStreamingService(String streamShortName) {
        return streams.get(streamShortName);
    }

    // Display streams based on streamShortName
    public String displayStreams(String streamShortName) {
        return streams.get(streamShortName).toString();
    }


    public void createMovieOffering(String streamShortName, String eventName, int eventYear) {

        StreamingService streamingService = streams.get(streamShortName);
        Event event = events.get(eventName);
        Studio studio = event.getStudio();

        // If eventType is ppv then get out of here
        if(event.getEventType().equals("ppv"))
            return;

        // Add the license fee for this event to the studio revenue
        studio.addLicenseFee(event.getLicenseFee());
        // Have the streaming service pay the license fee for this event
        streamingService.addLicenseFeesPaid(event.getLicenseFee());

        // Add this service and event to the streamingEvents linked hashmap
        LinkedHashMap<StreamingService,Event> streamingEvents = new LinkedHashMap<>();
        streamingEvents.put(streamingService,event);

        // Add it to ArrayList
        monthlyOffering.add(streamingEvents);
    }

    public void createPpvOffering(String streamShortName, String eventName, int eventYear,
                                  int viewingPrice) {

        StreamingService streamingService = streams.get(streamShortName);
        Event event = events.get(eventName);
        Studio studio = event.getStudio();

        // If the eventType is a movie then get out of here
        if(event.getEventType().equals("movie"))
            return;

        // Add the license fee to the studio revenue
        studio.addLicenseFee(event.getLicenseFee());
        // Have the streaming service pay the license fee
        streamingService.addLicenseFeesPaid(event.getLicenseFee());
        // PPV movie, so we set the viewing price for this movie
        // Originally had PPV subclass, but not sure it's necessary yet.
        event.setPpvViewingPrice(viewingPrice);

        // Add the service and event to the hashmap
        LinkedHashMap<StreamingService,Event> streamingEvents = new LinkedHashMap<>();
        streamingEvents.put(streamingService,event);

        // Add it to the ArrayList of streaming events
        monthlyOffering.add(streamingEvents);
    }

    public void displayOffers() {

        // Loop through monthly offering list
        for (Map<StreamingService, Event> entry : monthlyOffering) {
            // For every streaming service get event
            for (StreamingService streamingService : entry.keySet()) {
                Event event = entry.get(streamingService);
                // Two different prints - movie doesn't have a PPV viewing price
                if (event.getEventType().equals("movie")) {
                    System.out.println(streamingService.getStreamShortName() + "," +
                            event.getEventType() + "," + event.getEventName() + "," +
                            event.getEventYear());
                } else {
                    System.out.println(streamingService.getStreamShortName() + "," +
                            event.getEventType() + "," + event.getEventName() + "," +
                            event.getEventYear() + "," + event.getPpvViewingPrice());
                }
            }
        }
    }

    public void offeringsNextMonth(){
        // If the user calls for next month, increment month
        monthTimeStamp += 1;

        // If end of year, start back at January and increment year
        if (monthTimeStamp > 12) {
            monthTimeStamp = 1;
            yearTimeStamp += 1;
        }

        // Call nextMonthRevenue method for each studio and streaming service
        for (Map.Entry<String, Studio> entry : studios.entrySet()) {
            entry.getValue().nextMonthRevenue();
        }

        for (Map.Entry<String, StreamingService> entry : streams.entrySet()) {
            entry.getValue().nextMonthRevenue();
        }

        // Clear all events offered for the month
        monthlyOffering.clear();
    }

    public void setMonthTimeStamp(int monthTimeStamp) {
        this.monthTimeStamp = monthTimeStamp;
    }

    public void setYearTimeStamp(int yearTimeStamp) {
        this.yearTimeStamp = yearTimeStamp;
    }

    public int getMonthTimeStamp() {
        return monthTimeStamp;
    }

    public int getYearTimeStamp() {
        return yearTimeStamp;
    }

    public String displayTime() {
        return "time," + this.monthTimeStamp + "," + this.yearTimeStamp;
    }

}


