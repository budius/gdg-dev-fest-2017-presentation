package com.sensorberg.devfest2017.crypto;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.crypto.SecretKey;

public abstract class CryptoManager {

	private LivePin pin; // this is encrypted using AndroidKeyStore and saved to DB
	private LiveData<byte[]> salt; // this is auto-generated on 1st run and saved to DB
	private MutableLiveData<SecretKey> key; // this is generated based on pin + salt

	public void createPin(String pin) {
		this.pin.setValue(pin);
	}

	public LiveData<Integer> comparePin(String pin) {
		return new LivePinComparator(pin, key, salt);
	}

	public MutableLiveData<String> getData(String alias) {
		return new LiveEncryptedString(alias, key);
	}
}
