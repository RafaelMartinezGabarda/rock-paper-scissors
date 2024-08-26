package com.demo.rockpaperscissors.business.model.enums;

public enum Option {
    ROCK {
        @Override
        public boolean beats(Option option) {
            return option.equals(SCISSORS);
        }
    },
    PAPER {
        @Override
        public boolean beats(Option option) {
            return option.equals(ROCK);
        }
    },
    SCISSORS {
        @Override
        public boolean beats(Option option) {
            return option.equals(PAPER);
        }
    };

    public abstract boolean beats(Option option);
}
