package com.dm;

import javax.persistence.*;

/**
 * Created by liran on 22/07/17.
 */
@Entity(name = "KEEP_ALIVE")
public class KeepAlive {
    @Id
    @Column(name = "KEEP_ALIVE_ID")
    private Long KeepAliveID;

    @Transient
    private static KeepAlive keepAliveInstance = null;
    public KeepAlive() {
    }

    private KeepAlive(long keepAliveID) {
        this.KeepAliveID = keepAliveID;
    }
    public Long getKeepAliveID() {
        return KeepAliveID;
    }

    public static KeepAlive getKeepAliveInstance(){
        if(keepAliveInstance == null)
            keepAliveInstance = new KeepAlive(1);
        return keepAliveInstance;
    }
    @Override
    public String toString(){
        return String.format("{keepAliveID:\"%d\"}",KeepAliveID);
    }
}
