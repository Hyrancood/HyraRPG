package com.hyrancood.hyrarpg;

import com.hyrancood.hyrarpg.entity.projectile.RockEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HyraRPGEntities {
    public static EntityType<RockEntity> ROCK;

    @SubscribeEvent
    public static void onEntityTypeRegistration(RegistryEvent.Register<EntityType<?>> entityTypeRegisterEvent) {
        ROCK = EntityType.Builder.<RockEntity>of(RockEntity::new, EntityClassification.MISC)
                .sized(0.25F, 0.25F)
                .build("rock");
        ROCK.setRegistryName("rock");
        entityTypeRegisterEvent.getRegistry().register(ROCK);
    }

}
