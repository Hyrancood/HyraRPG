package com.hyrancood.hyrarpg.capability.mana;

public interface IMana {
    public float getMana();
    public float getMaxMana(int id);
    public float[] getMaxManaArray();
    public float getManaRegenSpeed(int id);
    public float[] getManaRegenSpeedArray();

    public void setMana(float amount);
    public void setMaxMana(int id, float amount);
    public void setMaxManaArray(float[] array);
    public void setManaRegenSpeed(int id, float amount);
    public void setManaRegenSpeedArray(float[] array);

    public void addMana(float amount);
    public void addMaxMana(int id, float amount);
    public void addManaRegenSpeed(int id, float amount);

}
