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
    
    private String userName;
    private String content;
    private Boolean isParent;
    private String title;
    private String parentId;
    private String threadId;
    private String totalPosts;
    private long epochTime;

    
    
    Message(String userName,String title, String content,String parentId,String messageId,String totalPosts,long epochTime){
        this.userName=userName;
        this.content=content;
        this.parentId=parentId;
        this.threadId=messageId;
        this.totalPosts=totalPosts;
        this.epochTime=epochTime;
              
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the isParent
     */
    public Boolean getIsParent() {
        return isParent;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @return the threadId
     */
    public String getThreadId() {
        return threadId;
    }

  
    /**
     * @return the totalPosts
     */
    public String getTotalPosts() {
        return totalPosts;
    }

   public long getEpochTime() {
        return epochTime;
    }           
    
    
}
