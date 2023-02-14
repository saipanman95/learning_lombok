package com.cwm.codingwithmike.controller;

import com.cwm.codingwithmike.service.FibonacciService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = {"/api/v1/fibonacci"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class FibonacciController {

    private FibonacciService fibonacciService = new FibonacciService();
    @GetMapping("/all")
    public ResponseEntity<List<BigInteger>> listFibonacciSequence(){
        LOGGER.info("FibonacciController.listFibonacciSequence() - starting");
        List<BigInteger> fibonacciNumbers = fibonacciService.getFibonacciNumbers();
        if(fibonacciNumbers.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        LOGGER.info("FibonacciController.listFibonacciSequence() - complete");
        return ResponseEntity.ok().body(fibonacciNumbers);
    }
}
