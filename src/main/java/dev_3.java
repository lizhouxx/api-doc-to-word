import java.util.ArrayList;

public class dev_3 {

    String API_NAME;
    
    String API_GROUP;

    String API_TITLE;

    String API_DESC;

    String API_TYPE;

    String API_URL;


    String API_EXAM_TITLE;

    String API_EXAM_CONT;


    ArrayList<dev_4>  dev_4_lists;

    ArrayList<dev_4>  dev_4_SUCCESS_lists;

    String API_SUCCESS_EXAM_NAME;
    String API_SUCCESS_EXAM_CONT;

    ArrayList<dev_4>  dev_4_ERROR_lists;
    String API_ERROR_EXAM_NAME;
    String API_ERROR_EXAM_CONT;

    public ArrayList<dev_4> getDev_4_ERROR_lists() {
        return dev_4_ERROR_lists;
    }

    public void setDev_4_ERROR_lists(ArrayList<dev_4> dev_4_ERROR_lists) {
        this.dev_4_ERROR_lists = dev_4_ERROR_lists;
    }

    public String getAPI_GROUP() {
        return API_GROUP;
    }

    public void setAPI_GROUP(String API_GROUP) {
        this.API_GROUP = API_GROUP;
    }

    public String getAPI_TITLE() {
        return API_TITLE;
    }

    public void setAPI_TITLE(String API_TITLE) {
        this.API_TITLE = API_TITLE;
    }

    public String getAPI_DESC() {
        return API_DESC;
    }

    public void setAPI_DESC(String API_DESC) {
        this.API_DESC = API_DESC;
    }

    public String getAPI_TYPE() {
        return API_TYPE;
    }

    public String getAPI_NAME()
    {
        return API_NAME;
    }

    public void setAPI_NAME(String aPI_NAME)
    {
        API_NAME = aPI_NAME;
    }

    public void setAPI_TYPE(String API_TYPE) {
        this.API_TYPE = API_TYPE;
    }

    public String getAPI_URL() {
        return API_URL;
    }

    public void setAPI_URL(String API_URL) {
        this.API_URL = API_URL;
    }

    public String getAPI_EXAM_TITLE() {
        return API_EXAM_TITLE;
    }

    public void setAPI_EXAM_TITLE(String API_EXAM_TITLE) {
        this.API_EXAM_TITLE = API_EXAM_TITLE;
    }

    public String getAPI_EXAM_CONT() {
        return API_EXAM_CONT;
    }

    public void setAPI_EXAM_CONT(String API_EXAM_CONT) {
        this.API_EXAM_CONT = API_EXAM_CONT;
    }

    public ArrayList<dev_4> getDev_4_lists() {
        return dev_4_lists;
    }

    public void setDev_4_lists(ArrayList<dev_4> dev_4_lists) {
        this.dev_4_lists = dev_4_lists;
    }


    public ArrayList<dev_4> getDev_4_SUCCESS_lists() {
        return dev_4_SUCCESS_lists;
    }

    public void setDev_4_SUCCESS_lists(ArrayList<dev_4> dev_4_SUCCESS_lists) {
        this.dev_4_SUCCESS_lists = dev_4_SUCCESS_lists;
    }

    public String getAPI_SUCCESS_EXAM_NAME() {
        return API_SUCCESS_EXAM_NAME;
    }

    public void setAPI_SUCCESS_EXAM_NAME(String API_SUCCESS_EXAM_NAME) {
        this.API_SUCCESS_EXAM_NAME = API_SUCCESS_EXAM_NAME;
    }

    public String getAPI_SUCCESS_EXAM_CONT() {
        return API_SUCCESS_EXAM_CONT;
    }

    public void setAPI_SUCCESS_EXAM_CONT(String API_SUCCESS_EXAM_CONT) {
        this.API_SUCCESS_EXAM_CONT = API_SUCCESS_EXAM_CONT;
    }

    public String getAPI_ERROR_EXAM_NAME() {
        return API_ERROR_EXAM_NAME;
    }

    public void setAPI_ERROR_EXAM_NAME(String API_ERROR_EXAM_NAME) {
        this.API_ERROR_EXAM_NAME = API_ERROR_EXAM_NAME;
    }

    public String getAPI_ERROR_EXAM_CONT() {
        return API_ERROR_EXAM_CONT;
    }

    public void setAPI_ERROR_EXAM_CONT(String API_ERROR_EXAM_CONT) {
        this.API_ERROR_EXAM_CONT = API_ERROR_EXAM_CONT;
    }
}
