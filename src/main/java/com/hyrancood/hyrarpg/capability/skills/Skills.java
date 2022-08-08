package com.hyrancood.hyrarpg.capability.skills;

public class Skills implements ISkills{
    private String firstSkill;
    private String secondSkill;
    private int firstSkillKD;
    private int secondSkillKD;

    public Skills(){
        firstSkill = "hardBlow";
        secondSkill = "none";
        firstSkillKD = 0;
        secondSkillKD = 0;
    }

    @Override
    public String getFirstSkill() { return this.firstSkill; }

    @Override
    public String getSecondSkill() { return this.secondSkill; }

    @Override
    public int getFirstSkillKD() { return this.firstSkillKD; }

    @Override
    public int getSecondSkillKD() { return this.secondSkillKD; }

    @Override
    public void setFirstSkill(String skill) {
        this.firstSkill = skill;
    }

    @Override
    public void setSecondSkill(String skill) {
        this.secondSkill = skill;
    }

    @Override
    public void setFirstSkillKD(int time) {
        this.firstSkillKD = time*20;
    }

    @Override
    public void setSecondSkillKD(int time) {
        this.secondSkillKD = time*20;
    }

    @Override
    public void shrinkSkillsKD() {
        if (this.firstSkillKD > 0) { this.firstSkillKD--; }
        if (this.secondSkillKD > 0) { this.secondSkillKD--; }
    }
}
