package ru.stqa.pft.sanbox;

public class Program2 {
    public static void main(String[] args){
        Point a = new Point(6, 9);
        Point f = new Point(2, 6);
        System.out.println("Расстояние между точками " + Point.distance(a, f));
    }

}
