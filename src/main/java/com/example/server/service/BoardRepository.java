package com.example.server.service;

import com.example.server.domain.Member;
import com.example.server.domain.Review;
import com.example.server.vo.ReviewKeyAndCateVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface BoardRepository {


    // Member

    List<Member> getMembers();

    int checkName (String name);

    int checkEmail (String email);

    Member checkMember(String email, String password);

    void insertMember(Member member);


    // Review

    Review getReviewByView();

    Review getReview(int id);


    List<Review> getReviewAll();

    List<Review> getReviewAllForReviewKeyAndCate(ReviewKeyAndCateVO reviewKeyAndCateVO);


    List<Review> getReviewAllByView();

    List<Review> getReviewAllByViewInCate(ReviewKeyAndCateVO reviewKeyAndCateVO);

    List<Review> getReviewAllByViewInKey(ReviewKeyAndCateVO reviewKeyAndCateVO);

    List<Review> getReviewAllByViewInKeyAndCate(ReviewKeyAndCateVO reviewKeyAndCateVO);


    List<Review> getReviewAllByRating();

    List<Review> getReviewAllByRatingInCate(ReviewKeyAndCateVO reviewKeyAndCateVO);

    List<Review> getReviewAllByRatingInKey(ReviewKeyAndCateVO reviewKeyAndCateVO);

    List<Review> getReviewAllByRatingInCateAndKey(ReviewKeyAndCateVO reviewKeyAndCateVO);


    List<Review> getReviewAllByCategory(ReviewKeyAndCateVO reviewKeyAndCateVO);

    List<Review> getReviewAllByKeyword(String keyword);

    void insertReview(Review review);

    void updateReview(Review review);

    void updateReviewByView(Review review);

    void deleteReview(int id);

}
