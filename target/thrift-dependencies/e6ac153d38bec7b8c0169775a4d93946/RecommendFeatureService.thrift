/**
 *  recommend extract feature service
 *  @author wangjinlong
 */
include "miliao_shared.thrift"
namespace java com.xiaomi.data.recommend.feature.service

struct Impression {
    1:optional i64 exposeNum;
    2:optional i64 clickNum;
    3:optional i64 viewNum;
    4:optional double duration;
}
service RecommendExtractFeature  extends miliao_shared.MiliaoSharedService {
    /**
     *  get specify the docIds of total impression
     *  @param docId
     *  @return Map<String,Impression> k
     */
    map<string,Impression> getTotalImpression(1:list<string> docIds);
    /**
     *  get specify the docIds of realtime impression
     *  @param docId
     *  @return map<String,Impression>
     */
    map<string,Impression>  getRealTimeImpression(1:list<string> docIds);
}