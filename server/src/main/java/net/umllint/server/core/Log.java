package net.umllint.server.core;

import java.util.LinkedList;
import java.util.List;

public class Log {

    private List<String> messages = new LinkedList<String>();
    private static Log instance = null;

    protected Log() {
    }

    public static Log getInstance() {

        if (instance == null) {
            instance = new Log();
        }

        return instance;
    }


    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}

