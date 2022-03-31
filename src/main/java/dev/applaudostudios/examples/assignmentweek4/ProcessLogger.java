package dev.applaudostudios.examples.assignmentweek4;


import dev.applaudostudios.examples.assignmentweek4.common.notification.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class ProcessLogger<String> implements Observer<String> {
    private final Logger LOGGER = LoggerFactory.getLogger("ProcessCartLogger");
    Marker processInfo = MarkerFactory.getMarker("PROCESS-INFO");
    @Override
    public void updated(String observable) {
        LOGGER.info(processInfo, (java.lang.String) observable);
    }
}
