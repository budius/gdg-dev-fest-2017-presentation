package com.sensorberg.devfest2017;

import com.sensorberg.devfest2017.data.Account;
import com.sensorberg.devfest2017.livedata.LiveRefreshableRetrofit;

/**
 * That's a pseudo dagger2 implementation.
 */
public class Dependencies {

	public static Provider get() {
		return null;
	}

	public interface Provider {
		void inject(MainViewModel viewModel);

		void inject(LiveRefreshableRetrofit<Account> liveRefreshableRetrofit);
	}
}
