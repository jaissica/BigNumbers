package bignumber;

/**
 * The below interface represents all the methods implementing it should have.
 **/
public interface NodeInterface {

  /**
   * Adds n to the front of the node/list.
   *
   * @param n digit that needs to be added in front of the list.
   * @return NodeInterface with n added to it in the front.
   */

  NodeInterface addFront(int n);

  /**
   * Returns count of the number of elements in the node.
   *
   * @return int count of node.
   */

  int count();

  /**
   * Provides digit found at given index, starting the first position from 0 and the
   * rightmost element.
   *
   * @param index position from which digit is needed.
   * @return int digit found at provided index.
   * @throws IllegalArgumentException when invalid input is passed to it.
   */

  int get(int index) throws IllegalArgumentException;

  /**
   * Removes the first element from the list.
   *
   * @return NodeInterface with the first element removed from the list.
   */

  NodeInterface remove();


}
