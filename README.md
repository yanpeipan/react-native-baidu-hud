### onChangedAssistant 诱导辅助 [iOS](http://jianshu.io)

name                  | type   | description
----------------------|--------|-------------------
event                 | string | onChangedAssistant
body                  | Object | assistant
body.assistUpdateType | String | assistUpdateType
body.assistantType    | String | assistantType
body.limitedSpeed     | String | limitedSpeed
body.distance         | String | distance

### onChangedManeuver 机动点

name                       | type   | description
---------------------------|--------|------------------
event                      | string | onChangedManeuver
body                       | Object | maneuver
body.maneuverId            | string |
body.straight              |        |
body.maneuverName          |        |
body.distance              |        |
body.nextRoadName          |        |
body.longitude             |        |
body.latitude              |        |
body.ringFlag              |        |
body.laneCount             |        |
body.laneTotalAddType      |        |
body.laneAdditionTypeArray |        |
body.laneSignTypeArray     |        |

### onChangedServiceArea 服务区

name             | type   | descption
-----------------|--------|---------------------
event            | string | onChangedServiceArea
body.serviceArea | string |
body.distance    | string |

### onChangedRemainInfo 目的地剩余信息
