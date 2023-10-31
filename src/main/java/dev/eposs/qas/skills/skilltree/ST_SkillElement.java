package dev.eposs.qas.skills.skilltree;

import java.util.Set;

public class ST_SkillElement {
    public static ST_SkillElement EMPTY = new ST_SkillElement("", Set.of());

    public final String rootName;
    public final Set<ST_PathElement> elements;

    ST_SkillElement(String rootName, Set<ST_PathElement> elements) {
        this.rootName = rootName;
        this.elements = elements;
    }

    String getNameAsNbtKey() {
        return rootName.toLowerCase().replaceAll(" ", "_");
    }
}
