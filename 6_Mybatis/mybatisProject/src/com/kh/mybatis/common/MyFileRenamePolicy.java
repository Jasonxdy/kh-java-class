package com.kh.mybatis.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy; // cos.jar에서 제공해줌

public class MyFileRenamePolicy implements FileRenamePolicy {
	//								인터페이스이므로 사용을 강제하는 형태

	@Override
	public File rename(File originFile) {
		
		long currentTime = System.currentTimeMillis();
		// (우리나라 기준) 1970년 1우러 1일 오전 9시부터 현재 시간까지의
		// 밀리세컨드(ms) 시간을 추출하는 메소드
		// 이것을 이용하면 절대 이름이 겹칠일이 없으므로 (long 타입임)
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
		int ranNum = (int)(Math.random()*100000); // 5자리 랜덤 숫자 생성 (0~99999)
		
		// 파일명을 변경해도 확장자를 유지하기 위하여 
		// 별도로 확장자 명 가져오기
		String name = originFile.getName();
		String ext = null;
		
		int dot = name.lastIndexOf("."); // .의 가장 마지막 위치 인덱스 반환
		// ex) apple.jpg면 .의 인덱스
		
		if(dot != -1) {
			// dot 포함해서 확장자 추출 (ext)
			ext = name.substring(dot);
		} else {
			// 확장자가 없는 경우 
			ext = "";
		}
		
		
		String fileName = ft.format(new Date(currentTime)) + ranNum + ext;
		File newFile = new File(originFile.getParent(), fileName);
		
		return newFile;
	}

}
