package org.example;

public class AdditionSub extends Addition{
    public int add(int a,int b){
        System.out.println("In child class");
        return a+b;
    }
}
