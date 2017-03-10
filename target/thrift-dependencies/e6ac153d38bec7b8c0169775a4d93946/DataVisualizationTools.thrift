include "miliao_shared.thrift"
/**
 * 数据可视化工具thrift接口
 * @author 王金龙
 */

namespace java com.xiaomi.miliao.thrift.datavisualization

service DataVisualizationToolsService extends miliao_shared.MiliaoSharedService {
      bool addData(1: i32 type, 2: string subType, 3: string name, 4: string count1, 5: string count2, 6: i64 timestamp, 7: string validate);
}
