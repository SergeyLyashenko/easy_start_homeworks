package homework_3;

import java.util.Stack;


public class ParseHtml {
    public String parseHtmlDoc(StringBuilder htmlDoc) {
        StringBuilder builder = new StringBuilder();
        Stack<WhereAmI> stack = new Stack<>();
        stack.push(WhereAmI.BETWEEN_TAGS);
        char temp;

        for (int i = 0; i < htmlDoc.length(); i++) {

            temp = htmlDoc.charAt(i);

            if (temp == '<' && htmlDoc.charAt(i + 1) == 's' && htmlDoc.charAt(i + 2) == 'c'
                && (stack.peek() != WhereAmI.INSIDE_COMMENT_WITH_SINGLE_QUOTES
                    || stack.peek() != WhereAmI.INSIDE_COMMENT_WITH_DOUBLE_QUOTES)) {
                stack.push(WhereAmI.INSIDE_SCRIPT_TAG);
                continue;
            }
            if (temp == '<' && htmlDoc.charAt(i + 1) == '/' && htmlDoc.charAt(i + 2) == 's' && htmlDoc.charAt(i + 3) == 'c'
                    && stack.peek() == WhereAmI.INSIDE_SCRIPT_TAG) {
                stack.pop();
                i += 8;
                continue;
            }
            if (stack.peek() == WhereAmI.INSIDE_SCRIPT_TAG) {
                continue;
            }
            if (temp == '<' && (stack.peek() != WhereAmI.INSIDE_COMMENT_WITH_SINGLE_QUOTES
                    || stack.peek() != WhereAmI.INSIDE_COMMENT_WITH_DOUBLE_QUOTES)) {
                stack.push(WhereAmI.INSIDE_TAG);
                continue;
            }
            if (temp == '>' && stack.peek() == WhereAmI.INSIDE_TAG) {
                stack.pop();
                continue;
            }
            if (temp == '\'' && stack.peek() != WhereAmI.INSIDE_COMMENT_WITH_SINGLE_QUOTES) {
                stack.push(WhereAmI.INSIDE_COMMENT_WITH_SINGLE_QUOTES);
                continue;
            }
            if (temp == '\'' && stack.peek() == WhereAmI.INSIDE_COMMENT_WITH_SINGLE_QUOTES) {
                stack.pop();
                continue;
            }
            if (temp == '\"' && stack.peek() != WhereAmI.INSIDE_COMMENT_WITH_DOUBLE_QUOTES) {
                stack.push(WhereAmI.INSIDE_COMMENT_WITH_DOUBLE_QUOTES);
                continue;
            }
            if (temp == '\"' && stack.peek() == WhereAmI.INSIDE_COMMENT_WITH_DOUBLE_QUOTES) {
                stack.pop();
                continue;
            }
            if (stack.peek()==WhereAmI.BETWEEN_TAGS) {
                builder.append(temp);
            }
        }
        return builder.toString();
    }
}
