package ru.stqa.pft.sanbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstPointTest {

    @Test
    public void testPoint(){
        Point h = new Point(6, 9);
        Point j = new Point(2, 6);
        Assert.assertEquals(Point.distance(h, j), 5);
    }

    @Test
    public void  testPoint2(){
        Point d = new Point(4,1);
        Point s = new Point(4,7);
        Assert.assertEquals(d.distance(s), 6);
    }

    @Test
    public void testPoint3(){
        Point y = new Point(5, 6);
        Point g = new Point(5, 8);
        Assert.assertEquals(Point.distance(y, g), 2);
    }

    @Test
    public void  testPoint4(){
        Point u = new Point(10,6);
        Point b = new Point(2,6);
        Assert.assertEquals(u.distance(b), 8);
    }
}

