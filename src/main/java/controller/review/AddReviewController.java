package controller.review;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.naming.SizeLimitExceededException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.Review;
import model.service.ReviewManager;

public class AddReviewController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ReviewManager reviewMan = ReviewManager.getInstance();
		
		String memberId = UserSessionUtils.getLoginUserId(session);
		String content = null;
		String careId = null;
		String sitterId = null;
		String rate = null;
		ArrayList<String> files = new ArrayList<>();
		
		// 리뷰 추가 처리
		boolean check = ServletFileUpload.isMultipartContent(request);
		if(check) {//파일 전송이 포함된 상태가 맞다면
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			File dir = new File(path);
			
			if(!dir.exists()) dir.mkdir();
			//전송된 파일을 저장할 실제 경로를 만든다.
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
                //파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
                factory.setSizeThreshold(10 * 1024);
                //메모리에 한번에 저장할 데이터의 크기를 설정한다.
                //10kb 씩 메모리에 데이터를 읽어 들인다.
                factory.setRepository(dir);
                //전송된 데이터의 내용을 저장할 임시 폴더를 지정한다.                
    
                ServletFileUpload upload = new ServletFileUpload(factory);
                //Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
                upload.setSizeMax(10 * 1024 * 1024);
                //업로드 될 파일의 최대 용량을 10MB까지 전송 허용한다.
                upload.setHeaderEncoding("utf-8");
                //업로드 되는 내용의 인코딩을 설정한다.
                                
                List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
                
                //upload 객체에 전송되어 온 모든 데이터를 Collection 객체에 담는다.
                int cnt = 1;
                for(int i = 0; i < items.size(); ++i) {
                	FileItem item = (FileItem)items.get(i);
                	//commons-fileupload를 사용하여 전송받으면 
                	//모든 parameter는 FileItem 클래스에 하나씩 저장된다.
                	
                	String value = item.getString("utf-8");
                	//넘어온 값에 대한 한글 처리를 한다.
                	
                	if(item.isFormField()) {//일반 폼 데이터라면...
                		if(item.getFieldName().equals("content")) 
                			content = value;
                		else if(item.getFieldName().equals("careId")) 
                			careId = value;
                		else if(item.getFieldName().equals("sitterId"))
                			sitterId = value;
                		else if(item.getFieldName().equals("reviewStar"))
                			rate = value;
                	}
                	else {//파일이라면...
                		if(item.getFieldName().equals("picture")) {
                		//key 값이 picture이면 파일 저장을 한다.
                			String filename = item.getName();//파일 이름 획득 (자동 한글 처리 됨)
                			if(filename == null || filename.trim().length() == 0) continue;
                			//파일이 전송되어 오지 않았다면 건너 뛴다.
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			//파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
                			//실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드이다.
                			File file = new File(dir, filename);
                			String ext = filename.substring(filename.lastIndexOf( "." ));
                			String newFilename = "care-" + careId + "-" + cnt + ext;
                			File newFile = new File(dir, newFilename);
                			item.write(file);
                			file.renameTo(newFile);
                			//파일을 upload 경로에 실제로 저장한다.
                			files.add(newFilename);
                			cnt++;
                		}
                	}
                }
                
                // 리뷰 정보 저장 후 추가 처리
                Review review = new Review(content, Float.parseFloat(rate), 
    					new Care(Integer.parseInt(careId)), files);
            	boolean isAdded = reviewMan.add(review, memberId, sitterId);
            	if (!isAdded)
            		request.setAttribute("addFailed", true);
			}catch(SizeLimitExceededException e) {
			//업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
				e.printStackTrace();           
            }catch(FileUploadException e) {
            //파일 업로드와 관련되어 발생할 수 있는 예외 처리
                e.printStackTrace();
            }catch(Exception e) {            
                e.printStackTrace();
            }
		}
		return "redirect:/reservation/viewSitterDetail?sitterId=" + sitterId;
	}
}
