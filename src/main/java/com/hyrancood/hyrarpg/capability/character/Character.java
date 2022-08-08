package com.hyrancood.hyrarpg.capability.character;

public class Character implements ICharacter {
    private String character;
    private int step;
    private int level;
    private float xp;
    private int needXP;

    public Character() {
        this.character = "fighter";
        this.step = 0;
        this.level = 0;
        this.xp = 0;
        this.needXP = 10;
    }

    @Override
    public String getCharacter() {return this.character;}

    @Override
    public int getStep() {return this.step;}

    @Override
    public int getLevel() {return this.level;}

    @Override
    public float getXP() {return this.xp;}

    @Override
    public int getNeedXP() {return this.needXP;}

    @Override
    public void setCharacter(String character) {this.character = character;}

    @Override
    public void setStep(int step) {this.step = step;}

    @Override
    public void setLevel(int level) {this.level = level;}

    @Override
    public void setXP(float xp) {this.xp = xp;}

    @Override
    public void setNeedXP(int needXP) {this.needXP = needXP;}

    @Override
    public void addStep() {this.step++;}

    @Override
    public void addLevel() {this.level++;}

    @Override
    public void addXP(float xp) {this.xp += xp;}

    @Override
    public void addNeedXP(int needXP) {this.needXP += needXP;}
}
