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

	/* 전체 리뷰 반환 */
	public List<Review> findReviewList() throws SQLException {
		String find_review_sql = "SELECT review_id, write_date, content, rate, c.care_id, c.member_id, c.sitter_id, address, img_src "
				+ "FROM review r JOIN care c ON (r.care_id = c.care_id) "
				+ "JOIN member m ON (c.sitter_id = m.member_id) "
				+ "JOIN attachment atm ON (m.member_id = atm.member_id) "
				+ "WHERE atm.category_id = 'AtchId04'";
		jdbcUtil.setSqlAndParameters(find_review_sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				List<Review> reviews = new ArrayList<Review>();
				Review review = new Review(rs.getString("review_id"), rs.getString("write_date"),
						rs.getString("content"), rs.getFloat("rate"),
						new Care(rs.getInt("care_id"), new Member(rs.getString("member_id")),
								new PetSitter(new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")))));
				reviews.add(review);
				while (rs.next()) {
					review = new Review(rs.getString("review_id"), rs.getString("write_date"), rs.getString("content"),
							rs.getFloat("rate"), new Care(rs.getInt("care_id"), new Member(rs.getString("member_id")),
									new PetSitter(new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")))));
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

	/* 선택한 돌보미의 리뷰 반환 */
	public List<Review> findReviewListOfSitter(String sitterId) throws SQLException {
		String sql = "SELECT review_id, write_date, content, rate, care_id, c.member_id, c.sitter_id, m.address, img_src "
				+ "FROM review r JOIN care c USING (care_id) " + "JOIN member m ON (c.sitter_id = m.member_id) "
				+ "JOIN attachment atm ON (m.member_id = atm.member_id) "
				+ "WHERE sitter_id = ? AND atm.category_id = 'AtchId04'";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { sitterId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Review> reviews = new ArrayList<Review>();
			if (rs.next()) {
				Review review = new Review(rs.getString("review_id"), rs.getString("write_date"),
						rs.getString("content"), rs.getFloat("rate"),
						new Care(rs.getInt("care_id"), new Member(rs.getString("member_id")),
								new PetSitter(new Member(rs.getString("sitter_id"), rs.getString("address"),
										rs.getString("img_src")))));
				reviews.add(review);
				while (rs.next()) {
					review = new Review(rs.getString("review_id"), rs.getString("write_date"), rs.getString("content"),
							rs.getFloat("rate"),
							new Care(rs.getInt("care_id"), new Member(rs.getString("member_id")),
									new PetSitter(new Member(rs.getString("sitter_id"), rs.getString("address"),
											rs.getString("img_src")))));
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

	/* 리뷰 첨부파일 반환 */
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
	
	/* 특정 돌봄 내역에 대하여 리뷰가 존재하는지 확인 */
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
	
	/* 리뷰 추가 */
	public int add(Review review) throws SQLException {
		String sql = "INSERT INTO review (review_id, write_date, content, rate, care_id) " 
				+ "VALUES (review_seq.NEXTVAL, SYSDATE, ?, ?, ?)";
		
		Object[] obj = new Object[] {review.getContent(), review.getRate(), review.getCareInfo().getId()};
		jdbcUtil.setSqlAndParameters(sql, obj);
		
		try {
			int recordCount = jdbcUtil.executeUpdate();

			return recordCount;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	/* 리뷰 첨부파일(이미지) 추가 */
	public int addAttachment(String imgSrc, String memberId) throws SQLException {
		String sql = "INSERT INTO attachment (img_src, member_id, category_id) " 
				+ "VALUES (?, ?, 'AtchId03')";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { imgSrc, memberId });
		
		try {
			int recordCount = jdbcUtil.executeUpdate();

			return recordCount;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}	

	/* 별점 평균 업데이트 */
	public int updateAvgRate(String sitterId, Float rate) throws SQLException {
		String sql = "UPDATE petsitter "
				+ "SET avg_rate = ("
				+ "SELECT (SUM(rate) + " + rate + ") / (COUNT(review_id) + 1)"
				+ "FROM review JOIN care USING (care_id)"
				+ "WHERE sitter_id = ?)";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] { sitterId });
		try {
			int recordCount = jdbcUtil.executeUpdate();

			return recordCount;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
}
