package com.cwm.codingwithmike.service;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@Service
public class FibonacciService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciService.class);

    private final int n = 10000;
    @Getter(lazy = true) private final List<BigInteger> fibonacciNumbers = calculateFibonacciSequence();

    private List<BigInteger> calculateFibonacciSequence() {
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
