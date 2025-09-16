package com.rajeev.models;

public class Symbol {
    private char charSymbol;
    private String color;

    public Symbol(char charSymbol, String color) {
        this.charSymbol = charSymbol;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public char getSymbol() {
        return charSymbol;
    }

    public void setSymbol(char symbol) {
        this.charSymbol = symbol;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * (result + charSymbol);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Symbol)) return false;
        Symbol other = (Symbol)obj;
        return charSymbol == other.charSymbol && color.equals(other.color);
    }
}
