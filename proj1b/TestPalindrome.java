import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String t1 = "";
        String t2 = "a";
        String t3 = "abccba";
        String f1 = "abcc";
        assertTrue(palindrome.isPalindrome(t1));
        assertTrue(palindrome.isPalindrome(t2));
        assertTrue(palindrome.isPalindrome(t3));
        assertFalse(palindrome.isPalindrome(f1));
    }

    @Test
    public void testIsPalindrome1() {
        String t1 = "";
        String t2 = "a";
        String t3 = "flake";
        String f1 = "aca";
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome(t1, cc));
        assertTrue(palindrome.isPalindrome(t2, cc));
        assertTrue(palindrome.isPalindrome(t3, cc));
        assertFalse(palindrome.isPalindrome(f1, cc));
    }
}
