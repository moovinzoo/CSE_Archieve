package com.dmlab.util;

import java.util.EmptyStackException;

import com.dmlab.interfaces.Stack;

/**
 * Array-based Stack
 * The size of the internal array should be 128
 */
public class MyStack<E> implements Stack<E> {
	
	private E[] mData;
	private int mCursor;
	
	public MyStack() {
		clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		/* Code Here */
		this.mData = (E[]) new Object[128];
		this.mCursor = 0;
	}

	@Override
	public void push(E item) throws RuntimeException {
		/* Code Here */
		this.mData[this.mCursor % 128] = item;
		this.mCursor++;
	}

	@Override
	public E pop() throws EmptyStackException {
		/* Code Here */
		this.mCursor--;
		return this.mData[mCursor % 128];
	}

	@Override
	public E peek() throws EmptyStackException {
		/* Code Here */
		return this.mData[(this.mCursor -1) % 128];
	}

	@Override
	public boolean empty() {
		/* Code Here */
		return (this.mCursor % 128 == 0);
	}

}
