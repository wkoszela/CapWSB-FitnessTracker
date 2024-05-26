package com.capgemini.wsb.fitnesstracker.training.internal;

public class TrainingExistException extends Exception{
    public TrainingExistException(String email) { super("Training Exists");

    }

}
