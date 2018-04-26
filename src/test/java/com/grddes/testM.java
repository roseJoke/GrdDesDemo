package com.grddes;

import groovy.transform.Synchronized;

/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/3/19  17:26
 */
public class testM {
    public testM(){
        System.out.println("执行了testM的构造方法啊！");
    }

    public String test() {

        synchronized(this){
           return new apple().out();
        }

    }

 /*   public static void main(String[] args){
        for(int i=0;i<10;i++) {
            testM t = new testM();
            System.out.println(t.test());
        }
    }*/
}
