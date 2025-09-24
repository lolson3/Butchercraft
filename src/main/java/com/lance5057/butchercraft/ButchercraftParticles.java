// com/lance5057/butchercraft/registry/ModParticles.java
package com.lance5057.butchercraft;

import com.lance5057.butchercraft.Butchercraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ButchercraftParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES =
			DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Butchercraft.MOD_ID);

	// true = always show, no distance culling (you can set false if you want)
	public static final RegistryObject<SimpleParticleType> BLOOD_DROP =
			PARTICLES.register("blood_drop", () -> new SimpleParticleType(true));

	public static void register(IEventBus bus) {
		PARTICLES.register(bus);
	}
}
