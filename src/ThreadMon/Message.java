/* Comments to be added
 * 
 */

package ThreadMon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jer
 */
public class Message {
    
    private String threadId;
    private String parentId;
    private String modthreadId;
    private String title;
    private long epochTime;
    private String content;
    private String hashContent;
    private String userName;
    private String totalPosts;
        
        
    
    Message(String threadId,String parentId, String title,long epochTime,String content,String userName,String totalPosts){
        this.threadId = threadId;
        this.parentId = parentId;
        this.title = title;
        this.epochTime = epochTime;
        this.content = content;
        this.userName = userName;
        this.totalPosts = totalPosts;
        
              
    }
}
