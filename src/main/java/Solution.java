import java.util.HashMap;
import java.util.Objects;

public class Solution {
  private static class Record {
    private final int sIndex;
    private final int pIndex;

    public Record(int sIndex, int pIndex) {
      this.sIndex = sIndex;
      this.pIndex = pIndex;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Record)) return false;
      Record record = (Record) o;
      return sIndex == record.sIndex && pIndex == record.pIndex;
    }

    @Override
    public int hashCode() {
      return Objects.hash(sIndex, pIndex);
    }
  }
  public boolean isMatch(String s, String p) {
    HashMap<Record, Boolean> cache = new HashMap<>();
    return DFS(s, 0, p, 0, cache);
  }
  public boolean DFS(String s, int sIndex, String p, int pIndex, HashMap<Record, Boolean> cache) {
    int sLen = s.length();
    int pLen = p.length();
    if (sIndex >= sLen && pIndex >= pLen) { // match all
      return true;
    }
    if (pIndex >= pLen) { // could not match s
      return false;
    }
    Record record = new Record(sIndex, pIndex);
    if (cache.containsKey(record)) {
      return cache.get(record);
    }
    boolean match = (sIndex < sLen) &&
        (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.');
    boolean result;
    if (pIndex+1 < pLen && p.charAt(pIndex+1) == '*') {
      result = (match && DFS(s, sIndex+1, p, pIndex , cache)) ||
          DFS(s, sIndex, p, pIndex+2, cache);
      cache.put(record, result);
      return result;
    }

    if (match) {
      result = DFS(s, sIndex+1, p, pIndex+1, cache);
      cache.put(record, result);
      return result;
    }
    cache.put(record, false);
    return false;
  }
  public boolean isMatchV1(String s, String p) {
    int sLen = s.length();
    int pLen = p.length();
    // dp[i][j]:  s[i:] match p[j:] or not
    boolean[][] dp = new boolean[sLen+1][pLen+1];
    // empty string case
    dp[sLen][pLen] = true;
    for (int sStart = sLen; sStart>=0; sStart--) {
      for (int pStart = pLen - 1; pStart>=0; pStart--) {
        boolean match = (sStart < sLen) && (s.charAt(sStart) == p.charAt(pStart) || p.charAt(pStart) == '.');
        // pStart + 1 is *
        if (pStart + 1 < pLen && p.charAt(pStart+1) == '*') {
          // match or not match
          dp[sStart][pStart] = (match && dp[sStart+1][pStart]) || dp[sStart][pStart+2];
        } else {
          dp[sStart][pStart] = (match && dp[sStart+1][pStart+1]);
        }
      }
    }
    return dp[0][0];
  }
}
