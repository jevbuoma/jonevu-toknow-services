/**
 * 
 */
package com.jonevu.abi.gameofchance.model;

import java.util.Arrays;

/**
 * @author jonev
 *
 */
public class Entry {
	private int[] numbers;
	
	private String sign;
	
	private int numOfPicks;

	/**
	 * @return the numbers
	 */
	public int[] getNumbers() {
		return numbers;
	}

	/**
	 * @param numbers the numbers to set
	 */
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the numOfPicks
	 */
	public int getNumOfPicks() {
		return numOfPicks;
	}

	/**
	 * @param numOfPicks the numOfPicks to set
	 */
	public void setNumOfPicks(int numOfPicks) {
		this.numOfPicks = numOfPicks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entry [numbers=" + Arrays.toString(numbers) + ", sign=" + sign + ", numOfPicks=" + numOfPicks + "]";
	}
}
