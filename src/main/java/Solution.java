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
    boolean result = false;
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
}
