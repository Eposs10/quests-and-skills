package dev.eposs.qas.skills;

public class Skill {
    public static final int xpPerLevel = 1000;

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

    public long getLevel() {
        return getLevelForTotalExp(skillExp);
    }

    /**
     * x : exp
     * @return lvl = sqrt(exp) / 10
     */
    public static long getLevelForTotalExp(long exp) {
        return (long) (Math.sqrt(exp) / 10);
    }

    /**
     * x : lvl
     * @return exp = 100 * x^2
     */
    public static long getExpForLevel(long level) {
        return (long) (100 * Math.pow(level, 2));
    }
}
