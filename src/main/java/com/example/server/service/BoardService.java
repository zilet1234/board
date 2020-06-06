package com.example.server.service;

import com.example.server.domain.Member;
import com.example.server.domain.Review;
import com.example.server.vo.ReviewKeyAndCateVO;

import java.util.HashMap;
import java.util.List;

public interface BoardService {
    List<Member> getMembers();

    void setMember(Member member);

    String checkName(String name);

    String checkEmail(String email);

    String checkMember(String email, String password);


    Review getReviewByView();

    Review getReview(int id);


    List<Review> getReviewAll();

    List<Review> getReviewAll(ReviewKeyAndCateVO reviewKeyAndCateVO);


    List<Review> getReviewAllByView();

    List<Review> getReviewAllByViewInCate(ReviewKeyAndCateVO reviewKeyAndCateVO);

    List<Review> getReviewAllByViewInKey(ReviewKeyAndCateVO reviewKeyAndCateVO);

    List<Review> getReviewAllByViewInKeyAndCate(ReviewKeyAndCateVO reviewKeyAndCateVO);


    List<Review> getReviewAllByRating();

    List<Review> getReviewAllByRatingInCate(ReviewKeyAndCateVO reviewKeyAndCateVO);

    List<Review> getReviewAllByRatingInKey(ReviewKeyAndCateVO reviewKeyAndCateVO);

    List<Review> getReviewAllByRatingInKeyAndCate(ReviewKeyAndCateVO reviewKeyAndCateVO);


    List<Review> getReviewAllByCategory(ReviewKeyAndCateVO reviewKeyAndCateVO);

    List<Review> getReviewAllByKeyword(String keyword);


    void setReview(Review review);

    void updateReview(Review review);

    void updateReviewByView(Review review);

    void deleteReview(int id);
}
