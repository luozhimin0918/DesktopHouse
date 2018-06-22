package com.example.yinlian.desktophousekeeper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import org.json.JSONArray;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yinlian.tariff.index.ApiManager;
import com.example.yinlian.desktophousekeeper.model.AppInfoJSon;
import com.example.yinlian.desktophousekeeper.model.DataRespRecordPay;
import com.example.yinlian.desktophousekeeper.model.DeviceInfoJSon;
import com.example.yinlian.desktophousekeeper.model.OrderInfoRespJson;
import com.example.yinlian.desktophousekeeper.model.OrderInfoRespJsonDatail;
import com.example.yinlian.desktophousekeeper.model.ReqDetailJson;
import com.example.yinlian.desktophousekeeper.model.TariffInfoListJson;
import com.example.yinlian.desktophousekeeper.trace.Utills;
import com.socks.library.KLog;
import com.ums.upos.sdk.exception.CallServiceException;
import com.ums.upos.sdk.exception.SdkException;
import com.ums.upos.sdk.system.BaseSystemManager;
import com.ums.upos.sdk.system.OnServiceStatusListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textRespose;
    Button  getTariffInfo;
    Button forTrial;
    Button recordPaymentInfo;
    Button getOrderInfo;
    BaseSystemManager baseSystemManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textRespose = findViewById(R.id.textRespose);
        getTariffInfo=findViewById(R.id.getTariffInfo);
        forTrial=findViewById(R.id.forTrial);
        recordPaymentInfo=findViewById(R.id.recordPaymentInfo);
        getOrderInfo=findViewById(R.id.getOrderInfo);
        //创建一个请求队列
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        getTariffInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTariffInfo();
            }
        });
        forTrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getforTrial();
            }
        });
        recordPaymentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecordPaymentInfo();
            }
        });
        getOrderInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOrderInfo();
            }
        });
       baseSystemManager = BaseSystemManager.getInstance();

        try {
            baseSystemManager.deviceServiceLogin(getApplicationContext(), null, "99999999", new OnServiceStatusListener() {
                @Override
                public void onStatus(int i) {


                    KLog.d("onStatus",""+i);
                }
            });

        } catch (SdkException e) {
            e.printStackTrace();
        }
        ApiManager apiManager=ApiManager.getInstance(this);
        apiManager.getTariffInfo(new ApiManager.RespCallBack() {
            @Override
            public void onResponse(String jsonRespString) {
                      KLog.json("ApiMa",jsonRespString);
            }
        }, new ApiManager.RespErrorCallBack() {
            @Override
            public void onError(String errorStr) {
                KLog.json("ApiMa",errorStr);
            }
        });
       }

    RequestQueue requestQueue;
    public void getOrderInfo() {
        //创建一个请求
        String url = "http://500995bc.nat123.cc:12986/bmp/api/getOrderInfo";
        JSONObject jsonObj = new JSONObject();
        try {
            ReqDetailJson reqDetailJson=new ReqDetailJson();
            //"{\"tariffDescList\":\"默认套餐内容,默认套餐二\",\"tariffDesc\":\"默认套餐内容\"}"
            reqDetailJson.setTariffDescList("默认套餐内容,默认套餐二");
            jsonObj.put("reqDetail",JSON.toJSONString(reqDetailJson));
            AppInfoJSon appInfoJSon = new AppInfoJSon();
            appInfoJSon.setAppName("靓丽前台-银商版");
            appInfoJSon.setAppId("afd2baf088034179b4c98826b4d9fcca");
            String appPackname= Utills.getAppProcessName(getApplicationContext());//获取包名
            appInfoJSon.setAppPackName("com.shboka.beautyorderums");
            appInfoJSon.setAppVersionCode("3.0.6.1");
            KLog.json("appInfoJson", JSON.toJSONString(appInfoJSon));
            jsonObj.put("appInfo", JSON.toJSONString(appInfoJSon));
            jsonObj.put("interType", "BMP-QUERY");
            jsonObj.put("version", "001");
            DeviceInfoJSon deviceInfoJSon = new DeviceInfoJSon();
            deviceInfoJSon.setProdCode("19");//产品型号
            deviceInfoJSon.setFirmCode("109");//厂商代码

            //获取sn
            String deviceInfoMap  = null;
            try {
                deviceInfoMap = baseSystemManager.readSN();
                deviceInfoMap=baseSystemManager.getDeviceInfo().toString();
            } catch (SdkException e) {
                e.printStackTrace();
            } catch (CallServiceException e) {
                e.printStackTrace();
            }
            KLog.d("deviceInfo",deviceInfoMap);
            deviceInfoJSon.setDeviceSn("0820043480");//终端硬件序列号

            KLog.json("deviceInfo",JSON.toJSONString(deviceInfoJSon));
            jsonObj.put("deviceInfo",JSON.toJSONString(deviceInfoJSon));
            jsonObj.put("mac","ums2018");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject s) {
                KLog.json("MainFour",s.toString());
                String msgString = null;
                try {
                    JSONObject jsonObject=s.getJSONObject("data");
                    OrderInfoRespJson orderInfoRespJson =JSON.parseObject(jsonObject.toString(),OrderInfoRespJson.class);
                   String ordeRespStr = orderInfoRespJson.getRespDetail();
                   List<OrderInfoRespJsonDatail> respRecordPay=JSON.parseArray(ordeRespStr,OrderInfoRespJsonDatail.class);
                    OrderInfoRespJsonDatail  orderInfoRespJsonDatail=  respRecordPay.get(0);

                  textRespose.setText(
                            "endTime:"+orderInfoRespJsonDatail.getEndTime()+"\n"+
                            "lastPaymentPrice:"+orderInfoRespJsonDatail.getLastPaymentPrice()+"\n"+
                            "lastPaymentTerm:"+orderInfoRespJsonDatail.getLastPaymentTerm()+"\n"+

                            "orderStatus:"+orderInfoRespJsonDatail.getOrderStatus()+"\n"+
                            "remainingDays:"+orderInfoRespJsonDatail.getRemainingDays()+"\n"+
                            "startTime:"+orderInfoRespJsonDatail.getStartTime()+"\n"+
                            "tariffDesc:"+orderInfoRespJsonDatail.getTariffDesc()+"\n"+
                            "totalPrice:"+orderInfoRespJsonDatail.getTotalPrice()

                    );

                    msgString = s.getString("msg");
                    Toast.makeText(getApplicationContext(),msgString,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();

                    try {
                        msgString = s.getString("msg");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    textRespose.setText(msgString);
                    Toast.makeText(getApplicationContext(),msgString,Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textRespose.setText("加载错误"+volleyError);
            }
        }){
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
    public void getRecordPaymentInfo() {
        //创建一个请求
        String url = "http://500995bc.nat123.cc:12986/bmp/api/recordPaymentInfo";
        JSONObject jsonObj = new JSONObject();
        try {
            ReqDetailJson reqDetailJson=new ReqDetailJson();
            //"{\"tariffDescList\":\"默认套餐内容,默认套餐二\",\"tariffDesc\":\"默认套餐内容\"}"
            reqDetailJson.setTariffDesc("默认套餐内容");
            reqDetailJson.setPaymentPrice("100");
            reqDetailJson.setPaymentTerm("45");
            reqDetailJson.setPurchaseQuantity("30");
            jsonObj.put("reqDetail",JSON.toJSONString(reqDetailJson));
            AppInfoJSon appInfoJSon = new AppInfoJSon();
            appInfoJSon.setAppName("靓丽前台-银商版");
            appInfoJSon.setAppId("afd2baf088034179b4c98826b4d9fcca");
            String appPackname= Utills.getAppProcessName(getApplicationContext());//获取包名
            appInfoJSon.setAppPackName("com.shboka.beautyorderums");
            appInfoJSon.setAppVersionCode("3.0.6.1");
            KLog.json("appInfoJson", JSON.toJSONString(appInfoJSon));
            jsonObj.put("appInfo", JSON.toJSONString(appInfoJSon));
            jsonObj.put("interType", "BMP-QUERY");
            jsonObj.put("version", "001");
            DeviceInfoJSon deviceInfoJSon = new DeviceInfoJSon();
            deviceInfoJSon.setProdCode("19");//产品型号
            deviceInfoJSon.setFirmCode("109");//厂商代码

            //获取sn
            String deviceInfoMap  = null;
            try {
                deviceInfoMap = baseSystemManager.readSN();
                deviceInfoMap=baseSystemManager.getDeviceInfo().toString();
            } catch (SdkException e) {
                e.printStackTrace();
            } catch (CallServiceException e) {
                e.printStackTrace();
            }
            KLog.d("deviceInfo",deviceInfoMap);
            deviceInfoJSon.setDeviceSn("0820043480");//终端硬件序列号

            KLog.json("deviceInfo",JSON.toJSONString(deviceInfoJSon));
            jsonObj.put("deviceInfo",JSON.toJSONString(deviceInfoJSon));
            jsonObj.put("mac","ums2018");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject s) {
                KLog.json("MainThree",s.toString());
                String msgString = null;
                try {
                    JSONObject jsonObject=s.getJSONObject("data");

                    DataRespRecordPay respRecordPay=JSON.parseObject(jsonObject.toString(),DataRespRecordPay.class);
                    textRespose.setText("ageId:"+respRecordPay.getAgeId()+"\n"+
                            "appId:"+respRecordPay.getAppId()+"\n"+
                            "apvId:"+respRecordPay.getApvId()+"\n"+
                            "endTime:"+respRecordPay.getEndTime()+"\n"+
                            "lastPaymentPrice:"+respRecordPay.getLastPaymentPrice()+"\n"+
                            "lastPaymentTerm:"+respRecordPay.getLastPaymentTerm()+"\n"+

                            "lastPaymentTime:"+respRecordPay.getLastPaymentTime()+"\n"+
                            "orderStatus:"+respRecordPay.getOrderStatus()+"\n"+
                            "posId:"+respRecordPay.getPosId()+"\n"+
                            "remainingDays:"+respRecordPay.getRemainingDays()+"\n"+
                            "startTime:"+respRecordPay.getStartTime()+"\n"+
                            "tariffDesc:"+respRecordPay.getTariffDesc()+"\n"+
                            "tariffTag:"+respRecordPay.getTariffTag()+"\n"+
                            "totalPrice:"+respRecordPay.getTotalPrice()+"\n"+
                            "totalTerm:"+respRecordPay.getTotalTerm()
                    );

                    msgString = s.getString("msg");
                    Toast.makeText(getApplicationContext(),msgString,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();

                    try {
                        msgString = s.getString("msg");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    textRespose.setText(msgString);
                    Toast.makeText(getApplicationContext(),msgString,Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textRespose.setText("加载错误"+volleyError);
            }
        }){
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
    public void getforTrial() {
        //创建一个请求
        String url = "http://500995bc.nat123.cc:12986/bmp/api/forTrial";
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("reqDetail", "{\"tariffDescList\":\"默认套餐内容,默认套餐二\",\"tariffDesc\":\"默认套餐内容\"}");
            AppInfoJSon appInfoJSon = new AppInfoJSon();
            appInfoJSon.setAppName("靓丽前台-银商版");
            appInfoJSon.setAppId("afd2baf088034179b4c98826b4d9fcca");
            String appPackname= Utills.getAppProcessName(getApplicationContext());//获取包名
            appInfoJSon.setAppPackName("com.shboka.beautyorderums");
            appInfoJSon.setAppVersionCode("3.0.6.1");
            KLog.json("appInfoJson", JSON.toJSONString(appInfoJSon));
            jsonObj.put("appInfo", JSON.toJSONString(appInfoJSon));
            jsonObj.put("interType", "BMP-QUERY");
            jsonObj.put("version", "001");
            DeviceInfoJSon deviceInfoJSon = new DeviceInfoJSon();
            deviceInfoJSon.setProdCode("19");//产品型号
            deviceInfoJSon.setFirmCode("109");//厂商代码

            //获取sn
            String deviceInfoMap  = null;
            try {
                deviceInfoMap = baseSystemManager.readSN();
                deviceInfoMap=baseSystemManager.getDeviceInfo().toString();
            } catch (SdkException e) {
                e.printStackTrace();
            } catch (CallServiceException e) {
                e.printStackTrace();
            }
            KLog.d("deviceInfo",deviceInfoMap);
            deviceInfoJSon.setDeviceSn("0820043480");//终端硬件序列号

            KLog.json("deviceInfo",JSON.toJSONString(deviceInfoJSon));
            jsonObj.put("deviceInfo",JSON.toJSONString(deviceInfoJSon));
            jsonObj.put("mac","ums2018");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject s) {
                KLog.json("MainTwo",s.toString());
                String msgString = null;
                try {
                    JSONObject jsonObject=s.getJSONObject("data");

                        JSONArray array =jsonObject.getJSONArray("tariffInfoList");
                        JSONObject tariArrayOne = array.getJSONObject(0);

                        TariffInfoListJson tariffInfoListJson=JSON.parseObject(tariArrayOne.toString(),TariffInfoListJson.class);
                        textRespose.setText("套餐描述:"+tariffInfoListJson.getTariffDesc()+"\n"+
                                "套餐标签:"+tariffInfoListJson.getTariffTag()+"\n"+
                                "原价:"+tariffInfoListJson.getOriginalPrice()+"\n"+
                                "现价:"+tariffInfoListJson.getPresentPrice()+"\n"+
                                "折扣:"+tariffInfoListJson.getDiscount()+"\n"+
                                "服务期:"+tariffInfoListJson.getServiceTerm()+"\n"+
                                "试用期:"+tariffInfoListJson.getProbation()+"\n"+
                                "是否为默认套餐:"+tariffInfoListJson.getIsDefaulted()
                        );

                    msgString = s.getString("msg");
                    Toast.makeText(getApplicationContext(),msgString,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();

                    try {
                        msgString = s.getString("msg");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    textRespose.setText(msgString);
                    Toast.makeText(getApplicationContext(),msgString,Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textRespose.setText("加载错误"+volleyError);
            }
        }){
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
    public void getTariffInfo() {
        //创建一个请求
//        String url = "https://ynadi.kuaixun56.com/user/login";
        String url = "http://500995bc.nat123.cc:12986/bmp/api/findTariffInfoList";
//        String url="http://500995bc.nat123.cc:12986/bmp/api/getOrderInfo";//  /api/forTrial  api/recordPaymentInfo

        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("reqDetail", "{\"tariffDescList\":\"默认套餐内容,默认套餐二\"}");
            AppInfoJSon appInfoJSon = new AppInfoJSon();
            appInfoJSon.setAppName("靓丽前台-银商版");
            appInfoJSon.setAppId("afd2baf088034179b4c98826b4d9fcca");
           String appPackname= Utills.getAppProcessName(getApplicationContext());//获取包名
            appInfoJSon.setAppPackName("com.shboka.beautyorderums");
            appInfoJSon.setAppVersionCode("3.0.6.1");
            KLog.json("appInfoJson", JSON.toJSONString(appInfoJSon));
            jsonObj.put("appInfo", JSON.toJSONString(appInfoJSon));
            jsonObj.put("interType", "BMP-QUERY");
            jsonObj.put("version", "001");
            DeviceInfoJSon deviceInfoJSon = new DeviceInfoJSon();
            deviceInfoJSon.setProdCode("19");//产品型号
            deviceInfoJSon.setFirmCode("109");//厂商代码

             //获取sn
                String deviceInfoMap  = null;
                try {
                    deviceInfoMap = baseSystemManager.readSN();
                    deviceInfoMap=baseSystemManager.getDeviceInfo().toString();
                } catch (SdkException e) {
                    e.printStackTrace();
                } catch (CallServiceException e) {
                    e.printStackTrace();
                }
                KLog.d("deviceInfo",deviceInfoMap);
            deviceInfoJSon.setDeviceSn("0820043480");//终端硬件序列号

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
                try {
                    JSONObject jsonObject=s.getJSONObject("data");
                    JSONArray array =jsonObject.getJSONArray("tariffInfoList");
                    JSONObject tariArrayOne = array.getJSONObject(0);
                    String msgString = s.getString("msg");
                    Toast.makeText(getApplicationContext(),msgString,Toast.LENGTH_SHORT).show();
                    TariffInfoListJson tariffInfoListJson=JSON.parseObject(tariArrayOne.toString(),TariffInfoListJson.class);
                    textRespose.setText("套餐描述:"+tariffInfoListJson.getTariffDesc()+"\n"+
                            "套餐标签:"+tariffInfoListJson.getTariffTag()+"\n"+
                            "原价:"+tariffInfoListJson.getOriginalPrice()+"\n"+
                            "现价:"+tariffInfoListJson.getPresentPrice()+"\n"+
                            "折扣:"+tariffInfoListJson.getDiscount()+"\n"+
                            "服务期:"+tariffInfoListJson.getServiceTerm()+"\n"+
                            "试用期:"+tariffInfoListJson.getProbation()+"\n"+
                            "是否为默认套餐:"+tariffInfoListJson.getIsDefaulted()
                            );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textRespose.setText("加载错误"+volleyError);
            }
        }){
            /*@Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();
              *//*  map.put("reqDetail","{\"tariffDescList\":\"默认套餐内容,默认套餐二\"}");//传入参数
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
*//*
                return map;
            }*/
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




    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    break;
            }
        }
    };



}
