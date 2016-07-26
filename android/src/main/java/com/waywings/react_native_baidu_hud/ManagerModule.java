package com.waywings.react_native_baidu_hud;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.navi.event.vistor.SimpleGuideModle;
import com.baidu.navi.event.vistor.StringUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.baidu.navi.event.vistor.StringUtils.UnitLangEnum;
import com.baidu.navisdk.hudsdk.BNRemoteConstants;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNEnlargeRoad;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGAssistant;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGAuthSuccess;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGCarFreeStatus;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGCarInfo;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGCarTunelInfo;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGCruiseEnd;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGCruiseStart;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGCurShapeIndexUpdate;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGCurrentRoad;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGDestInfo;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGGPSLost;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGGPSNormal;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGManeuver;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGNaviEnd;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGNaviStart;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGNearByCameraInfo;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGNextRoad;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGRPYawComplete;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGRPYawing;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGRemainInfo;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGRouteInfo;
import com.baidu.navisdk.hudsdk.BNRemoteMessage.BNRGServiceArea;
import com.baidu.navisdk.hudsdk.client.BNRemoteVistor;
import com.baidu.navisdk.hudsdk.client.HUDConstants;
import com.baidu.navisdk.hudsdk.client.HUDSDkEventCallback;
import com.baidu.navisdk.hudsdk.client.HUDSDkEventCallback.OnConnectCallback;
import com.baidu.navisdk.hudsdk.client.HUDSDkEventCallback.OnRGInfoEventCallback;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanpeipan on 16/7/21.
 */
public class ManagerModule extends ReactContextBaseJavaModule {

    private static String TAG = "hud";

    private Handler mMainHandler = null;
    private Context context;

    public ManagerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext.getBaseContext();
        mMainHandler = new Handler(reactContext.getMainLooper());
        BNRemoteVistor.getInstance().init(context, reactContext.getPackageName(),
                getAppVersionName(context, context.getPackageName()), mRGEventCallback, mConnectCallback);
        BNRemoteVistor.getInstance().setShowLog(true);
    }

    @Override
    public String getName() {
        return "BaiduHUDManager";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    @ReactMethod
    public void initServices(String ak) {
        // Setting.setShowLog(false);
    }

    @ReactMethod
    public void connect() {
        BNRemoteVistor.getInstance().open();
    }

    @ReactMethod
    public void disconnect() {
        BNRemoteVistor.getInstance().close(OnConnectCallback.CLOSE_NORMAL, "user closed");;
    }

    private OnRGInfoEventCallback mRGEventCallback = new OnRGInfoEventCallback(){

        @Override
        public void onAssistant(BNRGAssistant arg0) {

            if (arg0.getAssistantDistance() > 0) {

                WritableMap map = Arguments.createMap();
                WritableMap assistant = Arguments.createMap();

                assistant.putInt("assistUpdateType", arg0.getAssistUpdateType());
                assistant.putInt("assistantType", arg0.getAssistantType());
                assistant.putInt("limitedSpeed", arg0.getAssistantLimitedSpeed());
                assistant.putInt("distance", arg0.getAssistantDistance());

                map.putString("event", "onChangedAssistant");
                map.putMap("body", assistant);

                getReactApplicationContext()
                        .getJSModule(RCTNativeAppEventEmitter.class)
                        .emit(TAG, map);
            }
        }

        @Override
        public void onCruiseEnd(BNRGCruiseEnd arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "cruise end");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e(TAG, "关闭电子狗: ");
                }
            });
        }

        @Override
        public void onCruiseStart(BNRGCruiseStart arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "cruise start");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e(TAG, "开启电子狗: " );
                }
            });
        }

        @Override
        public void onCurrentRoad(BNRGCurrentRoad arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onCurrentRoad...........curRoadName = " + arg0.getCurrentRoadName());

            final String curRoadName = arg0.getCurrentRoadName();
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e(TAG, "当前道路: " + curRoadName);
                }
            });
        }

        @Override
        public void onGPSLost(BNRGGPSLost arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onGPSLost....");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e(TAG, "GPS信号丢失");
                }
            });
        }

        @Override
        public void onGPSNormal(BNRGGPSNormal arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onGPSNormal....");

            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e(TAG, "GPS信号正常");
                }
            });
        }

        @Override
        public void onManeuver(BNRGManeuver arg0) {

            WritableMap map = Arguments.createMap();
            WritableMap maneuver = Arguments.createMap();
            WritableArray laneAdditionTypeArray = Arguments.fromArray(arg0.getManeuverLaneTypeArray());
            WritableArray laneSignTypeArray = Arguments.fromArray(arg0.getManeuverLaneSignArray());

            maneuver.putInt("maneuverId", arg0.getManeuverId());
            maneuver.putBoolean("straight", arg0.getIsStragiht());
            maneuver.putString("maneuverName", arg0.getManeuverName());
            maneuver.putInt("distance", arg0.getDistance());
            maneuver.putString("nextRoadName", arg0.getNextRoadName());
            maneuver.putDouble("longitude", arg0.getManeuverLon());
            maneuver.putDouble("latitude", arg0.getManeuverLat());
            maneuver.putInt("ringFlag", arg0.getManeuverRingInfo());
            maneuver.putInt("laneCount", arg0.getManeuverTotalLaneSum());
            maneuver.putInt("laneTotalAddType", arg0.getManeuverLaneTotalType());
            maneuver.putArray("laneAdditionTypeArray", laneAdditionTypeArray);
            maneuver.putArray("laneSignTypeArray", laneSignTypeArray);


            map.putString("event", "onChangedAssistant");
            map.putMap("maneuver", maneuver);

            getReactApplicationContext()
                    .getJSModule(RCTNativeAppEventEmitter.class)
                    .emit(TAG, map);
        }

        @Override
        public void onNaviEnd(BNRGNaviEnd arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onNaviEnd...........");

            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e(TAG, "导航结束");
                }
            });
        }

        @Override
        public void onNaviStart(BNRGNaviStart arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onNaviStart...........");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e(TAG, "导航开始");
                }
            });
        }

        @Override
        public void onNextRoad(BNRGNextRoad arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onNextRoad...........nextRoadName = " + arg0.getNextRoadName());
        }

        @Override
        public void onRemainInfo(BNRGRemainInfo arg0) {

            WritableMap map = Arguments.createMap();
            WritableMap body = Arguments.createMap();

            body.putInt("remainDistance", arg0.getRemainDistance());
            body.putInt("remainTime", arg0.getRemainTime());

            map.putString("event", "onChangedRemainInfo");
            map.putMap("body", body);

            getReactApplicationContext()
                    .getJSModule(RCTNativeAppEventEmitter.class)
                    .emit(TAG, map);
        }

        @Override
        public void onRoutePlanYawComplete(BNRGRPYawComplete arg0) {

            WritableMap map = Arguments.createMap();

            map.putString("event", "onRoutePlanYawing");
            getReactApplicationContext()
                    .getJSModule(RCTNativeAppEventEmitter.class)
                    .emit(TAG, map);
        }

        @Override
        public void onRoutePlanYawing(BNRGRPYawing arg0) {

            WritableMap map = Arguments.createMap();

            map.putString("event", "onRoutePlanYawing");

            getReactApplicationContext()
                    .getJSModule(RCTNativeAppEventEmitter.class)
                    .emit(TAG, map);
        }

        @Override
        public void onServiceArea(BNRGServiceArea arg0) {

            WritableMap map = Arguments.createMap();
            WritableMap serviceArea = Arguments.createMap();

            serviceArea.putString("serviceArea", arg0.getServiceAreaName());
            serviceArea.putInt("distance", arg0.getServiceAreaDistance());

            map.putString("event", "onChangedServiceArea");
            map.putMap("serviceArea", serviceArea);

            getReactApplicationContext()
                    .getJSModule(RCTNativeAppEventEmitter.class)
                    .emit(TAG, map);
        }

        @Override
        public void onEnlargeRoad(BNEnlargeRoad arg0) {

            WritableMap map = Arguments.createMap();
            WritableMap body = Arguments.createMap();
            Bitmap basicImage = arg0.getBasicImage();
            Bitmap arrowImage = arg0.getArrowImage();

            // todo test enlargeBasicImage & enlargeArrowImage

            body.putInt("enlargeShowState", arg0.getEnlargeRoadState());
            body.putInt("enlargeType", arg0.getEnlargeRoadType());
            body.putString("enlargeBasicImage", basicImage.toString());
            body.putString("enlargeArrowImage", arrowImage.toString());
            body.putInt("enlargeTotalDist", arg0.getTotalDist());
            body.putInt("enlargeRemainDist", arg0.getRemainDist());
            body.putString("nextRoadName", arg0.getRoadName());
            body.putInt("carPosX", arg0.getCarPosX());
            body.putInt("carPosY", arg0.getCarPosY());
            body.putInt("carPosRotate", arg0.getCarPosRotate());
            body.putInt("lastCarPosX", arg0.getLastCarPosX());
            body.putInt("lastCarPosY", arg0.getLastCarPosY());
            body.putInt("lastCarPosRotate", arg0.getLastCarPosRotate());

            map.putString("event", "onEnlargeRoad");

            getReactApplicationContext()
                    .getJSModule(RCTNativeAppEventEmitter.class)
                    .emit(TAG, map);
        }

        @Override
        public void onCarFreeStatus(BNRGCarFreeStatus arg0) {

            WritableMap map = Arguments.createMap();

            map.putString("event", "onChangedCarFreeStatus");
            map.putBoolean("body", arg0.getCarFreeStatus());

            getReactApplicationContext()
                    .getJSModule(RCTNativeAppEventEmitter.class)
                    .emit(TAG, map);
        }

        @Override
        public void onCarInfo(BNRGCarInfo arg0) {

            WritableMap map = Arguments.createMap();
            WritableMap body = Arguments.createMap();

            body.putDouble("angle", arg0.getCarAngle());
            body.putInt("speed", arg0.getCurSpeed());
            body.putDouble("longitude", arg0.getLongitude());
            body.putDouble("latitude", arg0.getLatitude());

            map.putString("event", "onChangedCarPointInfo");
            map.putMap("body", body);

            getReactApplicationContext()
                    .getJSModule(RCTNativeAppEventEmitter.class)
                    .emit(TAG, map);

        }

        @Override
        public void onCarTunelInfo(BNRGCarTunelInfo arg0) {
            // TODO Auto-generated method stub
            Log.d(BNRemoteConstants.MODULE_TAG, "onCarTunelInfo...... ");
        }

        @Override
        public void onCurShapeIndexUpdate(BNRGCurShapeIndexUpdate arg0) {
            // TODO Auto-generated method stub
            Log.d(BNRemoteConstants.MODULE_TAG, "onCurShapeIndexUpdate...... ");
        }

        @Override
        public void onDestInfo(BNRGDestInfo arg0) {
            // TODO Auto-generated method stub
            Log.d(BNRemoteConstants.MODULE_TAG, "onDestInfo...... ");
        }

        @Override
        public void onNearByCamera(BNRGNearByCameraInfo arg0) {
            // TODO Auto-generated method stub
            Log.d(BNRemoteConstants.MODULE_TAG, "onNearByCamera...... ");
        }

        @Override
        public void onRouteInfo(BNRGRouteInfo arg0) {
            // TODO Auto-generated method stub
            Log.d(BNRemoteConstants.MODULE_TAG, "onRouteInfo...... ");
        }

    };

    private OnConnectCallback mConnectCallback = new OnConnectCallback() {

        @Override
        public void onReConnected() {
            Log.e(BNRemoteConstants.MODULE_TAG, "reConnect to server success");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e(TAG, "重新连接到百度导航");
                }
            });

        }

        @Override
        public void onConnected() {
            Log.e(BNRemoteConstants.MODULE_TAG, "connect to server success");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e(TAG, "成功连接到百度导航");
                }
            });
        }

        @Override
        public void onClose(int arg0, String arg1) {
            Log.e(BNRemoteConstants.MODULE_TAG, "MainActivity.................onClose()  disconnect, reason = " + arg1);
            final int reasonId = arg0;
            final String reason = arg1;
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e(TAG, "连接断开, " + reason);
                }
            });
        }

        @Override
        public void onAuth(BNRGAuthSuccess arg0) {
            if (arg0 != null) {
                Log.d(BNRemoteConstants.MODULE_TAG, "auth success, serverVer = " + arg0.getServerVersion());
                final String serverVer = arg0.getServerVersion();
                mMainHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        Log.e(TAG, "认证成功， 服务器版本: " + serverVer);
                    }
                });
            }
        }

        @Override
        public void onStartLBSAuth() {

        }

        @Override
        public void onEndLBSAuth(int result, String reason) {
            // TODO Auto-generated method stub
            if (result == 0) {
            }
        }
    };

    private  String getAppVersionName(Context context, String appName) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(appName, 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
