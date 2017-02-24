package homework_1;

/**
 * На вход консольного приложения передается строка слов, разделенных пробелами. Твоя задача сделать из слов анаграммы ("задом наперед")
 * при этом оставив порядок слов неизменными. Результат вывести в консоль. Пример "мама мыла раму" => "амам алым умар"
 * при решении задачи нельзя использовать дополнительную память. В том числе, когда это происходит в неявном виде при вызове функций стандартной библиотеки.
 */
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
