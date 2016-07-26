//
//  BNHUDListenerProtocol.h
//  HUDSDK
//
//  Created by baidu on 15/6/1.
//  Copyright (c) 2015年 baidu. All rights reserved.
//

#ifndef HUDSDK_BNHUDProtocols_h
#define HUDSDK_BNHUDProtocols_h

#import "BNHUDDefines.h"
#import "BNHUDMessages.h"


@protocol BNHUDListenerProtocol <NSObject>

@optional

/**
 * @brief 导航机动点更新
 *
 * @param maneuver 机动点信息实体对象
 *
 */
- (void)onChangedWithManeuver:(BNHUDManeuver *)maneuver;

/**
 * @brief 服务区更新回调
 *
 * @param serviceArea 服务区信息实体对象
 *
 */
- (void)onChangedWithServiceArea:(BNHUDServiceArea *)serviceArea;

/**
 * @brief 辅助诱导图标更新回调
 *
 * @param assistant 辅助诱导信息实体对象
 *
 */
- (void)onChangedWithAssistant:(BNHUDAssistant *)assistant;

/**
 * @brief 到达目的地的距离和时间更新
 *
 * @param remainInfo 剩余信息实体对象
 *
 */
- (void)onChangedWithRemainInfo:(BNHUDRemainInfo *)remainInfo;

/**
 * @brief 当前道路名更新
 *
 * @param currentRoad 当前路名实体对象
 */
- (void)onChangedWithCurrentRoad:(BNHUDCurrentRoad *)currentRoad;

/**
 * @brief gps丢失
 */
- (void)onGPSLost;

/**
 * @brief gps正常
 */
- (void)onGPSNormal;

/**
 * @brief 开始导航
 */
- (void)onNaviStart;

/**
 * @brief 结束导航
 */
- (void)onNaviEnd;

/**
 * @brief 电子狗模式开启
 */
- (void)onCruiseStart;

/**
 * @brief 电子狗模式结束
 */
- (void)onCruiseEnd;

/**
 * @brief 导航中偏航
 */
- (void)onRoutePlanYawing;

/**
 * @brief 导航中偏航结束,重新算路成功
 */
- (void)onReRoutePlanComplete;

/**
 * @brief 路口放大图更新
 *
 * @param enlargeRoad 路口放大图对象
 */
- (void)onChangedWithEnlargeRoad:(BNHUDEnlargeRoad *)enlargeRoad;

/**
 * @brief 目的地更新，在开始导航和偏航后更新
 *
 * @param destionationInfo 目的地信息对象
 */
- (void)onChangedWithDestinationInfo:(BNHUDDestinationInfo *)destionationInfo;

/**
 * @brief 车点信息更新
 *
 * @param destionationInfo 车点信息对象
 */
- (void)onChangedWithCarPointInfo:(BNHUDCarPointInfo *)carPointInfo;

/**
 * @brief 车是否处于自由态状态更新
 *
 * @param carFreeStatus 车点自由态信息对象
 */
- (void)onChangedWithCarFreeStatus:(BNHUDCarFreeStatus *)carFreeStatus;

/**
 * @brief 车是否在隧道态状态更新
 *
 * @param carFreeStatus 车点是否在隧道信息对象
 */
- (void)onChangedWithTunnelUpdate:(BNHUDCarInTunnelStatus *)inTunnelStatus;


/**
 * @brief 路线信息更新
 *
 * @param carFreeStatus 路线信息对象
 */
- (void)onChangedWithRountInfo:(BNHUDRouteInfo *)routeInfo;

/**
 * @brief 形状点信息更新
 *
 * @param carFreeStatus 形状点信息对象
 */
- (void)onChangedWithShapeInfo:(BNHUDShapeInfo *)shapeInfo;

/**
 * @brief 附近摄像头信息更新
 *
 * @param carFreeStatus 附近摄像头信息对象
 */
- (void)onChangeWithNearbyCamera:(BNHUDNearbyCamera *)nearbyCamera;

@end


#endif
