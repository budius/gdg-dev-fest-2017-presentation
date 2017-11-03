package com.sensorberg.devfest2017.crypto;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.crypto.SecretKey;

class LivePinComparator extends LiveData<Integer> {
	public LivePinComparator(String pin, MutableLiveData<SecretKey> key, LiveData<byte[]> salt) {
	}
}
