package com.example.android_demo.community;

import static com.example.android_demo.Constants.constant.IP_ADDRESS;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_demo.MainViewModel;
import com.example.android_demo.MyApplication;
import com.example.android_demo.R;
import com.example.android_demo.bean.CommunityBean;
import com.example.android_demo.bean.PostBean;
import com.example.android_demo.utils.UserUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddPostActivity extends AppCompatActivity {
    EditText titleET,contentET;
    String title,content,userId,communityId;
    Button cancel,commit;
    public static MyApplication application;
    String IP="192.168.241.1:8081";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Message");
        assert bundle != null;
        communityId=bundle.getString("community_id");

        setContentView(R.layout.add_post_layout);
        titleET=findViewById(R.id.titleEditText);
        contentET=findViewById(R.id.contentEditText);
        cancel=findViewById(R.id.post_cancel_Button);
        commit=findViewById(R.id.post_commit_Button);

        application = MyApplication.getInstance();

        cancel.setOnClickListener(v->{
            finish();
        });

        commit.setOnClickListener(v->{
            title = titleET.getText().toString();
            content = contentET.getText().toString();
            sendPost(title,content);
        });
    }
    private void sendPost(String title, String content) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", 1);
            jsonObject.put("communityId", communityId);
            jsonObject.put("title", title);
            jsonObject.put("content", content);
            jsonObject.put("isPublic", "true");
            //todo 还差点信息没加

        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());
        Log.i("Addpost", jsonObject.toString());

        String url = "http://" + IP + "/post/addPost";
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        // 异步发送请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                // failed
                Log.e("Addpost", "failed");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // success
                final String responseData = response.body().string();
                Log.i("Addpost", responseData);
                try {
                    JSONObject json = new JSONObject(responseData);
                    String data = json.getString("data");
                    JSONObject dataJson = new JSONObject(data);
                    Log.e("Addpost", "failed");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}