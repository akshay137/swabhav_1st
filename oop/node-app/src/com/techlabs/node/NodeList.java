package com.techlabs.node;

public class NodeList<E> {
	private Node<E> head;
	private int size;

	public NodeList() {
		this.head = null;
		this.size = 0;
	}

	public void addLast(E data) {
		this.size++;
		if (this.head == null) {
			this.head = new Node<E>(data);
			return;
		}

		Node<E> itr = this.head;
		while (itr.getNext() != null)
			itr = itr.getNext();
		itr.setNext(new Node<E>(data));
	}

	public void addFirst(E data) {
		this.size++;
		Node<E> node = new Node<E>(data);
		node.setNext(this.head);
		this.head = node;
	}

	public void add(E data) {
		addLast(data);
	}

	public void add(int index, E data) {
		if (index >= this.size)
			throw new IndexOutOfBoundsException();
		if (index == 0) {
			addFirst(data);
			return;
		}
		int counter = 0;
		Node<E> prePtr = this.head;
		Node<E> ptr = this.head;
		while (counter++ < index) {
			prePtr = ptr;
			ptr = ptr.getNext();
		}
		prePtr.setNext(new Node<E>(data));
		prePtr.getNext().setNext(ptr);
		this.size++;
	}

	public E removeFirst() {
		if (this.head == null)
			return null;
		this.size--;
		Node<E> first = this.head;
		this.head = this.head.getNext();
		return first.getData();
	}

	public E removeLast() {
		if (this.head == null)
			return null;
		this.size--;
		Node<E> ptr = this.head;
		Node<E> last = ptr;
		while (ptr.getNext() != null) {
			last = ptr;
			ptr = ptr.getNext();
		}
		if (last == ptr) {
			this.head = null;
			return null;
		}

		last.setNext(null);
		return last.getData();
	}

	public void remove(int index) {
		if (index >= this.size)
			throw new IndexOutOfBoundsException();
		if (this.head == null)
			return;
		if (index == 0) {
			removeFirst();
			return;
		}
		if (index == this.size - 1) {
			removeLast();
			return;
		}
		int counter = 0;
		Node<E> prePtr = this.head;
		Node<E> ptr = this.head;
		while (counter++ <= index) {
			prePtr = ptr;
			ptr = ptr.getNext();
		}
		prePtr.setNext(ptr.getNext());
		this.size--;
	}

	public void remove(E data) {
		if (this.head == null)
			return;
		Node<E> prePtr = this.head;
		Node<E> ptr = this.head;
		while (ptr != null && !ptr.getData().equals(data)) {
			prePtr = ptr;
			ptr = ptr.getNext();
		}
		if (ptr == null)
			return;
		if (ptr == this.head) {
			removeFirst();
			return;
		}
		prePtr.setNext(ptr.getNext());
		this.size--;
	}

	public Node<E> getHead() {
		return this.head;
	}

	public int size() {
		return this.size;
	}
}
