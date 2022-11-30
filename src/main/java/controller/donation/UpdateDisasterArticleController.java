package controller.donation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.AnimalArticle;
import model.DisasterArticle;
import model.DonationImage;
import model.service.AnimalManager;
import model.service.DisasterManager;
import model.service.UserNotFoundException;

public class UpdateDisasterArticleController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(UpdateDisasterArticleController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			String articleIdStr = request.getParameter("articleId");
			int articleId = Integer.parseInt(articleIdStr);
			
			DisasterManager manager = DisasterManager.getInstance();
			DisasterArticle article = manager.findDisasterArticleByArticleId(articleId);
			request.setAttribute("article", article);
			
			return "/donationForm/disasterUpdateForm.jsp";  
	    }
		
		//LocalDate now  = LocalDate.now();
    	//String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		int articleId = 0;
		DisasterArticle disaster = new DisasterArticle();
		List<DonationImage> imageList = new ArrayList<DonationImage>();
		
		String filename = null;
		int fileOrder = 1;
		
		boolean check = ServletFileUpload.isMultipartContent(request);              
        if(check) {    // 전송된 요청 메시지의 타입이 multipart 인지 여부를 체크한다. (multipart/form-data)
            // 아래와 같이 하면 Tomcat 내부에 복사된 프로젝트 밑에 upload 폴더가 생성됨 
            ServletContext context = request.getServletContext();
//            String path = context.getRealPath("/upload");
            String path = context.getRealPath("");
            path = path+"..\\..\\..\\..\\..\\..\\donationSystem\\src\\main\\webapp\\upload";
            
            File dir = new File(path);          
            // Tomcat 외부의 폴더에 저장하려면 아래와 같이 절대 경로로 폴더 이름을 지정함
            // File dir = new File("C:/Temp");
                        
            if (!dir.exists()) dir.mkdir();  // 전송된 파일을 저장할 폴더 생성
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                    // 파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
                factory.setSizeThreshold(10 * 1024);
                    // 메모리에 한번에 저장할 데이터의 크기를 10KB로 설정한다.
                factory.setRepository(dir);
                    // 전송된 데이터의 내용을 저장할 폴더를 지정한다.                
                
                ServletFileUpload upload = new ServletFileUpload(factory);
                    // Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
                upload.setSizeMax(10 * 1024 * 1024);
                    // 업로드 될 파일의 최대 용량을 10MB로 설정한다.
                upload.setHeaderEncoding("utf-8");
                    // 업로드 되는 내용의 인코딩 방식을 설정한다.
                                
                List<FileItem> items = (List<FileItem>)upload.parseRequest(request);                
                    // upload 객체에 전송된 모든 데이터(요청 파라미터)를 Collection 객체에 담는다.
                log.debug("step");
                for (int i = 0; i < items.size(); ++i) {
                    FileItem item = (FileItem)items.get(i);            
                    String value = item.getString("utf-8"); // 넘어온 값에 대한 한글 처리를 한다.                 
                    if (item.isFormField()) {  // item이 일반 데이터인 경우
                    	 if(item.getFieldName().equals("articleId")) {
                    		articleId = Integer.parseInt(value);
                         	disaster.setArticleId(articleId);
                    	 }
                    	 else if(item.getFieldName().equals("title"))
                    		disaster.setTitle(value);
                         else if(item.getFieldName().equals("deadline"))
                        	disaster.setDeadline(value);
                         else if(item.getFieldName().equals("bank_name"))
                        	disaster.setBankName(value);
                         else if(item.getFieldName().equals("acc_holder"))
                        	disaster.setAccHolder(value);
                         else if(item.getFieldName().equals("acc_num"))
                        	disaster.setAccNum(value);
                         else if(item.getFieldName().equals("due_date"))
                        	disaster.setDueDate(value);
                         else if(item.getFieldName().equals("use_plan"))
                        	disaster.setUsePlan(value);
                         else if(item.getFieldName().equals("other_text"))
                        	disaster.setOtherText(value);
                         //AnimalArticle 필드
                         else if(item.getFieldName().equals("name"))
                        	disaster.setName(value);
                         else if(item.getFieldName().equals("area"))
                        	disaster.setArea(value);
                         else if(item.getFieldName().equals("type"))
                         	disaster.setType(value);
                         else if(item.getFieldName().equals("damage_amount"))
                        	disaster.setDamageAmount(Integer.parseInt(value));
                         else if(item.getFieldName().equals("situation"))
                        	disaster.setSituation(value);
                    }
                    else {  // item이 파일인 경우   
                        if (item.getFieldName().equals("image")) {
                            // parameter 이름이 image이면 파일 저장을 한다.
                            String oriFilename = item.getName();    // 파일 이름 획득 (자동 한글 처리 됨)
                            if (oriFilename == null || oriFilename.trim().length() == 0) continue;
                                // 파일이 전송되어 오지 않았다면 건너뜀
                            
                            // filename = oriFileName.substring(oriFileName.lastIndexOf("\\") + 1);                                          
                                // 파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
                                // 실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드임
                            
                            filename = UUID.randomUUID().toString() + "_" 
                                    + oriFilename.substring(oriFilename.lastIndexOf("\\") + 1);  
                                // 파일 이름이 중복되지 않도록 UUID(Universally Unique IDentifier)를 생성해서 원래의 이름 앞에 붙임
                                
                            // 또는 아래와 같이 원래의 파일의 확장자만 추출해서 UUID와 함께 파일 이름 생성
                            // filename = UUID.randomUUID().toString() 
                            //      + oriFilename.substring(oriFilename.lastIndexOf("."));
                            
                            //DonationImage 생성 후 List<DonationImage>에 저장 
                            DonationImage image = new DonationImage();
                            image.setArticleId(articleId); 
                            image.setFileOrder(fileOrder++);
                            image.setFileName(filename);
                            image.setArticle(disaster);
                            imageList.add(image);
                            log.debug("Image: {}", image);
                            
                            System.out.println("uploaded file: " + filename);
                            File file = new File(dir, filename);
                            item.write(file);
                                // 파일을 upload 경로에 실제로 저장한다.
                                // FileItem 객체로부터 바로 출력 저장할 수 있다.
                        }
                    }
                }
            } catch(SizeLimitExceededException e) {
                // 업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
                e.printStackTrace();           
            } catch(FileUploadException e) {
                // 파일 업로드와 관련되어 발생할 수 있는 예외 처리
                e.printStackTrace();
            } catch(Exception e) {            
                e.printStackTrace();
            }
            
            disaster.setArticleId(articleId);
            disaster.setCategory("disaster"); 
            disaster.setIdCheck("Y"); 
            disaster.setCreateDate(null);
            disaster.setUpdateDate(null);
            disaster.setReceiptCheck("N");
            disaster.setTotalAmount(0); 
            disaster.setUserId(userId); 
            
            disaster.setImageList(imageList);
            
            log.debug("DisasterArticle Controller: {}", disaster);
        }      
    	 
    	 try {
    		 DisasterManager manager = DisasterManager.getInstance();
    		 manager.update(disaster);
             
             //DONATION_IMAGE 테이블에 레코드 생성
             for (int i=0; i<imageList.size(); i++)
                 manager.update_image(imageList.get(i));
    		 return "redirect:/donationList/disaster?articleId="+articleId; 
    	 }catch (Exception e) {
    		 request.setAttribute("createFailed", true);
    		 request.setAttribute("exception", e);
    		 request.setAttribute("disaster", disaster);
    		 return "/donationForm/disasterUpdateForm.jsp"; 
		}
		
	}

}
