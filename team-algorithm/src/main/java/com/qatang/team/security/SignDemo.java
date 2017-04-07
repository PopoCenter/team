package com.qatang.team.security;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/**
 * Created by xdd on 2017/4/6.
 */
public class SignDemo {

    /**
     * 得到产生的私钥/公钥对
     */
    public static KeyPair getKeypair() {
        //产生RSA密钥对(myKeyPair)
        KeyPairGenerator myKeyGen = null;
        try {
            myKeyGen = KeyPairGenerator.getInstance("RSA");
            myKeyGen.initialize(1024);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        KeyPair myKeyPair = myKeyGen.generateKeyPair();
        return myKeyPair;
    }

    /**
     * 根据密钥对对信息进行加密，返回公钥值
     */
    public static byte[] getPublicByKeypair(Signature mySig, KeyPair myKeyPair, byte[] information) {
        byte[] publicInfo = null;
        try {
            mySig.initSign(myKeyPair.getPrivate());  //用私钥初始化签名对象
            mySig.update(information);  //将待签名的数据传送给签名对象
            publicInfo = mySig.sign();  //返回签名结果字节数组
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicInfo;
    }

    /**
     * 公钥验证签名
     */
    public static boolean decryptByPublic(Signature mySig, KeyPair myKeyPair, String information, byte[] publicInfo) {
        boolean verify = false;
        try {
            mySig.initVerify(myKeyPair.getPublic());  //使用公钥初始化签名对象,用于验证签名
            mySig.update(information.getBytes()); //更新签名内容
            verify = mySig.verify(publicInfo); //得到验证结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verify;
    }


    public static void main(String[] args) {
        try {
            //1.首先获得公钥/私钥对  2.根据获得公钥/私钥对，根据指定的算法来加密指定的内容，根据私钥加密获得相对应的公钥，公开，给用户； 3.用户根据内容来验证自己是否是跟传来的公钥是一对。。。
            KeyPair keyPair = getKeypair();
            Signature mySig = Signature.getInstance("MD5WithRSA");//用指定算法产生签名对象
            byte[] publicInfo = getPublicByKeypair(mySig, keyPair, "验证我".getBytes());
            boolean verify = decryptByPublic(mySig, keyPair, "验证我", publicInfo);
            System.out.println("验证签名的结果是：" + verify);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}