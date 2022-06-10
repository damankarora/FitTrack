package com.activitytracker.foodlogger.Commons;

public class ExceptionContainer {
    public static class InvalidPayloadException extends Exception{};
    public static class UserAlreadyExistsException extends Exception{};
    public static class UserNotFound extends Exception{};
}
