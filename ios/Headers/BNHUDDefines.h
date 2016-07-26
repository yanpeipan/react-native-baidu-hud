//
//  BNHUDDefines.h
//  HUDSDK
//
//  Created by baidu on 15/6/12.
//  Copyright (c) 2015年 baidu. All rights reserved.
//

#ifndef HUDSDK_BNHUDDefines_h
#define HUDSDK_BNHUDDefines_h

/*
 *  HUD SDK 错误类型
 */
typedef NS_ENUM(NSUInteger, BN_HUD_SDK_ERROR_TYPE) {
    BN_HUD_SDK_UNREACHABLE_ERROR,                    /**< 不能连接上服务器 */
    BN_HUD_SDK_NOT_AUTHORIZE_ERROR,                  /**< CLINET未被授权 */
    BN_HUD_SDK_CLIENT_OFFLINE_ERROR,                 /**< CLIENT端断开 */
    BN_HUD_SDK_SERVER_OFFLINE_ERROR,                 /**< SERVER端断开 */
    BN_HUD_SDK_SERVER_ILLEGAL_ERROR                  /**< 非法Server端 */
};

/*
 *  辅助诱导图类型
 */
typedef NS_ENUM(NSUInteger, BNHUDNaviAssistant_Type_Enum)
{
    BNHUDNaviAssistant_Type_Joint,                  /**<  合流 - joint */
    BNHUDNaviAssistant_Type_Tunnel ,                /**<  隧道 - Tunnel */
    BNHUDNaviAssistant_Type_Bridge ,                /**<  桥梁 - Bridge */
    BNHUDNaviAssistant_Type_Railway ,               /**<  铁道 - Railway */
    BNHUDNaviAssistant_Type_BlindBend,	            /**<  转弯 - Blind bend */
    BNHUDNaviAssistant_Type_BlindSlope,             /**<  陡坡 - Blind slope */
    BNHUDNaviAssistant_Type_Rockfall,               /**<  落石 - Rock fall left */
    BNHUDNaviAssistant_Type_Accident ,              /**<  事故多发区 - Accident */
    BNHUDNaviAssistant_Type_SpeedCamera ,           /**<  测速摄像头 - Camera to detect speed */
    BNHUDNaviAssistant_Type_TrafficLightCamera ,    /**<  交通信号灯摄像头 - Camera near traffic light */
    BNHUDNaviAssistant_Type_PeccanryCamera ,        /**<  违章摄像头 - camera for peccanry */
    BNHUDNaviAssistant_Type_IntervalCamera,         /**<  区间限速  - camera for peccanry */
    BNHUDNaviAssistant_Type_Children ,              /**<  注意儿童 - Be careful for the children */
    BNHUDNaviAssistant_Type_Uneven ,                /**<  路面不平 - Road is uneven */
    BNHUDNaviAssistant_Type_Narrow,                 /**<  道路变窄 - Road will be narrow */
    BNHUDNaviAssistant_Type_Villiage ,              /**<  前面村庄 - There is a villiage nearby */
    BNHUDNaviAssistant_Type_Slip ,                  /**<  路面易滑 - Take care for slipping */
    BNHUDNaviAssistant_Type_OverTakeForbidden ,     /**<  禁止超车 - Over take is not allowed */
    BNHUDNaviAssistant_Type_Honk                    /**<  请鸣喇叭 - Need to honk */
};

/*
 *  路线详情项转向类型
 */
typedef NS_ENUM(NSUInteger, BNHUDNaviManeuver_Kind_Enum)
{
    BNHUDNaviManeuver_Kind_Invalid ,			          /**<  无效值 */
    BNHUDNaviManeuver_Kind_Front ,			              /**<  直行 */
    BNHUDNaviManeuver_Kind_Right_Front ,		          /**<  右前方转弯 */
    BNHUDNaviManeuver_Kind_Right ,			              /**<  右转 */
    BNHUDNaviManeuver_Kind_Right_Back ,		              /**<  右后方转弯 */
    BNHUDNaviManeuver_Kind_Back ,				          /**<  掉头 */
    BNHUDNaviManeuver_Kind_Left_Back ,		              /**<  左后方转弯 */
    BNHUDNaviManeuver_Kind_Left ,				          /**<  左转 */
    BNHUDNaviManeuver_Kind_Left_Front ,		              /**<  左前方转弯 */
    BNHUDNaviManeuver_Kind_Ring ,				          /**<  环岛 */
    BNHUDNaviManeuver_Kind_RingOut ,			          /**<  环岛出口 */
    BNHUDNaviManeuver_Kind_Left_Side ,		              /**<  普通/JCT/SAPA二分歧 靠左 */
    BNHUDNaviManeuver_Kind_Right_Side ,		              /**<  普通/JCT/SAPA二分歧 靠右 */
    BNHUDNaviManeuver_Kind_Left_Side_Main ,	              /**<  左侧走本线 */
    BNHUDNaviManeuver_Kind_Branch_Left_Main ,             /**<  靠最左走本线 */
    BNHUDNaviManeuver_Kind_Right_Side_Main ,	          /**<  右侧走本线 */
    BNHUDNaviManeuver_Kind_Branch_Right_Main,             /**<  靠最右走本线 */
    BNHUDNaviManeuver_Kind_Center_Main ,                  /**<  中间走本线 */
    BNHUDNaviManeuver_Kind_Left_Side_IC ,		          /**<  IC二分歧左侧走IC */
    BNHUDNaviManeuver_Kind_Right_Side_IC ,	              /**<  IC二分歧右侧走IC */
    BNHUDNaviManeuver_Kind_Branch_Left ,		          /**<  普通三分歧/JCT/SAPA 靠最左 */
    BNHUDNaviManeuver_Kind_Branch_Right ,		          /**<  普通三分歧/JCT/SAPA 靠最右 */
    BNHUDNaviManeuver_Kind_Branch_Center ,	              /**<  普通三分歧/JCT/SAPA 靠中间 */
    BNHUDNaviManeuver_Kind_Start ,			              /**<  起始地 */
    BNHUDNaviManeuver_Kind_Dest ,				          /**<  目的地 */
    BNHUDNaviManeuver_Kind_VIA1 ,				          /**<  途径点1 */
    BNHUDNaviManeuver_Kind_VIA2 ,				          /**<  途径点2 */
    BNHUDNaviManeuver_Kind_VIA3 ,				          /**<  途径点3 */
    BNHUDNaviManeuver_Kind_VIA4 ,				          /**<  途径点4 */
    BNHUDNaviManeuver_Kind_InFerry ,			          /**<  进入渡口 */
    BNHUDNaviManeuver_Kind_OutFerry ,			          /**<  脱出渡口 */
    BNHUDNaviManeuver_Kind_TollGate ,                     /**<  收费站 */
    BNHUDNaviManeuver_Kind_Left_Side_Straight_IC ,        /**<  IC二分歧左侧直行走IC */
    BNHUDNaviManeuver_Kind_Right_Side_Straight_IC ,       /**<  IC二分歧右侧直行走IC */
    BNHUDNaviManeuver_Kind_Left_Side_Straight ,           /**<  普通/JCT/SAPA二分歧左侧 直行 */
    BNHUDNaviManeuver_Kind_Right_Side_Straight ,          /**<  普通/JCT/SAPA二分歧右侧 直行 */
    BNHUDNaviManeuver_Kind_Branch_Left_Straight ,         /**<  普通/JCT/SAPA三分歧左侧 直行 */
    BNHUDNaviManeuver_Kind_Branch_Center_Straight ,       /**<  普通/JCT/SAPA三分歧中央 直行 */
    BNHUDNaviManeuver_Kind_Branch_Right_Straight ,        /**<  普通/JCT/SAPA三分歧右侧 直行 */
    BNHUDNaviManeuver_Kind_Branch_Left_IC ,               /**<  IC三分歧左侧走IC */
    BNHUDNaviManeuver_Kind_Branch_Center_IC ,             /**<  IC三分歧中央走IC */
    BNHUDNaviManeuver_Kind_Branch_Right_IC ,              /**<  IC三分歧右侧走IC */
    BNHUDNaviManeuver_Kind_Branch_Left_IC_Straight ,      /**<  IC三分歧左侧直行 */
    BNHUDNaviManeuver_Kind_Branch_Center_IC_Straight ,	  /**<  IC三分歧中间直行 */
    BNHUDNaviManeuver_Kind_Branch_Right_IC_Straight ,     /**<  IC三分歧右侧直行 */
    BNHUDNaviManeuver_Kind_Straight_2Branch_Left_Base ,   /**<  八方向靠左直行*/
    BNHUDNaviManeuver_Kind_Straight_2Branch_Right_Base ,  /**<  八方向靠右直行*/
    BNHUDNaviManeuver_Kind_Straight_3Branch_Left_Base  ,  /**<  八方向靠最左侧直行*/
    BNHUDNaviManeuver_Kind_Straight_3Branch_Middle_Base , /**<  八方向沿中间直行 */
    BNHUDNaviManeuver_Kind_Straight_3Branch_Right_Base ,  /**<  八方向靠最右侧直行 */
    BNHUDNaviManeuver_Kind_Left_2Branch_Left_Base ,       /**<  八方向左转+随后靠左 */
    BNHUDNaviManeuver_Kind_Left_2Branch_Right_Base ,      /**<  八方向左转+随后靠右 */
    BNHUDNaviManeuver_Kind_Left_3Branch_Left_Base ,       /**<  八方向左转+随后靠最左 */
    BNHUDNaviManeuver_Kind_Left_3Branch_Middle_Base ,     /**<  八方向左转+随后沿中间 */
    BNHUDNaviManeuver_Kind_Left_3Branch_Right_Base ,      /**<  八方向左转+随后靠最右 */
    BNHUDNaviManeuver_Kind_Right_2Branch_Left_Base ,      /**<  八方向右转+随后靠左 */
    BNHUDNaviManeuver_Kind_Right_2Branch_Right_Base ,     /**<  八方向右转+随后靠右 */
    BNHUDNaviManeuver_Kind_Right_3Branch_Left_Base ,      /**<  八方向右转+随后靠最左 */
    BNHUDNaviManeuver_Kind_Right_3Branch_Middle_Base ,    /**<  八方向右转+随后沿中间 */
    BNHUDNaviManeuver_Kind_Right_3Branch_Right_Base,      /**<  八方向右转+随后靠最右 */
    BNHUDNaviManeuver_Kind_Left_Front_2Branch_Left_Base,  /**<  八方向左前方靠左侧 */
    BNHUDNaviManeuver_Kind_Left_Front_2Branch_Right_Base, /**<  八方向左前方靠右侧  */
    BNHUDNaviManeuver_Kind_Right_Front_2Branch_Left_Base, /**<  八方向右前方靠左侧 */
    BNHUDNaviManeuver_Kind_Right_Front_2Branch_Right_Base /**<  八方向右前方靠右侧 */
};

/*
 *  辅助诱导图标更新类型
 */
typedef NS_ENUM(NSUInteger, BNHUDNaviAssistant_Update_Type)
{
    BNHUDNaviAssistant_Type_Show,                   /**<  辅助诱导图标显示 */
    BNHUDNaviAssistant_Type_Update,                 /**<  辅助诱导图标更新 */
    BNHUDNaviAssistant_Type_Hide                    /**<  辅助诱导图标隐藏 */
};

typedef NS_ENUM(NSUInteger, BNHUDNavi_Route_Type)
{
    BNHUDNavi_Route_Type_High_Speed,                     /**< 高速 */
    BNHUDNavi_Route_Type_Quick_Road,                     /**< 快速 */
    BNHUDNavi_Route_Type_National_Road,                  /**< 国道 */
    BNHUDNavi_Route_Type_Provincial_Road,                /**< 省道 */
    BNHUDNavi_Route_Type_Country_Road,                   /**< 县道 */
    BNHUDNavi_Route_Type_Other                           /**< 其他 */
};

typedef NS_ENUM(NSUInteger, BNHUDNavi_Lane_Type)
{
    BNHUDNavi_Lane_Type_Turn_Left_90 = 0x11000000,                /**< 左转(高亮) */
    BNHUDNavi_Lane_Type_Turn_Left_90_Gray = 0x10000000,           /**< 左转 */
    BNHUDNavi_Lane_Type_Turn_Right_90 = 0x00110000,               /**< 右转(高亮) */
    BNHUDNavi_Lane_Type_Turn_Right_90_Gray = 0x00100000,          /**< 右转 */
    BNHUDNavi_Lane_Type_Straight= 0x00001100,                     /**< 直行(高亮) */
    BNHUDNavi_Lane_Type_Straight_Gray = 0x00001000,               /**< 直行 */
    BNHUDNavi_Lane_Type_Turn_Around_Left = 0x00000011,            /**< 左调头(高亮) */
    BNHUDNavi_Lane_Type_Turn_Around_Left_Gray = 0x00000010,       /**< 左调头 */
    BNHUDNavi_Lane_Type_2Cross_Left_Straight_Gray = 0x10001000,   /**< 左转+直行 */
    BNHUDNavi_Lane_Type_2Cross_Turn_Left = 0x11001000,            /**< 左转(高亮)+直行 */
    BNHUDNavi_Lane_Type_2Cross_Left_Straight= 0x10001100,         /**< 左转+直行(高亮) */
    BNHUDNavi_Lane_Type_2Cross_Right_Straight_Gray = 0x00101000,  /**< 右转+直行 */
    BNHUDNavi_Lane_Type_2Cross_Turn_Right = 0x00111000,           /**< 右转(高亮)+直行 */
    BNHUDNavi_Lane_Type_2Cross_Right_Straight= 0x00101100,        /**< 右转+直行(高亮) */
    BNHUDNavi_Lane_Type_3Cross_Gray = 0x10101000,                 /**< 左转+直行+右转 */
    BNHUDNavi_Lane_Type_3Cross_Turn_Left = 0x11101000,            /**< 左转(高亮)+直行+右转 */
    BNHUDNavi_Lane_Type_3Cross_Turn_Right = 0x10111000,           /**< 左转+直行+右转(高亮) */
    BNHUDNavi_Lane_Type_3Cross_Straight= 0x10101100,              /**< 左转+直行(高亮)+右转 */
    BNHUDNavi_Lane_Type_Around_And_Left_Gray = 0x10000010,        /**< 左转+左调头 */
    BNHUDNavi_Lane_Type_Left_And_AROUND = 0x11000010,             /**< 左转(高亮)+左调头 */
    BNHUDNavi_Lane_Type_Around_And_Left = 0x10000011,             /**< 左转+左调头(高亮) */
    BNHUDNavi_Lane_Type_Left_And_Right_Gray = 0x10100000,         /**< 左转+右转 */
    BNHUDNavi_Lane_Type_Left_And_Right = 0x11100000,              /**< 左转(高亮)+右转 */
    BNHUDNavi_Lane_Type_Right_And_Left = 0x10110000,              /**< 左转+右转(高亮) */
    BNHUDNavi_Lane_Type_Around_And_Right_Gray = 0x00100010,       /**< 右转+左调头 */
    BNHUDNavi_Lane_Type_Right_And_Around = 0x00110010,            /**< 右转(高亮)+左调头 */
    BNHUDNavi_Lane_Type_Around_And_Right = 0x00100011,            /**< 右转+左调头(高亮) */
    BNHUDNavi_Lane_Type_Around_And_Straight_Gray = 0x00001010,    /**< 直行+左调头 */
    BNHUDNavi_Lane_Type_Straight_And_Around = 0x00001110,         /**< 直行(高亮)+左调头 */
    BNHUDNavi_Lane_Type_Around_And_Straight= 0x00001011,          /**< 直行+左调头(高亮) */
    BNHUDNavi_Lane_Type_Around_Left_Right_Gray = 0x10100010,      /**< 左转+右转+左调头 */
    BNHUDNavi_Lane_Type_Left_Around_Right = 0x11100010,           /**< 左转(高亮)+右转+左调头 */
    BNHUDNavi_Lane_Type_Right_Around_Left = 0x10110010,           /**< 左转+右转(高亮)+左调头 */
    BNHUDNavi_Lane_Type_Around_Left_Right = 0x10100011,           /**< 左转+右转+左调头(高亮) */
    BNHUDNavi_Lane_Type_Around_Left_Straight_Gray = 0x10001010,   /**< 左转+直行+左调头 */
    BNHUDNavi_Lane_Type_Left_Around_Straight= 0x11001010,         /**< 左转(高亮)+直行+左调头 */
    BNHUDNavi_Lane_Type_Straight_Left_AROUND = 0x10001110,        /**< 左转+直行(高亮)+左调头 */
    BNHUDNavi_Lane_Type_Around_Left_Straight= 0x10001011,         /**< 左转+直行+左调头(高亮) */
    BNHUDNavi_Lane_Type_Around_Right_Straight_Gray = 0x00101010,  /**< 右转+直行+左调头 */
    BNHUDNavi_Lane_Type_Right_Around_Straight= 0x00111010,        /**< 右转(高亮)+直行+左调头 */
    BNHUDNavi_Lane_Type_Straight_Right_AROUND = 0x00101110,       /**< 右转+直行(高亮)+左调头 */
    BNHUDNavi_Lane_Type_Around_Right_Straight= 0x00101011,        /**< 右转+直行+左调头(高亮) */
    BNHUDNavi_Lane_Type_Around_Left_Right_Straight_Gray = 0x10101010,  /**< 左转+右转+直行+左调头 */
    BNHUDNavi_Lane_Type_Left_Around_Right_Straight= 0x11101010,   /**< 左转(高亮)+右转+直行+左调头 */
    BNHUDNavi_Lane_Type_Right_Around_Left_Straight= 0x10111010,   /**< 左转+右转(高亮)+直行+左调头 */
    BNHUDNavi_Lane_Type_Straight_Around_Left_Right = 0x10101110,  /**< 左转+右转+直行(高亮)+左调头 */
    BNHUDNavi_Lane_Type_Around_Left_Right_Straight= 0x10101011    /** 左转+右转+直行+左调头(高亮) */
};

typedef NS_ENUM(NSUInteger, BNHUDNavi_Restrict_Type)
{
    BNHUDNavi_Restrict_Type_CurveLeft 				= 1 ,  /**< 向左急弯路 */
    BNHUDNavi_Restrict_Type_CurveRight 				= 2 ,  /**< 向右急弯路 */
    BNHUDNavi_Restrict_Type_CurveReverse 			= 3 ,  /**< 反向急弯路 */
    BNHUDNavi_Restrict_Type_CurveContinuous 		= 4 ,  /**< 连续急弯路 */
    BNHUDNavi_Restrict_Type_SlopeUp 				= 5 ,  /**< 上陡坡 */
    BNHUDNavi_Restrict_Type_SlopeDown 				= 6 ,  /**< 下陡坡 */
    BNHUDNavi_Restrict_Type_NarrowBoth 				= 7 ,  /**< 两侧变窄 */
    BNHUDNavi_Restrict_Type_NarrowRight 		    = 8 ,  /**< 右侧变窄 */
    BNHUDNavi_Restrict_Type_NarrowLeft   			= 9 ,  /**< 左侧变窄 */
    BNHUDNavi_Restrict_Type_NarrowBridge 			= 10 , /**< 窄桥 */
    BNHUDNavi_Restrict_Type_BidirectionalTraffic 	= 11 , /**< 双向交通 */
    BNHUDNavi_Restrict_Type_Children 				= 12 , /**< 注意儿童 */
    BNHUDNavi_Restrict_Type_LiveStock 				= 13 , /**< 注意牲畜 */
    BNHUDNavi_Restrict_Type_RockfallLeft 			= 14 , /**< 注意落石（左侧）*/
    BNHUDNavi_Restrict_Type_RockfallRight 			= 15 , /**< 注意落石（右侧）*/
    BNHUDNavi_Restrict_Type_CrossWind 				= 16 , /**< 注意横风 */
    BNHUDNavi_Restrict_Type_Slip 					= 17 , /**< 易滑 */
    BNHUDNavi_Restrict_Type_HillsideDangerousLeft 	= 18 , /**< 傍山险路（左侧）*/
    BNHUDNavi_Restrict_Type_HillsideDangerousRight 	= 19 , /**< 傍山险路（右侧）*/
    BNHUDNavi_Restrict_Type_OnDamLeft 				= 20 , /**< 堤坝路（左侧）*/
    BNHUDNavi_Restrict_Type_OnDamRight 				= 21 , /**< 堤坝路（右侧）*/
    BNHUDNavi_Restrict_Type_Viliage 				= 22 , /**< 村庄 */
    BNHUDNavi_Restrict_Type_HumpBridge 				= 23 , /**< 驼峰桥 */
    BNHUDNavi_Restrict_Type_Uneven 					= 24 , /**< 路面不平 */
    BNHUDNavi_Restrict_Type_UnderWater 				= 25 , /**< 过水路面 */
    BNHUDNavi_Restrict_Type_ManagedRailwayCross 	= 26 , /**< 有人值守铁路道口 */
    BNHUDNavi_Restrict_Type_UnManagedRailwayCross 	= 27 , /**< 无人值守铁路道口 */
    BNHUDNavi_Restrict_Type_AccidentProne 			= 28 , /**< 事故易发路段 */
    BNHUDNavi_Restrict_Type_ByPassBoth 				= 29 , /**< 左右绕行 */
    BNHUDNavi_Restrict_Type_ByPassLeft 				= 30 , /**< 左侧绕行 */
    BNHUDNavi_Restrict_Type_ByPassRight 			= 31 , /**< 右侧绕行 */
    BNHUDNavi_Restrict_Type_Caution 				= 32 , /**< 注意危险 */
    BNHUDNavi_Restrict_Type_OverTakeForbidden 		= 33 , /**< 禁止超车 */
    BNHUDNavi_Restrict_Type_OverTakeForbiddenRemove = 34 , /**< 解除禁止超车 */
    BNHUDNavi_Restrict_Type_Honk 					= 35 , /**< 鸣喇叭 */
    BNHUDNavi_Restrict_Type_ContinuousDown 			= 36 , /**< 连续下坡 */
    BNHUDNavi_Restrict_Type_CautionSign 			= 37 , /**< 注意警示牌（文字提示）*/
    BNHUDNavi_Restrict_Type_JointLeft 				= 38 , /**< 注意左侧汇流 */
    BNHUDNavi_Restrict_Type_JointRight 				= 39 , /**< 注意右侧汇流 */
    BNHUDNavi_Restrict_Type_StopCar 				= 40 , /**< 停车让行 */
    BNHUDNavi_Restrict_Type_JointCar 				= 41 , /**< 汇车让行 */
    BNHUDNavi_Restrict_Type_LowSpeed 				= 42 , /**< 减速让行 */
    BNHUDNavi_Restrict_Type_Tunnel 					= 43 , /**< 隧道开灯 */
    BNHUDNavi_Restrict_Type_WaterRoad 				= 44 , /**< 潮汐路 */
    BNHUDNavi_Restrict_Type_Hump 					= 45 , /**< 凸起路 */
    BNHUDNavi_Restrict_Type_Sinks 					= 46 , /**< 凹下路 */
    BNHUDNavi_Restrict_Type_Invalid 				= 0xFFFFFFFF  /**< 无效 */
};


typedef NS_ENUM(NSUInteger, BNHUDNavi_Camera_Type)
{
    BNHUDNavi_Camera_Type_Limit_Speed = 1,        /**< 限速 */
    BNHUDNavi_Camera_Type_Traffic_Light,          /**< 红绿灯 */
    BNHUDNavi_Camera_Type_Break_Rules             /**< 违章 */
};


typedef NS_ENUM(NSUInteger, BNHUDNavi_Lane_Increase_Type)
{
    BNHUDNavi_Lane_Type_None_Increase,            /**< 不增加 */
    BNHUDNavi_Lane_Type_Left_Increase,            /**< 左增加 */
    BNHUDNavi_Lane_Type_Right_Increase            /**< 右增加 */
};

#endif
