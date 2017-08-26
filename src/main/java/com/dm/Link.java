package com.dm;

import javax.persistence.*;

import static java.lang.String.format;

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
    private String linkHeadLine;

    @Column(name = "URL")
    private String linkURL;

    @Override
    public String toString(){
        return format("{linkHeadLine:\"%s\",linkURL:\"%s\"}",linkID, linkHeadLine, linkURL);
    }
    public Link(){}
    public Link(Long linkID, String lintkHeadLine, String linkURL) {
        this.linkID = linkID;
        this.linkHeadLine = lintkHeadLine;
        this.linkURL = linkURL;
    }

    public Long getLinkID() {
        return linkID;
    }

    public void setLinkID(Long linkID) {
        this.linkID = linkID;
    }

    public String getLinkHeadLine() {
        return linkHeadLine;
    }

    public void setLinkHeadLine(String linkHeadLine) {
        this.linkHeadLine = linkHeadLine;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }
}
