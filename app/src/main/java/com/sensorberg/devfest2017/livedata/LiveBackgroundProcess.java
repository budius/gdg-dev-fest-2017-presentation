package com.sensorberg.devfest2017.livedata;

import android.arch.lifecycle.LiveData;

public class LiveBackgroundProcess extends LiveData<LiveBackgroundProcess.State> implements Runnable {

	@Override public void run() {

	}

	public enum State {

	}
}
