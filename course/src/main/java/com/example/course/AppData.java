package com.example.course;

import java.io.*;
import java.security.SecureRandom;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class AppData
{
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomId() {
        StringBuilder sb = new StringBuilder(25);
        for (int i = 0; i < 25; i++)
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        return sb.toString();
    }
    public static int findMovieIndexById(String id) {
        for (int i = 0; i < AppData.moviesList.size(); i++) {
            if (AppData.moviesList.get(i).getId().equals(id))
                return i;
        }
        return -1;
    }
    public static void writeMoviesToFile(List<Movies> moviesList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(moviesList);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    @SuppressWarnings("unchecked")
    public static void readMoviesFromFile() throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        if(file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Movies> list = (List<Movies>) objectInputStream.readObject();
            AppData.moviesList = list.isEmpty() ? new ArrayList<>() : list;
            objectInputStream.close();
            fileInputStream.close();
        }
    }
    public static boolean checkData(Movies movies)
    {
        if(movies.getMovieName().length() == 0 || movies.getMovieName().length() > 100)
            return false;
        if(movies.getProducer().length() == 0 || movies.getProducer().length() > 50)
            return false;
        if(movies.getActor().length() == 0 || movies.getActor().length() > 50)
            return false;
        if(movies.getOutYear() < 1900 || movies.getOutYear() > Year.now().getValue())
            return false;
        if(!checkRateAndWatchersQuantity(movies.getRate(), movies.getWatchersQuantity()))
           return false;
        return movies.getMoney() >= 0;
    }
    public static boolean checkRateAndWatchersQuantity(float rate, int watchers)
    {
        if(watchers < 0)
            return false;
        return !(rate < 0) && !(rate > 10);
    }

    public static List<Movies> moviesList;
    public static String fileName = "movies.dat";
}
