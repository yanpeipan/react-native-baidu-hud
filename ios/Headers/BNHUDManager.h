//
//  BNHUDManager.h
//  HUDSDK
//
//  Created by baidu on 15/6/1.
//  Copyright (c) 2015年 baidu. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "BNHUDProtocols.h"

typedef void(^LinkerSuccessBlock)();
typedef void(^LinkerErrorBlock)(NSError *error);

@interface BNHUDManager : NSObject

/*
 * @brief 创建HUD SDK 单例
 */
+ (BNHUDManager *)shareInstance;

/*
 * @brief 释放HUD SDK 单例
 */
+ (void)releaseInstance;

/**
 *  初始化服务需要在AppDelegate的 application:didFinishLaunchingWithOptions:
 *  中调用
 *
 *  @param ak AppKey
 */
- (void)initServices:(NSString *)ak;

/*
 * @brief 设置导航事件监听器
 * @param listener 导航对应的事件监听器
 */
- (void)setBNEventListener:(id<BNHUDListenerProtocol>)listener;

/*
 * @brief 连接服务，为本地连接
 */
- (void)connectToBNService;

/*
 * @brief 连接对应iP地址的服务
 * @param ip server端ip地址
 */
- (void)connectToBNServiceWithIP:(NSString *)ip;

/*
 * @brief 断开远程服务
 */
- (void)disConnectToBNService;

/*
 * @brief 连接远程服务，必须在连接状态才会有对应回调
 * @param successBlock 连接远程服务成功回调
 * @param failBlock 连接远程服务错误回调，此时已与远程服务断开
 */
- (void)connectRemoteServiceSuccess:(LinkerSuccessBlock)successBlock
           connectRemoteserviceFail:(LinkerErrorBlock)failBlock;



@end
