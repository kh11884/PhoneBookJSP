package ru.academits.coverter;

import java.util.ArrayList;
import java.util.List;

public class RequestConverter {

    public int[] getIDs(String requestString) {
        String[] pairs = requestString.split("&");
        List<Integer> iDs = new ArrayList<>();

        for (String pair : pairs) {
            String[] splittedPair = pair.split("=");

            if (splittedPair[0].equals("ID")) {
                return new int[]{Integer.parseInt(splittedPair[1])};
            } else {
                iDs.add(Integer.parseInt(splittedPair[1]));
            }
        }
        return iDs.stream().mapToInt(i -> i).toArray();
    }
}
