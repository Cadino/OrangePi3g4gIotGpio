package com.cyberz.dht11;

public interface OnValueGetListener {
    void Onsucess(String temp, String hum);

    void onError(String Error);
}
