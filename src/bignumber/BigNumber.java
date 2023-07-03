package bignumber;

/**
 * BigNumber interface mentions all the operations that
 * the classes implementing it should have.
 */
public interface BigNumber extends Comparable<BigNumber> {

  /**
   * Return the length of the string passed.
   *
   * @return int length of the number.
   */

  int length();

  /**
   * Shifts the BigNumber towards left by the number of shifts provided.
   *
   * @param noOfShifts number of shifts.
   */

  void shiftLeft(int noOfShifts);

  /**
   * Shifts the BigNumber towards right by the number of shifts provided.
   *
   * @param noOfShifts number of shifts.
   */

  void shiftRight(int noOfShifts);

  /**
   * Adds the digit n to the last digit of the BigNumber and changing the preceding
   * digits if required.
   *
   * @param n digit to be added to the BigNumber.
   * @throws IllegalArgumentException if n is not in correct input(negative number) format.
   */

  void addDigit(int n) throws IllegalArgumentException;

  /**
   * Returns the digit at the given position starting from the rightmost element.
   *
   * @param pos index at which digit needs to be returned.
   * @return int digit found at provided position.
   * @throws IllegalArgumentException if pos is not in correct input
   *                                  format for example its negative.
   */

  int getDigitAt(int pos) throws IllegalArgumentException;

  /**
   * Creates another replica of current BigNumber.
   *
   * @return BigNumber copy of current BigNumber.
   */

  BigNumber copy();

  /**
   * Mathematically adds corresponding digit of current BigNumber and other BigNumber.
   *
   * @param other Another BigNumber .
   * @return BigNumber sum of both current BigNumber and other BigNumber.
   */

  BigNumber add(BigNumber other);


  /**
   * Compares the current BigNumber and other BigNumber.
   *
   * @param other Another BigNumber.
   * @return int if BigNumber and other BigNumber are same returns 0 else 1/-1.
   */

  int compareTo(BigNumber other);

  /**
   * Converts the BigNumber to the String format.
   *
   * @return String provided BigNumber in String format.
   */

  String toString();

  /**
   * Compares two objects.
   *
   * @param o Other object.
   * @return boolean returns true if same else otherwise.
   */

  boolean equals(Object o);

  /**
   * Generates hash code for the object.
   *
   * @return int returns hash code for the object.
   */

  int hashCode();
}


