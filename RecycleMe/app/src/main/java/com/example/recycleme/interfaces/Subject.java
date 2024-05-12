package com.example.recycleme.interfaces;

import com.example.recycleme.interfaces.Observer;

/**
 * Interface for Observer pattern
 * @author Julius Liem - u7724204
 */
public interface Subject {
    public void attach(Observer observer);
    public void detach(Observer observer);
    public void notifyAllObservers(String message);
}
