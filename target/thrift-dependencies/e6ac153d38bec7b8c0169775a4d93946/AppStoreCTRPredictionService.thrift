include "miliao_shared.thrift"

namespace java com.xiaomi.data.ctr.thrift.service

// 用户信息
struct ClientInfo {
    1: string imei;
    2: string expId;
    3: optional string ip;
}

// 广告信息
struct AdInfo {
    1: i64 adId;
}

// 广告打分结果
struct Result {
    1: map<i64, double> scores;
}

/**
 * appstore ctr prediction service interface
 */
service AppStoreCTRPredictionService extends miliao_shared.MiliaoSharedService {
    // 打分接口
    Result score(1: ClientInfo clientInfo, 2: list<AdInfo> adInfos);
    // 更新cache接口,用于测试，及快速fix cache数据用
    void updateCache();
}
