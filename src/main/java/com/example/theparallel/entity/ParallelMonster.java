package com.example.theparallel.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ParallelMonster extends Monster {
    public enum Kind { STALKER, TORMENTOR, VIRUS }
    private final Kind kind;
    private int stareTicks;

    public ParallelMonster(EntityType<? extends Monster> type, Level level, Kind kind) {
        super(type, level);
        this.kind = kind;
        this.xpReward = 12;
    }

    public static AttributeSupplier.Builder createAttributes(double health, double damage, double speed) {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, health)
                .add(Attributes.MOVEMENT_SPEED, speed)
                .add(Attributes.ATTACK_DAMAGE, damage)
                .add(Attributes.FOLLOW_RANGE, 40.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.65);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, kind == Kind.TORMENTOR ? 1.35 : 1.0, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.75));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 28.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) return;
        Player nearest = this.level().getNearestPlayer(this, 24.0);
        if (nearest != null && this.hasLineOfSight(nearest)) {
            stareTicks++;
            // The Stalker pauses to watch before beginning its approach.
            if (kind == Kind.STALKER && stareTicks < 45 && this.distanceToSqr(nearest) > 9.0) {
                this.getNavigation().stop();
            }
        } else {
            stareTicks = Math.max(0, stareTicks - 2);
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
}
