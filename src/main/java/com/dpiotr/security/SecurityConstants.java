package com.dpiotr.security;

/**
 * Created by dpiotr on 29.12.17.
 */
public class SecurityConstants {
    public static final String SECRET = "MIIBOAIBAAJASSzqpcMbDP5RF77cC1NFA5Zemq2V7sNaFlKhwkXAePZbNaH4tNEf\n" +
            "PgyPO9BPrnccf1PIyx+AGghO0OnYQe8v6wIDAQABAkA41R3ZaFMWIF8zJhUY5q9v\n" +
            "PdZ0I+WS7z6En+DqVepLB5bs00ROw4pxhph0tZgi63cdwd3b1auWeAYB7PiQmIQh\n" +
            "AiEAiRzCPTEwYQAhLUtNGAPnNPStTirmUUDm66OpgzVoCP0CIQCIn+TlpqI+82Cj\n" +
            "xMk+V8JKHPFMoLqLOpAxvMHb1LIFBwIgXnZIv93Gpd1v/gOV7oip3or/Zw4ZNTuQ\n" +
            "nCFaJMJDlYUCIHrS6+98910KZPW1gomWUadZD6Co2isykdD09X6QXx3fAiA/5HzC\n" +
            "AERTAKc68/kxQE98Cr29h7tt0XlmSiqjj90Lrw==";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}