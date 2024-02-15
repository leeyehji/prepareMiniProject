package api.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import api.bean.ApiDTO;
import api.bean.JsonParseDTO;
import api.bean.JsonParseDTO.Item;
import api.bean.JsonParseDTO.Items;




public class ApiDAO {

	private SqlSessionFactory sqlSessionFactory;

	// 싱글톤
	private static ApiDAO apiDAO = new ApiDAO();

	public static ApiDAO getInstance() {
		return apiDAO;
	}

	// 생성자에서 mybatis-config.xml 에 명시되어 있는 JDBC의 값을 SqlSessionFactiory에 Builder 시킨다
	public ApiDAO() {

		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void apiWrite(Item item) {
		
		
		//Item class가 중첩되어 있어 mybatis mapper에서 인식 하지 못하는 문제를
		//해결 하지 못해 DTO를 생성하여 전달하였다.
		
		ApiDTO apiDTO=new ApiDTO();
		apiDTO.setAddr1(item.getAddr1());
		apiDTO.setAddr2(item.getAddr2());
		apiDTO.setContentid(item.getContentid());
		apiDTO.setContenttypeid(item.getContenttypeid());
		apiDTO.setMapx(item.getMapx());
		apiDTO.setMapy(item.getMapy());
		apiDTO.setMlevel(item.getMlevel());
		apiDTO.setTel(item.getTel());
		apiDTO.setTitle(item.getTitle());
		
		SqlSession sqlSession = sqlSessionFactory.openSession(); // 생성
		sqlSession.insert("apiSQL.apiWrite", apiDTO);
		sqlSession.commit();
		sqlSession.close();

	}
}
