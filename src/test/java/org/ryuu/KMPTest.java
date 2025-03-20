package org.ryuu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.ryuu.KMP.*;

class KMPTest {
    @Test
    void KMPNormalSearch() {
        KMPNormal kmp = new KMPNormal();
        String text = "ABABDABAAACAAAAABCABAB";
        String pattern = "AACAAA";

        int result = kmp.search(text, pattern);
        assertEquals(8, result);
    }
    @Test
    void KMPNext() {
        KMPNext kmp = new KMPNext();
        String text = "ABABDABAAACAAAAABCABAB";
        String pattern = "AACAAA";

        int result = kmp.search(text, pattern);
        assertEquals(8, result);
    }

    @Test
    void KMPNextVal() {
        KMPNextVal kmp = new KMPNextVal();
        String text = "ABABDABAAACAAAAABCABAB";
        String pattern = "AACAAA";

        int result = kmp.search(text, pattern);
        assertEquals(8, result);
    }
}
