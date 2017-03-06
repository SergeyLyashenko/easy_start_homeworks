package homework_3;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sergey on 06.03.2017.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        HtmlHandler handler = new HtmlHandler();
        StringBuilder myStringBuilder = handler.handleWebPage("https://eda.ru/recepty/osnovnye-blyuda/kurica-pikasso-25902");
        ParseHtml parseHtml = new ParseHtml();
        System.out.println(parseHtml.parseHtmlDoc(myStringBuilder));

     /*   Stack<WhereAmI> stack = new Stack<WhereAmI>();
        stack.push(WhereAmI.BETWEEN_TAGS);
        stack.push(WhereAmI.INSIDE_COMMENT_WITH_DOUBLE_QUOTES);
        stack.push(WhereAmI.INSIDE_COMMENT_WITH_SINGLE_QUOTES);
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
*/

    }
}
