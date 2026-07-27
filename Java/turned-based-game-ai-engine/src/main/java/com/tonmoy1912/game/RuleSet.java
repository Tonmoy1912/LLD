package com.tonmoy1912.game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RuleSet implements Iterable<Rule> {
    private Set<Rule> ruleList = new HashSet<>();

    public void add(Rule rule) {
        ruleList.add(rule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return ruleList.iterator();
    }
}
