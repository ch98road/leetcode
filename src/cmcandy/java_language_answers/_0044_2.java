package cmcandy.java_language_answers;

/**
 * Created by CHEN on 2020/5/9.
 * 官方解
 */
public class _0044_2 {
    static class Solution {
        public boolean isMatch(String s, String p) {
            int sLen = s.length(), pLen = p.length();

            // base cases
            if (p.equals(s) || p.equals("*")) return true;
            if (p.isEmpty() || s.isEmpty()) return false;

            // init all matrix except [0][0] element as False
            boolean[][] d = new boolean[pLen + 1][sLen + 1];
            d[0][0] = true;

            // DP compute
            for(int pIdx = 1; pIdx < pLen + 1; pIdx++) {
                // the current character in the pattern is '*'
                if (p.charAt(pIdx - 1) == '*') {
                    int sIdx = 1;
                    // d[p_idx - 1][s_idx - 1] is a string-pattern match
                    // on the previous step, i.e. one character before.
                    // Find the first idx in string with the previous math.
                    while ((!d[pIdx - 1][sIdx - 1]) && (sIdx < sLen + 1)) sIdx++;
                    // If (string) matches (pattern),
                    // when (string) matches (pattern)* as well
                    d[pIdx][sIdx - 1] = d[pIdx - 1][sIdx - 1];
                    // If (string) matches (pattern),
                    // when (string)(whatever_characters) matches (pattern)* as well
                    while (sIdx < sLen + 1) d[pIdx][sIdx++] = true;
                }
                // the current character in the pattern is '?'
                else if (p.charAt(pIdx - 1) == '?') {
                    for(int sIdx = 1; sIdx < sLen + 1; sIdx++)
                        d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1];
                }
                // the current character in the pattern is not '*' or '?'
                else {
                    for(int sIdx = 1; sIdx < sLen + 1; sIdx++) {
                        // Match is possible if there is a previous match
                        // and current characters are the same
                        d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1] &&
                                (p.charAt(pIdx - 1) == s.charAt(sIdx - 1));
                    }
                }
            }
            return d[pLen][sLen];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String p = "";
        System.out.println(p.length());
        System.out.println(solution.isMatch("aa","a") );
        System.out.println(solution.isMatch("aa","*") );
        System.out.println(solution.isMatch("","*") );
        System.out.println(solution.isMatch("acdcb","a*c?b") );
        System.out.println(solution.isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba","a*******b"));
        System.out.println(solution.isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb","**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
    }
}
