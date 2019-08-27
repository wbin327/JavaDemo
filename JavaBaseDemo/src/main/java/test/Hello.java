package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Hello {
    public static String getFileName(String fileName){
        String[] list = fileName.split("\\.");
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<list.length-1; i++){
            builder.append(list[i]);
            if(i != list.length -2)
                builder.append(".");
        }
        return builder.toString();
    }

    public static String f2(String s) {return (s + "world");}



    public static void main(String[] args){
        String a = "hello/**";
        System.out.println(a.replace("/**", "/"));
        System.out.println(a);
        String b = "4.42.jpg";
        System.out.println(getFileName(b));
        String s2 = "hello";
        f2(s2);
        System.out.println(s2);
    }
}
