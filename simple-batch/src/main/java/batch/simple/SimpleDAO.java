package batch.simple;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SimpleDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	public SimpleDAO(SqlSessionFactory sqlSessionFactory){
		// 메인에서 생성한 세션 펙토리를 받아옴
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public List<SimpleVO> selectAll(SimpleVO vo){

		List<SimpleVO> list = null;
		
		// 메소드 단위로 세션 오픈 -> 사용 -> 클로즈
		SqlSession session = sqlSessionFactory.openSession();
		try {

			list = session.selectList("batch.simple.SimpleDAO.selectAll", vo);
			
		} finally {
		  session.close();
		}
		
		return list;
		
	}

	public int insert(SimpleVO vo) {
		int i = 0;
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			i = session.insert("batch.simple.SimpleDAO.insert", vo);
			
			// commit
//			session.commit();
		}
		finally {
		  session.close();
		}

		return i;
	} 
	
}
