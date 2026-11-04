package com.example.theparallel.entity;

import com.example.theparallel.TheParallel;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public final class ParallelEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheParallel.MOD_ID);

    private static ResourceKey<EntityType<?>> entityKey(String name) {
        return ResourceKey.create(
                Registries.ENTITY_TYPE,
                Identifier.fromNamespaceAndPath(TheParallel.MOD_ID, name)
        );
    }

    public static final RegistryObject<EntityType<ParallelMonster>> STALKER = ENTITY_TYPES.register("stalker",
            () -> EntityType.Builder.<ParallelMonster>of(
                    (type, level) -> new ParallelMonster(type, level, ParallelMonster.Kind.STALKER),
                    MobCategory.MONSTER)
                    .sized(0.8F, 2.65F).clientTrackingRange(10).build(entityKey("stalker")));

    public static final RegistryObject<EntityType<ParallelMonster>> TORMENTOR = ENTITY_TYPES.register("tormentor",
            () -> EntityType.Builder.<ParallelMonster>of(
                    (type, level) -> new ParallelMonster(type, level, ParallelMonster.Kind.TORMENTOR),
                    MobCategory.MONSTER)
                    .sized(1.0F, 3.1F).clientTrackingRange(12).build(entityKey("tormentor")));

    public static final RegistryObject<EntityType<ParallelMonster>> VIRUS = ENTITY_TYPES.register("virus",
            () -> EntityType.Builder.<ParallelMonster>of(
                    (type, level) -> new ParallelMonster(type, level, ParallelMonster.Kind.VIRUS),
                    MobCategory.MONSTER)
                    .sized(0.9F, 2.2F).clientTrackingRange(10).build(entityKey("virus")));

    private ParallelEntities() {}

    @Mod.EventBusSubscriber(modid = TheParallel.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class Attributes {
        @SubscribeEvent
        public static void createAttributes(EntityAttributeCreationEvent event) {
            event.put(STALKER.get(), ParallelMonster.createAttributes(34.0, 5.0, 0.31).build());
            event.put(TORMENTOR.get(), ParallelMonster.createAttributes(75.0, 9.0, 0.36).build());
            event.put(VIRUS.get(), ParallelMonster.createAttributes(26.0, 4.0, 0.29).build());
        }
    }
}
