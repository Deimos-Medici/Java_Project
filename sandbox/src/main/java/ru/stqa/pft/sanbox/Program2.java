package ru.stqa.pft.sanbox;

public class Program2 {
    public static void main(String[] args){
        Point a = new Point();
        a.x = 6;
        a.y = 9;
        Point f = new Point();
        f.x = 2;
        f.y = 6;
        System.out.println("Расстояние между точками " + Point.distance(a, f));
    }

}
