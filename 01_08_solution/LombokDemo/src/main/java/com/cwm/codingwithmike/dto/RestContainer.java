package com.cwm.codingwithmike.dto;

public record RestContainer<T>(T record, String type) {
}
