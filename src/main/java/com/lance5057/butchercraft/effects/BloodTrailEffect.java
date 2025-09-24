package com.lance5057.butchercraft.effects;

import com.lance5057.butchercraft.ButchercraftParticles;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class BloodTrailEffect extends SoapableMobEffect {
	public BloodTrailEffect() {
		super(MobEffectCategory.HARMFUL, 7995392);
	}
	
	@Override
	 public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
			super.applyEffectTick(pLivingEntity, pAmplifier);

			if (pLivingEntity.isInWaterRainOrBubble()) return;
			if (pLivingEntity.getDeltaMovement().lengthSqr() < 0.005) return;
			
			for (int i = 0; i < 3; i++)
				pLivingEntity.level().addParticle(ButchercraftParticles.BLOOD_DROP.get(),
						pLivingEntity.position().x - 0.25f + pLivingEntity.level().random.nextDouble() / 2,
						pLivingEntity.position().y + 0.25f - pLivingEntity.level().random.nextDouble(),
						pLivingEntity.position().z - 0.25f + pLivingEntity.level().random.nextDouble() / 2, 0, 0,
						0);
	 }

	@Override
	public boolean isDurationEffectTick(int duration, int amp) {
		// ~once every 5 ticks (4x/sec); tweak as desired
		int interval = Math.max(1, 5 - amp);
		return duration % interval == 0;
	}
}
