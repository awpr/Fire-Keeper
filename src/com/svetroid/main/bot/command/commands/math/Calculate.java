package com.svetroid.main.bot.command.commands.math;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Calculate extends Command {

  public Calculate() {
    settings.setNameAndAliases("calc", "calculate", "evaluate", "math", "maths");
    settings.setHelp(Main.bot.getPrefix() + "calc [expression|logic]");
    settings.setDescription(
        "Evaluates an expression, either mathematical or logical.\nFor more information, either go to the support server with the `information` command or search for the EvalEx Java library.");
    settings.setEmbedColor(255, 147, 57);
  }

  protected void execute(Input input, Output output) {
    StringBuilder sb = new StringBuilder();
    for (String s : input.getArguments()) {
      sb.append(s.replaceAll(",", "").trim());
    }
    Expression expression = new Expression(sb.toString());
    BigDecimal result = expression.eval();
    DecimalFormat df = new DecimalFormat(
        "#,###.##################################################");
    output.sendMessage(createEmbed(this.settings.getEmbedColor(), "Result",
        "**```\n" + df.format(result).toString() + "\n```**"));
  }

}
