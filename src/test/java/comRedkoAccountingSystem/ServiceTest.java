package comRedkoAccountingSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Service class")
class ServiceTest {

    @RepeatedTest(10)
    @DisplayName("test getRandInt method for randomness")
    void testRandIntRandomness() {
        assertNotEquals(Service.getRandInt(0, 100000), Service.getRandInt(0, 100000), "Make not random numbers");
    }

    @RepeatedTest(100)
    @DisplayName("test getRandInt method for out of range")
    void testRandIntBorders() {
        int minVal = (int) (Math.random() * 10);
        int maxVal = (int) (Math.random() * 10) + 10;
        int randValue = Service.getRandInt(minVal, maxVal);
        assertTrue(minVal <= randValue && randValue <= maxVal, "Out of range");
    }

    @RepeatedTest(10)
    @DisplayName("test getRandInt method for minVal == maxVal")
    void testRandIntEqualBorder() {
        int val = (int) (Math.random() * 100) + 3;
        assertEquals(val, Service.getRandInt(val, val), "If left and right borders are equal it should return this value");
    }

    @RepeatedTest(10)
    @DisplayName("test getRandInt method for left border > right")
    void testRandIntWrongBorders() {
        int maxVal = (int) (Math.random() * 10);
        int minVal = (int) (Math.random() * 10) + 10;
        int randValue = Service.getRandInt(maxVal, minVal);
        assertTrue(maxVal <= randValue && randValue <= minVal, "If the left border is greater than the right one, the values should be swapped");
    }


    @RepeatedTest(10)
    @DisplayName("test getRandDouble method for randomness")
    void testRandDoubleRandomness() {
        assertNotEquals(Service.getRandDouble(0, 1000000), Service.getRandDouble(0, 100000), "Make not random numbers");
    }

    @RepeatedTest(100)
    @DisplayName("test getRandDouble method for out of range")
    void testRandDoubleBorders() {
        double minVal = Math.random() * 10;
        double maxVal = Math.random() * 10 + 10;
        double randValue = Service.getRandDouble(minVal, maxVal);
        assertTrue(minVal <= randValue && randValue <= maxVal, "Out of range");
    }

    @RepeatedTest(10)
    @DisplayName("test getRandDouble method for minVal == maxVal")
    void testRandDoubleEqualBorder() {
        int val = (int) (Math.random() * 100) + 3;
        assertEquals(val, (int)(Service.getRandDouble(val, val) + 0.5), "If left and right borders are equal it should return this value");
    }

    @RepeatedTest(10)
    @DisplayName("test getRandInt method for left border > right")
    void testRandDoubleWrongBorders() {
        double maxVal = Math.random() * 10;
        double minVal = Math.random() * 10 + 10;
        double randValue = Service.getRandDouble(maxVal, minVal);
        assertTrue(maxVal <= randValue && randValue <= minVal, "If the left border is greater than the right one, the values should be swapped");
    }

}