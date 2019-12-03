package com.techlabs.test;

import com.techlabs.node.*;

public class NodeListTest {
	private static <E> void printList(NodeList<E> list)
	{
		System.out.printf("size: %d ==> list: ", list.size());
		Node<E> ptr = list.getHead();
		while (ptr != null)
		{
			System.out.printf("%s ", ptr.getData());
			ptr = ptr.getNext();
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		NodeList<Integer> list = new NodeList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		printList(list);
		list.addFirst(0);
		printList(list);
		list.removeFirst();
		printList(list);
		list.removeLast();
		printList(list);
		list.add(0, -1);
		list.add(0, -2);
		printList(list);
		list.add(1, 0);
		printList(list);
		list.remove(2);
		list.remove((Integer)2);
		printList(list);
		list.remove(list.size() - 1);
		printList(list);
		list.add(2);
		list.add(1);
		printList(list);
		list.remove(new Integer(1));
		list.remove(new Integer(20));
		printList(list);
	}
}
