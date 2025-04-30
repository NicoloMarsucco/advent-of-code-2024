package com.nmarsucco.adventofcode.util;

public class WholeFile {

    public final int id;
    public final int startIndex; // inclusive
    public final int length;

    public WholeFile(int id, int startIndex, int length) {
        this.id = id;
        this.startIndex = startIndex;
        this.length = length;
    }
}
