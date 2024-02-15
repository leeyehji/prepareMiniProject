package apiStudy;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.bean.JsonParseDTO;
import api.bean.*;
import api.dao.ApiDAO;

public class JsonParse {

	public void jsonParse(String json) {
		// 받아온 json 객체를 준비
		String jsonString = json;

		// jackson 내장 객체인 ObjectMapper
		ObjectMapper mapper = new ObjectMapper();

		// json 에서 원하지 않는 값을 없애 주는 메소드
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			// 중요! 내가 생성한 Class 형태로 값을 읽어주는 문장
			JsonParseDTO jsonParseDTO = mapper.readValue(jsonString, JsonParseDTO.class);

			// Item 객체 타입의 List로 받아온다.
			List<JsonParseDTO.Item> item = jsonParseDTO.getResponse().getBody().getItems().getItem();

			// 이 List를 DB에 연결하기 위해 ApiDAO의 apiWrite(String) 메소드 로 리스트를 넘겨준다.
			ApiDAO apiDAO = ApiDAO.getInstance();
			
			// item 객체를 하나씩 전해주기 위해 for문을 이용한다.
			for (int i = 0; i < item.size(); i++) {
				apiDAO.apiWrite(item.get(i));
			}
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}