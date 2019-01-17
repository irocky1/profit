package io.github.biezhi.profit.thirdparty.payjs;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.*;


public class SignUtil {

    public static String sign(Map<String,String> map, String privateKey){
        Collection<String> keyset= map.keySet();
        List<String> keyList= new ArrayList<>(keyset);
        Collections.sort(keyList);
        StringBuilder sb = new StringBuilder();
        for (String key : keyList){
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        sb.append("key=").append(privateKey);
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        return  md5.digestHex(sb.toString());
    }

    public static void main(String[] args) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        System.out.println(md5.digestHex("你好"));
    }

}
