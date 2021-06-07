package ru.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");


        byte param1 = 1;
        short param2 = 2;
        int param3 = 3;
        long param4 = 4L;
        double param5 = 5;
        float param6 = 6f;
        char param7 = '7';
        boolean param8 = true;

        LOG.debug("Our parameters: byte {}, short {}, int {}, long {}, double {}, float {}, char {}, boolean {}",
                        param1, param2, param3, param4, param5, param6, param7, param8);

    }
}
