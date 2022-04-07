package json;

import Carpentry.Carpentry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;

public class CarpentryJson {
    private final static String carpJson = "Carpentry.json";

    public void saveCarpentryJson(Carpentry carp) {
        File file = new File(carpJson);
        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            gson.toJson(carp, Carpentry.class, buffer);
            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Carpentry readCarpentryJson(){
        File file = new File(carpJson);
        Carpentry carp = null;
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            Gson gson = new Gson();

            carp = gson.fromJson(buffer, new TypeToken<Carpentry>(){}.getType());
            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return carp;
    }
}
