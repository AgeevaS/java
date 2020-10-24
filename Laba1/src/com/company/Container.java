package com.company;


import java.util.Arrays;
import java.util.Scanner;

public class Container
{
    private int capacity;
    private int size;
    private int[] array;
    private int coef=2;

    Container(){
        size=0;
        capacity=10;
        array=new int[capacity];
    }

    public int getSize()
    {
        return this.size;
    }

    public boolean isFull()
    {
        return size==capacity-1;
    }

    public void add(int el)
    {
        if (isFull())
            changeSize();
        array[size++] = el;
    }

    public void add_to_the_place(final int el, final int index){
        int newSize=size-index;
        final int[] ar = new int[newSize];
        System.arraycopy(array, index, ar, 0, newSize);
        array[index]=el;
        if(size+1==capacity-1){
            changeSize();
        }
        System.arraycopy(ar, 0, array, index+1, newSize);
        ++size;
    }

    public void delete(final int index){
        if (index > size - 1) {
            System.out.println("error");
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        --size;
    }

    public void print(){
        for(int i=0;i<size;++i)
            System.out.print(array[i]+" ");
        System.out.println();
    }

    protected void changeSize(){
        int[] newArray=new int[size];
        System.arraycopy(array, 0, newArray, 0, size);
        capacity*=coef;
        array=new int[capacity];
        System.arraycopy(newArray, 0, array, 0, size);
    }
}
