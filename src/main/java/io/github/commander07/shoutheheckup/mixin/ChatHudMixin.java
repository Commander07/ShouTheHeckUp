package io.github.commander07.shoutheheckup.mixin;

import io.github.commander07.shoutheheckup.ShouTheHeckUp;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.github.commander07.shoutheheckup.Config;

import java.util.Arrays;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {

    @Shadow @Final private MinecraftClient client;

    @Shadow protected abstract void addMessage(Text message, @Nullable MessageSignatureData signature, int ticks, @Nullable MessageIndicator indicator, boolean refresh);

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"), cancellable = true)
    private void addMessageMixin(@NotNull Text message, MessageSignatureData signature, int ticks, MessageIndicator indicator, boolean refresh, CallbackInfo ci) {
        String[] message_arr = message.getString().split(" ");
        ShouTheHeckUp.LOGGER.info(Arrays.toString(message_arr));
        if (Config.enabled && message_arr.length >= 3) {
            if (message_arr[2].contains("shouts:")) {
                if (Config.ignoredPlayers.contains(message_arr[0]) || Config.hideLevel == 3 || Config.bannedWords.stream().anyMatch(message.getString()::contains)) {
                    switch (Config.hideLevel) {
                        case 3, 2 -> ci.cancel();
                        case 1 -> {
                            addMessage(Text.of("§7§o[Blocked shout message]").getWithStyle(Text.empty().getStyle().withHoverEvent(HoverEvent.Action.SHOW_TEXT.buildHoverEvent(message))).get(0), signature, this.client.inGameHud.getTicks(), indicator, false);
                            ci.cancel();
                        }
                        case 0 -> {
                            addMessage(Text.of("§7§o[Blocked shout message by " + message_arr[0] + "]").getWithStyle(Text.empty().getStyle().withHoverEvent(HoverEvent.Action.SHOW_TEXT.buildHoverEvent(message))).get(0), signature, this.client.inGameHud.getTicks(), indicator, false);
                            ci.cancel();
                        }
                    }
                }
            }
        } else {
            if (Config.enabled && Config.filterChat && Config.bannedWords.stream().anyMatch(message.getString()::contains)) {
                switch (Config.hideLevel) {
                    case 3, 2 -> {
                        ci.cancel();
                    }
                    case 1 -> {
                        addMessage(Text.of("§7§o[Blocked message by " + message_arr[0] + "]").getWithStyle(Text.empty().getStyle().withHoverEvent(HoverEvent.Action.SHOW_TEXT.buildHoverEvent(message))).get(0), signature, this.client.inGameHud.getTicks(), indicator, false);
                        ci.cancel();
                    }
                    case 0 -> {
                        addMessage(Text.of("§7§o[Blocked message]").getWithStyle(Text.empty().getStyle().withHoverEvent(HoverEvent.Action.SHOW_TEXT.buildHoverEvent(message))).get(0), signature, this.client.inGameHud.getTicks(), indicator, false);
                        ci.cancel();
                    }
                }
            }
        }
    }
}
