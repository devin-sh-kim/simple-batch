package batch.simple;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SimpleMain {

	public static void main(String[] args) {

		try {

			// jdbc 설정 로드
			Properties jdbcConfig = new Properties();
			jdbcConfig.load(Resources.getResourceAsStream("config/jdbc.properties"));
			
			// mybatis 설정 로드
			InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
			
			// 세션펙토리 생성
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, jdbcConfig);

			// DAO 에 세션 펙토리 전달
			SimpleDAO dao = new SimpleDAO(sqlSessionFactory);
			
			// 조회 조건용 VO
			SimpleVO vo = new SimpleVO();
			// 조회 조건 설정
			// Name 이 NULL이면 모두 조회 
			vo.setName("kim");
			
			// 결과 리스트
			List<SimpleVO> list = dao.selectAll(vo);
			
			// 결과 출력
			for(SimpleVO e : list){
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
