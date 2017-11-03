package com.sensorberg.devfest2017;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.sensorberg.devfest2017.data.Account;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

	@Inject DataRepo repo;

	private final LiveData<Account> account;

	public MainViewModel() {
		Dependencies.get().inject(this);
		account = repo.getAccount();
	}

	public LiveData<Account> getAccount() {
		return account;
	}
}
