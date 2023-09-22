package com.kingmed.immuno.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  用于计数的类
 */
@Data
@AllArgsConstructor
public class IndexCounter {

    private int index;

    /**
     * max: list的长度, 也就是最大的index-1
     */
    private int max;

    public boolean isMax(){
        return this.index >= this.max;
    }

    public void add(){
        this.index+=1;
    }
    public void resetAdd(){
        this.index+=1;
        if(this.index >= this.max){
            this.index = 0;
        }
    }
    public void reSet(){
        this.index = 0;
    }

}
