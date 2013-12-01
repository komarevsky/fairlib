package com.freebetbot.test.fairlib.type;

import com.freebetbot.fairlib.type.FairPrice;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class PriceTest {
    
    public PriceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void isValidTest() {
        System.out.println("=================================================");
        System.out.println("isValidTest");
        
        double p1 = 1.38;
        FairPrice f1 = new FairPrice(p1);
        assertTrue(f1.isValid());
        
        p1 = 1.38223;
        f1.setPrice(p1);
        assertFalse(f1.isValid());
        
        p1 = 0;
        f1.setPrice(p1);
        assertFalse(f1.isValid());
        
        p1 = 1.009999999;
        f1.setPrice(p1);
        assertFalse(f1.isValid());
        
        p1 = 1.01;
        f1.setPrice(p1);
        assertTrue(f1.isValid());
        
        p1 = 1000;
        f1.setPrice(p1);
        assertTrue(f1.isValid());
        
        p1 = 1001;
        f1.setPrice(p1);
        assertFalse(f1.isValid());

        System.out.println("=================================================");
    }
    
    @Test
    public void printNextTest() {
        System.out.println("=================================================");
        System.out.println("printNextTest");
        
        double maxPrice = FairPrice.getMaxPrice();
        FairPrice f1 = new FairPrice(FairPrice.getMinPrice());
        System.out.println(f1.getPrice());
        do {
            f1 = f1.getNextPrice();
            System.out.println(f1.getPrice());
        } while (f1.getPrice() < maxPrice);
        
        f1 = f1.getNextPrice();
        assertTrue(f1.getPrice() == maxPrice);
        
        System.out.println("=================================================");
    }

    @Test
    public void printPrevTest() {
        System.out.println("=================================================");
        System.out.println("printPrevTest");
        
        double minPrice = FairPrice.getMinPrice();
        FairPrice f1 = new FairPrice(FairPrice.getMaxPrice());
        System.out.println(f1.getPrice());
        do {
            f1 = f1.getPreviousPrice();
            System.out.println(f1.getPrice());
        } while (f1.getPrice() > minPrice);
        
        f1 = f1.getPreviousPrice();
        assertTrue(f1.getPrice() == minPrice);
        
        System.out.println("=================================================");
    }

}
