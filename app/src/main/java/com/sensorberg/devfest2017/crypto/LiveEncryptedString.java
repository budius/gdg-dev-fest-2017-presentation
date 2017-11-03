package com.sensorberg.devfest2017.crypto;

import android.arch.lifecycle.MutableLiveData;

import javax.crypto.SecretKey;

class LiveEncryptedString extends MutableLiveData<String> {
	public LiveEncryptedString(String alias, MutableLiveData<SecretKey> key) {
	}
}
