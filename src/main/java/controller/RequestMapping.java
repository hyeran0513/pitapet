package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.care.*;
import controller.like.*;
import controller.mainpage.*;
import controller.manager.*;
import controller.member.*;
import controller.pet.*;
import controller.reservation.*;
import controller.review.*;
import controller.sitter.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
    	// main
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/mainpage", new MainPageController());
        // member
        //로그인
        mappings.put("/member/login/form", new ForwardController("/member/loginForm.jsp"));
        mappings.put("/member/login", new LoginController());
        mappings.put("/member/logout", new LogoutController());
        
        mappings.put("/member/register/form", new ForwardController("/member/registerForm.jsp"));
        mappings.put("/member/memberMyPage", new MemberMyPageController());
        mappings.put("/member/updateMember", new UpdateMemberController());
        mappings.put("/member/updateProfilePic", new UpdateProfilePicController());
        mappings.put("/member/applySitter", new ApplySitterController());
        mappings.put("/member/register", new RegisterController());
        mappings.put("/member/viewSitterApply", new ViewSitterApplyController());
        mappings.put("/member/sitterApplyCancel", new CancelSitterApplyController());
        // pet
        mappings.put("/pet/addPet", new AddPetController());
        mappings.put("/pet/deletePet", new DeletePetController());
        mappings.put("/pet/listPet", new ListPetController());
        // sitter
        mappings.put("/petSitter/registerSitter", new RegisterSitterController());
        // reservation
        mappings.put("/reservation/listSitter", new ListSitterController());
        mappings.put("/reservation/viewSitterDetail", new ViewSitterDetailController());
        mappings.put("/reservation/reserve", new ReserveController());
        mappings.put("/reservation/viewReservation", new ViewReservationController());
        mappings.put("/reservation/cancelReservation", new CancelReservationController());
        // care
        mappings.put("/care/listCareDiary", new CareDiaryListController());
        mappings.put("/care/recordCare", new RecordCareController());
        // review
        mappings.put("/review/listReview", new ListReviewController());
        mappings.put("/review/addReview", new AddReviewController());
        // like
        mappings.put("/like/listLike", new ListLikeController());
        mappings.put("/like/changeLike", new LikeController());
        // manager
        mappings.put("/manager/listSitterApply", new ListSitterApplyController());
        mappings.put("/manager/viewApply", new ViewApplyController());
        mappings.put("/manager/updateStatus", new UpdateStatusController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
