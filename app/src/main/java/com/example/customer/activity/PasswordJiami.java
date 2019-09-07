package com.example.customer.activity;

import android.util.Log;

import com.example.customer.util.RsaUtils;

import java.security.InvalidKeyException;
import java.security.PublicKey;

import javax.crypto.Cipher;

//PUBLIC_KEY_STR  公钥
public class PasswordJiami {
    public  static PublicKey publicKey,publicKeyj;
    public  static String passwordjiami( String password){
        //获取公钥
        publicKey = RsaUtils.keyStrToPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDrouoy4rro4ICiwC+re4/uMZIylYtDXb1KnCBpKMgKLgB0GvI+L3rhONcWz40N0ar3wLLzffAgwNUJvc9m5EM/pgf0PnckzTK+bluA7enNb3dbXpqBV0Yu69ufv8hqhwpI3HB2csIvUqzPXtf7WHrMB8IGQCk67Y03ZCq4Kra5wIDAQAB");
        //公钥加密结果
        String  publicEncryptedResult = RsaUtils.encryptDataByPublicKey(password.getBytes(), publicKey);
        //私钥解密结果
//       String privateDecryptedResult = RsaUtils.decryptedToStrByPrivate(publicEncryptedResult,privateKey);
        //String s = RsaUtils.decryptedToStrByPublicKey(String.valueOf(password.getBytes()), publicKey);
        return publicEncryptedResult;
    }
    public  static String passwjiemi( String password){

        //获取公钥
        publicKeyj = RsaUtils.keyStrToPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDrouoy4rro4ICiwC+re4/uMZIylYtDXb1KnCBpKMgKLgB0GvI+L3rhONcWz40N0ar3wLLzffAgwNUJvc9m5EM/pgf0PnckzTK+bluA7enNb3dbXpqBV0Yu69ufv8hqhwpI3HB2csIvUqzPXtf7WHrMB8IGQCk67Y03ZCq4Kra5wIDAQAB");
        //公钥加密结果
        //String  publicEncryptedResult = RsaUtils.encryptDataByPublicKey(password.getBytes(), publicKey);
        //私钥解密结果
//       String privateDecryptedResult = RsaUtils.decryptedToStrByPrivate(publicEncryptedResult,privateKey);

        String s = RsaUtils.decryptedToStrByPublicKey(password, publicKeyj);

        //Log.e("Token值2",s);
        return s;
    }



}
