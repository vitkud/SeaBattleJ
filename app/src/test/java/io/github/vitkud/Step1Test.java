package io.github.vitkud;

import org.junit.Test;

import static org.junit.Assert.*;

public class Step1Test {

    @Test
    public void autofill() {
        Step1.pole1 = Step1.init_field();
        Step1.pole2 = Step1.init_field();
        Step1.autofill(Step1.pole1);
        Step1.printpole(Step1.pole1, Step1.pole2);
        int total = 0;
        for (int yy = 0; yy < 10; yy++) {
            for (int xx = 0; xx < 10; xx++) {
                if (Step1.pole1[yy][xx] > 0) {
                    total += 1;
                }
            }
        }
        assertEquals(4 + 3 * 2 + 2 * 3 + 4, total);
    }

    @Test
    public void place_ship() {
        Step1.pole1 = Step1.init_field();
        Step1.pole2 = Step1.init_field();
        assertTrue(Step1.place_ship(Step1.pole1, 3, true, 0, 0));
        assertFalse(Step1.place_ship(Step1.pole1, 3, true, 0, 0));
        Step1.printpole(Step1.pole1, Step1.pole2);
    }

}
