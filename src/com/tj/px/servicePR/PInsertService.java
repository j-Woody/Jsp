package com.tj.px.servicePR;

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
import com.tj.px.dao.ProjectDao;
import com.tj.px.service.Service;

public class PInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String uploadPath = request.getRealPath("upload");
		System.out.println("들어옴");
		HttpSession session = request.getSession();
		ProjectDao pDao= ProjectDao.getInstance();
		int result= 0;
			    int size = 10 * 1024 * 1024; 
				
				String pImage = "";
				MultipartRequest mRequest = null;
				
				
				try{
			        // 파일업로드 및 업로드 후 파일명 가져옴
					mRequest = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
					Enumeration<String> files = mRequest.getFileNames();
					String file = (String)files.nextElement(); 
					pImage = mRequest.getFilesystemName(file); 	
					String pTitle = mRequest.getParameter("pTitle");
					String pDate = mRequest.getParameter("pDate");
					String mEmail = (String)session.getAttribute("mEmail");
					int pPay = Integer.parseInt(mRequest.getParameter("pPay"));
				
					String pContent = mRequest.getParameter("pContent");
					
					String pCategory = mRequest.getParameter("pCategory");
					String pAccount = mRequest.getParameter("pAccount");
					String pBankName = mRequest.getParameter("pBankName");
					String pAcName = mRequest.getParameter("pAcName");
					
					result = pDao.insertProject(pTitle, pDate, mEmail, pPay, pContent, pImage, pCategory, pAccount, pBankName, pAcName);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
				if(result == pDao.FAIL) {
					request.setAttribute("errorMsg", "등록실패");
				}else {
					File file = new File(uploadPath+"/"+pImage);
						if(file.exists()) {
							InputStream is = null;
							OutputStream os = null;
						
							try {
								is = new FileInputStream(file);
								os = new FileOutputStream("C:/Users/502-29/Desktop/190313 (2)/WebContent/upload/"+pImage);
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
