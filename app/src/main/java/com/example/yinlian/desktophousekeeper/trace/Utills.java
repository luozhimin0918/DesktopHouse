package com.example.yinlian.desktophousekeeper.trace;

import android.app.ActivityManager;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.yinlian.desktophousekeeper.model.AppInfoJSon;
import com.example.yinlian.desktophousekeeper.model.DeviceInfoJSon;
import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Luozhimin on 2018-06-21.9:42
 */
public class Utills {
    public static String getTariffInfo(  RequestQueue requestQueue){
        final String[] reqeutString = {null};
//创建一个请求
//        String url = "https://ynadi.kuaixun56.com/user/login";
        String url="http://500995bc.nat123.cc:12986/bmp/api/findTariffInfoList";


        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("reqDetail","{\"tariffDescList\":\"默认套餐内容,默认套餐二\"}");
            AppInfoJSon appInfoJSon=new AppInfoJSon();
            appInfoJSon.setAppName("靓丽前台-银商版");
            appInfoJSon.setAppId("afd2baf088034179b4c98826b4d9fcca");
            appInfoJSon.setAppPackName("com.shboka.beautyorderums");
            appInfoJSon.setAppVersionCode("3.0.6.1");
            KLog.json("appInfoJson", JSON.toJSONString(appInfoJSon));
            jsonObj.put("appInfo",JSON.toJSONString(appInfoJSon));
            jsonObj.put("interType","BMP-QUERY");
            jsonObj.put("version","001");
            DeviceInfoJSon deviceInfoJSon=new DeviceInfoJSon();
            deviceInfoJSon.setProdCode("19");
            deviceInfoJSon.setFirmCode("109");
            deviceInfoJSon.setDeviceSn("0820043480");
            KLog.json("deviceInfo",JSON.toJSONString(deviceInfoJSon));
            jsonObj.put("deviceInfo",JSON.toJSONString(deviceInfoJSon));
            jsonObj.put("mac","ums2018");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject s) {
                KLog.json("Main",s.toString());
             reqeutString[0] =s.toString();
            }

        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
                reqeutString[0]="加载错误"+volleyError;
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
        return reqeutString[0];
    }


    /**
     * 获取当前应用程序的包名
     * @param context 上下文对象
     * @return 返回包名
     */
    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }
}
