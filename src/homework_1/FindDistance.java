package homework_1;

/**
 * Твоя задача создать консольное приложение, которое принимало бы на вход ряд чисел и выводило расстояние между двумя наименьшими.
 * Например, дано ряд чисел: "23 45 34 12 45 4 38 56 2 49 100". Наименьшие числа в нем 2 и 4. Расстояние между ними - 3.
 */
public class FindDistance {
    public static void main(String[] args) {
        int[] mas = {3, 45, 34, -1, 45, 4, -1, 2, -1, 3, 1};


        System.out.println(minDistance(mas));
    }

    private static int minDistance(int[] mas) {
        int min1 = mas[0];
        int indexMin1 = 0;
        int min2 = mas[1];
        int indexMin2 = 1;

        if (mas[1] <= mas[0]) {
            min1 = mas[1];
            indexMin1 = 1;
            min2 = mas[0];
            indexMin2 = 0;
        }
        
        for (int i = 2; i < mas.length; i++) {
            if (mas[i] <= min1) {
                min2 = min1;
                indexMin2 = indexMin1;
                min1 = mas[i];
                indexMin1 = i;
            } else {
                if (mas[i] < min2) {
                    min2 = mas[i];
                    indexMin2 = i;
                }
                if (mas[i] == min2 && Math.abs(i - indexMin1) < Math.abs(indexMin2 - indexMin1)) {
                    min2 = mas[i];
                    indexMin2 = i;
                }
            }
        }
        return Math.abs(indexMin2 - indexMin1);
    }
}
