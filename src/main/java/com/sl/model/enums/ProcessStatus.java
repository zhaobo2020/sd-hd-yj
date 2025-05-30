package com.sl.model.enums;

import lombok.Getter;
import java.util.Arrays;
import java.util.Optional;

@Getter
public enum ProcessStatus {
    WAITING(0, "等待"),
    CALCULATING(1, "计算中"),
    SUCCESS(2, "成功"),
    FAILED(3, "失败"),

//    超时
    TIMEOUT(4, "超时");

    private final int code;
    private final String description;

    ProcessStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Optional<ProcessStatus> fromCode(int code) {
        return Arrays.stream(values())
                .filter(status -> status.code == code)
                .findFirst();
    }

    @Override
    public String toString() {
        return this.description;
    }
}