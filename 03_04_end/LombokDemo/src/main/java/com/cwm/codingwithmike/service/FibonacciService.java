package com.cwm.codingwithmike.service;

import lombok.Getter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class FibonacciService {
    private final int n = 10000;
    @Getter(lazy = true)
    private final List<BigInteger> fibonacciNumbers = calculateFibonacciSequence();

    public List<BigInteger> calculateFibonacciSequence() {
        LOGGER.info("FibonacciService.calculateFibonacciSequence() - starting");
        List<BigInteger> result = new LinkedList<>();
        result.add(BigInteger.ZERO);
        result.add(BigInteger.ONE);

        for (int i = 2; i < n; i++) {
            result.add(result.get(i - 1).add(result.get(i - 2)));
        }
        LOGGER.info("FibonacciService.calculateFibonacciSequence() - completed");
        return result;
    }
}
