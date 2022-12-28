package com.dsi.myapplication;

public class Quran {
    private String num ;

    public Quran(String num) {
        this.num = num;
    }

    public String nombreOfChap(){
        String res = "001";
       switch (num){
           case "الفاتحة" :
               res = "001";
               break;
           case "البقرة" :
               res = "002";
               break;
           case "آل عِمران" :
               res = "003";
               break;
           case "النساء" :
               res = "004";
               break;
       }
       return res ;
   }
}
