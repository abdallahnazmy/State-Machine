package com.states.EmployeeStatesMS.enums;

public enum ContractType {

    FULL_TIME("FULL_TIME", 1),
    PART_TIME("PART_TIME", 2),
    FREELANCE("FREELANCE", 3);
    private final String key;
    private final Integer value;

    ContractType(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
