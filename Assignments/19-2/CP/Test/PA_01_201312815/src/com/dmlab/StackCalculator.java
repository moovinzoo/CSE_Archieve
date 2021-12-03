package com.dmlab;

import com.dmlab.util.EmptyQueueException;
import com.dmlab.util.MyQueue;
import com.dmlab.util.MyStack;

public class StackCalculator {

	private MyStack<String> mStack;
	private MyQueue<String> mQueue;
	private MyStack<Integer> mStackCalc;

	private static final int TYPE_OPERATOR = 0;
	private static final int TYPE_BRACE = 1;
	private static final int TYPE_NUMBER = 2;

	public StackCalculator() {
		mStack = new MyStack<String>();
		mQueue = new MyQueue<String>();
		mStackCalc = new MyStack<Integer>();
	}

	/**
	 * Solve the given infix form of equation
	 * @param infix
	 * 			the infix form of an equation
	 * 			e.g. 1 + 2 - ( 3 - 4 )
	 * @return
	 * 			the result from the calculation of given equation
	 */
	public int solve(String infix) {
		/* Code Here */
		// TODO :: INFIX TO POSTFIX

		// empty queue : empty stack : unused calStack
		String[] infix_split = infix.split(" ");
		for (String item : infix_split) {
			if (this.typeOf(item) == TYPE_OPERATOR) {
				while (!mStack.empty() && !mStack.peek().equals("(")) {
					if (this.prior(mStack.peek()) >= this.prior(item)) {
						mQueue.add(mStack.pop());
					} else {
						break;
					}
				}
				mStack.push(item);

			} else if (this.typeOf(item) == TYPE_BRACE) {
				if (item.equals("(")) {
					mStack.push(item);
				} else { // item.equals(")")
					while (!mStack.empty() && !mStack.peek().equals("(")) {
						mQueue.add(mStack.pop());
					}

					if (mStack.peek().equals("(")) mStack.pop();
				}
			} else if (this.typeOf(item) == TYPE_NUMBER) {
				mQueue.add(item);
			}
		}

		// empty queue : full stack : unused calStack
		while (!mStack.empty()) {
			mQueue.add(mStack.pop());
		}

		// full queue : empty stack : empty calStack
		// TODO :: CALCULATE THE POSTFIX FORM OF AN EQUATION
		while (!mQueue.empty()) {
			try {
				String item = mQueue.poll();
				if (this.typeOf(item) == TYPE_OPERATOR) {
					int b = mStackCalc.pop();
					int a = mStackCalc.pop();
					int c = 0;
					switch (item) {
						case "+":
							c = a + b;
							break;
						case "-":
							c = a - b;
							break;
						case "*":
							c = a * b;
							break;
						case "/":
							c = a / b;
							break;
						default: break;
					}
					mStackCalc.push(c);
				} else if (this.typeOf(item) == TYPE_NUMBER) {
					mStackCalc.push(Integer.parseInt(item));
				}
			} catch (EmptyQueueException e) {
				e.printStackTrace();
			}
		}

		return mStackCalc.pop();
	}

	private int typeOf(String entry) {
		if (entry.equals("+") || entry.equals("-") || entry.equals("*") || entry.equals("/")) {
			return TYPE_OPERATOR;
		} else if (entry.equals("(") || entry.equals(")")) {
			return TYPE_BRACE;
		} else {
			return TYPE_NUMBER;
		}
	}

	private int prior(String item) {
		if (item.equals("+") || item.equals("-")) {
			return 0;
		}
		return 1;
	}
}
