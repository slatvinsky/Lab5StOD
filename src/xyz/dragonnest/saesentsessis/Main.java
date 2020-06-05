package xyz.dragonnest.saesentsessis;

import java.util.HashMap;
import java.util.Random;

public class Main {

    String dictionary_ = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    String dictionary = "1234567890";

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    private void run() {
        System.out.println("My execution:\n");
        HashTable<Integer, String> map = new HashTable<>();
        System.out.println(map.capacity());
        Random rnd = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            int steps = rnd.nextInt(6)+1;
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < steps; j++) {
                builder.append(dictionary.charAt(rnd.nextInt(dictionary.length())));
            }
            map.put(rnd.nextInt(100000), builder.toString());
        }
        double time = System.currentTimeMillis()-start;
        long buffer = 0;
        System.out.println("Time to add 10E6 random elements: "+time/1000+"s.");
        start = System.currentTimeMillis();
        int successful = 0, unsuccessful = 0;
        for (int i = 0; i < 1000000; i++) {
            int steps = rnd.nextInt(6)+1;
            long nanoStart = System.nanoTime();
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < steps; j++) {
                builder.append(dictionary.charAt(rnd.nextInt(dictionary.length())));
            }
            buffer += System.nanoTime()-nanoStart;
            if (map.contains(Integer.parseInt(builder.toString()))) successful++;
            else unsuccessful++;
        }
        time = System.currentTimeMillis() - buffer/1E6 - start;
        System.out.println("Time to find random 10E6 elements: "+time/1000+"s. Successfully found: "+successful+", unsuccessfully: "+unsuccessful+"\n\nStandart library execution:\n");
        HashMap<Integer, String> stdMap = new HashMap<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            int steps = rnd.nextInt(6)+1;
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < steps; j++) {
                builder.append(dictionary.charAt(rnd.nextInt(dictionary.length())));
            }
            stdMap.put(rnd.nextInt(2000000), builder.toString());
        }
        time = System.currentTimeMillis()-start;
        buffer = 0;
        System.out.println("Time to add 10E6 random elements: "+time/1000+"s.");
        start = System.currentTimeMillis();
        successful = 0; unsuccessful = 0;
        for (int i = 0; i < 1000000; i++) {
            int steps = rnd.nextInt(6)+1;
            long nanoStart = System.nanoTime();
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < steps; j++) {
                builder.append(dictionary.charAt(rnd.nextInt(dictionary.length())));
            }
            buffer += System.nanoTime()-nanoStart;
            if (stdMap.containsKey(Integer.parseInt(builder.toString()))) successful++;
            else unsuccessful++;
        }
        time = System.currentTimeMillis() - buffer/1E6 - start;
        System.out.println("Time to find random 10E6 elements: "+time/1000+"s. Successfully found: "+successful+", unsuccessfully: "+unsuccessful);

    }
}
