package com.dm;

import javax.persistence.*;

/**
 * Created by liran on 5/4/17.
 */
@Entity(name="LINKS")
public class Link {
    @Id
    @Column(name = "LINK_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long linkID;

    @Column(name = "HEADLINE")
    private String lintkHeadLine;

    @Column(name = "URL")
    private String linkURL;

    @Override
    public String toString(){
        return String.format("{linkID:\"%s\",linkHeadLine:\"%s\",linkURL:\"%s\"}",linkID, lintkHeadLine, linkURL);
    }
    public Link(){}
    public Link(Long linkID, String lintkHeadLine, String linkURL) {
        this.linkID = linkID;
        this.lintkHeadLine = lintkHeadLine;
        this.linkURL = linkURL;
    }

    public Long getLinkID() {
        return linkID;
    }

    public void setLinkID(Long linkID) {
        this.linkID = linkID;
    }

    public String getLintkHeadLine() {
        return lintkHeadLine;
    }

    public void setLintkHeadLine(String lintkHeadLine) {
        this.lintkHeadLine = lintkHeadLine;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }
}
