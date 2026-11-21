package com.Peterhun.create_reactive_stress.mixin;

import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(value = MechanicalMixerBlockEntity.class, remap = false)
public interface MechanicalMixerAccessor {
    @Invoker("getMatchingRecipes")
    List<Recipe<?>> createReactiveStress$getMatchingRecipes();
}
