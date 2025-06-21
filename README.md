# Shopify Technical Challenge

## Problem Statement

Given an input text and command string, apply the command instructions on the input text and return the updated text and cursor position.

## Command Rules

### Basic Commands
- `h` - Move cursor to the left
- `l` - Move cursor to the right
- `r<c>` - Replace the next character with `c` (character after `r`)

### Advanced Commands
- `[N]h` - Move cursor N characters to the left
- `[N]l` - Move cursor N characters to the right
- `Nr<c>` - Replace next N characters with `c` (character after `r`)

## Test Cases

### Test Case 1
**Input:**
- Text: `"Hello World"`
- Command: `"hll"`

**Expected Output:**
- Text: `"Hello World"`
- Cursor Position: `2`

### Test Case 2
**Input:**
- Text: `"Hello World"`
- Command: `"rl"`

**Expected Output:**
- Text: `"lello World"`
- Cursor Position: `0`

### Test Case 3
**Input:**
- Text: `"Hello World"`
- Command: `"rh6l9l4hrw"`

**Expected Output:**
- Text: `"hello world"`
- Cursor Position: `6`

### Test Case 4
**Input:**
- Text: `"Hello World"`
- Command: `"9lrL7h2rL"`

**Expected Output:**
- Text: `"HeLLo WorLd"`
- Cursor Position: `3`

### Test Case 5
**Input:**
- Text: `"Hello World"`
- Command: `"999999999999999999999999999lr0"`

**Expected Output:**
- Text: `"Hello Worl0"`
- Cursor Position: `10`

### Test Case 6
**Input:**
- Text: `"Hello World"`
- Command: `"999rs"`

**Expected Output:**
- Text: `"sssssssssss"`
- Cursor Position: `10`

## Implementation Notes

This project implements a text manipulator that processes commands to modify text and track cursor position. The solution handles both basic and advanced command patterns with proper boundary checking and character replacement logic.
