package com.Security;

import static java.lang.String.format;

public class AuthEnc {

    public String i;
    public String p;

    public AuthEnc(){}

    @Override
    public String toString(){
        return format("i:\"%s\",p:\"%s\"",i,p);
    }

}
