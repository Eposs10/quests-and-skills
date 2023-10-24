package dev.eposs.qas.util.skills;

public class Skill {
    private final String name;
    private long skillExp;

    public Skill(String name, long skillExp) {
        this.name = name;
        this.skillExp = skillExp;
    }

    public String getName() {
        return name;
    }

    /**
     * @param skillExp cant be negative value
     */
    public void setSkillExp(long skillExp) {
        if (skillExp < 0) this.skillExp = Long.MAX_VALUE;
        else this.skillExp = skillExp;
    }

    public long getSkillExp() {
        return skillExp;
    }

    /**
     * 1000 Exp = 1 Level
     * @return skillExp / 1000
     */
    public long getLevel() {
        return skillExp / 1000;
    }
}
