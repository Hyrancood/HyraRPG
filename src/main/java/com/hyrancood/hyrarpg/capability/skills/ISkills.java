package com.hyrancood.hyrarpg.capability.skills;

public interface ISkills {
    public String getFirstSkill();
    public String getSecondSkill();
    public int getFirstSkillKD();
    public int getSecondSkillKD();

    public void setFirstSkill(String skill);
    public void setSecondSkill(String skill);

    public void setFirstSkillKD(int time); //в секундах
    public void setSecondSkillKD(int time);

    public void shrinkSkillsKD();
}
