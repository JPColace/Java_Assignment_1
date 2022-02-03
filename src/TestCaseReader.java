import java.util.Scanner;

public class TestCaseReader {

    MonthlyOfferings monthlyOfferings = new MonthlyOfferings();
    WatchEvent watchEvent = new WatchEvent(monthlyOfferings);


    public TestCaseReader() { }

    public void processInstructions(Boolean verboseMode) {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        while (true) {
            try {
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);
                if (tokens[0].equals("create_studio")) {
                    monthlyOfferings.createStudio(tokens[1], tokens[2]);
                } else if (tokens[0].equals("create_stream")) {
                    monthlyOfferings.createStream(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                } else if (tokens[0].equals("create_demo")) {
                    watchEvent.createDemo(tokens[1], tokens[2],
                            Integer.parseInt(tokens[3]));
                } else if (tokens[0].equals("display_demo")) {
                    System.out.println(watchEvent.getDemoInfo(tokens[1]));
                } else if (tokens[0].equals("display_stream")) {
                    System.out.println(monthlyOfferings.displayStreams(tokens[1]));
                } else if (tokens[0].equals("display_studio")) {
                    System.out.println(monthlyOfferings.getStudioInfo(tokens[1]));
                } else if (tokens[0].equals("create_event")) {
                    monthlyOfferings.createEvent(tokens[1],tokens[2], Integer.parseInt(tokens[3]),
                            Integer.parseInt(tokens[4]),tokens[5], Integer.parseInt(tokens[6]));
                } else if (tokens[0].equals("offer_movie")) {
                    monthlyOfferings.createMovieOffering(tokens[1], tokens[2],
                            Integer.parseInt(tokens[3]));
                } else if (tokens[0].equals("offer_ppv")) {
                    monthlyOfferings.createPpvOffering(tokens[1],tokens[2],Integer.parseInt(tokens[3]),
                            Integer.parseInt(tokens[4]));
                } else if (tokens[0].equals("display_events")) {
                    monthlyOfferings.displayEvents();
                } else if (tokens[0].equals("display_offers")) {
                    monthlyOfferings.displayOffers();
                } else if (tokens[0].equals("watch_event")) {
                    watchEvent.eventViewer(tokens[1],Integer.parseInt(tokens[2]),tokens[3],
                            tokens[4],Integer.parseInt(tokens[5]));
                } else if (tokens[0].equals("display_time")) {
                    System.out.println(monthlyOfferings.displayTime());
                } else if (tokens[0].equals("next_month")) {
                    watchEvent.demosNextMonth();
                    monthlyOfferings.offeringsNextMonth();
                } else if (tokens[0].equals("stop")) {
                    break;
                } else {
                    if (verboseMode) {
                        System.out.println("command_" + tokens[0] + "_NOT_acknowledged");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        if (verboseMode) { System.out.println("stop_acknowledged"); }
        commandLineInput.close();
    }

}
