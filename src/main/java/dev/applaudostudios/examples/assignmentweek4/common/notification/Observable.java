package dev.applaudostudios.examples.assignmentweek4.common.notification;

import dev.applaudostudios.examples.assignmentweek4.common.notification.Observer;

public interface Observable {
    void attach(Observer<?> observer);
    void detach(Observer<?> observer);
}
