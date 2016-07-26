//
//  BNHUDMessages.h
//  HUDSDKClientTest
//
//  Created by baidu on 15/11/19.
//  Copyright © 2015年 baidu. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "BNHUDDefines.h"

@interface BNHUDManeuver : NSObject

//机动点Id
@property (nonatomic, assign) NSInteger maneuverId;
//是否为顺行模式
@property (nonatomic, assign) BOOL straight;
//下一个机动点名称（诱导图标名）
@property (nonatomic, copy) NSString *maneuverName;
//距离下一个机动点距离（以米为单位）
@property (nonatomic, assign) NSInteger distance;
//下一路名
@property (nonatomic, copy) NSString *nextRoadName;
//经度
@property (nonatomic, assign) double longitude;
//纬度
@property (nonatomic, assign) double latitude;
//路口环岛信息(0为不是环路，1为环路入口，2为环路出口)
@property (nonatomic, assign) NSInteger ringFlag;
//车道总数
@property (nonatomic, assign) NSInteger laneCount;
//车道整体增加类型
@property (nonatomic, assign) NSInteger laneTotalAddType;
//车道增加类型数组，对应BNHUDNavi_Lane_Increase_Type枚举
@property (nonatomic, strong) NSArray *laneAdditionTypeArray;
//车道转向标数组，对应BNHUDNavi_Lane_Type枚举
@property (nonatomic, strong) NSArray *laneSignTypeArray;

@end

@interface BNHUDServiceArea : NSObject

//服务区的名字
@property (nonatomic, copy) NSString *serviceArea;
//服务区的距离，当distance为0或者serviceArea为空时，表明服务区消失
@property (nonatomic, assign) NSInteger distance;

@end

@interface BNHUDAssistant : NSObject
//辅助诱导更新类型
@property (nonatomic, assign) BNHUDNaviAssistant_Update_Type assistUpdateType;
//辅助诱导类型
@property (nonatomic, assign) BNHUDNaviAssistant_Type_Enum assistantType;
//当类型是SpeedCamera和IntervalCamera的时候，会带有限速的值
@property (nonatomic, assign) NSInteger limitedSpeed;
// 诱导距离(以米位单位),当距离为0时，表明这个诱导丢失消失
@property (nonatomic, assign) NSInteger distance;

@end

@interface BNHUDRemainInfo : NSObject

//到达目的地的剩余距离（以米为单位）
@property (nonatomic, assign) NSInteger remainDistance;
// 到达目的地的剩余时间(以秒为单位)
@property (nonatomic, assign) NSInteger remainTime;

@end

@interface BNHUDCurrentRoad : NSObject

//当前路名
@property (nonatomic, copy) NSString *currentRoadName;

@end

@interface BNHUDEnlargeRoad : NSObject

//放大图显示状态，0：显示，1：更新信息，2：隐藏
@property (nonatomic, assign) NSInteger enlargeShowState;
//放大图类型，0：栅格放大图，1：矢量放大图，2：方向看板，3：目的地街景
@property (nonatomic, assign) NSInteger enlargeType;
//背景图
@property (nonatomic, strong) UIImage *enlargeBasicImage;
//箭头图
@property (nonatomic, strong) UIImage *enlargeArrowImage;
//总距离
@property (nonatomic, assign) NSInteger enlargeTotalDist;
//剩余距离
@property (nonatomic, assign) NSInteger enlargeRemainDist;
//路名
@property (nonatomic, copy) NSString *nextRoadName;
//车标当前位置经度
@property (nonatomic, assign) double carPosX;
//车标当前位置纬度
@property (nonatomic, assign) double carPosY;
//车标当前位置方向
@property (nonatomic, assign) NSInteger carPosRotate;
//车标上一位置经度
@property (nonatomic, assign) double lastCarPosX;
//车标上一位置纬度
@property (nonatomic, assign) double lastCarPosY;
//车标上一位置方向
@property (nonatomic, assign) NSInteger lastCarPosRotate;

@end

@interface BNHUDDestinationInfo : NSObject

//目的地经度
@property (nonatomic, assign) double longitude;
//目的地纬度
@property (nonatomic, assign) double latitude;
//目的地总距离
@property (nonatomic, assign) NSInteger destTotalDist;
//目的地图标
@property (nonatomic, copy) NSString *destIcon;

@end

@interface BNHUDCarPointInfo : NSObject

//经度
@property (nonatomic, assign) double longitude;
//纬度
@property (nonatomic, assign) double latitude;
//速度
@property (nonatomic, assign) NSInteger speed;
//角度
@property (nonatomic, assign) NSInteger angle;

@end

@interface BNHUDCarFreeStatus : NSObject

//是否车标自由
@property (nonatomic, assign) BOOL isCarFree;

@end

@interface BNHUDCarInTunnelStatus : NSObject

//是否在隧道中
@property (nonatomic, assign) BOOL isInTunnel;

@end

@interface BNHUDShapeInfo : NSObject

//当前车点在整个路线中的位置
@property (nonatomic, assign) NSInteger shapeIndex;
//距出发点的路线距离
@property (nonatomic, assign) NSInteger distance;

@end

@interface BNHUDARRouteNode : NSObject

//经度
@property (nonatomic, assign) double longitude;
//纬度
@property (nonatomic, assign) double latitude;
//该形状点距出发点距离
@property (nonatomic, assign) NSInteger fromStartDist;
//该形状点之前路段的类型
@property (nonatomic, assign) BNHUDNavi_Route_Type routeType;

@end

@interface BNHUDTimeLine : NSObject

//时间段上路段的类型（高速/主路/local road内部路）
@property (nonatomic, assign) BNHUDNavi_Route_Type routeType;
//时间段起点距出发点的剩余路线距离
@property (nonatomic, assign) NSInteger fromStartPointDistance;
//时间段终点距出发点的剩余路线距离
@property (nonatomic, assign) NSInteger fromEndPointDistance;

@end

@interface BNHUDTrafficRestrict : NSObject

//经度
@property (nonatomic, assign) double longitude;
//纬度
@property (nonatomic, assign) double latitude;
//距离出发点的路线距离
@property (nonatomic, assign) NSInteger distance;
//类型（前方道路变窄/前方路滑/etc）
@property (nonatomic, assign) BNHUDNavi_Restrict_Type restrictType;

@end

@interface BNHUDRouteInfo : NSObject

//路线ID
@property (nonatomic, assign) NSInteger routeID;
//整个路线上的所有形状点数据列表，存放的BNHUDARRouteNode对象
@property (nonatomic, strong) NSArray *arRoutList;
//整个路线上的所有时间线数据列表，存放的BNHUDTimeLine对象
@property (nonatomic, strong) NSArray *timeLineList;
//整个路线上的所有限制性信息列表，存放的BNHUDTrafficRestrict对象
@property (nonatomic, strong) NSArray *restritInfoList;

@end

@interface BNHUDCamera : NSObject

//经度
@property (nonatomic, assign) double longitude;
//纬度
@property (nonatomic, assign) double latitude;
//摄像头类型（高速/其他）
@property (nonatomic, assign) BNHUDNavi_Camera_Type cameraType;

@end

@interface BNHUDNearbyCamera : NSObject

//附近摄像头列表，存放的BNHUDCamera对象
@property (nonatomic, strong) NSArray *cameraList;

@end

