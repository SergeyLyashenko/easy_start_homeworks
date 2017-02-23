package homework_1;

/**
 * На вход консольного приложения передается строка слов, разделенных пробелами. Твоя задача сделать из слов анаграммы ("задом наперед")
 * при этом оставив порядок слов неизменными. Результат вывести в консоль. Пример "мама мыла раму" => "амам алым умар"
 * при решении задачи нельзя использовать дополнительную память. В том числе, когда это происходит в неявном виде при вызове функций стандартной библиотеки.
 */
public class Anagrams {
    public static void main(String[] args) {
        String myString = "мама мыла раму";
        printAnagram(myString, " ");
    }

    private static void printAnagram(String string, String splitter) {
        int firstIndex = 0;

        //проверить содержит ли строка сплиттер
        int lastIndex = string.contains(splitter) ? string.indexOf(splitter) : string.length();

        for (int i = 0; i < string.length(); i++) {

            //выводим сам сплиттер и ищем следующее слово
            if (i == lastIndex) {
                firstIndex = lastIndex + splitter.length();
                i += splitter.length() - 1;
                lastIndex = string.indexOf(splitter, lastIndex + splitter.length()) == -1 ? string.length() : string.indexOf(splitter, lastIndex + splitter.length());
                System.out.print(splitter);
            }
            //выводим слово в обратном порядке
            else {
                System.out.print(string.charAt(lastIndex - i - 1 + firstIndex));
            }
        }
    }
}
