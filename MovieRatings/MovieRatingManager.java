package MovieRatings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MovieRatings {
    private final int id;
    private final String title;
    private final int year;
    private int rating;
    private final int deepseekRecommendation;
    private final List<String> genres;

    public MovieRatings(int id, String title, int year, int rating, 
                       int deepseekRecommendation, List<String> genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.deepseekRecommendation = deepseekRecommendation;
        this.genres = genres;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public int getRating() { return rating; }
    public int getDeepseekRecommendation() { return deepseekRecommendation; }
    public List<String> getGenres() { return genres; }
    
    // Setter for rating
    public void setRating(int rating) { this.rating = rating; }
}

public class MovieRatingManager {
    public static void main(String[] args) {
        String csvFile = "MovieRatings/movieList1.csv";
        List<MovieRatings> movies = new ArrayList<>();

        // Read CSV and create objects
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                
                int id = Integer.parseInt(data[0].trim());
                String title = data[1].trim().replaceAll("^\"|\"$", "");
                int year = Integer.parseInt(data[2].trim());
                int rating = Integer.parseInt(data[3].trim());
                int deepseekRec = Integer.parseInt(data[4].trim());
                List<String> genres = List.of(data[5].trim().split("\\s+"));
                
                movies.add(new MovieRatings(id, title, year, rating, deepseekRec, genres));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Collect new ratings only for unrated movies
        Scanner scanner = new Scanner(System.in);
        for (MovieRatings movie : movies) {
            if (movie.getRating() != -1) {
                continue; // Skip already rated movies
            }

            System.out.printf("\n%s (%d)\n", movie.getTitle(), movie.getYear());
            System.out.printf("Genres: %s\n", String.join(", ", movie.getGenres()));
            System.out.printf("DeepSeek Recommendation: %d/10\n", movie.getDeepseekRecommendation());
            System.out.print("Your rating (0-10, Enter to skip): ");
            
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue; // Keep as -1
            }

            try {
                int newRating = Integer.parseInt(input);
                if (newRating < 0 || newRating > 10) {
                    System.out.println("Invalid rating! Please use 0-10. Keeping as unrated.");
                } else {
                    movie.setRating(newRating);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Keeping as unrated.");
            }
        }

        // Write updated ratings back to CSV
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("Number,Title,Year,Rating,DeepSeek_Recommendation,Genre\n");
            for (MovieRatings movie : movies) {
                String formattedTitle = movie.getTitle().contains(",") 
                        ? "\"" + movie.getTitle() + "\"" 
                        : movie.getTitle();
                
                writer.write(String.format("%d,%s,%d,%d,%d,%s%n",
                        movie.getId(),
                        formattedTitle,
                        movie.getYear(),
                        movie.getRating(),
                        movie.getDeepseekRecommendation(),
                        String.join(" ", movie.getGenres())));
            }
            System.out.println("\nRatings updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}