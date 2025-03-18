package org.ryuu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KMPTest {

    @Test
    void search() {
        KMP kmp = new KMP();
        String text = "ABABDABAAACAAAAABCABAB";
        String pattern = "AACAAA";

        int result = kmp.search(text, pattern);
        assertEquals(8, result);
    }
}
