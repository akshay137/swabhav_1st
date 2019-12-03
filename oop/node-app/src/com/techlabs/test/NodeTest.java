package com.techlabs.test;

import com.techlabs.node.*;

public class NodeTest {
	private static <E> void printInfo(Node<E> node)
	{
		while (node != null)
		{
			System.out.printf("%s ", node.getData());
			node = node.getNext();
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		Node<Integer> one = new Node<Integer>(10);
		Node<Integer> two = new Node<Integer>(20);
		Node<Integer> three = new Node<Integer>(30);
		
		one.setNext(two);
		two.setNext(three);
		printInfo(one);
		printInfo(two);
		printInfo(three);
	}
}
