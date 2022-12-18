package hn.iargueta.zuulgateway.enums;

import lombok.Getter;

@Getter
public enum ZuulFilterType {
    PRE("pre"),POST("post");
    private String name;
    ZuulFilterType(String name){
        this.name = name;
    }
}
