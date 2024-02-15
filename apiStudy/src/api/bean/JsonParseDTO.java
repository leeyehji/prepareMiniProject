package api.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonParseDTO {

    private Response response;

    // getter and setter
    @Getter
    @Setter
    public static class Response {
        private Header header;
        private Body body;

        // getter and setter
    }
    
    @Getter
    @Setter
    public static class Header {
        private String resultCode;
        private String resultMsg;

        // getter and setter
    }
    
    @Getter
    @Setter
    public static class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;

        // getter and setter
    }
    
    @Getter
    @Setter
    public static class Items {
        private List<Item> item;
        
        // getter and setter
    }
    
    @Getter
    @Setter
    public static class Item {
    	
    	private String addr1;
    	private String addr2;
    	private String contentid;
    	private String contenttypeid;
    	private String mapx;
    	private String mapy;
    	private String mlevel;
    	private String tel;
    	private String title;

        // getter and setter
    }
}
