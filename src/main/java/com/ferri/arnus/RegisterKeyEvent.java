package com.ferri.arnus;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = SoundControl.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class RegisterKeyEvent {
	
	public static KeyMapping muteMasterKey = new KeyMapping("key.soundcontrol.mutemaster", GLFW.GLFW_KEY_M, "key.soundcontrol.categories.soundcontrol");
	public static KeyMapping unmuteMasterKey = new KeyMapping("key.soundcontrol.unmutemaster", GLFW.GLFW_KEY_N, "key.soundcontrol.categories.soundcontrol");
	public static KeyMapping upMasterKey = new KeyMapping("key.soundcontrol.upmaster", GLFW.GLFW_KEY_PERIOD, "key.soundcontrol.categories.soundcontrol");
	public static KeyMapping downMasterKey = new KeyMapping("key.soundcontrol.downmaster", GLFW.GLFW_KEY_COMMA, "key.soundcontrol.categories.soundcontrol");
	
	
	@SubscribeEvent
	static void onClientSetup(FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(muteMasterKey);
		ClientRegistry.registerKeyBinding(unmuteMasterKey);
		ClientRegistry.registerKeyBinding(upMasterKey);
		ClientRegistry.registerKeyBinding(downMasterKey);
		
		SoundOverlay.init();
	}
	
}
