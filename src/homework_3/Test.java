package homework_3;


import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        HtmlHandler handler = new HtmlHandler();
        StringBuilder myStringBuilder = handler.handleWebPage("https://eda.ru/recepty/osnovnye-blyuda/kurica-pikasso-25902");
        ParseHtml parseHtml = new ParseHtml();
        System.out.println(parseHtml.parseHtmlDoc(myStringBuilder));

    }
}
