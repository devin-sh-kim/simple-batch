package batch.simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

public class SimpleService {

	private SimpleDAO dao;
	
	public SimpleService(SimpleDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * 입력 파일 읽어 VO 생성 후 DB insert 처리 파일 첫라인은 컬럼 이름 필드 구분은 탭
	 * 
	 * @param inputFile
	 * @param dao 
	 * @return insert count
	 * @throws IOException
	 */
	public int add(File inputFile) throws IOException {

		int insertCount = 0;

		BufferedReader in = new BufferedReader(new FileReader(inputFile));

		String line = null;

		int i = 0;
		while ((line = in.readLine()) != null) {
			i++;

			System.out.println("readLine(" + i + ")\t : " + line);

			// 첫 라인은 skip
			if (i == 1){
				continue;
			}
				

			// line 탭으로 분리
			
			SimpleVO vo = parseLine(line, "\t");
//			System.out.println(vo);
			
			if(vo != null){
				insertCount += dao.insert(vo);
			}
			
		}

		in.close();
		
		return insertCount;
	}
	
	private SimpleVO parseLine(String line, String delim) {

		SimpleVO vo = new SimpleVO();
		
		StringTokenizer st = new StringTokenizer(line, delim);

		vo.setName(st.nextToken());
		vo.setEmail(st.nextToken());
		
		return vo;
	}

	public List<SimpleVO> list(SimpleVO vo) {
		return dao.selectAll(vo);
	}
	
}
