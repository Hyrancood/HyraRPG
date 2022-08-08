package com.hyrancood.hyrarpg.capability.character;

public interface ICharacter {

    public String getCharacter();
    public int getStep();
    public int getLevel();
    public float getXP();
    public int getNeedXP();

    public void setCharacter(String character);
    public void setStep(int step);
    public void setLevel(int level);
    public void setXP(float xp);
    public void setNeedXP(int needXP);

    public void addStep();
    public void addLevel();
    public void addXP(float xp);
    public void addNeedXP(int needXP);
}
