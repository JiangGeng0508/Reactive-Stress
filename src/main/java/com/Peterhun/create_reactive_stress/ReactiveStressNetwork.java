package com.Peterhun.create_reactive_stress;

import com.simibubi.create.content.kinetics.KineticNetwork;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.server.level.ServerLevel;

public final class ReactiveStressNetwork {
    private ReactiveStressNetwork() {}

    public static void refresh(KineticBlockEntity member) {
        if (!(member.getLevel() instanceof ServerLevel) || !member.hasNetwork()) return;

        KineticNetwork network = member.getOrCreateNetwork();
        if (network == null) return;
        Float previousImpact = network.members.get(member);
        if (previousImpact == null) return;

        float impact = member.calculateStressApplied();
        if (Math.abs(impact - previousImpact) > 1e-4f) {
            network.members.put(member, impact);
            // A newly connected source's capacity can still be waiting for the dirty network update.
            network.updateNetwork();
            member.setChanged();
        }
    }
}
