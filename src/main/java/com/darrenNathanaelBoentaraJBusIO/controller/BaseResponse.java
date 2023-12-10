package com.darrenNathanaelBoentaraJBusIO.controller;

/**
 * This class represents a generic response structure
 * that includes a success flag, a message, and a payload.
 *
 * @author Darren Nathanael
 * @param <T> The type of the payload.
 */
public class BaseResponse<T> {
    public boolean success;
    public String message;
    public T payload;

    /**
     * The Constructor with the specified success flag,
     * message, and payload.
     *
     * @param success Indicates whether the operation was successful.
     * @param message A message providing additional information about the response.
     * @param payload The payload containing the actual data of the response.
     */
    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
}
