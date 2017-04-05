package com.svetroid.main.bot.command.type;

public enum RPSType {

  ROCK {
    @Override
    public boolean beats(RPSType t) {
      if (t == SCISSORS || t == LIZARD || t == BULL) {
        return true;
      }
      return false;
    }
  }, PAPER {
    @Override
    public boolean beats(RPSType t) {
      if (t == ROCK || t == SPOCK || t == WELL) {
        return true;
      }
      return false;
    }
  }, SCISSORS {
    @Override
    public boolean beats(RPSType t) {
      if (t == PAPER || t == LIZARD || t == BULL) {
        return true;
      }
      return false;
    }
  }, LIZARD {
    @Override
    public boolean beats(RPSType t) {
      if (t == PAPER || t == SPOCK || t == BULL) {
        return true;
      }
      return false;
    }
  }, SPOCK {
    @Override
    public boolean beats(RPSType t) {
      if (t == ROCK || t == SCISSORS || t == WELL) {
        return true;
      }
      return false;
    }
  }, WELL {
    @Override
    public boolean beats(RPSType t) {
      if (t == ROCK || t == SCISSORS || t == LIZARD) {
        return true;
      }
      return false;
    }
  }, BULL {
    @Override
    public boolean beats(RPSType t) {
      if (t == PAPER || t == WELL || t == SPOCK) {
        return true;
      }
      return false;
    }
  };

  public static RPSType parseType(String value) {
    if (value.equalsIgnoreCase("rock") || value.equalsIgnoreCase("r") || value
        .equalsIgnoreCase("stone") || value.equalsIgnoreCase(":fist:")) {
      return ROCK;
    } else if (value.equalsIgnoreCase("paper") || value.equalsIgnoreCase("p") || value
        .equalsIgnoreCase(":raised_hand:")) {
      return PAPER;
    } else if (value.equalsIgnoreCase("scissors") || value.equalsIgnoreCase("s") || value
        .equalsIgnoreCase("scissor") || value.equalsIgnoreCase(":v:")) {
      return SCISSORS;
    } else if (value.equalsIgnoreCase("lizard") || value.equalsIgnoreCase("l") || value
        .equalsIgnoreCase(":dragon:")) {
      return LIZARD;
    } else if (value.equalsIgnoreCase("spock") || value.equalsIgnoreCase("sp") || value
        .equalsIgnoreCase(":vulcan_salute:")) {
      return SPOCK;
    } else if (value.equalsIgnoreCase("well") || value.equalsIgnoreCase("w") || value
        .equalsIgnoreCase(":ok_hand:")) {
      return WELL;
    } else if (value.equalsIgnoreCase("bull") || value.equalsIgnoreCase("b") || value
        .equalsIgnoreCase(":horns_sign:")) {
      return BULL;
    } else {
      return null;
    }
  }

  public abstract boolean beats(RPSType t);

}
