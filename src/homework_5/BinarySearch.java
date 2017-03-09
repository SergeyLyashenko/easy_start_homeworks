package homework_5;

public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {21, 22, 23, 24, 24, 27, 27, 27, 27, 27, 27, 27, 27, 12, 13, 14, 15, 16, 17, 18, 19, 21};

        System.out.println(binSearch(array, 21));
        System.out.println(binSearch(array, 27));
        System.out.println(binSearch(array, 11));
        System.out.println(binSearch(array, 12));
    }


    public static int binSearch(int[] array, int target) {
        int indexOfMax = findIndexOfMax(array);
        if (target == array[0]) {
            return 0;
        }
        if (target > array[0]) {
            return binSearchWithIndexes(array, target, 0, indexOfMax);
        } else {
            return binSearchWithIndexes(array, target, indexOfMax + 1, array.length - 1);
        }
    }

    public static int binSearchWithIndexes(int[] array, int target, int leftIndex, int rightIndex) {
        int middleIndex;

        while (leftIndex < rightIndex) {
            middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (array[middleIndex] < target) {
                leftIndex = middleIndex + 1;
            } else {
                rightIndex = middleIndex;
            }
        }
        return target == array[rightIndex] ? rightIndex : -1;
    }

    public static int findIndexOfMax(int[] array) {
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        int indexOfMax = array.length - 1;
        int middleIndex;

        while (leftIndex < rightIndex) {
            middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (array[middleIndex] >= array[indexOfMax]) {
                leftIndex = middleIndex + 1;
                indexOfMax = middleIndex;
            } else {
                rightIndex = middleIndex;
            }
        }
        return indexOfMax;
    }
}
