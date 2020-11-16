package spring;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author shen
 * @date 2020/9/16 15:49
 */

public class test5 {
    public static void main(String[] args) {
        System.out.println(test5.addLeftRepairForNum("1",3,"0"));

    }

    /**
     *  字符串左补数据
     * @param str
     * @param strLength
     * @return
     */
    public static String addLeftRepairForNum(String str, int strLength,String repairStr) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append(repairStr).append(str);//左补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }
}
