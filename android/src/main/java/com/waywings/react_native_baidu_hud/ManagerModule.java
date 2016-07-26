package com.waywings.react_native_baidu_hud;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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

            WritableMap map = Arguments.createMap();
            map.putString("event", "onChangedAssistant");
            map.putString("from", "SpeechUnderstander");
            map.putString("result", resultString);

            getReactApplicationContext()
                    .getJSModule(RCTNativeAppEventEmitter.class)
                    .emit("HUD", map);

            Log.d(BNRemoteConstants.MODULE_TAG, "onAssistant......distance = " + arg0.getAssistantDistance() + ", type = " + arg0.getAssistantType());

            String assistantTips = "";
            String assistantTypeS = "合流";
            if (arg0.getAssistantDistance() > 0) {
                switch (arg0.getAssistantType()) {
                    case HUDConstants.AssistantType.JOINT:
                        assistantTypeS = "合流";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.TUNNEL:
                        assistantTypeS = "隧道";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.BRIDGE:
                        assistantTypeS = "桥梁";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.RAILWAY:
                        assistantTypeS = "铁路";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.BLIND_BEND:
                        assistantTypeS = "急转弯";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.BLIND_SLOPE:
                        assistantTypeS = "陡坡";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.ROCKFALL:
                        assistantTypeS = "落石";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.ACCIDENT:
                        assistantTypeS = "事故多发区";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.SPEED_CAMERA:
                        assistantTypeS = "测速摄像";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS + "限速： " + arg0.getAssistantLimitedSpeed();
                        break;
                    case HUDConstants.AssistantType.TRAFFIC_LIGHT_CAMERA:
                        assistantTypeS = "交通信号灯摄像";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.INTERVAL_CAMERA:
                        assistantTypeS = "区间测速";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.CHILDREN:
                        assistantTypeS = "注意儿童";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.UNEVEN:
                        assistantTypeS = "路面不平";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.NARROW:
                        assistantTypeS = "道路变窄";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.VILLAGE:
                        assistantTypeS = "前面村庄";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.SLIP:
                        assistantTypeS = "路面易滑";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.OVER_TAKE_FORBIDEN:
                        assistantTypeS = "禁止超车";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    case HUDConstants.AssistantType.HONK:
                        assistantTypeS = "请铭喇叭";
                        assistantTips = "前方" + getFormatAfterMeters(arg0.getAssistantDistance()) + assistantTypeS;
                        break;
                    default:
                        break;
                }
            }

            final String assistantTipstr = assistantTips;
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", assistantTipstr);
                }
            });
        }

        @Override
        public void onCruiseEnd(BNRGCruiseEnd arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "cruise end");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", "关闭电子狗: ");

                }
            });
        }

        @Override
        public void onCruiseStart(BNRGCruiseStart arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "cruise start");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", "开启电子狗: " );
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
                    Log.e("HUD", "当前道路: " + curRoadName);
                }
            });
        }

        @Override
        public void onGPSLost(BNRGGPSLost arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onGPSLost....");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", "GPS信号丢失");
                }
            });
        }

        @Override
        public void onGPSNormal(BNRGGPSNormal arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onGPSNormal....");

            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", "GPS信号正常");
                }
            });
        }

        @Override
        public void onManeuver(BNRGManeuver arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onManeuver...........name = " + arg0.getManeuverName() + ", distance = " +
                    arg0.getManeuverDistance() + ",nextRoadName = " + arg0.getNextRoadName());

//            final String afterMeterS = getFormatAfterMeters(arg0.getManeuverDistance());
//            final String nextRoadName = arg0.getNextRoadName();
//            final boolean isAlong = arg0.mIsStraight;
//            String turnName = arg0.name;
//            int turnIconResId = SimpleGuideModle.gTurnIconID[0];
//
//            if (turnName != null && !"".equalsIgnoreCase(turnName)) {
//                turnName = turnName + ".png";
//            }
//
//            if (turnName != null && !"".equalsIgnoreCase(turnName)) {
//                turnIconResId = SimpleGuideModle.getInstance().getTurnIconResId(turnName);
//            }
//
//            final int turnIcon = turnIconResId;

            mMainHandler.post(new Runnable() {

                @Override
                public void run() {


//                    mAfterMetersInfoTV.setText(afterMeterS);
//                    mGoWhereInfoTV.setText(nextRoadName);
//
//                    mNaviAfterView.setVisibility(View.VISIBLE);
//                    mAlongRoadView.setVisibility(View.GONE);
//                    mTurnIcon.setImageDrawable(getResources().getDrawable(turnIcon));
                }
            });
        }

        @Override
        public void onNaviEnd(BNRGNaviEnd arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onNaviEnd...........");

            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", "导航结束");
                }
            });
        }

        @Override
        public void onNaviStart(BNRGNaviStart arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onNaviStart...........");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", "导航开始");
                }
            });
        }

        @Override
        public void onNextRoad(BNRGNextRoad arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onNextRoad...........nextRoadName = " + arg0.getNextRoadName());
        }

        @Override
        public void onRemainInfo(BNRGRemainInfo arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onRemainInfo.............distance = " + arg0.getRemainDistance() + ", time = " + arg0.getRemainTime());

            final String remainDistance = calculateTotalRemainDistString(arg0.getRemainDistance());
            //final String remainTime = calculateArriveTime(arg0.getRemainTime());
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {

                    Log.e("HUD remainDistance",  remainDistance);

                }
            });
        }

        @Override
        public void onRoutePlanYawComplete(BNRGRPYawComplete arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onRoutePlanYawComplete............");

            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", "偏航算路完成");
                }
            });
        }

        @Override
        public void onRoutePlanYawing(BNRGRPYawing arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onRoutePlanYawing............");

            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", "偏航中...");
                }
            });
        }

        @Override
        public void onServiceArea(BNRGServiceArea arg0) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onServiceArea............name = " + arg0.getServiceAreaName() + ", distance = " + arg0.getServiceAreaDistance());

            final String serviceAreaTips = getFormatAfterMeters(arg0.getServiceAreaDistance()) + " " + arg0.getServiceAreaName();
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", serviceAreaTips.toString());
                }
            });
        }

        @Override
        public void onEnlargeRoad(BNEnlargeRoad enlargeRoad) {
            Log.d(BNRemoteConstants.MODULE_TAG, "onEnlargeRoad......enlargeRoad = " + enlargeRoad);

            final BNEnlargeRoad enlargeInfo = enlargeRoad;
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.e("HUD", enlargeInfo.toString());
                }
            });
        }

        @Override
        public void onCarFreeStatus(BNRGCarFreeStatus arg0) {
            // TODO Auto-generated method stub
            Log.d(BNRemoteConstants.MODULE_TAG, "onCarFreeStatus...... ");

        }

        @Override
        public void onCarInfo(BNRGCarInfo arg0) {
            // TODO Auto-generated method stub
            Log.d(BNRemoteConstants.MODULE_TAG, "onCarInfo...... ");

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
                    Log.e("HUD", "重新连接到百度导航");
                }
            });

        }

        @Override
        public void onConnected() {
            Log.e(BNRemoteConstants.MODULE_TAG, "connect to server success");
            mMainHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.e("HUD", "成功连接到百度导航");
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
                    Log.e("HUD", "连接断开, " + reason);
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
                        Log.e("HUD", "认证成功， 服务器版本: " + serverVer);
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

    private String calculateTotalRemainDistString(int nDist) {
        StringBuffer builder = new StringBuffer();
        StringUtils.formatDistance(nDist, UnitLangEnum.ZH, builder);
        String totalRemainDistS = builder.toString();

        return totalRemainDistS;
    }

    /**
     * 根据剩余距离获取格式化的字符串，如  200米后
     * @param nextRemainDist
     * @return
     */
    private String getFormatAfterMeters(int nextRemainDist) {
        StringBuffer distance = new StringBuffer();
        StringUtils.formatDistance(nextRemainDist, UnitLangEnum.ZH, distance);
        return "";//context.getResources().getString(R.string.nsdk_string_rg_sg_after_meters, distance);
    }
//
//    private String calculateArriveTime(int remainTime) {
//        long mArriveTime = System.currentTimeMillis();
//        Date curDate = new Date(mArriveTime);
//        mArriveTime += ( remainTime * 1000 );
//        Date arriveDate = new Date(mArriveTime);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        String mArriveTimeS = sdf.format(arriveDate);
//
//        GregorianCalendar curCal = new GregorianCalendar();
//        curCal.setTime(curDate);
//        GregorianCalendar arriveCal = new GregorianCalendar();
//        arriveCal.setTime(arriveDate);
//
//        if ( curCal.get(GregorianCalendar.DAY_OF_MONTH) == arriveCal.get(GregorianCalendar.DAY_OF_MONTH) ) {
//            if ( 0 == arriveCal.get(GregorianCalendar.HOUR_OF_DAY)) {
//                mArriveTimeS = String.format(context.getString(R.string.nsdk_string_rg_arrive_time_at_wee), mArriveTimeS);
//            } else {
//                mArriveTimeS = String.format(context.getString(R.string.nsdk_string_rg_arrive_time), mArriveTimeS);
//            }
//        } else {
//            int interval = getIntervalDays(curDate, arriveDate);
//            if( interval == 1 ) {
//                if ( arriveCal.get(GregorianCalendar.HOUR_OF_DAY) >= 0 && arriveCal.get(GregorianCalendar.HOUR_OF_DAY) < 4 ) {
//                    mArriveTimeS = String.format(context.getString(R.string.nsdk_string_rg_arrive_time),
//                            context.getString(R.string.nsdk_string_rg_wee_hours));
//                } else {
//                    mArriveTimeS = String.format(context.getString(R.string.nsdk_string_rg_arrive_time),
//                            context.getString(R.string.nsdk_string_rg_tomorrow));
//                }
//            } else if ( interval == 2 ) {
//                mArriveTimeS = String.format(context.getString(R.string.nsdk_string_rg_arrive_time),
//                        context.getString(R.string.nsdk_string_rg_the_day_after_tomorrow));
//            } else if ( interval > 2 ) {
//                mArriveTimeS = String.format(context.getString(R.string.nsdk_string_rg_arrive_time_after_day), "" + interval);
//            } else {
//                mArriveTimeS = String.format(context.getString(R.string.nsdk_string_rg_arrive_time), mArriveTimeS);
//            }
//        }
//
//        return mArriveTimeS;
//    }

    /**
     * 两个日期之间相隔的天数
     * @param fDate
     * @param oDate
     * @return
     */
    private static int getIntervalDays(Date fDate, Date oDate) {
        if (null == fDate || null == oDate) {
            return 0;
        }

        long intervalMilli = oDate.getTime() - fDate.getTime();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fDate = (Date) sdf.parse(sdf.format(fDate));
            oDate = (Date) sdf.parse(sdf.format(oDate));
            intervalMilli = oDate.getTime() - fDate.getTime();
        } catch (Exception e) {

        }

        return (int) (intervalMilli / (24 * 60 * 60 * 1000));

    }

}
