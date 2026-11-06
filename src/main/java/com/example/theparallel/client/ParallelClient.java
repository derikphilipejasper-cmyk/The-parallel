
package com.example.theparallel.client;

import com.example.theparallel.TheParallel;
import com.example.theparallel.entity.ParallelEntities;
import com.example.theparallel.entity.ParallelMonster;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = TheParallel.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public final class ParallelClient {

    private ParallelClient() {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ParallelEntities.STALKER.get(),
                ctx -> new SimpleRenderer(ctx, "stalker")
        );

        event.registerEntityRenderer(
                ParallelEntities.TORMENTOR.get(),
                ctx -> new SimpleRenderer(ctx, "tormentor")
        );

        event.registerEntityRenderer(
                ParallelEntities.VIRUS.get(),
                ctx -> new SimpleRenderer(ctx, "virus")
        );
    }

    private static final class SimpleRenderer extends HumanoidMobRenderer<
            ParallelMonster, HumanoidModel<ParallelMonster>> {

        private final ResourceLocation texture;

        SimpleRenderer(EntityRendererProvider.Context ctx, String name) {
            super(
                    ctx,
                    new HumanoidModel<>(ctx.bakeLayer(ModelLayers.PLAYER)),
                    0.5F
            );

            this.texture = new ResourceLocation(
                    TheParallel.MOD_ID,
                    "textures/entity/" + name + ".png"
            );
        }

        @Override
        public ResourceLocation getTextureLocation(ParallelMonster entity) {
            return texture;
        }
    }
}
