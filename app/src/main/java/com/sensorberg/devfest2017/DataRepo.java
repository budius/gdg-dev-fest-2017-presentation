package com.sensorberg.devfest2017;

import android.arch.lifecycle.LiveData;

import com.sensorberg.devfest2017.data.Account;

import retrofit2.Call;
import retrofit2.http.Header;

public interface DataRepo {

	LiveData<Account> getAccount();

	Call<Account> getAccount(@Header("Cache-Control") String cache);

}
