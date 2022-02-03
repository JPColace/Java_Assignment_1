public class Event {
    private String eventType;
    private String eventName;
    private int eventYear;
    private int eventDuration;
    private int licenseFee;
    private Studio studio;
    private int ppvViewingPrice;

    public Event(String eventType, String eventName, int eventYear, int eventDuration,
                 int licenseFee){
        this.eventName = eventName;
        this.licenseFee = licenseFee;
        this.eventType = eventType;
        this.eventYear = eventYear;
        this.eventDuration = eventDuration;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getLicenseFee() {
        return licenseFee;
    }

    public void setLicenseFee(int licenseFee) {
        this.licenseFee = licenseFee;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public void setEventType() {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public int getEventYear() {
        return eventYear;
    }

    public void setEventYear(int eventYear) {
        this.eventYear = eventYear;
    }

    public void setEventDuration() {
        this.eventDuration = eventDuration;
    }

    public int getEventDuration() {
        return eventDuration;
    }

    public int getPpvViewingPrice() {
        return ppvViewingPrice;
    }

    public void setPpvViewingPrice(int ppvViewingPrice) {
        this.ppvViewingPrice = ppvViewingPrice;
    }

    @Override
    public String toString(){
        return this.eventType + "," + this.eventName + "," + this.eventYear + "," +
                this.eventDuration + "," + this.getStudio().getStudioShortName() + "," +
                this.licenseFee;
    }
}
