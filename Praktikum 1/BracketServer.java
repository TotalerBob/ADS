package praktikum_1;

import java.util.HashMap;
import java.util.Map;

public class BracketServer {
    HashMap<String, String> brackets = new HashMap<>();

    Stack stack = new ListStack();
    String stringToCheck;
    int readerPosition = 0;

    public boolean checkBrackets(String arg) {
        // Reset everything
        reset(arg);

        // Loop over input
        while (stringToCheck.length() > readerPosition) {
            // Get next character
            String character = nextBracket();
            switch (character) {
                // Opening brackets
                case "(":
                case "[":
                case "{":
                case "<":
                case "<*":
                    stack.push(character);
                    break;
                // Closing brackets
                case ")":
                case "}":
                case "]":
                case ">":
                case "*>":
                    if (stack.peek() == null || !stack.pop().toString().equals(brackets.get(character)))
                        return false;
                    break;
            }
        }

        // If stack is empty at the end, success
        return stack.isEmpty();
    }

    private String nextBracket() {
        // As long as there are chars in the string
        while (stringToCheck.length() > readerPosition) {
            // Get the char at the current position
            String character = String.valueOf(stringToCheck.charAt(readerPosition));
            String doubleBracket = "";
            if(readerPosition + 1 < stringToCheck.length()){
                doubleBracket = character + String.valueOf(stringToCheck.charAt(readerPosition + 1));
            }

            readerPosition++;

            // Check if it is a double bracket
            if(doubleBracket.equals("<*") || doubleBracket.equals("*>")){
                readerPosition++;
                return doubleBracket;
            }

            // Check if it is a single bracket
            for(Map.Entry<String, String> entry : brackets.entrySet()) {
                String key = entry.getKey();
                if(key.equals(character)){
                    return character;
                }
            }
        }

        return "";
    }

    private void reset(String arg){
        stringToCheck = arg;
        readerPosition = 0;
        stack = new ListStack();

        brackets.put("(", ")");
        brackets.put("{", "}");
        brackets.put("[", "]");
        brackets.put("<", ">");
        brackets.put("<*", "*>");
        brackets.put(")", "(");
        brackets.put("}", "{");
        brackets.put("]", "[");
        brackets.put(">", "<");
        brackets.put("*>", "<*");
    }
}
