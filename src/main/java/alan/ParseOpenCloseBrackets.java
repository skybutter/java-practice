package alan;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import java.util.*;

/**
 * Given a string containing just the characters '(' and ')', determine if
 * the input string is valid.
 *
 * An input string is valid if:
 * 1. Open parentheses must be closed in the correct order.
 *
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 * Input: "()", Output: true
 *
 * Example 2:
 * Input: "())", Output: False
 *
 * Example 3:
 * Input: "(", Output: False
 *
 * Example 4:
 * Input: "()()()", Output: True
 *
 * Example 5:
 * Input: "(()((())))", Output: True
 *
 * Example 6:
 * Input: ")(", Output: False
 *
 */
public class ParseOpenCloseBrackets {
    public boolean isValid(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        //Stack<String> stack = new Stack<>();
        // Method 1: using String[]
        // String[] array = str.split("");
        // for (int i=0; i < array.length; i++) {
        //   if ("(".equals(array[i])) {
        //     stack.push("(");
        //   } else {  // handling ")" for now
        //     if (stack.empty()) {
        //       return false;
        //     } else {
        //       stack.pop();
        //     }
        //   }
        // }
        // Method 2: using substring()
        //int len = str.length();
        // for (int i=0; i < len; i++) {
        //   String c = str.substring(i, i+1);
        //   if ("(".equals(c)) {
        //     stack.push("(");
        //   } else {  // handling ")" for now
        //     if (stack.empty()) {
        //       return false;
        //     } else {
        //       stack.pop();
        //     }
        //   }
        // }
        // Method 3: using using Character
        Stack<Character> stack = new Stack<>();
        int len = str.length();
        for (int i=0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {  // handling ")" for now
                if (stack.empty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        if (stack.empty()) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void testEmptyString() {
        Assert.assertTrue("Should be valid when empty", isValid(""));
    }

    @Test
    public void testNullString() {
        Assert.assertTrue("Should be valid when null", isValid(null));
    }

    @Test
    public void testValid1() {
        Assert.assertTrue("'()' should be valid", isValid("()"));
    }

    @Test
    public void testValid2() {
        Assert.assertTrue("'()()()' should be valid", isValid("()()()"));
    }

    @Test
    public void testValid3() {
        Assert.assertTrue("'(()((())))' should be valid", isValid("(()((())))"));
    }

    @Test
    public void testInvalid1() {
        Assert.assertFalse("'(' should be invalid", isValid("("));
    }

    @Test
    public void testInvalid2() {
        Assert.assertFalse("')(' should be invalid", isValid(")("));
    }

    @Test
    public void testInvalid3() {
        Assert.assertFalse("'())' should be invalid", isValid("())"));
    }

    public static void main(String[] args) {
        JUnitCore.main("NYTimes");
    }
}