package bignumber;


/**
 * The BigNumberImpl class implements all the methods mentioned in the BigNumber interface.
 */
public class BigNumberImpl implements BigNumber {

  NodeInterface bigNum;
  private String currStr = "";
  private String reversStr = "";

  /**
   * Default constructor which initializes the bigNum node with 0 as its first element
   * and rest as empty.
   */
  public BigNumberImpl() {
    bigNum = new ElementNode(0, new EmptyNode());
    currStr = "0";
    reversStr = "0";
  }

  /**
   * Below constructor takes str as initial string and creates a list of all the valid digits
   * found in the list with the help of NodeInterface.
   *
   * @param str initial String input to the constructor consisting of valid digit inputs.
   * @throws IllegalArgumentException when an invalid input is found in str String.
   */

  public BigNumberImpl(String str) throws IllegalArgumentException {
    if (str.equals("") || str == null || str.isEmpty()) {
      bigNum = new ElementNode(0, new EmptyNode());
      currStr = "0";
      reversStr = "0";
      return;
    }


    bigNum = new EmptyNode();

    int i = 0;
    int len = str.length();

    while (i < len && str.charAt(i) == '0') {
      i++;
    }
    if (i == len) {
      bigNum = new ElementNode(0, new EmptyNode());
      currStr = "0";
      reversStr = "0";
      return;
    }

    while (i < len) {
      int currVal = Integer.valueOf(str.substring(i, i + 1));
      if (!(currVal >= 0 && currVal <= 9)) {
        throw new IllegalArgumentException("Invalid input found");
      }
      bigNum = bigNum.addFront(currVal);
      currStr += currVal;
      reversStr = currVal + reversStr;
      i++;
    }

  }

  @Override
  public int length() {
    return reversStr.length();
  }

  @Override
  public void shiftLeft(int noOfShifts) {
    if (noOfShifts == 0) {
      return;
    }
    if (this.length() == 1 && currStr.equals("0")) {
      currStr = "0";
      reversStr = "0";
      return;
    }

    if (noOfShifts > 0) {
      for (int i = 1; i <= noOfShifts; i++) {
        bigNum = bigNum.addFront(0);
        currStr += "0";
        reversStr = "0" + reversStr;
      }
    } else {

      shiftRight(Math.abs(noOfShifts));

    }
  }

  @Override
  public void shiftRight(int noOfShifts) {
    int len = this.length();
    if (noOfShifts == 0) {
      return;
    }
    if (len == 1 && currStr.equals("0")) {
      return;
    }
    if (noOfShifts >= len) {
      this.bigNum = new ElementNode(0, new EmptyNode());
      currStr = "0";
      reversStr = "0";
      return;
    }

    if (noOfShifts < 0) {
      shiftLeft(Math.abs(noOfShifts));
    } else {

      for (int i = 1; i <= noOfShifts; i++) {
        bigNum = bigNum.remove();
        currStr = currStr.substring(0, currStr.length() - 1);
      }
      reversStr = reversStr.substring(noOfShifts);
    }
  }

  @Override
  public void addDigit(int n) throws IllegalArgumentException {
    if (n < 0 || n > 9) {
      throw new IllegalArgumentException("Invalid input "
              + "either number is negative or greater than 9");
    }
    if (n == 0) {
      return;
    }
    int len = reversStr.length();
    int removeTracker = 0;
    StringBuilder toAdd = new StringBuilder();
    int carry = 0;
    int sum = Integer.valueOf(reversStr.substring(0, 1)) + n;
    if (len == 1) {
      if (sum > 9) {
        bigNum = bigNum.remove();
        bigNum = bigNum.addFront(1);
        bigNum = bigNum.addFront(sum - 10);
        this.currStr = "1" + (sum - 10);
        this.reversStr = (sum - 10) + "1";
        return;
      } else {
        bigNum = bigNum.remove();
        bigNum = bigNum.addFront(sum);
        this.currStr = String.valueOf(sum);
        this.reversStr = String.valueOf(sum);
        return;
      }
    }
    int i = 1;
    while (i < len) {
      if (sum > 9) {
        carry = 1;
        removeTracker++;
        toAdd.append(sum - 10);
      } else {
        carry = 0;
        removeTracker++;
        toAdd.append(sum);
        carry = 0;
        break;
      }
      sum = Integer.parseInt(reversStr.substring(i, i + 1)) + carry;
      i++;
    }

    if (carry > 0) {
      toAdd.append(sum - 10);
      removeTracker++;
      toAdd.append(carry);
    }

    for (int j = 1; j <= removeTracker; j++) {
      bigNum = bigNum.remove();
      this.currStr = currStr.substring(0, currStr.length() - 1);
    }

    int addLength = toAdd.length() - 1;
    for (int j = addLength; j >= 0; j--) {
      bigNum = bigNum.addFront(Integer.parseInt(toAdd.substring(j, j + 1)));
      this.currStr = this.currStr + toAdd.substring(j, j + 1);
    }

    StringBuilder r = new StringBuilder(this.currStr);
    this.reversStr = r.reverse().toString();

  }

  @Override
  public int getDigitAt(int pos) throws IllegalArgumentException {
    if (pos < 0) {
      throw new IllegalArgumentException("Negative index provided");
    }
    if (pos >= this.length()) {
      return 0;
    }
    return bigNum.get(pos);
  }

  @Override
  public BigNumber copy() {
    return new BigNumberImpl(this.currStr);
  }

  @Override
  public BigNumber add(BigNumber other) {
    int sizeThis = this.length() - 1;
    int sizeOther = other.length() - 1;
    int max = Math.max(sizeThis, sizeOther);
    int carry = 0;
    String otherStr = other.toString();
    String thisStr = this.toString();
    StringBuilder answer = new StringBuilder();

    for (int i = 0; i <= max; i++) {
      int num1 = 0;
      int num2 = 0;
      if (i <= sizeThis) {
        num1 = Integer.valueOf(thisStr.substring(sizeThis - i, sizeThis - i + 1));
      }
      if (i <= sizeOther) {
        num2 = Integer.valueOf(otherStr.substring(sizeOther - i, sizeOther - i + 1));
      }
      int sum = num1 + num2 + carry;
      if (sum > 9) {
        carry = Integer.parseInt(String.valueOf(sum).substring(0, 1));
        answer.insert(0, Integer.parseInt(String.valueOf(sum).substring(1, 2)));
      } else {
        carry = 0;
        answer.insert(0, sum);
      }
    }
    if (carry > 0) {
      answer.insert(0, carry);
    }
    return new BigNumberImpl(answer.toString());
  }


  @Override
  public int compareTo(BigNumber other) {
    String ts = this.toString();
    String os = other.toString();
    int t = this.toString().length();
    int o = other.toString().length();

    if (t < o) {
      return -1;
    } else if (o < t) {
      return 1;
    } else {
      for (int i = 0; i < t; i++) {
        if (ts.charAt(i) != os.charAt(i)) {
          int tVal = Integer.valueOf(ts.charAt(i));
          int oVal = Integer.valueOf(os.charAt(i));
          if (tVal > oVal) {
            return 1;
          } else {
            return -1;
          }
        }
      }
    }
    return 0;
  }


  @Override
  public String toString() {

    if (this.currStr == null || this.currStr.equals("0") || this.currStr.equals("")) {
      return "0";
    }
    return this.currStr;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BigNumber)) {
      return false;
    }
    BigNumber that = (BigNumber) o;
    return hashCode() == that.hashCode();
  }

  @Override
  public int hashCode() {
    // s[0]*31^(2) + s[1]*31^1 + s[2]
    int hash = 0;
    int len = this.reversStr.length();
    for (int i = len - 1; i >= 0; i--) {
      hash += this.reversStr.charAt(i) * 31 ^ i;
    }
    return hash;
  }


}
