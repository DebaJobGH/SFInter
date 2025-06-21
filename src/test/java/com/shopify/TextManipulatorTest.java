package com.shopify;

import com.shopify.model.ManipulatedTextRequest;
import com.shopify.model.ManipulatedTextResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TextManipulator Tests")
class TextManipulatorTest {

    private final TextManipulator textManipulator = new TextManipulator();

    @Test
    @DisplayName("Test case 1: hll")
    void testCase1() {
        // Given
        ManipulatedTextRequest request = ManipulatedTextRequest.builder()
            .inputString("Hello World")
            .commands("hll")
            .build();

        // When
        ManipulatedTextResult result = textManipulator.textManipulator(request);

        // Then
        assertEquals("Hello World", result.getManipulatedText());
        assertEquals(2, result.getCursorPosition());
    }

    @Test
    @DisplayName("Test case 2: rl")
    void testCase2() {
        // Given
        ManipulatedTextRequest request = ManipulatedTextRequest.builder()
            .inputString("Hello World")
            .commands("rl")
            .build();

        // When
        ManipulatedTextResult result = textManipulator.textManipulator(request);

        // Then
        assertEquals("lello World", result.getManipulatedText());
        assertEquals(0, result.getCursorPosition());
    }

    @Test
    @DisplayName("Test case 3: rh6l9l4hrw")
    void testCase3() {
        // Given
        ManipulatedTextRequest request = ManipulatedTextRequest.builder()
            .inputString("Hello World")
            .commands("rh6l9l4hrw")
            .build();

        // When
        ManipulatedTextResult result = textManipulator.textManipulator(request);

        // Then
        assertEquals("hello world", result.getManipulatedText());
        assertEquals(6, result.getCursorPosition());
    }

    @Test
    @DisplayName("Test case 4: 9lrL7h2rL")
    void testCase4() {
        // Given
        ManipulatedTextRequest request = ManipulatedTextRequest.builder()
            .inputString("Hello World")
            .commands("9lrL7h2rL")
            .build();

        // When
        ManipulatedTextResult result = textManipulator.textManipulator(request);

        // Then
        assertEquals("HeLLo WorLd", result.getManipulatedText());
        assertEquals(3, result.getCursorPosition());
    }

    @Test
    @DisplayName("Test case 5: 999999999999999999999999999lr0")
    void testCase5() {
        // Given
        ManipulatedTextRequest request = ManipulatedTextRequest.builder()
            .inputString("Hello World")
            .commands("999999999999999999999999999lr0")
            .build();

        // When
        ManipulatedTextResult result = textManipulator.textManipulator(request);

        // Then
        assertEquals("Hello Worl0", result.getManipulatedText());
        assertEquals(10, result.getCursorPosition());
    }

    @Test
    @DisplayName("Test case 6: 999rs")
    void testCase6() {
        // Given
        ManipulatedTextRequest request = ManipulatedTextRequest.builder()
            .inputString("Hello World")
            .commands("999rs")
            .build();

        // When
        ManipulatedTextResult result = textManipulator.textManipulator(request);

        // Then
        assertEquals("sssssssssss", result.getManipulatedText());
        assertEquals(10, result.getCursorPosition());
    }
} 