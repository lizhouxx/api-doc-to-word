import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.StringWriter;

/**
 * 使用方法：
 *      将api-doc在线地址 写入source 字符串中，以逗号进行分割即可。必须把api-doc经过进行在线部署。让程序使用http|https|file:///
 */
public class Test {

    private static boolean proxySet = false;
    public static void main(String args[]) throws Exception {
        //添加模板文件信息
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "src/main/java");
        ve.init();
        Template t = ve.getTemplate("demo.vm");
        VelocityContext ctx = new VelocityContext();
        //将其放入模版中
        ArrayList<dev_1> lists = new ArrayList<>();


        String source = "file:///D:/work_2.5/pg/tmp/apidoc_loc/";



        for(int x = 0 ;x <source.split(",").length;x++){
            String tmp_url = source.split(",")[x];
            dev_1 tmp_dev_1 = new dev_1();
            //获取project信息
            String url = tmp_url + "/api_project.js";
            String params = "";
            String getResult = HttpsUtils.sendGet(url, params, proxySet);
            getResult = getResult.replace("define(","").replace(");","");
            JSONObject json = JSON.parseObject(getResult);
            tmp_dev_1.setAPI_PROJECT(json.getString("name"));
            String url_pro = json.getString("url");
            ArrayList<dev_2> dev_2_list = new ArrayList<>();
            //获取data信息
            String url1 = tmp_url + "/api_data.js";
            params = "";
            getResult = HttpsUtils.sendGet(url1, params, proxySet);
            getResult = getResult.replace("define(","").replace(");","");
            json = JSON.parseObject(getResult);
            JSONArray jsons = json.getJSONArray("api");
            HashMap<String,ArrayList<dev_3>> hash = new HashMap<>();
            ArrayList<String> dev_2_names = new ArrayList<>();
            for(int i = 0 ; i<jsons.size();i++){
                if(!dev_2_names.contains(jsons.getJSONObject(i).getString("groupTitle"))){
                    //过滤无效数据
                    if("".equals(jsons.getJSONObject(i).getString("url"))&&"0.0.0".equals(jsons.getJSONObject(i).getString("version"))){
                        continue;
                    }
                    dev_2_names.add(jsons.getJSONObject(i).getString("groupTitle"));
                    hash.put(jsons.getJSONObject(i).getString("groupTitle"),new ArrayList<dev_3>());
                }
                dev_3 tmp_dev_3 = new dev_3();
                if(jsons.getJSONObject(i).getString("groupTitle")!=null){
                    tmp_dev_3.setAPI_GROUP(jsons.getJSONObject(i).getString("groupTitle"));
                }
                if(jsons.getJSONObject(i).getString("title")!=null){
                    tmp_dev_3.setAPI_TITLE(jsons.getJSONObject(i).getString("title"));
                }
                if(jsons.getJSONObject(i).getString("description")!=null){
                    tmp_dev_3.setAPI_DESC(jsons.getJSONObject(i).getString("description"));
                }
                if(jsons.getJSONObject(i).getString("type")!=null){
                    tmp_dev_3.setAPI_TYPE(jsons.getJSONObject(i).getString("type").toUpperCase());
                }
                if(jsons.getJSONObject(i).getString("url")!=null){
                    tmp_dev_3.setAPI_URL((url_pro==null?"":url_pro)+jsons.getJSONObject(i).getString("url"));
                }
                if(jsons.getJSONObject(i).getJSONArray("examples")!=null){
                    tmp_dev_3.setAPI_EXAM_TITLE(jsons.getJSONObject(i).getJSONArray("examples").getJSONObject(0).getString("title"));
                    tmp_dev_3.setAPI_EXAM_CONT(jsons.getJSONObject(i).getJSONArray("examples").getJSONObject(0).getString("content").replace("\\n","\r\n"));
                }
                if(jsons.getJSONObject(i).getJSONObject("parameter")!=null){
                    if(jsons.getJSONObject(i).getJSONObject("parameter").getJSONObject("fields")!=null){
                        JSONArray tmp_jsons = jsons.getJSONObject(i).getJSONObject("parameter").getJSONObject("fields").getJSONArray("Parameter");
                        ArrayList<dev_4> tmp_dev_4 = new ArrayList<>();
                        for (int j = 0; j < tmp_jsons.size(); j++) {
                            dev_4 tmp_dev4 = new dev_4();
                            tmp_dev4.setTYPE(tmp_jsons.getJSONObject(j).getString("type"));
                            tmp_dev4.setOPTIONAL(tmp_jsons.getJSONObject(j).getBoolean("optional"));
                            tmp_dev4.setFIELD(tmp_jsons.getJSONObject(j).getString("field"));
                            tmp_dev4.setDESC(tmp_jsons.getJSONObject(j).getString("description"));
                            tmp_dev_4.add(tmp_dev4);
                        }
                        tmp_dev_3.setDev_4_lists(tmp_dev_4);
                    }
                }
                if(jsons.getJSONObject(i).getJSONObject("success")!=null){
                    if( jsons.getJSONObject(i).getJSONObject("success").getJSONObject("fields")!=null) {
                        JSONObject tmp_jsonobjects = jsons.getJSONObject(i).getJSONObject("success").getJSONObject("fields");
                        JSONArray tmp_jsons = new JSONArray();
                        for (Map.Entry<String, Object> entry : tmp_jsonobjects.entrySet()) {
                            tmp_jsons = (JSONArray) entry.getValue();
                        }
                        ArrayList<dev_4> tmp_dev_4 = new ArrayList<>();
                        for (int j = 0; j < tmp_jsons.size(); j++) {
                            dev_4 tmp_dev4 = new dev_4();
                            tmp_dev4.setTYPE(tmp_jsons.getJSONObject(j).getString("type"));
                            tmp_dev4.setOPTIONAL(tmp_jsons.getJSONObject(j).getBoolean("optional"));
                            tmp_dev4.setFIELD(tmp_jsons.getJSONObject(j).getString("field"));
                            tmp_dev4.setDESC(tmp_jsons.getJSONObject(j).getString("description"));
                            tmp_dev_4.add(tmp_dev4);
                        }
                        tmp_dev_3.setDev_4_SUCCESS_lists(tmp_dev_4);
                    }
                    if(jsons.getJSONObject(i).getJSONObject("success").getJSONArray("examples")!=null) {
                        JSONArray tmp_jsonobjects = jsons.getJSONObject(i).getJSONObject("success").getJSONArray("examples");
                        tmp_dev_3.setAPI_SUCCESS_EXAM_NAME(tmp_jsonobjects.getJSONObject(0).getString("title"));
                        String tmp = tmp_jsonobjects.getJSONObject(0).getString("content").trim();
                        tmp = "<span class=\"pln\">"+tmp;
                        tmp = tmp.replaceAll("(\".*\")(\\s*):","<span class=\"str\">$1</span> :");
                        tmp = tmp.replaceAll(":(\\s*)(\".*\")",": <span class=\"str\">$2</span>");
                        tmp = tmp.replaceAll(":(\\s*)(true)",": <span class=\"kwd\">true</span>");
                        tmp = tmp.replaceAll(":(\\s*)(false)",": <span class=\"kwd\">false</span>");
                        tmp = tmp.replaceAll(":(\\s*)(null)",": <span class=\"kwd\">null</span>");
                        tmp = tmp.replaceAll(" :(\\s*)(\\d+)"," : <span class=\"lit\">$2</span>");
                        tmp = tmp + "</span>";
                        tmp_dev_3.setAPI_SUCCESS_EXAM_CONT(tmp);
                    }
                }

                if(jsons.getJSONObject(i).getJSONObject("error")!=null){
                    if( jsons.getJSONObject(i).getJSONObject("error").getJSONObject("fields")!=null) {
                        JSONObject tmp_jsonobjects = jsons.getJSONObject(i).getJSONObject("error").getJSONObject("fields");
                        JSONArray tmp_jsons = new JSONArray();
                        for (Map.Entry<String, Object> entry : tmp_jsonobjects.entrySet()) {
                            tmp_jsons = (JSONArray) entry.getValue();
                        }
                        ArrayList<dev_4> tmp_dev_4 = new ArrayList<>();
                        for (int j = 0; j < tmp_jsons.size(); j++) {
                            dev_4 tmp_dev4 = new dev_4();
                            tmp_dev4.setTYPE(tmp_jsons.getJSONObject(j).getString("type"));
                            tmp_dev4.setOPTIONAL(tmp_jsons.getJSONObject(j).getBoolean("optional"));
                            tmp_dev4.setFIELD(tmp_jsons.getJSONObject(j).getString("field"));
                            tmp_dev4.setDESC(tmp_jsons.getJSONObject(j).getString("description"));
                            tmp_dev_4.add(tmp_dev4);
                        }
                        tmp_dev_3.setDev_4_ERROR_lists(tmp_dev_4);
                    }
                    if( jsons.getJSONObject(i).getJSONObject("error").getJSONArray("examples")!=null) {
                        JSONArray tmp_jsonobjects = jsons.getJSONObject(i).getJSONObject("error").getJSONArray("examples");
                        tmp_dev_3.setAPI_ERROR_EXAM_NAME(tmp_jsonobjects.getJSONObject(0).getString("title"));
                        String tmp = tmp_jsonobjects.getJSONObject(0).getString("content").trim();
                        tmp = "<span class=\"pln\">"+tmp;
                        tmp = tmp.replaceAll("(\".*\")(\\s*):","<span class=\"str\">$1</span> :");
                        tmp = tmp.replaceAll(":(\\s*)(\".*\")",": <span class=\"str\">$2</span>");
                        tmp = tmp.replaceAll(":(\\s*)(true)",": <span class=\"kwd\">true</span>");
                        tmp = tmp.replaceAll(":(\\s*)(false)",": <span class=\"kwd\">false</span>");
                        tmp = tmp.replaceAll(":(\\s*)(null)",": <span class=\"kwd\">null</span>");
                        tmp = tmp.replaceAll(" :(\\s*)(\\d+)"," : <span class=\"lit\">$2</span>");
                        tmp = tmp + "</span>";
                        tmp_dev_3.setAPI_ERROR_EXAM_CONT(tmp);
                    }
                }

                hash.get(jsons.getJSONObject(i).getString("groupTitle")).add(tmp_dev_3);
            }

            // dev_2多了一个参数值，与group一致,得到
            for (String dev_2_name:dev_2_names
                    ) {
                dev_2 tmp_dev_2 = new dev_2();
                tmp_dev_2.setAPI_MODULE(dev_2_name);
                tmp_dev_2.setAPI_INFO(hash.get(dev_2_name));
                dev_2_list.add(tmp_dev_2);
            }
            //生成模板文件
            tmp_dev_1.setDev_2_lists(dev_2_list);
            lists.add(tmp_dev_1);
        }

        ctx.put("tmp_1","传入参数:");
        ctx.put("tmp_2","字段");
        ctx.put("tmp_3","类型");
        ctx.put("tmp_4","必须");
        ctx.put("tmp_5","描述");
        ctx.put("tmp_6","失败返回参数:");
        ctx.put("tmp_7","成功返回参数:");
        ctx.put("tmp_8","示例请求:");
        ctx.put("div_1_list",lists);
        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        write("D:\\tmp\\one.html",sw.toString());
        write("D:\\tmp\\one.doc",sw.toString());
    }

    public static void write(String fileName, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, false); // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件,false表示覆盖的方式写入
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}