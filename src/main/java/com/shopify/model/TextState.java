package com.shopify.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * State object that holds the current text and cursor position.
 * This allows us to modify both values throughout the command processing.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextState {
    private StringBuilder text;        // Mutable text that gets modified by commands
    private int cursorPosition;        // Current cursor position (0-indexed)
} 