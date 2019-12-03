package com.techlabs.adapter;

import java.util.*;

public class QueueAdapter<T> implements IQueue<T> {
	private LinkedList<T> list;
	
	public QueueAdapter ()
	{
		list = new LinkedList<T>();
	}

	@Override
	public void enqueue(T item) {
		list.addLast(item);
	}

	@Override
	public T dequeue() {
		return list.removeFirst();
	}
	
	@Override
	public int size()
	{
		return list.size();
	}
}
