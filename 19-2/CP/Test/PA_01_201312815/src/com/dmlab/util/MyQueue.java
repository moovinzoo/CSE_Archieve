package com.dmlab.util;

import com.dmlab.interfaces.Queue;

/**
 * Array-based Queue
 * The size of the internal array should be 128
 */
public class MyQueue<E> implements Queue<E>{

	private E[] mData;
	private int mCursor, mHead;
	
	public MyQueue() {
		clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		/* Code Here */
		this.mData = (E[]) new Object[128];
		this.mCursor = 0;
		this.mHead = 0;
	}

	@Override
	public void add(E item) throws RuntimeException {
		/* Code Here */
		this.mData[(this.mCursor % 128)] = item;
		this.mCursor++;
	}

	@Override
	public E poll() throws EmptyQueueException {
		/* Code Here */
		E currData = this.mData[this.mHead % 128];
		this.mHead++;

		return currData;
	}

	@Override
	public E peek() throws EmptyQueueException {
		/* Code Here */
		return this.mData[this.mHead % 128];
	}

	@Override
	public boolean empty() {
		/* Code Here */
		return (this.mCursor - this.mHead) % 128 == 0;
	}

}
