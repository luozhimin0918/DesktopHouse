package com.example.yinlian.desktophousekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textRespose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textRespose=findViewById(R.id.textRespose);
        //创建一个请求队列
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        post();
    }
    RequestQueue requestQueue;
    private void post(){

        //创建一个请求
//        String url = "https://ynadi.kuaixun56.com/user/login";
        String url="http://500995bc.nat123.cc:12986/bmp/api/findTariffInfoList";


        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("reqDetail","{\"tariffDescList\":\"默认套餐内容,默认套餐二\"}");
            jsonObj.put("appInfo","{ " +
                    "\"appName\": \"靓丽前台-银商版\", " +
                    "\"appId\": \"afd2baf088034179b4c98826b4d9fcca\", " +
                    "\"appPackName\": \"com.shboka.beautyorderums\", " +
                    "\"appVersionCode\": \"3.0.6.1\" " +
                    "}");
            jsonObj.put("interType","BMP-QUERY");
            jsonObj.put("version","001");
            jsonObj.put("deviceInfo","{ " +
                    " \"prodCode\": \"19\", " +
                    " \"firmCode\": \"109\", " +
                    " \"deviceSn\": \"0820043480\" " +
                    " }");
            jsonObj.put("mac","ums2018");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject s) {
                KLog.json("Main",s.toString());
                textRespose.setText("我的返回："+s.toString());
            }

        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textRespose.setText("加载错误"+volleyError);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

               Map<String,String> map = new HashMap<>();
              /*  map.put("reqDetail","{\"tariffDescList\":\"默认套餐内容,默认套餐二\"}");//传入参数
               map.put("appInfo","{ " +
                        " \"appName\": \"靓丽前台-银商版\", " +
                           " \"appId\": \"afd2baf088034179b4c98826b4d9fcca\", " +
                           " \"appPackName\": \"com.shboka.beautyorderums\", " +
                           " \"appVersionCode\": \"3.0.6.1\" " +
                           "}");
                   map.put("interType","BMP-QUERY");
                   map.put("version","001");
                   map.put("deviceInfo","{ " +
                           " \"prodCode\": \"19\", " +
                           " \"firmCode\": \"109\", " +
                           " \"deviceSn\": \"0820043480\" " +
                           " }");
                   map.put("mac","ums2018");
*/
                return map;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };

        //将post请求添加到队列中
        requestQueue.add(stringRequest);

    }

}
