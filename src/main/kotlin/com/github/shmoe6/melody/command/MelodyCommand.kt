package com.github.shmoe6.melody.command

import com.github.shmoe6.melody.Melody
import com.github.shmoe6.melody.core.MelodyConfig
import com.github.shmoe6.melody.handlers.OverlayHandler
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

object MelodyCommand : CommandBase() {
    override fun getCommandName(): String {
        return "melody"
    }

    override fun getCommandUsage(sender: ICommandSender?): String {
        return ""
    }

    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {
        Melody.currentGui = MelodyConfig.gui()
    }

    override fun canCommandSenderUseCommand(sender: ICommandSender?): Boolean {
        return true
    }

//    override fun getCommandAliases(): MutableList<String> {
//        return Arrays.asList("")
//    }
}