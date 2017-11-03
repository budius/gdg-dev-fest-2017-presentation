package com.sensorberg.devfest2017;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sensorberg.devfest2017.data.Account;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.text) TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		MainViewModel vm = ViewModelProviders.of(this).get(MainViewModel.class);
		vm.getAccount().observe(this, onAccountChanged);
	}
	private final Observer<Account> onAccountChanged = new Observer<Account>() {
		@Override public void onChanged(@Nullable Account account) {
			if (account != null) {
				setTitle(account.displayName);
			}
		}
	};

	private final Observer<String> onTextChanged = new Observer<String>() {
		@Override public void onChanged(@Nullable String s) {
			textView.setText(s);
		}
	};
}
