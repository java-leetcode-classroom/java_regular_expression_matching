import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
  final private Solution sol = new Solution();
  @Test
  void isMatchExamples1() {
    assertFalse(sol.isMatch("aa", "a"));
  }
  @Test
  void isMatchExamples2() {
    assertTrue(sol.isMatch("aa", "a*"));
  }
  @Test
  void isMatchExamples3() {
    assertTrue(sol.isMatch("ab", ".*"));
  }
  @Test
  void isMatchExamples4() {
    assertFalse(sol.isMatch("a", "b*"));
  }
}