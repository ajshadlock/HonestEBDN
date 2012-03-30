package ThreadMon;

/*
 * Main class for TheadMon
 * 
 * The main concept on this project is to monitor a given thread on a certain
 * website to see how many times the admin's do deletions / alterations to
 * a given thread.
 * 
 * The Main class at this point parses the html from the website gets out all 
 * the useless html.
 * 
 * 
 *  
 *
 */


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;


public class Main {

    static MessageThread mainThread = new MessageThread();

    public static void main(String[] args) throws ParseException {

        HtmlReader hr = new HtmlReader("http://www.ebrandon.ca/messagethread.aspx?message_id=605589&cat_id=325");
        ArrayList html = hr.readHtml();

        //for(int x=0;x<html.size();x++){
        //   System.out.println(html.get(x)); 
        // }

        HtmlFilter filter = new HtmlFilter(html, true);

        String rawString = filter.filterCrapOut();

        mainThread = filter.filterRawData(rawString);



       /* try {
            FileWriter outFile = new FileWriter("C:\\goo.txt");
            PrintWriter out = new PrintWriter(outFile);


            //out.print();

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //System.out.println(html);

    }
}
