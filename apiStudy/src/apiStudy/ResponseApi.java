package apiStudy;

//필요한 라이브러리 import
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResponseApi {

	public void responseApi() {
		try {
			
			// URL을 String 형태로 준비합니다.
			// 이곳에 생성 된 URL 을 붙여 넣기 한다.
			String urlStr = "https://apis.data.go.kr/B551011/KorService1/searchKeyword1?MobileOS=ETC&MobileApp=test&_type=json"
					+ "&keyword=%EB%B2%9A%EA%BD%83&serviceKey=kgvLC57BKXk43NDOu3vXGjb9b%2FdAhJIbAjsxU8zWPkibl%2FrcZpL5mDBBc1ihKXys4RY%2F7nmkk8YFA1um%2F1c%2FRA%3D%3D"; 
			
			
			// String 형태의 URL을 URL 객체로 변환합니다.
			URL url = new URL(urlStr);
			// URL 객체를 이용해 연결을 준비합니다.
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// HTTP 메소드를 GET으로 설정합니다.
			conn.setRequestMethod("GET");
			// 응답 데이터 형식을 JSON으로 설정합니다.
			conn.setRequestProperty("Content-type", "application/json");

			// 응답 코드가 200(성공)이 아니라면 예외를 발생시킵니다.
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
			
			// 응답을 읽기 위한 Reader를 준비하고, BufferedReader로 감쌉니다.
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			
			// 라인 단위로 읽어서 화면에 출력합니다.
			// while 조건문 속의 output이 URL을 웹 페이지에서 열었을 때 나오는 json 문장
			// 이 output을 json parse (json 문장을 DTO 형식의 개체로 만드는 과정) 하기 위해
			// 클래스를 만들어 메소드로 넘겨준다.
			
			while ((output = br.readLine()) != null) {
				
				JsonParse jsonParse = new JsonParse(); //객체 생성
				jsonParse.jsonParse(output);
				//JsonParse.java의 jsonParse 메소드로 String output을 매개변수로 하여 넘겨준다.
			}

			// 연결을 해제합니다.
			conn.disconnect();
		} catch (Exception e) {
			// 예외가 발생하면 화면에 출력합니다.
			System.out.println("Exception in NetClientGet:- " + e);
		}
	}
	

}
