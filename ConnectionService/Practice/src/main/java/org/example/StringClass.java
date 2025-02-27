package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringClass
{
    public void reverseString(){
        String s="Shruti";
    Stream.of(s).map(str->new StringBuilder(str).reverse()).forEach(System.out::println);
}

public void swapTwoStrings(){
//        StringBuilder  s1= new StringBuilder("Shruti");
//        StringBuilder  s2=new StringBuilder("Saxena");
//        s2=s2.append(s1);
//        s1= new StringBuilder(s2.substring(0, 6));
//        System.out.println(s1);
//        s2= new StringBuilder(s2.substring(6));
//        System.out.println(s2);

          String s1 = "shruti";
          String s2 = "saxena";

          s1= s1+s2;
          s2=s1.substring(0,s1.length()-s2.length());
          s1=s1.substring(s2.length());
          System.out.println(s2 +" "+s1);
}
public void reverseWordOfAString(){
        String str= "Hello my name is Shruti Saxena";
        Stream.of(str.split(" ")).map(word->new StringBuilder(word).reverse()).forEach(System.out::println);
}
public void countOccurenceOfEachCharacter(){
        String s="mississippi";
        s.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).forEach((a,b)->System.out.println(a+" "+b));
}
public void countOccurenceOfCharacter(){
    Map<Character, Integer> characterIntegerMap=new HashMap<>();
    String s="mississippi";
    char arr[]=s.toCharArray();
    for(int i=0;i<arr.length;i++){
        if(!characterIntegerMap.containsKey(arr[i])){
            characterIntegerMap.put(arr[i],1);
        }
        else{
            characterIntegerMap.put(arr[i],characterIntegerMap.get(arr[i])+1);
        }
    }
    characterIntegerMap.entrySet().stream().forEach(a-> System.out.println(a.getKey()+" "+a.getValue()));
}
}
