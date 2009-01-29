package org.jboss.workspace.client.framework;

public class MessageCallback {
    private AcceptsCallback callback;
    private String message;

    private MessageCallback next;

    public MessageCallback(AcceptsCallback callback, String message) {
        this.callback = callback;
        this.message = message;
    }

    public AcceptsCallback getCallback() {
        return callback;
    }

    public void setCallback(AcceptsCallback callback) {
        this.callback = callback;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageCallback getNext() {
        return next;
    }

    public void call() {
        callback.callback(message);
        if (next != null) next.call();
    }

    public void addMessageCallback(MessageCallback callback) {
        if (next == null) {
            next = callback;
            return;
        }

        MessageCallback n = next;
        while (n.next != null) n = n.next;
        n.next = callback;
    }
}