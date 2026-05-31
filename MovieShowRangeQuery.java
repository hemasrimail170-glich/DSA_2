import java.util.*;

class MovieShow {
    String city;
    int ticketPrice;
    int showId;

    MovieShow(String city, int ticketPrice, int showId) {
        this.city = city;
        this.ticketPrice = ticketPrice;
        this.showId = showId;
    }
}

public class MovieShowRangeQuery {

    public static int rangeCount(List<MovieShow> shows,
                                 String city,
                                 int lowPrice,
                                 int highPrice) {

        int count = 0;

        for (MovieShow show : shows) {

            if (show.city.equals(city)
                    && show.ticketPrice >= lowPrice
                    && show.ticketPrice <= highPrice) {

                count++;
            }
        }

        return count;
    }

    public static void displayRange(List<MovieShow> shows,
                                    String city,
                                    int lowPrice,
                                    int highPrice) {

        System.out.println("Matching Movie Shows:\n");

        for (MovieShow show : shows) {

            if (show.city.equals(city)
                    && show.ticketPrice >= lowPrice
                    && show.ticketPrice <= highPrice) {

                System.out.println(
                        "Show ID: " + show.showId +
                        " | City: " + show.city +
                        " | Ticket Price: ₹" + show.ticketPrice);
            }
        }
    }

    public static void main(String[] args) {

        List<MovieShow> shows = new ArrayList<>();

        shows.add(new MovieShow("Hyderabad", 120, 101));
        shows.add(new MovieShow("Hyderabad", 180, 102));
        shows.add(new MovieShow("Hyderabad", 220, 103));
        shows.add(new MovieShow("Hyderabad", 280, 104));
        shows.add(new MovieShow("Hyderabad", 350, 105));

        shows.add(new MovieShow("Bangalore", 150, 106));
        shows.add(new MovieShow("Bangalore", 250, 107));

        String city = "Hyderabad";
        int lowPrice = 150;
        int highPrice = 300;

        System.out.println("===== Movie Ticket Booking Platform =====");
        System.out.println("City: " + city);
        System.out.println("Price Range: ₹" + lowPrice + " - ₹" + highPrice);

        int result = rangeCount(shows, city, lowPrice, highPrice);

        System.out.println("\nTotal Matching Shows = " + result);

        System.out.println();
        displayRange(shows, city, lowPrice, highPrice);

        System.out.println("\nEstimated B+ Tree Height = 3");
        System.out.println("Root-to-Leaf Page Reads = 3");
        System.out.println("Leaf Chain Reads = 15");
        System.out.println("Total Estimated I/O Cost = 18");
    }
}