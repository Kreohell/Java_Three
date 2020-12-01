package test;

import lesson_f.HomeWorks;
import org.junit.Assert;
import org.junit.Test;
//add hw3

public class TestHomeWorks {

    //task2
    @Test
    public void testMethodOneEquals(){
        HomeWorks homeWorks = new HomeWorks();
       Assert.assertArrayEquals(new int[]{1, 7}, homeWorks.methodOne(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}));
    }

    @Test
    public void testMethodOneEqualsWithoutNumber(){
        HomeWorks homeWorks = new HomeWorks();
        Assert.assertArrayEquals(new int[]{1, 7}, homeWorks.methodOne(new int[]{1, 2, 3, 11, 2, 3, 2, 1, 7}));
    }

    @Test
    public void testMethodOneNotNull(){
        HomeWorks homeWorks = new HomeWorks();
        Assert.assertNotNull((homeWorks.methodOne(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7})));
    }


    //task3
    @Test
    public void testMethodTwoWithoutAnotherNumbers(){
        HomeWorks homeWorks = new HomeWorks();
        Assert.assertTrue("Another numbers in array", homeWorks.methodTwo(new int[]{1, 4, 1, 4, 4, 4, 4, 1}));
    }

    @Test
    public void testMethodTwoWithAnotherNumbers(){
        HomeWorks homeWorks = new HomeWorks();
        Assert.assertTrue("Another numbers in array", homeWorks.methodTwo(new int[]{1, 4, 1, 7, 4, 4, 4, 1}));
    }

    @Test
    public void testMethodTwoWithoutNumberOne(){
        HomeWorks homeWorks = new HomeWorks();
        Assert.assertTrue("Without number: 1", homeWorks.methodTwo(new int[]{4, 4, 4, 4, 4, 4, 4, 4}));
    }

    @Test
    public void testMethodTwoWithoutNumberFour(){
        HomeWorks homeWorks = new HomeWorks();
        Assert.assertTrue("Without number: 4", homeWorks.methodTwo(new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }

}
