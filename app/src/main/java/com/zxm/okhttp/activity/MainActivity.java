package com.zxm.okhttp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zxm.okhttp.R;
import com.zxm.okhttp.entiy.BaseUserInfo;
import com.zxm.okhttp.network.ApiClient;
import com.zxm.okhttp.network.ApiErrorModel;
import com.zxm.okhttp.network.RequestCallback;

import butterknife.BindView;

public class MainActivity extends Activity {
    @BindView(R.id.mail)
    EditText mail;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit.setOnClickListener(v -> ApiClient.getInstance().getOkhttpService().signUp(mail.getText().toString(), password.getText().toString()).enqueue(new RequestCallback<BaseUserInfo>() {
            @Override
            public void success(BaseUserInfo baseUserInfo) {
//                        do something
            }

            @Override
            public void failure(int statusCode, ApiErrorModel apiErrorModel) {
//                        no-op
            }
        }));
    }
}
