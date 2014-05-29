package net.umllint.tool;

import net.umllint.tool.model.pattern.ULPattern;

import java.util.LinkedList;
import java.util.List;

public class ULPatternManager {

    private List<ULPattern> patterns = new LinkedList<ULPattern>();

    public ULPatternManager() {

    }

    public List<ULPattern> getPatterns() {
        return patterns;
    }

    public void addPattern(ULPattern pattern) {
        this.patterns.add(pattern);
    }
}