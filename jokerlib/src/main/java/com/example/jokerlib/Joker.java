package com.example.jokerlib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Joker {
    private List<Joke> mJokes;

    public Joker() {
        initJokeList();
    }

    public Joke getRandomJoke() {
        Random random = new Random();
        return mJokes.get(random.nextInt(mJokes.size()));
    }

    private void initJokeList() {
        mJokes = new ArrayList<>();
        String[] jokeQuestions = {
                "How many programmers does it take to change a light bulb?",
                "Whats the object-oriented way to become wealthy?",
                "Why did the programmer quit his job?",
                "What did the Java code say to the C code?",
                "Why are Assembly programmers always soaking wet?",
                "What do cats and programmers have in common?",
                "What is the most used language in programming?",
                "Why did the database administrator leave his wife?",
                "Why do programmers always get Christmas and Halloween mixed up?",
                "How did the programmer die in the shower?"
        };
        String[] jokeAnswers = {
                "none, that's a hardware problem",
                "Inheritance",
                "Because he didn't get arrays.",
                "You've got no class.",
                "They work below C-level.",
                "When either one is unusually happy and excited, an appropriate question would be, \"did you find a bug?\"",
                "Profanity.",
                "She had one-to-many relationships",
                "Because DEC 25 = OCT 31",
                "He read the shampoo bottle instructions: Lather. Rinse. Repeat."

        };
        for(int i = 0; i < jokeQuestions.length; i++){
            Joke joke = new Joke();
            joke.setJokeQuestion(jokeQuestions[i]);
            joke.setJokeAnswer(jokeAnswers[i]);
            mJokes.add(joke);
        }
    }
}
