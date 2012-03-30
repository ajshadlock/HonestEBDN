package ThreadMon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HtmlReader {

    private String address;

    public HtmlReader(String address) {
        this.address = address;
    }

    public ArrayList readHtml() {
        ArrayList list = new ArrayList();

        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                    conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
       }
            br.close();
        } catch (MalformedURLException mue) {
            System.out.println(mue.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return list;
    }
}
