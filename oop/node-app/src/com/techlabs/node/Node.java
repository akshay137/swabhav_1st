package com.techlabs.node;

public class Node<E> {
	private E data;
	private Node<E> next;
	
	public Node(E data)
	{
		this.data = data;
		this.next = null;
	}
	
	public E getData()
	{
		return this.data;
	}
	
	public Node<E> getNext()
	{
		return this.next;
	}
	
	public void setData(E data)
	{
		this.data = data;
	}
	
	public void setNext(Node<E> next)
	{
		this.next = next;
	}
}
