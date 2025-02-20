package org.hswebframework.web.utils;

import io.netty.util.concurrent.FastThreadLocal;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DigestUtils {

    public static final FastThreadLocal<MessageDigest> md5 = new FastThreadLocal<MessageDigest>() {
        @Override
        protected MessageDigest initialValue() {
            return org.apache.commons.codec.digest.DigestUtils.getMd5Digest();
        }
    };

    public static final FastThreadLocal<MessageDigest> sha256 = new FastThreadLocal<MessageDigest>() {
        @Override
        protected MessageDigest initialValue() {
            return org.apache.commons.codec.digest.DigestUtils.getSha256Digest();
        }
    };
    ;
    public static final FastThreadLocal<MessageDigest> sha1 = new FastThreadLocal<MessageDigest>() {
        @Override
        protected MessageDigest initialValue() {
            return org.apache.commons.codec.digest.DigestUtils.getSha1Digest();
        }
    };
    ;

    public static byte[] md5(Consumer<MessageDigest> digestHandler) {
        return digest(md5::get, digestHandler);
    }

    public static String md5Hex(Consumer<MessageDigest> digestHandler) {
        return digestHex(md5::get, digestHandler);
    }

    public static byte[] sha1(Consumer<MessageDigest> digestHandler) {
        return digest(sha1::get, digestHandler);
    }

    public static String sha1Hex(Consumer<MessageDigest> digestHandler) {
        return digestHex(sha1::get, digestHandler);
    }

    public static byte[] sha256(Consumer<MessageDigest> digestHandler) {
        return digest(sha256::get, digestHandler);
    }

    public static String sha256Hex(Consumer<MessageDigest> digestHandler) {
        return digestHex(sha1::get, digestHandler);
    }

    public static byte[] md5(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.digest(md5.get(), data);
    }

    public static byte[] md5(String str) {
        return md5(str.getBytes());
    }

    public static String md5Hex(String str) {
        return Hex.encodeHexString(md5(str.getBytes()));
    }

    public static byte[] sha256(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.digest(sha256.get(), data);
    }

    public static byte[] sha256(String str) {
        return sha256(str.getBytes());
    }

    public static String sha256Hex(String str) {
        return Hex.encodeHexString(sha256(str.getBytes()));
    }

    public static byte[] sha1(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.digest(sha1.get(), data);
    }

    public static byte[] sha1(String str) {
        return sha1(str.getBytes());
    }

    public static String sha1Hex(String str) {
        return Hex.encodeHexString(sha1(str.getBytes()));
    }

    public static byte[] digest(MessageDigest digest, byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.digest(digest, data);
    }

    public static byte[] digest(MessageDigest digest, String str) {
        return digest(digest, str.getBytes());
    }

    public static String digestHex(MessageDigest digest, String str) {
        return Hex.encodeHexString(digest(digest, str));
    }

    private static byte[] digest(Supplier<MessageDigest> digestSupplier,
                                 Consumer<MessageDigest> digestHandler) {
        MessageDigest digest = digestSupplier.get();
        digestHandler.accept(digest);
        return digest.digest();
    }

    private static String digestHex(Supplier<MessageDigest> digestSupplier,
                                    Consumer<MessageDigest> digestHandler) {
        return Hex.encodeHexString(digest(digestSupplier, digestHandler));
    }
}
