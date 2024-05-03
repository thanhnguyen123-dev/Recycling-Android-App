package com.example.recycleme.cart;

import java.time.LocalDateTime;
import java.util.Objects;

public class NodeData<T> implements Comparable<NodeData<T>> {
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

    @Override
    public int compareTo(NodeData<T> o) {
        return this.dateTime.compareTo(o.getDateTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeData<?> nodeData = (NodeData<?>) o;
        return Objects.equals(dateTime, nodeData.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime);
    }
}
