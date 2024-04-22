package com.example.recycleme.cart;

import java.time.LocalDateTime;

public class NodeData<T> {
    private LocalDateTime dateTime;
    private T value;

    public NodeData(LocalDateTime dateTime, T value) {
        this.dateTime = dateTime;
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public T getValue() {
        return value;
    }
}
