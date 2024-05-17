package tratamientodatos.lectores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorSongs {

    private LectorSongs() {super();}
    public static List<String> readNames(String fileOfItemNames) throws IOException {
        List<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));)  {
            String line;

            while ((line = br.readLine()) != null) {
                names.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }
}
