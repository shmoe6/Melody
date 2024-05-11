package com.github.shmoe6.melody.command

import net.minecraft.client.Minecraft
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText
import java.util.*

object TestCommand : CommandBase() {
    override fun getCommandName(): String {
        return "test"
    }

    override fun getCommandUsage(sender: ICommandSender?): String {
        return ""
    }

    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {
        sender?.addChatMessage(ChatComponentText("§5[Melody] §fTest command working!"))
    }

    override fun canCommandSenderUseCommand(sender: ICommandSender?): Boolean {
        return true
    }

//    override fun getCommandAliases(): MutableList<String> {
//        return Arrays.asList("")
//    }

}