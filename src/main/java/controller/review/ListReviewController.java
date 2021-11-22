package controller.review;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.Member;
import model.dto.Review;
import model.service.MemberManager;
import model.service.ReviewManager;

public class ListReviewController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReviewManager reviewMan = ReviewManager.getInstance();
		MemberManager memMan = MemberManager.getInstance();
		
		List<Review> reviews = reviewMan.findReviewList();
		
		for (Review review : reviews) {
			Member sitterMemInfo = memMan.findMember(review.getCareInfo().getSitter().getSitter().getId());
			String[] address = sitterMemInfo.getAddress().split(" ");
			System.out.println(Arrays.toString(address));
			String city = null;
			for (int j = 0; j < address.length; j++) {
				if (address[j].matches("(.*)로")) {
					city = address[j].substring(0, address[j].length() - 1);
					//System.out.println(city);
				}
			}
			review.getCareInfo().getSitter().getSitter().setAddress(city);
			
			List<String> imgList = reviewMan.findReviewAttachments(review.getCareInfo().getCompanion().getId(), review.getCareInfo().getId());
			review.setImages(imgList);
		}
		request.setAttribute("reviews", reviews);
		
		return "/review/reviewList.jsp";
	}
}
