package org.leesyash;

import java.util.ArrayList;
import java.util.List;

public class TwitterThread {
    private final List<Twit> twits = new ArrayList<>();
    public static final int MAX_LENGTH = 25;

    public TwitterThread(Twit twit) {
        add(twit);
    }

    public void add(Twit twit) {
        if (twits.size() + 1 > MAX_LENGTH) throw new ThreadFullFilledException();
        twits.add(twit);
        twit.setNumber(twits.size());
    }

    @Override
    public String toString() {
        StringBuilder thread = new StringBuilder();
        for (int i = 0; i < twits.size(); i++) {
            if (i != 0) {
                thread.append("\n");
            }
            thread.append(twits.get(i).build(twits.size()));
        }
        return thread.toString();
    }
}
