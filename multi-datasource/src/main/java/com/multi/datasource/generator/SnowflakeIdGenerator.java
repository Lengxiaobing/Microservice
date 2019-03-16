package com.multi.datasource.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.security.SecureRandom;

/**
 * 雪花算法id生成器
 *
 * @author zx
 */
public class SnowflakeIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        return SnowflakeWorker.SNOWFLAKE_WORKER.nextId();
    }

    /**
     * @return SnowflakeWorker内部类实例
     */
    public static SnowflakeWorker getSnowflakeWorker() {
        return SnowflakeWorker.SNOWFLAKE_WORKER;
    }

    /**
     * SnowFlake由1位标志位，timeBit位时间差位，workerBit位机器位，startBit位启动位，sequenceBit位序列位合计64位组成，恰好为一个Long型。
     * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生id碰撞，效率较高，可自由配置，非常灵活。
     * SnowFlake的缺点是，强依赖机器时钟，如果机器上时钟回拨，会导致发号重复或者服务会处于不可用状态。
     *
     * @author Twitter
     */
    public static class SnowflakeWorker {
        /**
         * 雪花算法实例
         */
        private static final SnowflakeWorker SNOWFLAKE_WORKER = new SnowflakeWorker(0, 0);
        /**
         * 开始时间截 (2018-01-01 00:00:00)
         */
        private static final long START_TIME = 1514736000000L;
        /**
         * 序列id所占的位数
         */
        private static final long SEQUENCE_BIT = 12L;
        /**
         * 业务id所占的位数
         */
        private static final long BUS_BIT = 6L;
        /**
         * 工作id所占的位数
         */
        private static final long WORK_BIT = 4L;
        /**
         * 序列id的掩码
         */
        private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BIT);
        /**
         * 业务id的掩码
         */
        private static final long BUS_MASK = ~(-1L << BUS_BIT);
        /**
         * 工作id的掩码
         */
        private static final long WORK_MASK = ~(-1L << WORK_BIT);
        /**
         * 业务id向左移位数
         */
        private static final long BUS_LEFT = SEQUENCE_BIT;
        /**
         * 机器id向左移位数
         */
        private static final long WORK_LEFT = SEQUENCE_BIT + BUS_BIT;
        /**
         * 时间差向左移位数
         */
        private static final long TIME_LEFT = SEQUENCE_BIT + BUS_BIT + WORK_BIT;
        /**
         * 随机尾数生成器
         */
        private static final SecureRandom RANDOM = new SecureRandom();
        /**
         * 机器id
         */
        private final long workerId;
        /**
         * 启动id
         */
        private final long busId;
        /**
         * 毫秒内序列
         */
        private long sequence = 0L;
        /**
         * 上次生成id的时间截
         */
        private long lastTime = -1L;

        /**
         * @param workerId 工作id
         * @param busId    业务id
         */
        public SnowflakeWorker(long workerId, long busId) {
            setConfig(workerId, busId);
            this.workerId = workerId;
            this.busId = busId;
        }

        /**
         * 修改算法工作id和业务id
         *
         * @param workerId 工作id
         * @param busId    业务id
         */
        public void setConfig(long workerId, long busId) {
            if (lastTime > 0) {
                throw new IllegalStateException("已经派发Id，无法修改配置信息");
            }
            if (workerId > WORK_MASK || workerId < 0) {
                throw new IllegalArgumentException(String.format("工作Id不能大于%d或小于0", WORK_MASK));
            }
            if (busId > BUS_MASK || busId < 0) {
                throw new IllegalArgumentException(String.format("业务Id不能大于%d或小于0", BUS_MASK));
            }
        }

        /**
         * 根据snowflakeId解析其生成时间
         *
         * @param snowflakeId snowflakeId
         * @return snowflakeId生成时间
         */
        public static long parseTime(long snowflakeId) {
            return (snowflakeId >> TIME_LEFT) + START_TIME;
        }

        /**
         * 获得下一个id
         *
         * @return SnowflakeId
         */
        public synchronized long nextId() {
            long timestamp = timeGen();
            //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
            if (timestamp < lastTime) {
                throw new RuntimeException(String.format("时钟回拨，%d微秒内停止发放id", lastTime - timestamp));
            }
            //如果是同一时间生成的，则进行毫秒内序列
            if (lastTime == timestamp) {
                sequence = (sequence + 1) & SEQUENCE_MASK;
                //毫秒内序列溢出,阻塞到下一个毫秒,获得新的时间戳
                if (sequence == 0) {
                    timestamp = tilNextMillis(lastTime);
                }
            } else {
                //时间戳改变，毫秒内序列重置
                sequence = RANDOM.nextInt(10);
            }
            //上次生成id的时间截
            lastTime = timestamp;
            //移位并通过或运算拼到一起组成64位的id
            return ((timestamp - START_TIME) << TIME_LEFT) | (workerId << WORK_LEFT) | (busId << BUS_LEFT) | sequence;
        }

        /**
         * 阻塞到下一个毫秒，直到获得新的时间戳
         *
         * @param lastTime 上次生成id的时间截
         * @return 当前时间戳
         */
        private long tilNextMillis(long lastTime) {
            long timestamp = timeGen();
            while (timestamp <= lastTime) {
                timestamp = timeGen();
            }
            return timestamp;
        }

        /**
         * 返回以毫秒为单位的当前时间
         *
         * @return 当前时间(毫秒)
         */
        private static long timeGen() {
            return System.currentTimeMillis();
        }
    }
}