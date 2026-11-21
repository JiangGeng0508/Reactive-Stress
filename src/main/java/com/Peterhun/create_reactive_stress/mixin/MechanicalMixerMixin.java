package com.Peterhun.create_reactive_stress.mixin;

import com.Peterhun.create_reactive_stress.ReactiveStressNetwork;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MechanicalMixerBlockEntity.class, remap = false)
public abstract class MechanicalMixerMixin {
    @Inject(method = {"startProcessingBasin", "onBasinRemoved"}, at = @At("RETURN"))
    private void createReactiveStress$onProcessingChange(CallbackInfo ci) {
        ReactiveStressNetwork.refresh((MechanicalMixerBlockEntity) (Object) this);
    }

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/kinetics/mixer/MechanicalMixerBlockEntity;running:Z",
            opcode = Opcodes.PUTFIELD, shift = At.Shift.AFTER))
    private void createReactiveStress$onProcessingFinished(CallbackInfo ci) {
        ReactiveStressNetwork.refresh((MechanicalMixerBlockEntity) (Object) this);
    }
}
