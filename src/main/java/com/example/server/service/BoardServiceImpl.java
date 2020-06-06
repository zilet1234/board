package com.example.server.service;

import com.example.server.domain.Member;
import com.example.server.domain.Review;
import com.example.server.vo.ReviewKeyAndCateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    @Override
    public List<Member> getMembers() {
        return boardRepository.getMembers();
    }

    @Override
    public String checkName(String name) {
        if (boardRepository.checkName(name) > 0 ) {
            return "1";
        }
        return "0";
    }

    @Override
    public String checkEmail(String email) {
        if (boardRepository.checkEmail(email) > 0 ) {
            return "1";
        }
        return "0";
    }

    @Override
    public String checkMember(String email, String password) {
        Member member = boardRepository.checkMember(email, password);
        if (member == null) {
            return "NULL";
        }
        return member.name;
    }

    @Override
    public void setMember(Member member) {
        boardRepository.insertMember(member);
    }



    @Override
    public Review getReviewByView() {
        return boardRepository.getReviewByView();
    }

    @Override
    public Review getReview(int id) {
        return boardRepository.getReview(id);
    }

    @Override
    public List<Review> getReviewAll() {
        return boardRepository.getReviewAll();
    }

    @Override
    public List<Review> getReviewAll(ReviewKeyAndCateVO reviewKeyAndCateVO) {
        return boardRepository.getReviewAllForReviewKeyAndCate(reviewKeyAndCateVO);
    }

    @Override
    public List<Review> getReviewAllByView() {
        return boardRepository.getReviewAllByView();
    }
    @Override
    public List<Review> getReviewAllByViewInCate(ReviewKeyAndCateVO reviewKeyAndCateVO) {
        return boardRepository.getReviewAllByViewInCate(reviewKeyAndCateVO);
    }
    @Override
    public List<Review> getReviewAllByViewInKey(ReviewKeyAndCateVO reviewKeyAndCateVO) {
        return boardRepository.getReviewAllByViewInKey(reviewKeyAndCateVO);
    }
    @Override
    public List<Review> getReviewAllByViewInKeyAndCate(ReviewKeyAndCateVO reviewKeyAndCateVO) {
        return boardRepository.getReviewAllByViewInKeyAndCate(reviewKeyAndCateVO);
    }

    @Override
    public List<Review> getReviewAllByRating() {
        return boardRepository.getReviewAllByRating();
    }

    @Override
    public List<Review> getReviewAllByRatingInCate(ReviewKeyAndCateVO reviewKeyAndCateVO) {
        return boardRepository.getReviewAllByRatingInCate(reviewKeyAndCateVO);
    }

    @Override
    public List<Review> getReviewAllByRatingInKey(ReviewKeyAndCateVO reviewKeyAndCateVO) {
        return boardRepository.getReviewAllByRatingInKey(reviewKeyAndCateVO);
    }

    @Override
    public List<Review> getReviewAllByRatingInKeyAndCate(ReviewKeyAndCateVO reviewKeyAndCateVO) {
        return boardRepository.getReviewAllByRatingInCateAndKey(reviewKeyAndCateVO);
    }


    @Override
    public List<Review> getReviewAllByCategory(ReviewKeyAndCateVO reviewKeyAndCateVO) {
        return boardRepository.getReviewAllByCategory(reviewKeyAndCateVO);
    }

    @Override
    public List<Review> getReviewAllByKeyword(String keyword) {
        return boardRepository.getReviewAllByKeyword(keyword);
    }

    @Override
    public void setReview(Review review) {
        boardRepository.insertReview(review);
    }

    @Override
    public void updateReview(Review review) {
        boardRepository.updateReview(review);
    }

    @Override
    public void updateReviewByView(Review review) {
        boardRepository.updateReviewByView(review);
    }

    @Override
    public void deleteReview(int id) {
        boardRepository.deleteReview(id);
    }


}
