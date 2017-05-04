package com.dm;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by liran on 5/4/17.
 */
public class Link {
    @Id
    @Column(name = "LINK_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long linkID;

    @Column(name = "HEADLINE")
    private String headLine;

    @Column(name = "URL")
    private String URL;

    public Long getLinkID() {
        return linkID;
    }

    public Link(Long linkID, String headLine, String URL) {
        this.linkID = linkID;
        this.headLine = headLine;
        this.URL = URL;
    }

    public Link(){}

    public void setLinkID(Long linkID) {
        this.linkID = linkID;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
