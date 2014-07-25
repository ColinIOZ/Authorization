package com.big.authorization.util;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

import org.jasig.cas.authentication.handler.PasswordEncoder;

public class MD5 implements PasswordEncoder {

    @Override
    public String encode(String arg0) {

        MessageDigest digest = null;

        if (digest == null) {

            try {

                digest = MessageDigest.getInstance("MD5");

            } catch (NoSuchAlgorithmException nsae) {

                nsae.printStackTrace();

            }

        }

        // Now, compute hash.

        digest.update(arg0.getBytes());

        byte[] bytes = digest.digest();

        StringBuffer buf = new StringBuffer(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++) {

            if (((int) bytes[i] & 0xff) < 0x10) {

                buf.append("0");

            }

            buf.append(Long.toString((int) bytes[i] & 0xff, 16));

        }

        return buf.toString().toUpperCase();

    }

}