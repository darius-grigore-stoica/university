package org.example;

import java.util.Arrays;

public class Atom {
    private String input;

    private final String ID = "^[a-z][a-z0-9]{0,7}";
    private final String CONST = "^[0-9]\\.*[0-9]*";
    private final String[] BOOL_OPERATORS = {"<", ">", "<=", ">=", "==", "!="};
    private final String[] OPERATORS = {"+", "-", "*", "/", "="};
    private final String[] SEPARATORS = {",", ";", "{", "}", "<<", ">>", "[", "]"};
    private final String[] KEYWORDS = {"class", "Main", "public static void main", "String", "if", "else", "System.out.println"};

    public Atom(String input) {
        this.input = input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public boolean isId() {
        return input.matches(ID);
    }

    public boolean isConst() {
        return input.matches(CONST);
    }

    public boolean isBoolOperator() {
        return Arrays.stream(BOOL_OPERATORS).anyMatch((String element) -> element.equals(input));
    }

    public boolean isOperator() {
        return Arrays.stream(OPERATORS).anyMatch((String element) -> element.equals(input));
    }

    public boolean isSeparator() {
        return Arrays.stream(SEPARATORS).anyMatch((String element) -> element.equals(input));
    }

    public boolean isKeyword() {
        return Arrays.stream(KEYWORDS).anyMatch((String element) -> element.equals(input));
    }

    @Override
    public String toString() {
        return input;
    }
}