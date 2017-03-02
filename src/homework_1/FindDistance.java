package homework_1;


public class FindDistance {
    public static void main(String[] args) {
        int[] array = {3, 45, 34, -1, 45, 4, -1, 2, -1, 3, 1};

        System.out.println(minDistance(array));
    }

    private static int minDistance(int[] targetArray) {

        if (targetArray.length < 2) {
            return -1;
        }

        int min1 = targetArray[0];
        int indexMin1 = 0;
        int min2 = targetArray[1];
        int indexMin2 = 1;

        if (targetArray[1] <= targetArray[0]) {
            min1 = targetArray[1];
            indexMin1 = 1;
            min2 = targetArray[0];
            indexMin2 = 0;
        }

        for (int i = 2; i < targetArray.length; i++) {
            if (targetArray[i] <= min1) {
                min2 = min1;
                indexMin2 = indexMin1;
                min1 = targetArray[i];
                indexMin1 = i;
            } else {
                if (targetArray[i] < min2) {
                    min2 = targetArray[i];
                    indexMin2 = i;
                }
                if (targetArray[i] == min2 && Math.abs(i - indexMin1) < Math.abs(indexMin2 - indexMin1)) {
                    min2 = targetArray[i];
                    indexMin2 = i;
                }
            }
        }
        return Math.abs(indexMin2 - indexMin1);
    }
}
