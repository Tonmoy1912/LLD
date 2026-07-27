package com.tonmoy1912.game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RuleSet<T extends Board> implements Iterable<Rule<T>> {
    private Set<Rule<T>> ruleList=new HashSet<>();

    public void add(Rule<T> rule){
        ruleList.add(rule);
    }

    @Override
    public Iterator<Rule<T>> iterator() {
        return ruleList.iterator();
    }
}
