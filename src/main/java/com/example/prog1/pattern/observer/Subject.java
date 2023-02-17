package com.example.prog1.pattern.observer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers;
    protected Subject(File file){
        super();
        observers = new ArrayList<>();
    }
    public void attach (Observer observer){
        observers.add(observer);
    }
    public void detach(Observer observer){
        observers.remove(observer);
    }
    protected void notifyObservers(){
        for (Observer o : observers){
            o.update();
        }
    }
}
