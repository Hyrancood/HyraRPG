package com.hyrancood.hyrarpg.util.events;

import com.hyrancood.hyrarpg.HyraRPG;
import com.hyrancood.hyrarpg.capability.mana.IMana;
import com.hyrancood.hyrarpg.capability.mana.Mana;
import com.hyrancood.hyrarpg.capability.mana.ManaProvider;
import com.hyrancood.hyrarpg.util.CapabilityHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HyraRPG.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PassiveManaRegeneration {

    @SubscribeEvent
    public void manaRegeneration(TickEvent.PlayerTickEvent event){
        if (event.phase == TickEvent.Phase.END){
            PlayerEntity player = event.player;
            IMana mana = player.getCapability(ManaProvider.capability).orElseGet(Mana::new);

            float currentMana = mana.getMana();
            float currentMaxMana = mana.getMaxMana(1);
            float currentManaSpeed = mana.getManaRegenSpeed(1);
            float needTicks = 40 / currentManaSpeed;

            CompoundNBT nbt = player.getPersistentData();
            String name = "manaRegeneration";

            if (currentMana != currentMaxMana){
                if (currentMana < currentMaxMana){
                    nbt.putFloat(name, nbt.getFloat(name) + 1);
                    if (nbt.getFloat(name) >= needTicks){
                        mana.addMana(1);
                        nbt.putFloat(name, 0);
                        if(currentMana % 5 == 0) System.out.println(mana.getMana());
                    }
                } else {
                    mana.setMana(currentMaxMana);
                    nbt.putFloat(name, 0);
                }
            }
        }
    }
}
