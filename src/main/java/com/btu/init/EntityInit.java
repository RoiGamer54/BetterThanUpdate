// com/btu/init/EntityInit.java

package com.btu.init;

import com.btu.BetterThanUpdate;
import com.btu.entity.SeatEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntityInit {

    public static final EntityType<SeatEntity> SEAT = Registry.register(
            Registries.ENTITY_TYPE,
            BetterThanUpdate.id("seat"),
            // 🟢 CORRECTION: Ajout du type générique <SeatEntity>
            FabricEntityTypeBuilder.<SeatEntity>create(SpawnGroup.MISC, SeatEntity::new)
                    .dimensions(EntityDimensions.fixed(0.01f, 0.01f))
                    .build()
    );

    public static void load() {
        // Cette méthode vide force le chargement de la classe et l'enregistrement
    }
}