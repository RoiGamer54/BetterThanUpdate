// com/btu/entity/SeatEntity.java

package com.btu.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class SeatEntity extends ArmorStandEntity {

    public SeatEntity(EntityType<? extends ArmorStandEntity> entityType, World world) {
        super(entityType, world);
    }

    public SeatEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
        this.setInvisible(true);  // Rendre l'entité invisible
        this.setInvulnerable(true); // Empêche de la casser
        this.setNoGravity(true); // Elle ne tombe pas
    }

    @Override
    public void tick() {
        super.tick();
        // Si l'entité n'a plus de passager, elle se détruit (le joueur s'est relevé)
        if (!this.getWorld().isClient && this.getPassengerList().isEmpty()) {
            this.discard();
        }
    }
}
