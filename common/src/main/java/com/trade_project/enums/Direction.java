package com.trade_project.enums;

public enum Direction {

    Buy, Sell;


    public static Direction parse(Enum<?> value) throws IllegalArgumentException {
        for (Direction d : Direction.values()) {
            if (d.toString().equalsIgnoreCase(value.toString())) {
                return d;
            }
        }
        throw new IllegalArgumentException(new EnumConstantNotPresentException(Direction.class, value.toString()));
    }
}