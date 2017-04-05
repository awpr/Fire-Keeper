package com.svetroid.main.bot.feature;

import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;

public abstract class Feature {

  protected abstract void run(Input input, Output output);

}