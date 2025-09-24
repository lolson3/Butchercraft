// com/lance5057/butchercraft/client/ClientParticles.java
package com.lance5057.butchercraft.client;

import com.lance5057.butchercraft.Butchercraft;
import com.lance5057.butchercraft.particles.BloodDropParticle;
import com.lance5057.butchercraft.ButchercraftParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Butchercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientParticles {
	@SubscribeEvent
	public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ButchercraftParticles.BLOOD_DROP.get(), BloodDropParticle.Provider::new);
	}
}
