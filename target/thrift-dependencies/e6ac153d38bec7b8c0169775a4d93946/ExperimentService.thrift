/**
 * experiment service platform thrift interface
 * @author zhujian@xiaomi.com
 */
include "miliao_shared.thrift"

namespace java com.xiaomi.miliao.thrift.experiment.service

service ExperimentService extends miliao_shared.MiliaoSharedService {
    /**
     * receive:
     * 1) application id(appId: defined by experiment management platform)
     * 2) layer id(layerId: defined by experiment management platform)
     * 3) user id(userId: defined by application, it may be 'cookie'/'imei'/
     *    'xiaomid', but only one type can be used for one experiment.
     *
     * return:
     * "appId^layerId^expId"
     * expId: defined by experiment management platform, represents
     * <appId, layerId, userId> belongs to which experiment, default value is
     * 0.
     *
     * eg.
     * experiment client call server with: <0, 1, "1234">, experiment server
     * use this information, and find this user's expid is 37, then returns:
     * "0^1^37"
     */
    string getExpId(1: i32 appId, 2: i32 layerId, 3: string userId);
}
