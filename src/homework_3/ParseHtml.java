package homework_3;

import java.util.HashMap;
import java.util.Stack;


public class ParseHtml {
    public String parseHtmlDoc(StringBuilder htmlDoc) {
        StringBuilder builder = new StringBuilder();
        Stack<Position> myPosition = new Stack<>();
        myPosition.push(Position.BETWEEN_TAGS);
        char temp;

        final HashMap<String, String> lookupMap = new HashMap<>();
        for (final String[] seq : EntityList.entities)
            lookupMap.put(seq[1], seq[0]);

        String entity = "";

        for (int i = 0; i < htmlDoc.length(); i++) {

            temp = htmlDoc.charAt(i);


            if (temp == '<' && htmlDoc.charAt(i + 1) == 's' && htmlDoc.charAt(i + 2) == 'c'
                    && (myPosition.peek() != Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES
                    || myPosition.peek() != Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES)) {
                myPosition.push(Position.INSIDE_SCRIPT_TAG);
                continue;
            }
            if (temp == '<' && htmlDoc.charAt(i + 1) == '/' && htmlDoc.charAt(i + 2) == 's' && htmlDoc.charAt(i + 3) == 'c'
                    && myPosition.peek() == Position.INSIDE_SCRIPT_TAG) {
                myPosition.pop();
                i += 8;
                continue;
            }
            if (myPosition.peek() == Position.INSIDE_SCRIPT_TAG) {
                continue;
            }
            if (temp == '<' && htmlDoc.charAt(i + 1) == 's' && htmlDoc.charAt(i + 2) == 't'
                    && (myPosition.peek() != Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES
                    || myPosition.peek() != Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES)) {
                myPosition.push(Position.INSIDE_STYLE_TAG);
                continue;
            }
            if (temp == '<' && htmlDoc.charAt(i + 1) == '/' && htmlDoc.charAt(i + 2) == 's' && htmlDoc.charAt(i + 3) == 't'
                    && myPosition.peek() == Position.INSIDE_STYLE_TAG) {
                myPosition.pop();
                i += 7;
                continue;
            }
            if (myPosition.peek() == Position.INSIDE_STYLE_TAG) {
                continue;
            }
            if (temp == '<' && (myPosition.peek() != Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES
                    || myPosition.peek() != Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES)) {
                myPosition.push(Position.INSIDE_TAG);
                continue;
            }
            if (temp == '>' && myPosition.peek() == Position.INSIDE_TAG) {
                myPosition.pop();
                continue;
            }
            if (temp == '\'' && myPosition.peek() != Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES) {
                myPosition.push(Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES);
                continue;
            }
            if (temp == '\'' && myPosition.peek() == Position.INSIDE_COMMENT_WITH_SINGLE_QUOTES) {
                myPosition.pop();
                continue;
            }
            if (temp == '\"' && myPosition.peek() != Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES) {
                myPosition.push(Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES);
                continue;
            }
            if (temp == '\"' && myPosition.peek() == Position.INSIDE_COMMENT_WITH_DOUBLE_QUOTES) {
                myPosition.pop();
                continue;
            }
            if (temp == '&' && myPosition.peek() == Position.BETWEEN_TAGS) {
                myPosition.push(Position.ENTITY_CODE);
                //entity.append(temp);
                continue;
            }
            if (temp == ';' && myPosition.peek() == Position.ENTITY_CODE) {
                entity = "";
                myPosition.pop();
                continue;
            }
            if (myPosition.peek() == Position.ENTITY_CODE) {
                entity += temp;
                if (lookupMap.containsKey(entity))
                    builder.append(lookupMap.get(entity));
                continue;
            }

            if (myPosition.peek() == Position.BETWEEN_TAGS) {

                if (temp == ' ' && (builder.length() == 0 || builder.charAt(builder.length() - 1) == ' '))
                    continue;
                builder.append(temp);
            }
        }
        return builder.toString();
    }
}
