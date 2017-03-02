package homework_1;

public class Anagrams {
    public static void main(String[] args) {
        String myString = "  мама  мыла раму рома ";
        printAnagram(myString, " ");

    }

    private static void printAnagram(String string, String splitter) {
        int firstIndex = 0;
        int lastIndex = 0;


        while (lastIndex != -1) {

            lastIndex = string.indexOf(splitter, firstIndex);

            if (splitter.length() == 0) {
                lastIndex = -1;
            }

            if (lastIndex == firstIndex) {
                System.out.print(splitter);
                firstIndex += splitter.length();
            } else {
                printAnagramWord(string, firstIndex, lastIndex);
                firstIndex = lastIndex;
            }
        }
    }

    private static void printAnagramWord(String string, int firstIndex, int lastIndex) {
        if (lastIndex == -1) {
            lastIndex = string.length();
        }
        for (int i = lastIndex - 1; i >= firstIndex; i--) {
            System.out.print(string.charAt(i));
        }
    }
}
