package com.luojilab.datadev.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * user表分库的逻辑函数
 */
public class StudentSingleKeyDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

    private static final Logger logger = LogManager.getLogger(StudentSingleKeyDatabaseShardingAlgorithm.class);

    /**
     * sql 中关键字 匹配符为 =的时候，表的路由函数
     */
    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        logger.info("shardingValue: " + shardingValue + ", availableTargetNames: " + availableTargetNames);
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % 2 + "")) { // 以0或1结尾
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * sql 中关键字 匹配符为 in 的时候，表的路由函数
     */
    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        logger.info("shardingValue: " + shardingValue + ", availableTargetNames: " + availableTargetNames);
        Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(value % 2 + "")) { // 以0或1结尾
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    /**
     * sql 中关键字 匹配符为 between的时候，表的路由函数
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
                                                ShardingValue<Integer> shardingValue) {
        logger.info("shardingValue: " + shardingValue + ", availableTargetNames: " + availableTargetNames);
        Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
        Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % 2 + "")) { // 以0或1结尾
                    result.add(each);
                }
            }
        }
        return result;
    }

}