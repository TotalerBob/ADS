package praktikum_1;

import base.CommandExecutor;

public class XMLServer implements CommandExecutor {
    private Stack stack = new ListStack();
    private String xml = "";
    private int position = 0;

    @Override
    public String execute(String command) throws Exception {
        boolean success = checkWellformed(command);

        return (success) ? "Valid XML" : "Invalid XML";
    }

    public boolean checkWellformed(String arg) {
        stack = new ListStack();
        xml = arg;
        position = 0;

        while (xml.length() > position) {
            try {
                String token = getNextToken();
                if(token.charAt(token.length() - 1) == '/'){
                    // Self closing tag
                    continue;
                }

                if (token.charAt(0) == '/') {
                    // Closing tag
                    // Remove slash
                    token = token.substring(1);
                    if(!token.equals(stack.pop())){
                        return false;
                    }
                } else {
                    // Opening tag
                    stack.push(token);
                }

            } catch (Exception e) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    private String getNextToken() throws Exception {
        String currentWord = "";
        boolean openTagRead = false;
        boolean slashRead = false;

        for (; position < xml.length(); position++) {
            if (xml.charAt(position) == '>' && !openTagRead) {
                throw new Exception();
            }

            if (xml.charAt(position) == '>') {
                if (currentWord.length() < 1) {
                    throw new Exception();
                }

                position++;
                return currentWord;
            }

            if (xml.charAt(position) == '/' && (!openTagRead || slashRead)) {
                throw new Exception();
            }

            if (xml.charAt(position) == '<' && openTagRead) {
                throw new Exception();
            }

            if (xml.charAt(position) == '<') {
                openTagRead = true;
                continue;
            }

            if(openTagRead){
                currentWord += xml.charAt(position);
            }
        }

        throw new Exception();
    }
}
