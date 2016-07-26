/**
 * @providesModule HUD
 * @flow
 */
'use strict';

import {NativeModules} from 'react-native';

const {BaiduHUDManager} = NativeModules;

/**
 * High-level docs for the RNBaiduHUD iOS API can be written here.
 */

var HUD = {
  ...BaiduHUDManager,
};

module.exports = HUD;
