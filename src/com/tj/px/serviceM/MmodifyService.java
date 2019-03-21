package com.tj.px.serviceM;

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
import com.tj.px.dao.MemberDao;
import com.tj.px.dto.MemberDto;
import com.tj.px.service.Service;

public class MmodifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String path = request.getRealPath("memberImg");
		MemberDao dao = MemberDao.getInstance();
		HttpSession session = request.getSession();
		int maxSize = 1024*1024*10; // 최대 10mb
		String mImage = "";
		MultipartRequest mRequest = null;
		int result = 0;
		try {
			mRequest = new MultipartRequest(request, path,maxSize,"utf-8",new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			mImage = mRequest.getFilesystemName(param);
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
			MemberDto dto = (MemberDto)session.getAttribute("mDto");
			String mEmail = dto.getmEmail();
			String mName = mRequest.getParameter("mName");
			if(mName.equals("")) {
				mName = dto.getmName();
			}
			String sample6_postcode = mRequest.getParameter("sample6_postcode");
			String sample6_address = mRequest.getParameter("sample6_address");
			String sample6_detailAddress = mRequest.getParameter("sample6_detailAddress");
			String sample6_extraAddress = mRequest.getParameter("sample6_extraAddress");
			String mSelf = mRequest.getParameter("mSelf");
			if(mSelf.equals("")) {
				mSelf = dto.getmSelf();
			}
			String mPhone1 = mRequest.getParameter("mPhone1");
			if(mPhone1.equals("")) {
				mPhone1 = dto.getmPhone1();
			}
			String mPhone2 = mRequest.getParameter("mPhone2");
			if(mPhone2.equals("")) {
				mPhone2 = dto.getmPhone2();
			}
			String mPhone3 = mRequest.getParameter("mPhone3");
			if(mPhone3.equals("")) {
				mPhone3 = dto.getmPhone3();
			}
			if(mImage.equals("")) {
				mImage = "NoImg.jpg";
			}
			String mLoc = sample6_postcode+sample6_address+sample6_extraAddress+sample6_detailAddress;
			if(mLoc.equals("")) {
				mLoc = dto.getmLoc();
			}
		
			result = dao.modify(mName, mLoc, mImage, mSelf, mPhone1, mPhone2, mPhone3, mEmail);
			if(result == dao.FAIL) {
				request.setAttribute("errorMsg", "정보 수정 실패");
			}else {
				File file = new File(path+"/"+mImage);
				if(file.exists()) {
				InputStream is = null;
				OutputStream os = null;
				try {
					is = new FileInputStream(file);
					os = new FileOutputStream("C:/Users/502-29/Desktop/190313 (2)/WebContent/memberImg/"+mImage);				
					byte[] bs = new byte[(int)file.length()];
					while(true) {
						int nByteCnt = is.read(bs);
						if(nByteCnt == -1) {
							break;
						}
						os.write(bs,0 ,nByteCnt);
					}
				} catch (Exception e) {
					System.out.println("filecopy"+e.getMessage());
				}finally {
					try {
						if(os != null) os.close();
						if(is != null) is.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				}
				request.setAttribute("Msg", "수정완료");
				dto = dao.selectMember(mEmail);
				session.setAttribute("mDto", dto);
				
			}
	}

}
