package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.MemberDAO;
//import model.dao.ReviewDAO;
import model.dto.Member;
import model.service.exception.ExistingIdException;
import model.service.exception.MemberNotFoundException;
import model.service.exception.PasswordCkMismatchException;
import model.service.exception.PasswordMismatchException;

public class MemberManager {
   private static MemberManager userMan = new MemberManager();
   private MemberDAO memberDAO;
//   private ReviewDAO reviewDAO;
   
   private MemberManager() {
      try {
         memberDAO = new MemberDAO();
//         reviewDAO = new ReviewDAO();
      } catch (Exception e) {
         e.printStackTrace();
      }         
   }
   
   public static MemberManager getInstance() {
      return userMan;
   }
      
   public MemberDAO getMemberDAO() {
      return this.memberDAO;
   }
   
   public boolean login(String memberId, String password)
         throws SQLException, MemberNotFoundException, PasswordMismatchException {
         Member member = memberDAO.findMember(memberId);

         if (!member.matchPassword(password)) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
         }
         return true;
      }
   
   public Member findMember(String memberId) throws SQLException {
	   return memberDAO.findMember(memberId);
   }
      
   public String findProfileAttachment(String memberId) throws SQLException {
		return (String) memberDAO.findProfileAttachment(memberId);
	}
   
   public int createMember(Member newMember) throws SQLException, ExistingIdException {
		if (memberDAO.findMember(newMember.getId()) != null) {
			throw new ExistingIdException(newMember.getId() + "는 존재하는 아이디입니다.");
		}
		
		return memberDAO.createMember(newMember);
	}
}