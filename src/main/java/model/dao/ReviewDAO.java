package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Review;
import model.dto.Care;
import model.dto.Member;
import model.dto.PetSitter;

public class ReviewDAO {

	private JDBCUtil jdbcUtil = null;

	public ReviewDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	// 전체 리뷰 반환
	public List<Review> findReviewList() throws SQLException {
		String sql = "SELECT review_id, write_date, content, rate, care_id, member_id, sitter_id "
				+ "FROM review JOIN care USING (care_id)";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Review> reviews = new ArrayList<Review>();
			while (rs.next()) {
				Review review = new Review(rs.getString("review_id"), rs.getString("write_date"),
						rs.getString("content"), rs.getFloat("rate"),
						new Care(rs.getInt("care_id"), new Member(rs.getString("member_id")),
								new PetSitter(new Member(rs.getString("sitter_id")))));
				reviews.add(review);
			}
			return reviews;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	// 선택한 sitter의 리뷰 반환
	public List<Review> findReviewListOfSitter(String sitterId) throws SQLException {
		String sql = "SELECT review_id, write_date, content, rate, care_id, member_id, sitter_id "
				+ "FROM review JOIN care USING (care_id) " + "WHERE sitter_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { sitterId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Review> reviews = new ArrayList<Review>();
			if (rs.next()) {
				Review review = new Review(rs.getString("review_id"), rs.getString("write_date"),
						rs.getString("content"), rs.getFloat("rate"),
						new Care(rs.getInt("care_id"), new Member(rs.getString("member_id")),
								new PetSitter(new Member(rs.getString("sitter_id")))));
				reviews.add(review);
				while (rs.next()) {
					review = new Review(rs.getString("review_id"), rs.getString("write_date"), rs.getString("content"),
							rs.getFloat("rate"), new Care(rs.getInt("care_id"), new Member(rs.getString("member_id")),
									new PetSitter(new Member(rs.getString("sitter_id")))));
					reviews.add(review);
				}
				return reviews;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	// 리뷰 첨부파일 반환
	public List<String> findReviewAttachments(String memberId, int careId) throws SQLException {
		String sql = "SELECT img_src " + "FROM attachment " + "WHERE member_id=? AND category_id=? AND img_src LIKE ?";

		String like = "%care-" + Integer.toString(careId) + "-%";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId, "AtchId03", like }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<String> imgList = new ArrayList<String>();
			while (rs.next()) {
				String img_src = rs.getString("img_src");
				imgList.add(img_src);
			}
			return imgList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	// 선택한 sitter의 리뷰 반환
	public boolean isExistReview(Integer careId) throws SQLException {
		String sql = "SELECT review_id "
				+ "FROM review JOIN care USING (care_id) " 
				+ "WHERE care_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { careId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next())
				return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}
	
}
