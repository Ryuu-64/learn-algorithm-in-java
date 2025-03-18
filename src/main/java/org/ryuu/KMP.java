package org.ryuu;

/**
 * <a href="https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm"></a>
 */
public class KMP {
    public int search(String text, String pattern) {
        if (pattern.isEmpty()) {
            return 0;
        }

        int[] lps = buildLongestPrefixSuffix(pattern);
        int textIndex = 0, patternIndex = 0;

        while (textIndex < text.length()) {
            if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {// 当前字符匹配成功
                textIndex++;
                patternIndex++;

                if (patternIndex == pattern.length()) {
                    return textIndex - patternIndex;
                }
            } else if (patternIndex != 0) { // 部分匹配时回溯模式串指针
                patternIndex = lps[patternIndex - 1];
            } else { // 完全无匹配时移动文本指针
                textIndex++;
            }
        }

        return -1;
    }

    private int[] buildLongestPrefixSuffix(String pattern) {
        int patternLength = pattern.length();
        int[] longestPrefixSuffix = new int[patternLength];
        int currentLongestLength = 0;

        for (int currentIndex = 1; currentIndex < patternLength; ) {
            if (pattern.charAt(currentIndex) == pattern.charAt(currentLongestLength)) {
                currentLongestLength++;
                longestPrefixSuffix[currentIndex] = currentLongestLength;
                currentIndex++;
            } else if (currentLongestLength != 0) {
                currentLongestLength = longestPrefixSuffix[currentLongestLength - 1];
            } else {
                longestPrefixSuffix[currentIndex] = 0;
                currentIndex++;
            }
        }
        return longestPrefixSuffix;
    }
}
