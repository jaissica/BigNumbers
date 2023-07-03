package bignumber;

/**
 * Below class implements all the methods mentioned in NodeInterface and
 * assigns the current value and rest value to the list.
 */
public class ElementNode implements NodeInterface {

  private final int n;
  private final NodeInterface rest;

  /**
   * Below constructor takes n as current digit and rest as NodeInterface and creates a list.
   *
   * @param n    a single integer digit input assigned in the list.
   * @param rest NodeInterface which consists of the rest of the list.
   */
  public ElementNode(int n, NodeInterface rest) {
    this.n = n;
    this.rest = rest;
  }

  @Override
  public NodeInterface addFront(int n) {
    return new ElementNode(n, this);
  }

  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  @Override
  public int get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.n;
    }
    return this.rest.get(index - 1);
  }

  @Override
  public NodeInterface remove() {
    return rest;
  }


}
