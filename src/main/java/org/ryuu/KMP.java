package org.ryuu;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <a href="https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm"></a>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KMP {
    public static class KMPNormal {
        public int search(String text, String pattern) {
            if (pattern.isEmpty()) {
                return 0;
            }

            int[] lps = computeLongestPrefixSuffix(pattern);
            int textIndex = 0;
            int patternIndex = 0;

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

        private static int[] computeLongestPrefixSuffix(String pattern) {
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

    public static class KMPNext {
        public int search(String text, String pattern) {
            if (pattern.isEmpty()) {
                return 0;
            }

            int[] next = computeNext(pattern);
            int textIndex = 0;
            int patternIndex = 0;
            while (textIndex < text.length() && patternIndex < pattern.length()) {
                if (patternIndex == -1 || text.charAt(textIndex) == pattern.charAt(patternIndex)) {
                    textIndex++;
                    patternIndex++;
                } else {
                    patternIndex = next[patternIndex];
                }
            }

            if (patternIndex == pattern.length()) {
                return textIndex - patternIndex;
            }

            return -1;
        }

        private static int[] computeNext(String pattern) {
            int patternLength = pattern.length();
            int[] next = new int[pattern.length()];
            next[0] = -1;
            int k = -1;
            int i = 0;
            while (i < patternLength - 1) {
                if (k == -1 || pattern.charAt(i) == pattern.charAt(k)) {
                    k++;
                    i++;
                    next[i] = k;
                } else {
                    k = next[k];
                }
            }
            return next;
        }
    }

    public static class KMPNextVal {
        public int search(String text, String pattern) {
            if (pattern.isEmpty()) {
                return 0;
            }

            int[] nextVal = computeNextVal(pattern);
            int textIndex = 0;
            int patternIndex = 0;

            while (textIndex < text.length() && patternIndex < pattern.length()) {
                if (patternIndex == -1 || text.charAt(textIndex) == pattern.charAt(patternIndex)) {
                    textIndex++;
                    patternIndex++;
                } else {
                    patternIndex = nextVal[patternIndex];
                }
            }

            if (patternIndex == pattern.length()) {
                return textIndex - patternIndex;
            }

            return -1;
        }

        private static int[] computeNextVal(String pattern) {
            int[] next = computeNext(pattern);
            int[] nextVal = new int[pattern.length()];
            nextVal[0] = -1;

            for (int i = 1; i < pattern.length(); i++) {
                int nextI = next[i];
                if (pattern.charAt(i) == pattern.charAt(nextI)) {
                    nextVal[i] = nextVal[nextI];
                } else {
                    nextVal[i] = nextI;
                }
            }
            return nextVal;
        }

        private static int[] computeNext(String pattern) {
            int patternLength = pattern.length();
            int[] next = new int[pattern.length()];
            next[0] = -1;
            int right = 0;
            int left = -1;
            while (right < patternLength - 1) {
                if (left == -1 || pattern.charAt(left) == pattern.charAt(right)) {
                    left++;
                    right++;
                    next[right] = left;
                } else {
                    left = next[left];
                }
            }
            return next;
        }
    }
}
