package com.hyrancood.hyrarpg.capability.mana;

public class Mana implements IMana {
    private float mana;
    private float[] MaxMana;
    private float[] ManaRegenSpeed;

    public Mana(){
        this.mana = 0;
        this.MaxMana = new float[]{40, 40};
        this.ManaRegenSpeed = new float[]{1, 1};
    }

    @Override
    public float getMana() { return this.mana; }

    @Override
    public float getMaxMana(int id) {return this.MaxMana[id]; }

    @Override
    public float[] getMaxManaArray() { return this.MaxMana; }

    @Override
    public float getManaRegenSpeed(int id) { return this.ManaRegenSpeed[id]; }

    @Override
    public float[] getManaRegenSpeedArray() { return this.ManaRegenSpeed; }

    @Override
    public void setMana(float amount) {this.mana = amount;}

    @Override
    public void setMaxMana(int id, float amount) { this.MaxMana[id] = amount; }

    @Override
    public void setMaxManaArray(float[] array) { this.MaxMana = array; }

    @Override
    public void setManaRegenSpeed(int id, float amount) { this.ManaRegenSpeed[id] = amount;}

    @Override
    public void setManaRegenSpeedArray(float[] array) { this.ManaRegenSpeed = array; }

    @Override
    public void addMana(float amount) {
        this.mana += amount;
    }

    @Override
    public void addMaxMana(int id, float amount) {
        this.MaxMana[id] += amount;
    }

    @Override
    public void addManaRegenSpeed(int id, float amount) {
        this.ManaRegenSpeed[id] += amount;
    }
}
