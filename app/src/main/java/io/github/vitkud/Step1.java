package io.github.vitkud;

import java.util.Random;

public class Step1 {
    static int[] boats = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

    static int[][] pole1 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    static int[][] pole2 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    public static void printpole(int[][] pole1, int[][] pole2) { // Печатает оба поля
        System.out.println("      А Б В Г Д Е Ж З И К             А Б В Г Д Е Ж З И К");
        System.out.println("    |--------------------           |--------------------");
        for (int y = 0; y < pole1.length; y++) { //  заполняет цифры слева от поля
            String n;
            if (y < 9)
                n = " ";
            else n = "";
            System.out.print((y + 1) + " " + n + " | ");
            for (int x = 0; x < pole1[y].length; x++)
                System.out.print(pole1[y][x] + " ");
            System.out.print("     " + " ");
            System.out.print((y + 1) + " " + n + " | ");
            for (int x2 = 0; x2 < pole2[y].length; x2++)
                System.out.print(pole2[y][x2] + " ");
            System.out.println();
        }
    }

    static boolean place_ship(int[][] field, int width, int height, int x1, int y1) {       // Размещает корабль в указанном поле с указанными координатами.
        for (int iy = y1 - 1; iy < y1 + height + 1; iy++) {
            for (int ix = x1 - 1; ix < x1 + width + 1; ix++) {
                if (iy < 0 || iy >= 10 || ix < 0 || ix >= 10)    // Проверка на выход за границы поля
                    continue;
                if (field[iy][ix] != 0)      // Проверка на то, что клетки размещения корабля и на 1 клетку вокруг не заняты.
                    return false;
            }
        }
        for (int iy = y1; iy < y1 + height; iy++)              // Размещение "корабля" на поле
            for (int ix = x1; ix < x1 + width; ix++)
                field[iy][ix] = 2;
        return true;
    }

    static void place_ship_rnd(int[][] field, int width, int height) {          // Генерация координаты случайного размещения корабля на поле
        Random random = new Random();
        while (true) {
            int x1 = random.nextInt(10 - width + 1);
            int y1 = random.nextInt(10 - height + 1);
            boolean is_placed = place_ship(field, width, height, x1, y1);    // вызов процедуры размещения корабля
            if (is_placed)                            // Если результат вызова процедуры True, то разрываем бесконечный цикл.
                break;                                // TODO А если не смогли разместить, то что???
        }
    }

    static void autofill(int[][] field) {
        Random random = new Random();
        for (int boat : boats) {
            if (boat == 1)
                place_ship_rnd(field, 1, 1);
            else {
                boolean horizont = random.nextBoolean();                   // Генерация случайного положения горизонт/вертикаль
                if (horizont)
                    place_ship_rnd(field, boat, 1);
                else
                    place_ship_rnd(field, 1, boat);
            }
        }
    }

    public static void main(String[] args) {
        autofill(pole1);
        autofill(pole2);
        printpole(pole1, pole2);
    }
}
