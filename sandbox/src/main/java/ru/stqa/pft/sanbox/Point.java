package ru.stqa.pft.sanbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double sum() {
        return x + y;
    }

    public double distance(Point p) {
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
//        return Math.sqrt((this.x - p.x)*(this.x - p.x)+(this.y - p.y)*(this.y - p.y));
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt((p2.x - p1.x)*(p2.x - p1.x)+(p2.y - p1.y)*(p2.y - p1.y));
    }

    public static double sum(Point p) {
        return p.x +p.y;
    }

}
