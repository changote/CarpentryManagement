package json;

import Carpentry.Carpentry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.hildan.fxgson.FxGson;
import org.hildan.fxgson.FxGsonBuilder;

import java.io.*;
import java.util.regex.Pattern;

public class CarpentryJson {
    private final static String carpJson = "Carpentry.json";
    private final File file = new File(carpJson);

    public void saveCarpentryJson(Carpentry carp) {
        try {

            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
            Gson fxGson = FxGson.create();
            fxGson.toJson(carp, Carpentry.class, buffer);
            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Carpentry readCarpentryJson(){
        Carpentry carp = null;
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            Gson gson = FxGson.create();


            carp = gson.fromJson(buffer, Carpentry.class);
            buffer.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
        return carp;
    }
}
