package com.techlabs.adapter.test;

import com.techlabs.adapter.*;

public class AdapaterTest {
	public static void main(String[] args)
	{
		QueueAdapter<Integer> queue = new QueueAdapter<Integer>();
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		System.out.printf("dequeue: %d\n", queue.dequeue());
		System.out.printf("queue size: %d\n", queue.size());
	}
}
