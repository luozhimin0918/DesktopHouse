package com.example.yinlian.desktophousekeeper.index;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.yinlian.desktophousekeeper.model.AppInfoJSon;
import com.example.yinlian.desktophousekeeper.model.DeviceInfoJSon;
import com.example.yinlian.desktophousekeeper.model.ReqDetailJson;
import com.example.yinlian.desktophousekeeper.trace.Utills;
import com.socks.library.KLog;
import com.ums.upos.sdk.exception.CallServiceException;
import com.ums.upos.sdk.exception.SdkException;
import com.ums.upos.sdk.system.BaseSystemManager;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by Luozhimin on 2018-06-22.13:01
 */
public class RepObj {
    public static JSONObject netRespParame(Context context, BaseSystemManager baseSystemManager){
        JSONObject jsonObject =new JSONObject();
        try {
            ReqDetailJson reqDetailJson=new ReqDetailJson();
            //"{\"tariffDescList\":\"默认套餐内容,默认套餐二\",\"tariffDesc\":\"默认套餐内容\"}"
            reqDetailJson.setTariffDescList("默认套餐内容,默认套餐二");
            jsonObject.put("reqDetail", JSON.toJSONString(reqDetailJson));
            jsonObject.put("interType", "BMP-QUERY");
            jsonObject.put("version", "001");
            jsonObject.put("mac","ums2018");
            AppInfoJSon appInfoJSon = new AppInfoJSon();
            appInfoJSon.setAppName("靓丽前台-银商版");
            appInfoJSon.setAppId("afd2baf088034179b4c98826b4d9fcca");
            String appPackname= Utills.getAppProcessName(context);//获取包名
            appInfoJSon.setAppPackName("com.shboka.beautyorderums");
            appInfoJSon.setAppVersionCode("3.0.6.1");
            jsonObject.put("appInfo", JSON.toJSONString(appInfoJSon));
            KLog.json("appInfoJson", JSON.toJSONString(appInfoJSon));

            DeviceInfoJSon deviceInfoJSon = new DeviceInfoJSon();
            deviceInfoJSon.setProdCode("19");//产品型号
            deviceInfoJSon.setFirmCode("109");//厂商代码

            //获取sn
           String deviceInfoMap  = null;
            try {
                deviceInfoMap = baseSystemManager.readSN();
//                deviceInfoMap=baseSystemManager.getDeviceInfo().toString();
            } catch (SdkException e) {
                e.printStackTrace();
            } catch (CallServiceException e) {
                e.printStackTrace();
            }catch (NullPointerException e){

            }
            KLog.d("deviceInfo",deviceInfoMap);
            deviceInfoJSon.setDeviceSn("0820043480");//终端硬件序列号
            jsonObject.put("deviceInfo",JSON.toJSONString(deviceInfoJSon));
            KLog.json("deviceInfo",JSON.toJSONString(deviceInfoJSon));


        } catch (JSONException e) {
            e.printStackTrace();
        }






        return jsonObject;
    }
}
