package io.github.vitkud;

// Шаг 1. Расстановка кораблей пользователя и компьютера
// создаём два массива расстановки кораблей

// Шаг 2. Вывести оба поля на экран.

import java.util.Random;

public class Step1 {
    public static int[] boats = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    public static int[][] pole1 = new int[10][10];
    public static int[][] pole2 = new int[10][10];

    public static int[][] init_field() {
        int[][] field = new int[10][10];
        reset_field(field);
        return field;
    }

    public static void reset_field(int[][] field) {
        for (int yy = 0; yy < field.length; yy++)
            for (int xx = 0; xx < field[yy].length; xx++)
                field[yy][xx] = 0;
    }

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

    public static boolean place_ship(int[][] field, int ship, boolean horizont, int x1, int y1) {
        int h = !horizont ? ship : 1;
        int w = horizont ? ship : 1;
        for (int iy = y1 - 1; iy < y1 + h + 1; iy++) {
            for (int ix = x1 - 1; ix < x1 + w + 1; ix++) {
                if (iy < 0 || iy >= 10 || ix < 0 || ix >= 10)
                    continue;
                if (field[iy][ix] != 0)
                    return false;
            }
        }
        for (int i = 0; i < ship; i++)
            if (horizont) field[y1][x1+i] = 2;
            else field[y1+i][x1] = 2;

        return true;
    }

    public static void place_ship_rnd(int[][] field, int boat) {          // Генерация координаты случайного размещения корабля на поле
        Random random = new Random();
        while (true) {
            boolean horizont = random.nextBoolean();
            int x1, y1;
            if (horizont) {
                x1 = random.nextInt(10 - boat + 1);
                y1 = random.nextInt(10);
            } else {
                x1 = random.nextInt(10);
                y1 = random.nextInt(10 - boat + 1);
            }
            boolean is_placed = place_ship(field, boat, horizont, x1, y1);    // вызов процедуры размещения корабля
            if (is_placed)                            // Если результат вызова процедуры True, то разрываем бесконечный цикл.
                break;                                // TODO А если не смогли разместить, то что???
        }
    }

    public static void autofill(int[][] field) {
        for (int boat : boats)
            place_ship_rnd(field, boat);
    }

    public static void main(String[] args) {
        pole1 = init_field();
        pole2 = init_field();
        autofill(pole1);
        printpole(pole1, pole2);
        reset_field(pole1);
        printpole(pole1, pole2);
    }

}
