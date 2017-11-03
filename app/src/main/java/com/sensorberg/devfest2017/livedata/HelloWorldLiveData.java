package com.sensorberg.devfest2017.livedata;

import android.arch.lifecycle.LiveData;
import android.os.Handler;
import android.os.Looper;

import java.util.Arrays;
import java.util.List;

public class HelloWorldLiveData extends LiveData<String> {

  private static final Handler UI_HANDLER = new Handler(Looper.getMainLooper());

  private final List<String> data = Arrays.asList("Hello", "World", "<DevFest", "-", "2017>");

  @Override protected void onActive() {
    super.onActive();
    UI_HANDLER.postDelayed(update, 2000);
  }

  @Override protected void onInactive() {
    UI_HANDLER.removeCallbacks(update);
  }

  private final Runnable update = new Runnable() {
    @Override public void run() {
      if (!hasActiveObservers()) {
        return;
      }
      setValue(getValue() + data.remove(0));
      if (data.size() > 0) {
        UI_HANDLER.postDelayed(update, 2000);
      }
    }
  };
}
