package duckstats;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class imports the duck statistics from a file, prints the first 10 duck
 * statistics, and the first 10 duck statistics in descending order.
 *
 * @author Yu-Cheng Tu
 */
public class DuckCounterProcessor {

    /**
     * This is the main method, you may modify this method appropriately.
     */
    public static void main(String[] args) {
        DuckCounterProcessor dp = new DuckCounterProcessor();

        System.out.println("Processing Duck Data");
        System.out.println("----------------------");
        String currentDir = System.getProperty("user.dir");
        List<DuckCounter> duckData = dp.processFile(currentDir + "/ducks.csv");

        System.out.println();
        System.out.println("=================");
        System.out.println("First 10 Duck Stats");
        System.out.println("=================");
        dp.printFirstTenDuckCounts(duckData);
        System.out.println("=================");

        System.out.println("First 10 Duck Stats in Descending Order");
        System.out.println("=================");

        // step c.
        Collections.sort(duckData);
        dp.printFirstTenDuckCounts(duckData);
        System.out.println("=================");

    }


            // step b ii.
            private void printFirstTenDuckCounts(List<DuckCounter> duckData) {
                for (int i = 0; i < 10; i++) {
                    DuckCounter ducksPerCount = duckData.get(i);
                    System.out.printf("Date: %s, ducks: %d, ducklings: %d%n", ducksPerCount.getDate().toString(),
                            ducksPerCount.getDuckCount(), ducksPerCount.getDucklingCount());
                }
            }


            // step b i.
            private List<DuckCounter> processFile(String filePath) {

                ArrayList<DuckCounter> duckData = new ArrayList<DuckCounter>();
                try (BufferedReader bR = new BufferedReader(new FileReader(new File(filePath)))) {
                    String data = "";
                    while ((data = bR.readLine()) != null) {
                        String[] ducksPerPond = data.split(",");
                        String date = ducksPerPond[0];
                        int ducks = Integer.parseInt(ducksPerPond[1]);
                        int ducklings = Integer.parseInt(ducksPerPond[2]);
                        duckData.add(new DuckCounter(date, ducks, ducklings));

                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DuckCountException e) {
                    e.printStackTrace();
                }
                return duckData;

            }



}