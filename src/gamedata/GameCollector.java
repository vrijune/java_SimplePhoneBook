package gamedata;


import java.io.*;
import java.util.*;

/**
 * This class imports the games.csv file, and prints games from the collection.
 *
 * @author Yu-Cheng Tu
 */
public class GameCollector {
    List<Game> games;

    /**
     * This is the main method, do not modify this.
     */
    public static void main(String[] args) throws InvalidYearException, GameInFutureException {
        GameCollector gc = new GameCollector();

        String currentDir = System.getProperty("user.dir");
        gc.processFile(currentDir + "/games.csv");

        System.out.println("The first 5 games");
        System.out.println("===================");
        gc.printFirstFiveGames();
        System.out.println("===================");

        System.out.println("Number of Action games in the collection: " + gc.getNumberOfGamesByGenre(Genre.ACTION));
        System.out.println("Number of Puzzle games in the collection: " + gc.getNumberOfGamesByGenre(Genre.PUZZLE));
        System.out.println("Number of Role-Playing games in the collection: " + gc.getNumberOfGamesByGenre(Genre.RPG));
        System.out.println("Number of Sports games in the collection: " + gc.getNumberOfGamesByGenre(Genre.SPORTS));
        System.out.println("===================");

        System.out.println("The top 10 games by year and publisher");
        System.out.println("===================");
        gc.printTopTenSortedGames();
        System.out.println("===================");

        gc.exportSortedGames(currentDir + "/sortedGames.csv");

        System.out.println("Number of games per year");
        System.out.println("===================");
        Map<Integer, Integer> numOfGamesPerYear = gc.getCountOfGamesPerYear();
        gc.printGamesPerYear(numOfGamesPerYear);
    }

    private void printGamesPerYear(Map<Integer, Integer> numOfGamesPerYear) {
        Set<Integer> years = numOfGamesPerYear.keySet();
        for (Integer year : years) {
            System.out.printf("Games:%-5d Year: %4d%n", numOfGamesPerYear.get(year), year);
        }

    }

    // step c i.
    private int checkYear(String yearStr) throws InvalidYearException, GameInFutureException {
        if (yearStr.isEmpty()) {
            return 2016;
        }
        int year = Integer.parseInt(yearStr);
        if (year < 1950) {
            throw new InvalidYearException("The game is too old!");
        } else if (year > 2016) {
            throw new GameInFutureException("The game is too new!");
        }

        return year;
    }


    // step c ii.
    private Genre getGenre(String genre) {
        switch (genre.toLowerCase()) {
            case "action":
                return Genre.ACTION;
            case "misc":
                return Genre.MISC;
            case "role-playing":
                return Genre.RPG;
            case "puzzle":
                return Genre.PUZZLE;
            case "sports":
                return Genre.SPORTS;
            default:
                return Genre.OTHER;
        }
    }

    // step c iii.
    private Game processGameDetails(String[] gameDetails) throws InvalidYearException, GameInFutureException {
        int id = Integer.parseInt(gameDetails[0]);
        String game = gameDetails[1];
        String platform = gameDetails[2];
        int year = checkYear(gameDetails[3]);
        Genre genre = getGenre(gameDetails[4]);
        String publisher = gameDetails[5];
        return new Game(id, game, platform, year, genre, publisher);
    }

    // step c iv.
    private void processFile(String filePath) {
        games = new ArrayList<Game>();
        try (BufferedReader bR = new BufferedReader(new FileReader(new File(filePath)))) {
            String gameData;

            while ((gameData = bR.readLine()) != null) {
                String[] gameDetails = gameData.split(",");
                Game game = processGameDetails(gameDetails);
                games.add(game);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidYearException e) {
            System.out.println(e.getMessage());
        } catch (GameInFutureException e) {
            System.out.println(e.getMessage());
        }

    }

    // step c v.
    private void printFirstFiveGames() {
        for (int i = 0; i < 5; i++) {
            System.out.println(games.get(i));
        }


    }

    // step c vi.

    private int getNumberOfGamesByGenre(Genre genre) {
        int count = 0;
        for (Game game : games) {
            if (game.getGenre().equals(genre.toString())) {
                count++;
            }

        }

        return count;
    }

    // step c vii.
    private void printTopTenSortedGames() {
        Comparator<Game> gameComperator = new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                double diff = o1.getYear() - o2.getYear();
                if (diff != 0) {
                    return Double.valueOf(o2.getYear()).compareTo((double) o1.getYear());
                }
                return Double.valueOf(o2.getPublisher()).compareTo(Double.valueOf(o1.getPublisher()));
            }
        };
    }


    // step c viii.
    private void exportSortedGames(String filePath) {

        try (BufferedWriter bW = new BufferedWriter(new FileWriter(filePath))) {
            String gameContent = "";
            for (Game game : games) {
                gameContent += String.format("%s,%4d,%s%n", game.getName(), game.getYear(), game.getPublisher());
            }
            bW.write(gameContent);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

        // step c ix.
            private Map<Integer, Integer> getCountOfGamesPerYear () {
                Map<Integer, Integer> numOfGamesPerYear = new TreeMap<>();
                for (Game game : games) {
                    Integer value = numOfGamesPerYear.get(game.getYear());
                    int freq = value == null ? 1 : value + 1;

//            int freq;
//            if (value == null){
//                freq = 1;
//            }else{
//                freq = value+1;
//            }


                    numOfGamesPerYear.put(game.getYear(), freq);


                }


                return numOfGamesPerYear;
            } }
