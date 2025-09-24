// com/lance5057/butchercraft/particles/BloodDropParticle.java
package com.lance5057.butchercraft.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;

public class BloodDropParticle extends TextureSheetParticle {

	protected BloodDropParticle(ClientLevel level, double x, double y, double z,
								double vx, double vy, double vz, SpriteSet sprites) {
		super(level, x, y, z, vx, vy, vz);

		this.pickSprite(sprites);

		// motion & physics
		this.gravity = 0.20F;           // gentle fall
		this.friction = 0.86F;          // slow horizontal
		this.xd = vx; this.yd = vy; this.zd = vz;

		// visuals
		this.quadSize = 0.06F + random.nextFloat() * 0.04F;
		this.rCol = 0.78f; this.gCol = 0.02f; this.bCol = 0.02f; // deep red
		this.alpha = 0.95f;

		// lifespan
		this.lifetime = 16 + random.nextInt(10); // ~0.8–1.3s at 20tps
	}

	@Override
	public void tick() {
		super.tick();

		// vanish if touching water
		if (level.getFluidState(BlockPos.containing(x, y, z)).is(FluidTags.WATER)) {
			this.remove();
			return;
		}

		// "splat": when it hits ground, kill quickly
		if (this.onGround) {
			this.xd *= 0.5;
			this.zd *= 0.5;
			this.yd = 0;
			this.lifetime = Math.min(this.lifetime, 4);
		}
	}

	@Override
	public ParticleRenderType getRenderType() {
		// Use translucent sheet so we can keep a soft edge/alpha
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	// Factory
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;
		public Provider(SpriteSet sprites) { this.sprites = sprites; }

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level,
									   double x, double y, double z,
									   double vx, double vy, double vz) {
			return new BloodDropParticle(level, x, y, z, vx, vy, vz, sprites);
		}
	}
}
