/**
 * experiment service platform thrift interface
 * @author zhujian@xiaomi.com
 */
include "miliao_shared.thrift"

namespace java com.xiaomi.data.thrift.keywords.extract.service

service KeywordsExtractService extends miliao_shared.MiliaoSharedService {
    //提取句子中的关键词
    string extract(1: string statement);
}
