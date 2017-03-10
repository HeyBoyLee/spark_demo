/**
 * user profile data service thrift interface, which aims to extract user
 * attributes from the table
 * @author wanggang1@xiaomi.com
 */
include "miliao_shared.thrift"

namespace java com.xiaomi.miliao.thrift.userprofile.service

service UserProfileDataService extends miliao_shared.MiliaoSharedService {
    /**
     * Inputs:
     * 1. xiaomiId or imeiMd5: the key to extract the attribute
     * 2. attribute: attribute name, which is defined in
     *    OLAPTableConstants.java or DeviceTableConstants.java
     *
     * Return:
     * the value of the specified user attribute
     */

    string getUserAttri(1: string xiaomiId, 2: string attribute);

    list<string> getUserListAttri(1: list<string> xiaomiIds, 2: string attribute);

    string getDeviceAttri(1: string imeiMd5, 2: string attribute);

    list<string> getDeviceListAttri(1: list<string> imeiMd5, 2: string attribute);

}
