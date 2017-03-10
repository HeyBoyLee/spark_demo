include "miliao_shared.thrift"

namespace java com.xiaomi.data.ctr.thrift.service
/**
 * appstore ctr api service interface
 */
service AppStoreCTRApiService extends miliao_shared.MiliaoSharedService {
    /*
     * 添加一个map进入monitor
     */
    void insertMonitorItem(1: map<string,i32> itemMap);
}