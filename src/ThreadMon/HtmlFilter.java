/*
 * The HtmlFilter 
 */

package ThreadMon;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class HtmlFilter {

    private ArrayList list;
    private boolean debug;

    public HtmlFilter(ArrayList firstList, boolean debug){
        this.list = firstList;
        this.debug = debug;
    }

    public String filterCrapOut(){
        int startNum = 0;
        int endNum = 0;
        int rawDataLength;
        StringBuilder sb = new StringBuilder();

        Object[] tempList = list.toArray();

        for(int x=0;x<tempList.length;x++){

        if(tempList[x].toString().indexOf("lblPages1")!=-1)
            startNum = x;

         if(tempList[x].toString().indexOf("<table width=\"640\"><TR><TD width=\"190\">&nbsp;</TD><TD")!=-1)
            endNum = x;

        }

        rawDataLength = endNum - startNum;

        for(int x=startNum; x < endNum;x++){
            sb.append(tempList[x].toString());
        }


        return sb.toString();

    }

    public MessageThread filterRawData(String rawData) throws ParseException{
        ArrayList<Integer> beginArray = new ArrayList();
        ArrayList<Integer> endArray = new ArrayList();
        ArrayList<String> rawArray = new ArrayList();
        ArrayList<Message> listOMessages = new ArrayList();
        MessageThread finalArray = new MessageThread();
        String userId ="";
        String userName ="";
        String content ="";
        String parentId ="";
        String messageId ="";
        String totalPosts ="";
        String title ="";
        long epochTime = 0;

        //Revomes the intial crap out of the downloaded page. Also adds a hard break to the end of each >

        rawData=rawData.replace("<TABLE WIDTH=370>", "");
        rawData=rawData.replace("<TD width=40>", "");
        rawData=rawData.replace("<TABLE WIDTH=330 CELLPADDING=1 CELLSPACING=1>", "");
        rawData=rawData.replace("<TD BGCOLOR=#CCCCCC>", "");
        rawData=rawData.replace("<TABLE WIDTH=330 CELLPADDING=6 CELLSPACING=1>", "");
        rawData=rawData.replace("<TD BGCOLOR=#EEEEEE>", "");
        rawData=rawData.replace("<FONT SIZE=1 FACE=Verdana>", "");
        rawData=rawData.replace("<I>", " \"quote");
        rawData=rawData.replace("</I>","quote\" ");
        rawData=rawData.replace("<P>", "");
        rawData=rawData.replace("</TABLE>", "");
        rawData=rawData.replace("</a>", "");
        rawData=rawData.replace("&nbsp;", "");
        rawData=rawData.replace("<p>", "");
        rawData=rawData.replace("</A>", "");
        rawData=rawData.replace("<BR>", "");
        rawData=rawData.replace("<br>", "");
        rawData=rawData.replace("<b>", "");
        rawData=rawData.replace("</b>", "");
        rawData=rawData.replace("<B>", "");
        rawData=rawData.replace("</B>", "");
        rawData=rawData.replace("</b>", "");
        rawData=rawData.replace("</div>", "");
        rawData=rawData.replace("</FONT>", "");
        rawData=rawData.replace("</table>", "");
        rawData=rawData.replace("</span>", "");
        rawData=rawData.replace("<TR>", "");
        rawData=rawData.replace("<tr>", "");
        rawData=rawData.replace("<TD>", "");
        rawData=rawData.replace("<td>", "");
        rawData=rawData.replace("</TR>", "");
        rawData=rawData.replace("</TD>", "");
        rawData=rawData.replace("</tr>", "");
        rawData=rawData.replace("</td>", "");
        rawData=rawData.replace(">", ">\r");


        //Seperate the string into an array (easier to parse)
        StringTokenizer st = new StringTokenizer(rawData,"\r");

        while(st.hasMoreTokens())
           rawArray.add(st.nextToken());



        for (int x = 1; x < rawArray.size(); x++) {
            if (rawArray.get(x).indexOf("<A HREF=profile.aspx?person_id=") != -1) {
                beginArray.add(x);

            }
            if (rawArray.get(x).indexOf("<FONT SIZE=1>") != -1) {
                endArray.add(x);

            }
        }

        for (int x = 0; x < beginArray.size(); x++) {
            for (int y = beginArray.get(x); y < endArray.get(x); y++) {
                if (rawArray.get(y).indexOf("<A HREF=profile.aspx?person_id=") != -1) {

                    userId = rawArray.get(y).substring(rawArray.get(y).indexOf("id=") + 3, rawArray.get(y).indexOf(">"));
                    if (debug) {
                        System.out.println("UserId=" + userId);
                    }


                    userName = rawArray.get(y + 1).substring(0, rawArray.get(y + 1).indexOf("<img src="));
                    if (debug) {
                        System.out.println("userName:" + userName);
                    }

                    String tempDate = rawArray.get(y + 2);
                     
                    if (tempDate.indexOf("PM") != -1) {
                        epochTime = new java.text.SimpleDateFormat("MM/dd/yyy HH:mm:ss aa").parse(tempDate.substring(0,tempDate.indexOf("PM")+2)).getTime();
                     }
                    if (tempDate.indexOf("AM") != -1) {
                        epochTime = new java.text.SimpleDateFormat("MM/dd/yyy HH:mm:ss aa").parse(tempDate.substring(0,tempDate.indexOf("AM")+2)).getTime();
                     }
                    
                    //Add totalpost later
                    
                    if (debug) {
                        System.out.println("Date n junk:" + epochTime);                              
                    }

                }
                if (rawArray.get(y).indexOf("<td width=\"385\" class=\"contenttable\">") != -1) {
                    title = rawArray.get(y + 1).substring(0, rawArray.get(y + 1).indexOf("<td width=\"60\" valign=\"top\">"));
                    if (debug) {
                        System.out.println("Title:" + title);
                    }

                    messageId = rawArray.get(y + 2).substring(rawArray.get(y + 2).indexOf("message_id=") + 11, rawArray.get(y + 2).indexOf("&message_top"));
                    if (debug) {
                        System.out.println("messageId:" + messageId);
                    }

                    parentId = rawArray.get(y + 2).substring(rawArray.get(y + 2).indexOf("message_top_parent_id=") + 22, rawArray.get(y + 2).indexOf("&message_level"));
                    if (debug) {
                        System.out.println("parentId:" + parentId);
                    }

                }
                if (rawArray.get(y).indexOf("<div style=\"width:465px;overflow:hidden;\">") != -1) {
                    content = rawArray.get(y + 1);

                    if (content.indexOf("<div style=\"text-align:right;width:460px;\">") != -1) {
                        content = content.substring(0, content.indexOf("<div style=\"text-align:right;width:460px;\">"));
                    }
                    if (debug) {
                        System.out.println("Content:" + content);
                    }
                }



            }

            Message tempMessage = new Message(userName, title, content, parentId, messageId, "0", epochTime);
            finalArray.add(tempMessage);
        }

        return finalArray;


    }
}
