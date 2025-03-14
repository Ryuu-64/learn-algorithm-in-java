package org.ryuu;

/**
 * <a href="https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm"></a>
 */
public class KMP {
    // KMP 算法
    public int search(String text, String pattern) {
        int textLen = text.length();
        int patternLen = pattern.length();

        if (patternLen == 0) {
            return 0;
        }

        // 计算模式字符串的部分匹配表
        int[] lps = buildLPS(pattern);
        int textIdx = 0;  // 文本指针
        int patternIdx = 0;  // 模式字符串指针

        while (textIdx < textLen) {
            if (text.charAt(textIdx) == pattern.charAt(patternIdx)) {
                textIdx++;
                patternIdx++;
            }

            // 匹配成功
            if (patternIdx == patternLen) {
                return textIdx - patternIdx;  // 找到匹配的位置
            }

            if (textIdx >= textLen || text.charAt(textIdx) == pattern.charAt(patternIdx)) {
                continue;
            }

            // 如果模式串已经有部分匹配成功，则跳过已匹配的部分
            if (patternIdx != 0) {
                patternIdx = lps[patternIdx - 1];
            } else {
                textIdx++;
            }
        }

        return -1;
    }

    /**
     * (LPS) Longest Prefix Suffix
     */
    private int[] buildLPS(String pattern) {
        int patternLen = pattern.length();
        int[] lps = new int[patternLen];
        lps[0] = 0;  // 第一个字符没有前后缀
        int len = 0; // 最长前后缀的长度

        int i = 1;
        while (i < patternLen) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len != 0) {
                // 如果不匹配，len会回退到lps[len-1]位置
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        return lps;
    }
}
