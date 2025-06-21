package com.shopify;

import com.shopify.model.ManipulatedTextRequest;
import com.shopify.model.ManipulatedTextResult;
import com.shopify.model.TextState;
import java.util.Objects;

public class TextManipulator {

    /**
     * Main text manipulation method that processes a series of commands on input text.
     * Commands can be single characters (h, l, r) or prefixed with numbers (Nh, Nl, Nr).
     * 
     * @param request Contains the original text and command string
     * @return Result with manipulated text and final cursor position
     */
    public static ManipulatedTextResult textManipulator(ManipulatedTextRequest request) {
        // Handle null request gracefully
        if (Objects.isNull(request)) {
            return ManipulatedTextResult.builder().build();
        }
        
        // Initialize state with original text and cursor at position 0
        TextState state = TextState.builder()
            .text(new StringBuilder(request.getInputString()))
            .cursorPosition(0)
            .build();
        
        // Process each character in the command string
        for (int i = 0; i < request.getCommands().length(); i++) {
            char currentChar = request.getCommands().charAt(i);
            
            // Check if current character is a digit (indicating a numeric command)
            if (Character.isDigit(currentChar)) {
                // Handle commands with numeric prefixes (e.g., 5l, 3h, 2r)
                i = handleNumericCommand(request.getCommands(), i, state);
            } else {
                // Handle single character commands (e.g., h, l, r)
                i = handleSingleCommand(request.getCommands(), i, state);
            }
        }
        
        // Return the final result with manipulated text and cursor position
        return ManipulatedTextResult.builder()
            .manipulatedText(state.getText().toString())
            .cursorPosition(state.getCursorPosition())
            .build();
    }

    /**
     * Handles single character commands: h (left), l (right), r (replace).
     * 
     * @param commands The full command string
     * @param index Current position in the command string
     * @param state Current text and cursor state
     * @return Updated index position in command string
     */
    private static int handleSingleCommand(String commands, int index, TextState state) {
        return handleCommand(commands, index, state, 1);
    }

    /**
     * Handles commands with numeric prefixes: Nh (move left N times), Nl (move right N times), Nr (replace N characters).
     * 
     * @param commands The full command string
     * @param index Current position in the command string
     * @param state Current text and cursor state
     * @return Updated index position in command string
     */
    private static int handleNumericCommand(String commands, int index, TextState state) {
        // Parse the numeric prefix to get the count
        int count = parseNumber(commands, index);
        
        // Advance index past all digits to get to the actual command
        while (index < commands.length() && Character.isDigit(commands.charAt(index))) {
            index++;
        }
        
        // Process the command if we haven't reached the end of the command string
        if (index < commands.length()) {
            return handleCommand(commands, index, state, count);
        }
        
        return index; // Return current index for next iteration
    }

    /**
     * Unified method to handle both single and numeric commands.
     * 
     * @param commands The full command string
     * @param index Current position in the command string
     * @param state Current text and cursor state
     * @param count Number of times to apply the command (1 for single commands)
     * @return Updated index position in command string
     */
    private static int handleCommand(String commands, int index, TextState state, int count) {
        char command = commands.charAt(index);
        
        switch (command) {
            case 'h' -> moveCursorLeft(state, count);
            case 'l' -> moveCursorRight(state, count);
            case 'r' -> {
                if (index + 1 < commands.length()) {
                    replaceCharacters(state, count, commands.charAt(index + 1));
                    return index + 1; // Skip the replacement character in next iteration
                }
            }
        }
        
        return index; // Return current index for next iteration
    }

    /**
     * Moves cursor to the left by the specified number of positions.
     * 
     * @param state Current text and cursor state
     * @param count Number of positions to move left
     */
    private static void moveCursorLeft(TextState state, int count) {
        state.setCursorPosition(Math.max(0, state.getCursorPosition() - count));
    }

    /**
     * Moves cursor to the right by the specified number of positions.
     * 
     * @param state Current text and cursor state
     * @param count Number of positions to move right
     */
    private static void moveCursorRight(TextState state, int count) {
        state.setCursorPosition(Math.min(state.getText().length() - 1, state.getCursorPosition() + count));
    }

    /**
     * Replaces characters starting from cursor position with the specified character.
     * 
     * @param state Current text and cursor state
     * @param count Number of characters to replace
     * @param replacementChar Character to replace with
     */
    private static void replaceCharacters(TextState state, int count, char replacementChar) {
        // Calculate the end position (don't go beyond text length)
        int endPos = Math.min(state.getCursorPosition() + count, state.getText().length());
        
        // Replace each character from cursor position to end position
        for (int j = state.getCursorPosition(); j < endPos; j++) {
            state.getText().setCharAt(j, replacementChar);
        }
        
        // Move cursor to the end of the replaced characters (as per problem spec)
        state.setCursorPosition(endPos - 1);
    }

    /**
     * Parses a numeric prefix from the command string, handling very large numbers gracefully.
     * 
     * @param commands The full command string
     * @param startIndex Starting position to parse from
     * @return The parsed number (clamped to Integer.MAX_VALUE if too large)
     */
    private static int parseNumber(String commands, int startIndex) {
        StringBuilder numberStr = new StringBuilder();
        int i = startIndex;
        
        // Collect all consecutive digits
        while (i < commands.length() && Character.isDigit(commands.charAt(i))) {
            numberStr.append(commands.charAt(i));
            i++;
        }
        
        try {
            // Try to parse as long first (handles larger numbers than int)
            long countLong = Long.parseLong(numberStr.toString());
            // Clamp to Integer.MAX_VALUE to prevent overflow
            return (int) Math.min(countLong, Integer.MAX_VALUE);
        } catch (NumberFormatException e) {
            // If number is too large even for long, use maximum safe value
            return Integer.MAX_VALUE;
        }
    }
}