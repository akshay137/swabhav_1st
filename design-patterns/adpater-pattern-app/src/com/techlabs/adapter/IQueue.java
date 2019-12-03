package com.techlabs.adapter;

public interface IQueue<T> {
	void enqueue(T item);
	T dequeue();
	int size();
}
