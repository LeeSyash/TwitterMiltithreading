package org.leesyash;

public class Twit {
    public static final int MAX_LENGTH = 280;
    private final String indexSplitter = "/";
    private final StringBuilder body;

    public Twit(String word) {
        body = new StringBuilder();
        append(word);
    }

    public void append(String word) {
        String joiner = " ";
        if ((body.length() + joiner.length() + word.length()) > (MAX_LENGTH - TwitterThread.MAX_LENGTH * 2 + indexSplitter.length())) throw new TwitFullFilledException();
        if (body.length() != 0) body.append(" ");
        body.append(word);
    }

    public void setNumber(int number) {
        body.insert(0,"\n").insert(0, indexSplitter).insert(0, number);
    }

    public String build(int threadSize) {
        body.insert(body.indexOf(indexSplitter) + 1, threadSize);
        return body.toString();
    }
}
