package dev.eposs.qas.skills.skilltree;

import java.util.Set;

public class ST_Element {
    final String rootName;
    final Set<ST_PathElement> elements;

    ST_Element(String rootName, Set<ST_PathElement> elements) {
        this.rootName = rootName;
        this.elements = elements;
    }
}
