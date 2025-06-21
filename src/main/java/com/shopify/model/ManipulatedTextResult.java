package com.shopify.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManipulatedTextResult {
    private String manipulatedText;
    private int cursorPosition;
} 