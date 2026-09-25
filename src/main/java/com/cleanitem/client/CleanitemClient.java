package com.cleanitem.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CleanitemClient implements ClientModInitializer {

    // 声明三个按键绑定变量
    public static KeyBinding clearItemKey;
    public static KeyBinding timeDayKey;
    public static KeyBinding weatherClearKey;

    @Override
    public void onInitializeClient() {
        // 1. 注册 Delete 键 (清除掉落物)
        clearItemKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cleanitem.clear_items", 
                InputUtil.Type.KEYSYM,       
                GLFW.GLFW_KEY_DELETE,        
                "category.cleanitem.keys"    
        ));

        // 2. 注册 \ 键 (设置白天)
        timeDayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cleanitem.time_day",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_BACKSLASH, // \ 键的 GLFW 代码
                "category.cleanitem.keys"
        ));

        // 3. 注册 - 键 (清除天气)
        weatherClearKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cleanitem.weather_clear",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_MINUS, // - 键的 GLFW 代码
                "category.cleanitem.keys"
        ));

        // 注册客户端 Tick 事件，统一处理按键逻辑
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // 确保玩家和世界已经加载
            if (client.player != null && client.world != null) {
                
                // 处理 Delete 键
                while (clearItemKey.wasPressed()) {
                    client.player.networkHandler.sendChatCommand("kill @e[type=item]");
                }

                // 处理 \ 键
                while (timeDayKey.wasPressed()) {
                    client.player.networkHandler.sendChatCommand("time set day");
                }

                // 处理 - 键
                while (weatherClearKey.wasPressed()) {
                    client.player.networkHandler.sendChatCommand("weather clear");
                }
            }
        });
    }
}