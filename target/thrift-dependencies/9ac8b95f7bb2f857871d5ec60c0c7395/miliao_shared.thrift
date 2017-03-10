/**
 *
 */

namespace java com.xiaomi.miliao.thrift
namespace cpp  com.xiaomi.miliao.thrift

const i16 PERF_COUNTER_VAL_TYPE_LONG = 0;
const i16 PERF_COUNTER_VAL_TYPE_DOUBLE = 1;
const i16 PERF_COUNTER_VAL_TYPE_STRING = 2;

struct PerfCounterVal {
	1: required i16 type,	// 0 - Long, 1 - Double, 2 - String
	2: optional i64 longVal,
	3: optional double doubleVal,
	4: optional string stringVal,
}

/**
 * This a base service for all Miliao Thrift Service. All other services should extend this service.
 */
service MiliaoSharedService {
   /**
    * Returns a descriptive name of the service
    */
   string getName(),

   /**
    * Gets the counters for this service
    */
   map<string, i64> getCounters(),

   /**
    * Gets the counters start with prefix for this service
    */
   map<string, i64> getCountersByCategory(1: string prefix),

   /**
    * Gets the names of counters for this service
    */
   list<string> getCounterNames(),

   /**
    * Gets the value of a single counter
    */
   i64 getCounter(1: string key),
   
   /**
    * Returns the unix time that the server has been running since
    */
   i64 aliveSince(),

   /**
    * Suggest a stop to the server
    */
   oneway void shutdown(),

   /**
    * Dynamically set the logger level. if name is null or empty, set the root logger.
    */
   oneway void setLogLevel(1: string name, 2: string level),
   
   /*
    * Get performance counters
    */
   map<string, PerfCounterVal> getPerfCounters(),
}
