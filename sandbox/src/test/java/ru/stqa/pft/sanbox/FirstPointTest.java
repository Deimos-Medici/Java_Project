package ru.stqa.pft.sanbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstPointTest {

    @Test
    public void testPoint(){
        Point h = new Point(6, 9);
        Point j = new Point(2, 6);
        Assert.assertEquals(Point.distance(h, j), 5.0);
    }
}
