package com.multi.datasource.generator;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;
import java.util.UUID;

/**
 * 自定义uuid生成器
 *
 * @author zx
 */
public class UniqueIdGenerator implements IdentifierGenerator, Configurable {
    /**
     * UniqueIdGenerator编码器
     */
    AbstractEncoder encoder;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        String coding = String.valueOf(params.getProperty("coding")).toUpperCase();
        switch (coding) {
            case "COMPRESS":
                encoder = CompressEncoder.ENCODER;
                break;
            case "BASE64":
                encoder = Base64Encoder.ENCODER;
                break;
            case "BASE58":
                encoder = Base58Encoder.ENCODER;
                break;
            case "INTEGER":
                encoder = IntegerEncoder.ENCODER;
                break;
            default:
                throw new IllegalStateException("UniqueIdGenerator编码错误");
        }
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        UUID uuid = UUID.randomUUID();
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        return encoder.encode(buffer.array());
    }


    /**
     * 编码器抽象类
     *
     * @param <T> 编码结果类型
     */
    static abstract class AbstractEncoder<T extends Serializable> {
        /**
         * 对字节数组进行编码
         *
         * @param bytes 要编码的字节数组
         * @return 编码结果
         */
        abstract T encode(byte[] bytes);
    }

    /**
     * 编码器BigInteger实现
     */
    static final class IntegerEncoder extends AbstractEncoder<BigInteger> {
        /**
         * IntegerEncoder实例
         */
        static final IntegerEncoder ENCODER = new IntegerEncoder();

        @Override
        BigInteger encode(byte[] bytes) {
            return new BigInteger(1, bytes);
        }
    }

    /**
     * 编码器Compress实现
     */
    static final class CompressEncoder extends AbstractEncoder<String> {
        /**
         * CompressEncoder实例
         */
        static final CompressEncoder ENCODER = new CompressEncoder();

        @Override
        String encode(byte[] bytes) {
            return new BigInteger(1, bytes).toString(16);
        }
    }

    /**
     * 编码器Base64实现
     */
    static class Base64Encoder extends AbstractEncoder<String> {
        private static final Base64.Encoder BASE64 = Base64.getUrlEncoder().withoutPadding();
        /**
         * Base64Encoder实例
         */
        static final IntegerEncoder ENCODER = new IntegerEncoder();

        @Override
        String encode(byte[] bytes) {
            return BASE64.encodeToString(bytes);
        }
    }

    /**
     * 编码器Base58实现
     */
    static class Base58Encoder extends AbstractEncoder<String> {
        /**
         * 字母表
         */
        private static final char[] CHAR_ARRAY = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
        /**
         * char在字母表中位置
         */
        private static final int[] CHAR_INDEX = new int[128];

        static {
            // 将ascii码在字母表中的索引放到数组中
            for (int i = 0; i < CHAR_INDEX.length; i++) {
                CHAR_INDEX[i] = -1;
            }
            for (int i = 0; i < CHAR_ARRAY.length; i++) {
                CHAR_INDEX[CHAR_ARRAY[i]] = i;
            }
        }

        /**
         * Base58Encoder实例
         */
        static final Base58Encoder ENCODER = new Base58Encoder();

        @Override
        String encode(byte[] bytes) {
            return encode58(bytes);
        }

        /**
         * 进行base5编码
         *
         * @param input 需编码字节数组
         * @return base58字符串
         */
        private static String encode58(byte[] input) {
            if (input.length == 0) {
                return "";
            }
            input = Arrays.copyOfRange(input, 0, input.length);
            int zeroCount = 0;
            while (zeroCount < input.length && input[zeroCount] == 0) {
                ++zeroCount;
            }
            byte[] temp = new byte[input.length * 2];
            int j = temp.length;
            int startAt = zeroCount;
            while (startAt < input.length) {
                byte mod = divMod58(input, startAt);
                if (input[startAt] == 0) {
                    ++startAt;
                }
                temp[--j] = (byte) CHAR_ARRAY[mod];
            }
            while (j < temp.length && temp[j] == CHAR_ARRAY[0]) {
                ++j;
            }
            while (--zeroCount >= 0) {
                temp[--j] = (byte) CHAR_ARRAY[0];
            }
            byte[] output = Arrays.copyOfRange(temp, j, temp.length);
            try {
                return new String(output, "US-ASCII");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * 进行base5解码
         *
         * @param input base58字符串
         * @return 解码字节数组
         */
        private static byte[] decode58(String input) {
            if (input.length() == 0) {
                return new byte[0];
            }
            byte[] input58 = new byte[input.length()];
            for (int i = 0; i < input.length(); ++i) {
                char c = input.charAt(i);
                int digit58 = CHAR_INDEX[c];
                if (digit58 < 0) {
                    throw new IllegalArgumentException("not correct base58 string!");
                }
                input58[i] = (byte) digit58;
            }
            int zeroCount = 0;
            while (zeroCount < input58.length && input58[zeroCount] == 0) {
                ++zeroCount;
            }
            byte[] temp = new byte[input.length()];
            int j = temp.length;
            int startAt = zeroCount;
            while (startAt < input58.length) {
                byte mod = divMod256(input58, startAt);
                if (input58[startAt] == 0) {
                    ++startAt;
                }
                temp[--j] = mod;
            }
            while (j < temp.length && temp[j] == 0) {
                ++j;
            }
            return Arrays.copyOfRange(temp, j - zeroCount, temp.length);
        }

        /**
         * 除58取余数
         *
         * @param number  字节数组
         * @param startAt 起始位置
         * @return 除58的余数
         */
        private static byte divMod58(byte[] number, int startAt) {
            int remainder = 0;
            for (int i = startAt; i < number.length; i++) {
                int digit256 = (int) number[i] & 0xFF;
                int temp = remainder * 256 + digit256;
                number[i] = (byte) (temp / 58);
                remainder = temp % 58;
            }
            return (byte) remainder;
        }

        /**
         * 除256取余数
         *
         * @param number  字节数组
         * @param startAt 起始位置
         * @return 除256的余数
         */
        private static byte divMod256(byte[] number, int startAt) {
            int remainder = 0;
            for (int i = startAt; i < number.length; i++) {
                int digit58 = (int) number[i] & 0xFF;
                int temp = remainder * 58 + digit58;
                number[i] = (byte) (temp / 256);
                remainder = temp % 256;
            }
            return (byte) remainder;
        }
    }
}