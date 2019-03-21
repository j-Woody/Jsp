package com.tj.px.serviceB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.px.dao.BoardDao;
import com.tj.px.dao.ProjectDao;
import com.tj.px.dto.BoardDto;
import com.tj.px.service.Service;

public class BInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String uploadPath = request.getRealPath("boardImg");
		System.out.println("들어옴");
		BoardDao bDao= BoardDao.getInstance();
		int result= 0;
			    int size = 10 * 1024 * 1024; 
				HttpSession session = request.getSession();
				String bImage = "";
				MultipartRequest mRequest = null;
				try{
			        // 파일업로드 및 업로드 후 파일명 가져옴
					mRequest = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
					Enumeration<String> files = mRequest.getFileNames();
					String file = (String)files.nextElement(); 
					bImage = mRequest.getFilesystemName(file); 	
					String bTitle = mRequest.getParameter("bTitle");
					String bContent = mRequest.getParameter("bContent");
					String mEmail = (String)session.getAttribute("mEmail");
					String bIp = request.getRemoteAddr();
					result = bDao.insertBoard(mEmail, bTitle, bContent, bImage, bIp);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
				if(result == bDao.FAIL) {
					request.setAttribute("errorMsg", "등록실패");
				}else {
					File file = new File(uploadPath+"/"+bImage);
						if(file.exists()) {
							InputStream is = null;
							OutputStream os = null;
						
							try {
								is = new FileInputStream(file);
								os = new FileOutputStream("C:/Users/502-29/Desktop/190313 (2)/WebContent/boardImg/"+bImage);
								byte[] bs = new byte[(int)file.length()];
								while(true) {
									int nByteCnt=is.read(bs);
									if(nByteCnt == -1) {
										break;
									}
									os.write(bs,0,nByteCnt);
								}
								
							}catch (Exception e) {
								System.out.println(e.getMessage());
							}finally {
								try {
									if(os!=null) os.close();
									if(is!=null)is.close();
								} catch (Exception e2) {
									// TODO: handle exception
								}
							}
							request.setAttribute("Msg", "등록완료");
							System.out.println("완료");
						}
						
				}
				
	}

}
