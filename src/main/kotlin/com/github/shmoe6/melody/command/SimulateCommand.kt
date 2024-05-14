package com.github.shmoe6.melody.command

import com.github.shmoe6.melody.Melody
import com.github.shmoe6.melody.core.MelodyConfig
import net.minecraft.client.Minecraft
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText

object SimulateCommand : CommandBase() {
    override fun getCommandName(): String {
        return "simulate"
    }

    override fun getCommandUsage(sender: ICommandSender?): String {
        return ""
    }

    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {
        var msg = ""
        args?.forEach { msg += "$it "}
        msg = msg.substring(0..<msg.length - 1)
        Minecraft.getMinecraft().thePlayer.sendChatMessage(msg)
    }

    override fun canCommandSenderUseCommand(sender: ICommandSender?): Boolean {
        return true
    }

//    override fun getCommandAliases(): MutableList<String> {
//        return Arrays.asList("")
//    }
}