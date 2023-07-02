package com.hyrancood.hyrarpg.entity.projectile;

import com.hyrancood.hyrarpg.HyraRPGEntities;
import com.hyrancood.hyrarpg.item.HyraRPGItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class RockEntity extends ProjectileItemEntity {

    public RockEntity(EntityType<? extends RockEntity> entityType, World world) {
        super(entityType, world);
    }

    public RockEntity(World p_i50150_1_, LivingEntity p_i50150_2_) {
        super(HyraRPGEntities.ROCK, p_i50150_2_, p_i50150_1_);
    }

    public RockEntity(World p_i50151_1_, double p_i50151_2_, double p_i50151_4_, double p_i50151_6_) {
        super(HyraRPGEntities.ROCK, p_i50151_2_, p_i50151_4_, p_i50151_6_, p_i50151_1_);
    }

    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected Item getDefaultItem() {
        return HyraRPGItems.ROCK.get();
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return (IParticleData)(itemstack.isEmpty() ? ParticleTypes.ASH : new ItemParticleData(ParticleTypes.ITEM, itemstack));
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 3) {
            IParticleData iparticledata = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    protected void onHitBlock(BlockRayTraceResult ray) {
        super.onHitBlock(ray);
    }

    protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);
        Entity entity = p_213868_1_.getEntity();
        entity.hurt(DamageSource.thrown(this, this.getOwner()), 4);
    }

    protected void onHit(RayTraceResult p_70227_1_) {
        super.onHit(p_70227_1_);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)4);
            this.remove();
        }

    }

    @Override
    public void tick() {
        super.tick();
    }
}
