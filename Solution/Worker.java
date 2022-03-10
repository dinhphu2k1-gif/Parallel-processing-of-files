package Task.Solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class Worker implements Callable<HashMap<String, Integer>> {
    private String path = null;
    private HashMap<String, Integer> map  = new HashMap<>();

    public Worker(String path) {
        this.path = path;
    }

    // dem so tu trong moi doan van ban
    public void countWordOfText(String text) {
        // tach cac tu trong van ban
        String[] listWords = text.split("[^a-zA-Z]+");
        for (String c : listWords) {
            if (c.equals("")) {
                continue;
            }
            // neu tu da co thi tang them 1, nguoc lai thi tao moi
            if (map.containsKey(c)) {
                map.compute(c.toLowerCase(), (k, v) -> v + 1);
            } else {
                map.put(c.toLowerCase(), 1);
            }
        }
    }

    @Override
    public HashMap<String, Integer> call() throws Exception {
        BufferedReader bufferedReader = null;
        String text = "";

        try {
            Reader reader = new FileReader(this.path);
            bufferedReader = new BufferedReader(reader);

            String c = "";
            while ((c = bufferedReader.readLine()) != null) {
                text = text + c;
            }
        
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        this.countWordOfText(text);

        return map;
    }
}
