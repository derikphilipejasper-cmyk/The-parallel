package com.example.theparallel;

import com.example.theparallel.entity.ParallelEntities;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod(TheParallel.MOD_ID)
public class TheParallel {
    public static final String MOD_ID = "theparallel";

    public TheParallel() {
        ParallelEntities.ENTITY_TYPES.register(
                import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;.get().getModEventBus()
        );
    }
}
