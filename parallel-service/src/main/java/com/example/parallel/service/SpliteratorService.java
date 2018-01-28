package com.example.parallel.service;

import com.example.parallel.component.WordCounter;
import com.example.parallel.component.WordCounterSpliterator;
import org.springframework.stereotype.Service;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by ksb on 2018. 1. 28..
 */
@Service
public class SpliteratorService {

    public int countWorkds1(String SENTENCE) {
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        return countWorkds(stream);
    }

    public int countWorkds2(String SENTENCE) {
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);

        return countWorkds(stream);
    }


    private int countWorkds(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }
}
