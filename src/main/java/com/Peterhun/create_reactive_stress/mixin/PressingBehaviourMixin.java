package com.Peterhun.create_reactive_stress.mixin;

import com.Peterhun.create_reactive_stress.ReactiveStressNetwork;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.content.kinetics.press.PressingBehaviour;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PressingBehaviour.class, remap = false)
public abstract class PressingBehaviourMixin {
    @Inject(method = "start", at = @At("RETURN"))
    private void createReactiveStress$onPressStarted(PressingBehaviour.Mode mode, CallbackInfo ci) {
        createReactiveStress$refreshPress();
    }

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/kinetics/press/PressingBehaviour;running:Z",
            opcode = Opcodes.PUTFIELD, shift = At.Shift.AFTER))
    private void createReactiveStress$onPressFinished(CallbackInfo ci) {
        createReactiveStress$refreshPress();
    }

    @Unique
    private void createReactiveStress$refreshPress() {
        if (((PressingBehaviour) (Object) this).blockEntity instanceof MechanicalPressBlockEntity press) {
            ReactiveStressNetwork.refresh(press);
        }
    }
}
