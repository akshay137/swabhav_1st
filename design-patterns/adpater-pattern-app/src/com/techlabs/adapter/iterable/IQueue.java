package com.techlabs.adapter.iterable;

public interface IQueue<T> extends Iterable<T> {
	void enqueue(T item);
	T dequeue();
	int size();
}
