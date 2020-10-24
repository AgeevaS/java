package com.company;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Container a=new Container();
        System.out.println("Введите количество чисел: ");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt(), k;

        for (int i = 0; i < number; i++) {
            k = scan.nextInt();
            a.add(k);
        }
        System.out.print("Вы ввели: ");
        a.print();
        System.out.println("размер массива "+a.getSize());
        Scanner scc = new Scanner(System.in);
        System.out.print("номер какого элемента удалить? ");
        int bb = scc.nextInt()-1;
        a.delete(bb);
        a.print();

        Scanner sc = new Scanner(System.in);
        System.out.print("введите куда вставить  ");
        int b = sc.nextInt()-1;
        System.out.print("Введите число, которое необходимо вставить:  ");
        int s = sc.nextInt();
        a.add_to_the_place(s, b);
        a.print();

        scc.close(); scan.close(); sc.close();
    }
}
