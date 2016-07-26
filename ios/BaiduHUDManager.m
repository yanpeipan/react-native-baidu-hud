#import "BaiduHUDManager.h"
#import "RCTEventDispatcher.h"

@implementation BaiduHUDManager
@synthesize bridge = _bridge;


RCT_EXPORT_MODULE()


- (void)dealloc
{
    [BNHUDManager releaseInstance];
}

RCT_EXPORT_METHOD(initServices:(NSString*)ak) {
    [[BNHUDManager shareInstance] initServices:ak];
    BNHUDManager *hudManager = [BNHUDManager shareInstance];
    [hudManager connectRemoteServiceSuccess:^{
        [hudManager setBNEventListener:self];
    } connectRemoteserviceFail:^(NSError *error) {
        if (error.code == BN_HUD_SDK_SERVER_OFFLINE_ERROR) {
        }
    }];
}


RCT_EXPORT_METHOD(connect) {
    [[BNHUDManager shareInstance] connectToBNService];
}

RCT_EXPORT_METHOD(disconnect) {
    [[BNHUDManager shareInstance] disConnectToBNService];
}


/**
 * @brief 导航机动点更新
 *
 * @param maneuver 机动点信息实体对象
 *
 */
- (void)onChangedWithManeuver:(BNHUDManeuver *)maneuver
{
    NSString *imageName = [NSString stringWithFormat:@"hud_%@",maneuver.maneuverName];
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangedManeuver",
                                                        @"body": @{
                                                                @"maneuverId": @(maneuver.maneuverId),
                                                                @"straight": @(maneuver.straight),
                                                                @"maneuverName": maneuver.maneuverName,
                                                                @"distance": @(maneuver.distance),
                                                                @"nextRoadName": maneuver.nextRoadName,
                                                                @"longitude": @(maneuver.longitude),
                                                                @"latitude": @(maneuver.latitude),
                                                                @"ringFlag": @(maneuver.ringFlag),
                                                                @"laneCount": @(maneuver.laneCount),
                                                                @"laneTotalAddType": @(maneuver.laneTotalAddType),
                                                                @"laneAdditionTypeArray": maneuver.laneAdditionTypeArray,
                                                                @"laneSignTypeArray": maneuver.laneSignTypeArray,

                                                                },
                                                        @"imageName": imageName,
                                                        }];
    
}

/**
 * @brief 服务区更新回调
 *
 * @param serviceArea 服务区信息实体对象
 *
 */
- (void)onChangedWithServiceArea:(BNHUDServiceArea *)serviceArea
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangedServiceArea",
                                                        @"body": @{
                                                                @"serviceArea": serviceArea.serviceArea,
                                                                @"distance": @(serviceArea.distance),
                                                                },
                                                        }];
}

/**
 * @brief 辅助诱导图标更新回调
 *
 * @param assistant 辅助诱导信息实体对象
 *
 */
- (void)onChangedWithAssistant:(BNHUDAssistant *)assistant
{

    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangedAssistant",
                                                        @"body": @{
                                                                @"assistUpdateType": @(assistant.assistUpdateType),
                                                                @"assistantType": @(assistant.assistantType),
                                                                @"limitedSpeed": @(assistant.limitedSpeed),
                                                                @"distance": @(assistant.distance),
                                                                },
                                                        }];
}

/**
 * @brief 到达目的地的距离和时间更新
 *
 * @param remainInfo 剩余信息实体对象
 *
 */
- (void)onChangedWithRemainInfo:(BNHUDRemainInfo *)remainInfo
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangedRemainInfo",
                                                        @"body": @{
                                                                @"remainDistance": @(remainInfo.remainDistance),
                                                                @"remainTime": @(remainInfo.remainTime)
                                                                },
                                                        }];
}

/**
 * @brief 当前道路名更新
 *
 * @param currentRoad 当前路名实体对象
 */
- (void)onChangedWithCurrentRoad:(BNHUDCurrentRoad *)currentRoad
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangedWithCurrentRoad",
                                                        @"body": currentRoad.currentRoadName,
                                                        }];
}

/**
 * @brief gps丢失
 */
- (void)onGPSLost
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{@"event": @"onGPSLost"}];
}

/**
 * @brief gps正常
 */
- (void)onGPSNormal
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{@"event": @"onGPSNormal"}];
}

/**
 * @brief 开始导航
 */
- (void)onNaviStart
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{@"event": @"onNaviStart"}];
}

/**
 * @brief 结束导航
 */
- (void)onNaviEnd
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{@"event": @"onNaviEnd"}];
}

/**
 * @brief 电子狗模式开启
 */
- (void)onCruiseStart
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{@"event": @"onCruiseStart"}];
}

/**
 * @brief 电子狗模式结束
 */
- (void)onCruiseEnd
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{@"event": @"onCruiseEnd"}];
}

/**
 * @brief 导航中偏航
 */
- (void)onRoutePlanYawing
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{@"event": @"onRoutePlanYawing"}];
}

/**
 * @brief 导航中偏航结束,重新算路成功
 */
- (void)onReRoutePlanComplete
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{@"event": @"onRoutePlanYawing"}];
}

/**
 * @brief 路口放大图更新
 *
 * @param enlargeRoad 路口放大图对象
 */
- (void)onChangedWithEnlargeRoad:(BNHUDEnlargeRoad *)enlargeRoad
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onEnlargeRoad",
                                                        @"body": @{
                                                                @"enlargeShowState": @(enlargeRoad.enlargeShowState),
                                                                @"enlargeType": @(enlargeRoad.enlargeType),
                                                                @"enlargeBasicImage": enlargeRoad.enlargeBasicImage,
                                                                @"enlargeArrowImage": enlargeRoad.enlargeArrowImage,
                                                                @"enlargeTotalDist": @(enlargeRoad.enlargeTotalDist),
                                                                @"enlargeRemainDist": @(enlargeRoad.enlargeRemainDist),
                                                                @"nextRoadName": enlargeRoad.nextRoadName,
                                                                @"carPosX": @(enlargeRoad.carPosX),
                                                                @"carPosY": @(enlargeRoad.carPosY),
                                                                @"carPosRotate": @(enlargeRoad.carPosRotate),
                                                                @"lastCarPosX": @(enlargeRoad.lastCarPosX),
                                                                @"lastCarPosY": @(enlargeRoad.lastCarPosY),
                                                                @"lastCarPosRotate": @(enlargeRoad.lastCarPosRotate),
                                                                },
                                                        }];
}

/**
 * @brief 目的地更新，在开始导航和偏航后更新
 *
 * @param destionationInfo 目的地信息对象
 */
- (void)onChangedWithDestinationInfo:(BNHUDDestinationInfo *)destionationInfo
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangedWithDestinationInfo",
                                                        @"body": @{
                                                                @"longitude": @(destionationInfo.longitude),
                                                                @"latitude": @(destionationInfo.latitude),
                                                                @"destTotalDist": @(destionationInfo.destTotalDist),
                                                                @"destIcon": destionationInfo.destIcon,
                                                                },
                                                        }];
}

/**
 * @brief 车点信息更新
 *
 * @param destionationInfo 车点信息对象
 */
- (void)onChangedWithCarPointInfo:(BNHUDCarPointInfo *)carPointInfo
{

    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangedCarPointInfo",
                                                        @"body": @{
                                                                @"longitude": @(carPointInfo.longitude),
                                                                @"latitude": @(carPointInfo.latitude),
                                                                @"speed": @(carPointInfo.speed),
                                                                @"angle": @(carPointInfo.angle),
                                                                },
                                                        }];
}

/**
 * @brief 车是否处于自由态状态更新
 *
 * @param carFreeStatus 车点自由态信息对象
 */
- (void)onChangedWithCarFreeStatus:(BNHUDCarFreeStatus *)carFreeStatus
{

    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangedCarFreeStatus",
                                                        @"body": @(carFreeStatus.isCarFree),
                                                        }];
}

/**
 * @brief 车是否在隧道态状态更新
 *
 * @param carFreeStatus 车点是否在隧道信息对象
 */
- (void)onChangedWithTunnelUpdate:(BNHUDCarInTunnelStatus *)inTunnelStatus
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangedWithTunnelUpdate",
                                                        @"body": @(inTunnelStatus.isInTunnel),
                                                        }];
}


/**
 * @brief 路线信息更新
 *
 * @param carFreeStatus 路线信息对象
 */
- (void)onChangedWithRountInfo:(BNHUDRouteInfo *)routeInfo
{

    
}

/**
 * @brief 形状点信息更新
 *
 * @param carFreeStatus 形状点信息对象
 */
- (void)onChangedWithShapeInfo:(BNHUDShapeInfo *)shapeInfo
{
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangedWithShapeInfo",
                                                        @"body": @{
                                                                @"shapeIndex": @(shapeInfo.shapeIndex),
                                                                @"distance": @(shapeInfo.distance),
                                                                },
                                                        }];
}

/**
 * @brief 附近摄像头信息更新
 *
 * @param carFreeStatus 附近摄像头信息对象
 */
- (void)onChangeWithNearbyCamera:(BNHUDNearbyCamera *)nearbyCamera
{
    NSMutableArray *nearbyCameraList = [[NSMutableArray alloc] init];
    
    for (BNHUDCamera *camera in nearbyCamera.cameraList) {
        [nearbyCameraList addObject:@{
                           @"longitude": @(camera.longitude),
                           @"latitude": @(camera.latitude),
                           @"cameraType": @(camera.cameraType),

                           }];
    }
    [self.bridge.eventDispatcher sendAppEventWithName:@"hud"
                                                 body:@{
                                                        @"event": @"onChangeWithNearbyCamera",
                                                        @"body": nearbyCameraList,
                                                        }];
}


@end
