namespace java com.geofence.log

enum MetokLogCategory {
    normal=0,
    access=1,
    request=2,
    response=3,
    record=4,
    business=5,
}

struct MetokLogInfo {
    1: optional string logLevel;//log level: debug|info|warn|error
    2: optional MetokLogCategory logCategory;//log category: value of MetokLogCategory
}

struct MetokApiInfo {
    1: optional string serverHost;//server host
    2: optional string serverGroup;//serverGroup
    3: optional string serverMode;//server start mode: debug, staging, preview, production
    4: optional string apiName;//api short name
    5: optional string apiVersion;//api version
    6: optional string restUri;//rest uri
    7: optional string method;//method
}

struct ClientInfo {
    1: optional string clientIp;//remote client ip
    2: optional string imeiMd5;//imei md5
    3: optional string userAgent;//user agent
}

struct ServerAccessLog {
    1: optional string time;//time
    2: optional string uuid;//request uuid
    3: optional MetokApiInfo apiInfo;// api info
    4: optional ClientInfo clientInfo;// client info
    5: optional string requestUrl;//method restUri http-version
    6: optional i32 status;//http status
    7: optional double responseTime;// response time
    8: optional double responseLength;//response length
    98: optional MetokLogInfo logInfo;// log info
    99: optional map<string ,string> extra={};//extra
    100: optional bool test = 0;//is test
}
