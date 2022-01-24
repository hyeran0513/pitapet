package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.dao.mybatis.mapper.MemberMapper;
import model.dto.Member;


public class MemberDAO {

private SqlSessionFactory sqlSessionFactory;
	
	public MemberDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/* 특정 회원정보 반환 */
	public Member findMember(String memberId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(MemberMapper.class).selectMember(memberId);

		} finally {
			sqlSession.close();
		}
	}
	
	/* 회원정보 생성 */
	public int createMember(Member newMember) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(MemberMapper.class).createMember(newMember);
			
			if(result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 회원정보 업데이트 */
	public int update(Member updateInfo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(MemberMapper.class).updateMember(updateInfo);
			
			if(result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	/* 프로필 사진 삭제 */
	public int deleteProfilePic(String memberId, String like) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			int result = sqlSession.getMapper(MemberMapper.class).deleteProfilePic(memberId, like);
			
			if(result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	/* 프로필 사진 추가 */
	public int addAttachment(String image, String memberId) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			int result = sqlSession.getMapper(MemberMapper.class).addAttachment(memberId, image);
			
			if(result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
}
