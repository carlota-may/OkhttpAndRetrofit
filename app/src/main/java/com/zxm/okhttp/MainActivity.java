package com.zxm.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText mail = (EditText)findViewById(R.id.mail);
        final EditText password = (EditText)findViewById(R.id.password);
        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClient.getInstance().getOkhttpService().signUp(mail.getText().toString(),password.getText().toString()).enqueue(new RequestCallback<BaseUserInfo>() {
                    @Override
                    public void success(BaseUserInfo baseUserInfo) {
//                        do something
                    }

                    @Override
                    public void failure(int statusCode, ApiErrorModel apiErrorModel) {
//                        no-op
                    }
                });
            }
        });
    }
}
