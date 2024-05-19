package com.github.shmoe6.melody.command

import com.github.shmoe6.melody.handlers.math.ArithmeticExpressionHandler
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText
import java.util.*

object EvaluateCommand : CommandBase() {
    override fun getCommandName(): String {
        return "evaluate"
    }

    override fun getCommandUsage(sender: ICommandSender?): String {
        return ""
    }

    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {

        var expr = ""
        args?.forEach { expr += it }

        sender?.addChatMessage(ChatComponentText("§5[Melody] §f$expr evaluates to: ${ArithmeticExpressionHandler.handle(expr)}"))
    }

    override fun canCommandSenderUseCommand(sender: ICommandSender?): Boolean {
        return true
    }

    override fun getCommandAliases(): MutableList<String> {
        return Arrays.asList("eval", "expr", "calcexpr")
    }
}