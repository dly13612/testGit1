package com.example.demo.xmlParser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class JsonToXml {
    public static void main(String[] args) throws Exception {
        //请注意使用正常的xml
        //xml->json
//        String jsonStr = xmlToJson("D:\\测试.xml", null);
//        src/main/resources/static/测试.xml
//        String jsonStr = xmlToJson(ClassLoader.getSystemResource("bookSaxList.xml").getPath());
//        System.out.println(jsonStr);

        //json->xml
        String xmlstr = jsonToXml("{\"b_content\":{\"sdata\":\"Ps/DPJnZZPN6QQJQodY3+hK6PWCF3/2oi3DJPnFEXgKDrXX5rHT7q/I0nQPAruuBbQRfErnenQNvPpbf/lXl690qtye0/ZEuDs0ByFdFAGffQalB+Ij3lLUMDPz=\",\"userobj\":{\"uid\":\"ma000\",\"realtype\":\"DC\",\"cn\":\"法人用户2\",\"tokenid\":\"\",\"usertype\":\"2\",\"link_person_name\":\"联系人2\",\"isreal\":\"true\",\"telephonenumber\":\"13*******21\",\"mail\":\"ceshi@123.com\",\"idcardtype\":\"10\",\"createtime\":\"20150618191221\",\"extproperties\":[\"address=广东省广州市天河区天河北路XXX号\",\"legal_code=440***********033\",\"ent_type=-1\",\"link_person_code=350************14\",\"origin=gdbs\",\"card_type_two_num=-1\",\"cert_ca=-1\",\"accout_type=2\",\"account_uid=2\",\"comm_code=-1\",\"unit_type=-1\",\"legal_id_type=10\",\"landline=-1\",\"tax_code=-1\",\"cert_notafter=-1\",\"card_type_one_num=-1\",\"local_user=-1\",\"legal_person=郑**\",\"link_person_type=10\",\"card_type_three=-1\",\"card_type_two=-1\",\"card_type_three_num=-1\",\"cert_data=-1\",\"area=guangzhou\",\"uversion=3.0\",\"cert_notbefore=-1\",\"card_type_one=-1\",\"user_typeext=2\"],\"idcardnumber\":\"11***************23\",\"useridcode\":\"38c97fa1ee2e43d4a664cffc4554cde4\",\"creditable_level_of_account_way\":\"L2@YSS@2088******653||L0@IDV@44088******75||L3@GW@44088******75\",\"creditable_level_of_account\":\"L3\"},\"pareobj\":{\"uid\":\"mayintao\",\"realtype\":\"DC\",\"cn\":\"单位用户2\",\"tokenid\":\"\",\"usertype\":\"2\",\"link_person_name\":\"联系人2\",\"isreal\":\"true\",\"telephonenumber\":\"13*******21\",\"mail\":\"ceshi@123.com\",\"idcardtype\":\"50\",\"createtime\":\"20150618191221\",\"extproperties\":[\"address=广东省广州市东山区\",\"legal_id_type=-1\",\"link_person_type=-1\",\"legal_code=-1\",\"origin=gdbs\",\"tax_code=-1\",\"legal_person=-1\",\"area=shenzhen\",\"link_person_code=-1\",\"user_typeext=2\",\"uversion=1.0\"],\"idcardnumber\":\"456787654\",\"useridcode\":\"75c91fagrr2e67d4a169cfmc8735ctrf\",\"creditable_level_of_account_way\":\"L2@YSS@2088******653||L0@IDV@44088******75||L3@GW@44088******75\",\"creditable_level_of_account\":\"L3\"},\"user_creditable_level\":{\"creditable_level_of_account_way\":\"L2@YSS@2088******653||L0@IDV@44088******75||L3@GW@44088******75\",\"creditable_level_of_account_way_list\":[{\"auth_time\":\"2018-02-28 16:45:26\",\"uniqueid\":\"***86f93fb61***\",\"user_name\":\"郭**\",\"auth_identification\":\"2088******653\",\"identity_level\":\"L2\",\"credential_no\":\"44088******75\",\"way_code\":\"YSS\"},{\"auth_time\":null,\"uniqueid\":\"***764486f93fb61212***\",\"user_name\":\"郭**\",\"auth_identification\":\"44088******75\",\"identity_level\":\"L0\",\"credential_no\":\"44088******75\",\"way_code\":\"IDV\"},{\"auth_time\":\"2018-02-13 17:12:31\",\"uniqueid\":\"*****764486f93fb612122*****\",\"user_name\":\"郭**\",\"auth_identification\":\"44088******75\",\"identity_level\":\"L3\",\"credential_no\":\"44088******75\",\"way_code\":\"GW\"}],\"creditable_level_of_account\":\"L3\"}},\"time_stamp\":\"20200821\",\"version \":\"v1\",\"sign\":\"rxf0MFT7eQqYgYKWtgzNBi6mhS2tbqkPgI \"}");
        System.out.println(xmlstr);
        createXMLFile(formatXML(xmlstr), "test");
    }

    /**
     * xml转json字符串 注意:路径和字符串二传一另外一个传null<br>
     * 方 法 名：xmlToJson <br>
     * @param xmlPath xml路径(和字符串二传一,两样都传优先使用路径)
     * @param xmlStr xml字符串(和路径二传一,两样都传优先使用路径)
     * @return String
     * @throws IOException
     * @throws JDOMException
     */
    @SuppressWarnings("unchecked")
    public static String xmlToJson(String xmlPath){
        SAXBuilder sbder = new SAXBuilder();
        Map<String, Object> map = new HashMap<String, Object>();
        Document document;
        try {
            document = sbder.build(new File(xmlPath));

            //获取根节点
            Element el =  document.getRootElement();
            List<Element> eList =  el.getChildren();
            Map<String, Object> rootMap = new HashMap<String, Object>();
            //得到递归组装的map
            rootMap = xmlToMap(eList,rootMap);
            map.put(el.getName(), rootMap);
            //将map转换为json 返回
            return JSON.toJSONString(map);
        } catch (Exception e) {
            return "{}";
        }
    }

    /**
     * 节点转map<br>
     * 方 法 名：xmlToMap <br>
     * @param eList
     * @param map
     * @return Map<String,Object>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> xmlToMap(List<Element> eList,Map<String, Object> map){
        for (Element e : eList) {
            Map<String, Object> eMap = new HashMap<String, Object>();
            List<Element> elementList = e.getChildren();
            if(elementList!=null&&elementList.size()>0){
                eMap = xmlToMap(elementList,eMap);
                Object obj = map.get(e.getName());
                if(obj!=null){
                    List<Object> olist = new ArrayList<Object>();
                    if(obj.getClass().getName().equals("java.util.HashMap")){
                        olist.add(obj);
                        olist.add(eMap);
                    }else if(obj.getClass().getName().equals("java.util.ArrayList")){
                        olist = (List<Object>)obj;
                        olist.add(eMap);
                    }
                    map.put(e.getName(), olist);
                }else{
                    map.put(e.getName(), eMap);
                }
            }else{
                map.put(e.getName(), e.getValue());
            }
        }
        return map;
    }

    /**
     * json转xml<br>
     * 方 法 名：jsonToXml <br>
     * @param json
     * @return String
     */
    public static String jsonToXml(String json){
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            buffer.append("<base>");
            JSONObject jObj = JSON.parseObject(json);
            jsonToXmlstr(jObj,buffer);
            buffer.append("</base>");
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
     * json转str<br>
     * 方 法 名：jsonToXmlstr <br>
     * @param jObj
     * @param buffer
     * @return String
     */
    public static String jsonToXmlstr(JSONObject jObj,StringBuffer buffer ){
        Set<Entry<String, Object>>  se = jObj.entrySet();
        for( Iterator<Entry<String, Object>>   it = se.iterator();  it.hasNext(); )
        {
            Entry<String, Object> en = it.next();
            if(en.getValue() != null && en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONObject")){
                buffer.append("<"+en.getKey()+">");
                JSONObject jo = jObj.getJSONObject(en.getKey());
                jsonToXmlstr(jo,buffer);
                buffer.append("</"+en.getKey()+">");
            }else if(en.getValue() != null && en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONArray")){
                if (en.getKey().equals("extproperties")) {
                    JSONArray ja = jObj.getJSONArray(en.getKey());
                    Iterator<Object> it1 = ja.iterator();
                    List<String> list=new ArrayList<String>();
                    while (it1.hasNext()) {
                        String ob = (String) it1.next();
                        System.out.println(ob);
                    }
                }else {
                    JSONArray jarray = jObj.getJSONArray(en.getKey());
                    for (int i = 0; i < jarray.size(); i++) {
                        buffer.append("<"+en.getKey()+">");
                        JSONObject jsonobject =  jarray.getJSONObject(i);
                        jsonToXmlstr(jsonobject,buffer);
                        buffer.append("</"+en.getKey()+">");
                    }
                }

            }else if(en.getValue() != null && en.getValue().getClass().getName().equals("java.lang.String")){
                buffer.append("<"+en.getKey()+">"+en.getValue());
                buffer.append("</"+en.getKey()+">");
            }else{
                buffer.append("<"+en.getKey()+">"+"");
                buffer.append("</"+en.getKey()+">");
            }

        }
        return buffer.toString();
    }

    /**
     * 将已经格式化的xml字符串写入xml文件
     * @param xmlStr
     * @return
     */
    public static boolean createXMLFile(String xmlStr,String xmlName){
        boolean flag = false;
        try {
            XMLWriter output = null;
            //OutputFormat   format   =   OutputFormat.createPrettyPrint();
            //format.setSuppressDeclaration(true);
            // format.setEncoding("UTF-8");

            //如果上面设置的xml编码类型为GBK，则应当用FileWriter来构建xml文件，否则会出现中文连码问题
                /*outpt = new XMLWriter(
                        new FileWriter(
                                new File("D:/myeclipse/Workspaces/fusionChartsDemoTest/WebRoot/xml/"+xmlName+".xml")) ,
                                    format);
                  */

            //如果上面设置的xml编码类型为utf-8，则应当用FileOutputStream来构建xml文件，否则还是会出现问题
            output = new XMLWriter(
                    new FileOutputStream(
                            new File("D:/"+xmlName+".xml")));
            output.setEscapeText(false);
            output.write( xmlStr );
            output.close();
            return flag = true;

        } catch (IOException e) {
            e.printStackTrace();
            return flag;
        }

    }

    public static  String formatXML(String str) throws Exception {

        SAXReader reader=new SAXReader();

        //创建一个串的字符输入流
        StringReader in=new StringReader(str);
        org.dom4j.Document doc=reader.read(in);
        // 创建输出格式
        OutputFormat formater=OutputFormat.createPrettyPrint();
        //去掉xml文件的版本信息
        //formater.setSuppressDeclaration(true);
        //设置xml的输出编码
        formater.setEncoding("UTF-8");
        //创建输出(目标)
        StringWriter out=new StringWriter();
        //创建输出流
        XMLWriter writer=new XMLWriter(out,formater);
        //输出格式化的串到目标中，执行后。格式化后的串保存在out中。
        writer.write(doc);

        writer.close();

        // System.out.println(out.toString());

        //返回我们格式化后的结果
        return out.toString();
    }
}


