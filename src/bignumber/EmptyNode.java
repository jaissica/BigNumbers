package bignumber;

/**
 * The below class implements all the methods in the NodeInterface
 * which are null or empty.
 */

public class EmptyNode implements NodeInterface {
  @Override
  public NodeInterface addFront(int n) {
    return new ElementNode(n, new EmptyNode());
  }

  @Override
  public int count() {
    return 0;
  }


  @Override
  public int get(int index) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public NodeInterface remove() {
    return new EmptyNode();
  }


}
