package com.example.jokerlib;

import java.io.Serializable;

public class Joke implements Serializable {
    private String jokeQuestion;
    private String jokeAnswer;


    public Joke() {
    }

    public String getJokeQuestion() {
        return jokeQuestion;
    }

    public void setJokeQuestion(String jokeQuestion) {
        this.jokeQuestion = jokeQuestion;
    }

    public String getJokeAnswer() {
        return jokeAnswer;
    }

    public void setJokeAnswer(String jokeAnswer) {
        this.jokeAnswer = jokeAnswer;
    }

    @Override
    public String toString() {
        return this.jokeQuestion + " . . . \n" + this.jokeAnswer;
    }

    public static Joke jokeFromFormattedString(String s){
        Joke joke = new Joke();
        String lines[] = s.split("\\r?\\n");
        joke.setJokeQuestion(lines[0]);
        joke.setJokeAnswer(lines[1]);
        return joke;
    }
}
