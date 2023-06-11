package org.leesyash;

import java.util.ArrayList;
import java.util.List;

public class ThreadBuilder {

    public String getThread(String sourceText) {
        validateSourceText(sourceText);
        List<TwitterThread> threads = getThread(sourceText.split(" "));
        return threadsToString(threads);
    }

    private void validateSourceText(String sourceText) {
        if (sourceText.isEmpty()) throw new IllegalArgumentException("Twit must have one word or symbol");
        if (sourceText.length() <= Twit.MAX_LENGTH) throw new IllegalArgumentException("For this text enough one twit");
    }

    private List<TwitterThread> getThread(String[] splittedSourceText) {
        List<TwitterThread> threads = new ArrayList<>();
        Twit twit = new Twit(splittedSourceText[0]);
        TwitterThread thread = new TwitterThread(twit);
        threads.add(thread);


        for (int i = 1; i < splittedSourceText.length; i++) {
            try {
                twit.append(splittedSourceText[i]);
            } catch (TwitFullFilledException ex) {
                twit = new Twit(splittedSourceText[i]);
                try {
                    thread.add(twit);
                } catch (ThreadFullFilledException ex1) {
                    thread = new TwitterThread(twit);
                    threads.add(thread);
                }
            }
        }
        return threads;
    }

    private String threadsToString(List<TwitterThread> threads) {
        StringBuilder stringThreads = new StringBuilder();

        for (int i = 0; i < threads.size(); i++) {
            if (i != 0) {
                stringThreads.append(String.format("\nThread %d ========================\n", i));
            }
            stringThreads.append(threads.get(i));
        }

        return stringThreads.toString();
    }
}
