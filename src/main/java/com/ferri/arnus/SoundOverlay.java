package com.ferri.arnus;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;

public class SoundOverlay extends GuiComponent implements IIngameOverlay{
	
	private long time = 0;
    private final ResourceLocation SOUNDPLUS = new ResourceLocation(SoundControl.MODID, "textures/overlays/soundplus.png");

	
	public static void init() {
		OverlayRegistry.registerOverlayTop("soundcontrol_overlay", new SoundOverlay());
	}

	@Override
	public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
		long gameTime = Minecraft.getInstance().level.getGameTime();
		if(RegisterKeyEvent.downMasterKey.isDown() || RegisterKeyEvent.upMasterKey.isDown() || RegisterKeyEvent.unmuteMasterKey.isDown() ) {
			time = gameTime + 20;
		}
		if (time < gameTime) {
			return;
		}
		Font font = Minecraft.getInstance().font;
		float soundSourceVolume = Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.MASTER);
		int scaledw = Minecraft.getInstance().getWindow().getGuiScaledWidth();
		poseStack.pushPose();
		
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, SOUNDPLUS);
		RenderSystem.setShaderColor(0,0,0,0);
		
		MutableComponent volumeString = Component.literal((int)(soundSourceVolume*100) + "");
		GuiComponent.blit(poseStack,scaledw - font.width(volumeString.getString()) -32, 32, 0, 0, 16, 16, 16, 16);
		font.draw(poseStack, volumeString, scaledw - font.width(volumeString.getString()) -10 , 10, 0);
		poseStack.popPose();
		
	}

}
