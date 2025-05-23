package pyatigin.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public int nextInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public boolean nextBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }


}

