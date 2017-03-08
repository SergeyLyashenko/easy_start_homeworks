package homework_3;

import java.util.HashMap;
import java.util.Stack;


public class ParseHtml {
    public String parseHtmlDoc(StringBuilder htmlDoc) {
        StringBuilder builder = new StringBuilder();
        Stack<Position> stack = new Stack<>();
        stack.push(Position.BETWEEN_TAGS);
        char temp;

        final HashMap<String, String> lookupMap = new HashMap<>();
        for (final String[] seq : EntityList.entities)
            lookupMap.put(seq[1], seq[0]);

        String entity = "";

        for (int i = 0; i < htmlDoc.length(); i++) {

            temp = htmlDoc.charAt(i);


            if (temp == '<' && htmlDoc.charAt(i + 1) == 's' && htmlDoc.charAt(i + 2) == 'c'
                    && (stack.peek() != Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES
                    || stack.peek() != Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES)) {
                stack.push(Position.INSIDE_SCRIPT_TAG);
                continue;
            }
            if (temp == '<' && htmlDoc.charAt(i + 1) == '/' && htmlDoc.charAt(i + 2) == 's' && htmlDoc.charAt(i + 3) == 'c'
                    && stack.peek() == Position.INSIDE_SCRIPT_TAG) {
                stack.pop();
                i += 8;
                continue;
            }
            if (stack.peek() == Position.INSIDE_SCRIPT_TAG) {
                continue;
            }
            if (temp == '<' && htmlDoc.charAt(i + 1) == 's' && htmlDoc.charAt(i + 2) == 't'
                    && (stack.peek() != Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES
                    || stack.peek() != Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES)) {
                stack.push(Position.INSIDE_STYLE_TAG);
                continue;
            }
            if (temp == '<' && htmlDoc.charAt(i + 1) == '/' && htmlDoc.charAt(i + 2) == 's' && htmlDoc.charAt(i + 3) == 't'
                    && stack.peek() == Position.INSIDE_STYLE_TAG) {
                stack.pop();
                i += 7;
                continue;
            }
            if (stack.peek() == Position.INSIDE_STYLE_TAG) {
                continue;
            }
            if (temp == '<' && (stack.peek() != Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES
                    || stack.peek() != Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES)) {
                stack.push(Position.INSIDE_TAG);
                continue;
            }
            if (temp == '>' && stack.peek() == Position.INSIDE_TAG) {
                stack.pop();
                continue;
            }
            if (temp == '\'' && stack.peek() != Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES) {
                stack.push(Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES);
                continue;
            }
            if (temp == '\'' && stack.peek() == Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES) {
                stack.pop();
                continue;
            }
            if (temp == '\"' && stack.peek() != Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES) {
                stack.push(Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES);
                continue;
            }
            if (temp == '\"' && stack.peek() == Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES) {
                stack.pop();
                continue;
            }
            if (temp == '&' && stack.peek() == Position.BETWEEN_TAGS) {
                stack.push(Position.ENTITY_CODE);
                //entity.append(temp);
                continue;
            }
            if (temp == ';' && stack.peek() == Position.ENTITY_CODE) {
                entity = "";
                stack.pop();
                continue;
            }
            if (stack.peek() == Position.ENTITY_CODE) {
                entity += temp;
                if (lookupMap.containsKey(entity))
                    builder.append(lookupMap.get(entity));
                continue;
            }

            if (stack.peek() == Position.BETWEEN_TAGS) {

                if (temp == ' ' && (builder.length() == 0 || builder.charAt(builder.length() - 1) == ' '))
                    continue;
                builder.append(temp);
            }
        }
        return builder.toString();
    }
}
