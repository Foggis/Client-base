package client;

import client.utils.input.KeybindListener;
import client.eventbus.EventBus;
import client.features.ModuleManager;
import client.features.impl.module.combat.TriggerBotModule;
import client.features.impl.module.client.ClickGuiModule;
import client.utils.other.Hello;
import net.fabricmc.api.ModInitializer;

public class Client implements ModInitializer {

	public static final String MOD_ID = "abc123";
	public static final String NAME = "Placeholder";
	public static final String BY = "Fogma";
	public static final String VERSION = "0.1";
	public static final boolean DEV_MODE = true; 

	public static final EventBus EVENT_BUS = new EventBus();

	@Override
	public void onInitialize() {
		if (DEV_MODE) {
			Hello.Print();
		}





		ModuleManager.start(
				/*
				To add a module do: new ModuleNameModule()
				*/
				new ClickGuiModule(),
				new TriggerBotModule()
		);

		KeybindListener.start();
	}
}