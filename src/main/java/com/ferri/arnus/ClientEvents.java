package com.ferri.arnus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
	
	public static float prevMasterVolume = 0;
	
	 
	
	@SubscribeEvent
    static void keyPressed(KeyInputEvent event) {
		Options options = Minecraft.getInstance().options;
		float soundSourceVolume = options.getSoundSourceVolume(SoundSource.MASTER);
		if (RegisterKeyEvent.muteMasterKey.consumeClick() && soundSourceVolume != 0) {
//			ToastComponent toastcomponent = Minecraft.getInstance().getToasts();
//			SystemToast.addOrUpdate(toastcomponent, SystemToastIds.NARRATOR_TOGGLE, Component.translatable("soundcontroll.mutemaster"), Component.translatable("soundcontroll.mutemaster.desc"));
			prevMasterVolume = soundSourceVolume;
			options.setSoundCategoryVolume(SoundSource.MASTER, 0);
			options.save();
		}
		
		if (RegisterKeyEvent.unmuteMasterKey.consumeClick() && soundSourceVolume == 0) {
			options.setSoundCategoryVolume(SoundSource.MASTER, prevMasterVolume);
			options.save();
		}
		
		if (RegisterKeyEvent.upMasterKey.consumeClick() && soundSourceVolume < 1F) {
			options.setSoundCategoryVolume(SoundSource.MASTER, soundSourceVolume + 0.01F);
			options.save();
		}
		
		if (RegisterKeyEvent.downMasterKey.consumeClick() && soundSourceVolume > 0F) {
			options.setSoundCategoryVolume(SoundSource.MASTER, soundSourceVolume - 0.01F);
			options.save();
		}
	}
	
}
