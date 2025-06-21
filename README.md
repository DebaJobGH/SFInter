Shopify Techical Challenge

**Problem Statement**
Given an input text and command string, apply the command instructions on the inputed text and return the updated text and cursor position.

**Rules**
- `h`: Move cursor to the left.
- `l`: Move cursor to the right
- `r<c>`: Replace the next character with `c`(char after `r`)

- `[N]h`: Move cursor N characters to the left.
- `[N]l`: Move cursor N characters to the right. 
- `Nr<c>`: Replace next N character with `c`(char after `r`)

**Test Cases**
Test Case 1
- Input Text: `"Hello World"`
- Command: `"hll"`
- Output:
   - Text: `"Hello World"`
   - Cursor Position: `2`

Test Case 2
- Input Text: `"Hello World"`
- Command: `"rl"`
- Output:
   - Text: `"lello World"`
   - Cursor Position: `0`

Test Case 3
- Input Text: `"Hello World"`
- Command: `"rh6l9l4hrw"`
- Output:
   - Text: `"hello world"`
   - Cursor Position: `6`

Test Case 4
- Input Text: "Hello World"
- Command: "9lrL7h2rL"
- Output:
   - Text: "HeLLo WorLd"
   - Cursor Position: 3

Test Case 5
- Input Text: `"Hello World"`
- Command: `"999999999999999999999999999lr0"`
- Output:
   - Text: `"Hello Worl0"`
   - Cursor Position: `10`

Test Case 6
- Input Text: `"Hello World"`
- Command: `"999rs"`
- Output:
   - Text: `"sssssssssss"`
   - Cursor Position: `10`
