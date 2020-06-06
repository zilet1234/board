package com.example.server.controller;

import com.example.server.domain.Member;
import com.example.server.domain.Review;
import com.example.server.service.BoardService;
import com.example.server.vo.ReviewKeyAndCateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    /* MEMBER */

    @CrossOrigin("*")
    @RequestMapping(value = "/member", method = {RequestMethod.GET, RequestMethod.POST})
    public String member(){
        List<Member> members = boardService.getMembers();

        StringBuffer stringBuffer = new StringBuffer();
        for (Member member :  members) {
            stringBuffer.append(member.name);
            stringBuffer.append("|");
            stringBuffer.append(member.email);
            stringBuffer.append("|");
            stringBuffer.append(member.password);
        }
        return stringBuffer.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/setMember", method = RequestMethod.POST)
    public String setMember(@RequestBody String body) throws Exception {
        String memberOfElement[] = body.replace("=", "").split("\\|");

        Member member = new Member(0, memberOfElement[0], memberOfElement[1], memberOfElement[2]);
        try {
            boardService.setMember(member);
        } catch (Exception e) {
            throw new Exception("Error");
        }

        return "success";
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/checkName", method = RequestMethod.POST)
    public String checkName(@RequestBody String body) throws Exception {
        String name = body.replace("=", "");
        return boardService.checkName(name.trim());
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    public String checkEmail(@RequestBody String body) throws Exception {
        String email = body.replace("=", "");
        return boardService.checkEmail(email.trim());
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/checkMember", method = RequestMethod.POST)
    public String checkMember(@RequestBody String body) throws Exception {
        String memberOfElement[] = body.replace("=", "").split("\\|");
        System.out.println(memberOfElement[0] + ":" + memberOfElement[1] );
        return boardService.checkMember(memberOfElement[0].trim(), memberOfElement[1].trim());
    }


    /* REVIEW */

    @CrossOrigin("*")
    @RequestMapping(value = "/getReviewByView", method = {RequestMethod.POST, RequestMethod.GET})
    public String getReviewByView() throws Exception {

        Review review = boardService.getReviewByView();

        if (review == null) {
            return "NULL";
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(review.name + "|");
        stringBuffer.append(review.category + "|");
        stringBuffer.append(review.place + "|");
        stringBuffer.append(review.content + "|");
        stringBuffer.append(review.rating + "|");
        stringBuffer.append(review.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|");
        stringBuffer.append(review.view);

        return stringBuffer.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getReview", method = {RequestMethod.POST, RequestMethod.GET})
    public String getReview(@RequestBody String body) throws Exception {
        String id = body.replace("=", "");
        Review review = boardService.getReview(Integer.parseInt(id));

        if (review == null) {
            return "NULL";
        }

        StringBuffer stringBuffer = new StringBuffer();
        if ( review != null) {
            stringBuffer.append(review.name + "|");
            stringBuffer.append(review.category + "|");
            stringBuffer.append(review.place + "|");
            stringBuffer.append(review.content + "|");
            stringBuffer.append(review.rating + "|");
            stringBuffer.append(review.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|");
            stringBuffer.append(review.view);
        }
        return stringBuffer.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getReviewAll", method = RequestMethod.POST)
    public String getReviewAll(@RequestBody String body) throws Exception {
        String elementOfReview = body.replace("=", "");

        StringBuffer stringBuffer = new StringBuffer();
        List<Review> reviews = null;
        if (elementOfReview.trim().equals("NONE")) {
            reviews = boardService.getReviewAll();
            if (reviews == null || reviews.size() == 0) {
                return "NULL";
            }
        } else {

            String elements[] = elementOfReview.split("\\|");

            List<Integer> categoriList = new ArrayList<>();

            for(int i=1; i<elements.length; i++){
                categoriList.add(Integer.parseInt(elements[i]));
            }

            reviews = boardService.getReviewAll(new ReviewKeyAndCateVO(elements[0], categoriList));
        }

        int cnt = 0;
        for (Review review : reviews) {
            stringBuffer.append(review.id + "|");
            stringBuffer.append(review.name + "|");
            stringBuffer.append(review.category + "|");
            stringBuffer.append(review.place + "|");
            stringBuffer.append(review.content + "|");
            stringBuffer.append(review.rating + "|");
            stringBuffer.append(review.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|");
            stringBuffer.append(review.view);
            cnt++;

            if (cnt != reviews.size()) {
                stringBuffer.append("@@");
            }
        }

        return stringBuffer.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getReviewAllByView", method = RequestMethod.POST)
    public String getReviewAllByView(@RequestBody String body) throws Exception {

        String elementOfReview = body.replace("=", "");

        StringBuffer stringBuffer = new StringBuffer();
        List<Review> reviews = null;

        if (elementOfReview.trim().equals("NONE") ) {
            reviews = boardService.getReviewAllByView();
        } else {
            String elements[] = elementOfReview.split("\\|");

            List<Integer> categoriList = new ArrayList<>();

            switch (elements[0]){
                case "1":
                    for(int i=1; i<elements.length; i++){
                        categoriList.add(Integer.parseInt(elements[i]));
                    }
                    reviews = boardService.getReviewAllByViewInCate(new ReviewKeyAndCateVO(null, categoriList ));
                    break;
                case "2":
                    reviews = boardService.getReviewAllByViewInKey(new ReviewKeyAndCateVO(elements[1], null));
                    break;
                case "3":
                    for(int i=2; i<elements.length; i++){
                        categoriList.add(Integer.parseInt(elements[i]));
                    }
                    reviews = boardService.getReviewAllByViewInKeyAndCate(new ReviewKeyAndCateVO(elements[1], categoriList ));
                    break;
            }
        }

        if (reviews == null || reviews.size() == 0) {
            return "NULL";
        }

        int cnt = 0;
        for (Review review : reviews) {
            stringBuffer.append(review.id + "|");
            stringBuffer.append(review.name + "|");
            stringBuffer.append(review.category + "|");
            stringBuffer.append(review.place + "|");
            stringBuffer.append(review.content + "|");
            stringBuffer.append(review.rating + "|");
            stringBuffer.append(review.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|");
            stringBuffer.append(review.view);
            cnt ++;

            if (cnt != reviews.size() ){
                stringBuffer.append("@@");
            }
        }

        return stringBuffer.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getReviewAllByRating", method = RequestMethod.POST)
    public String getReviewAllByRating(@RequestBody String body) throws Exception {

        String elementOfReview = body.replace("=", "");

        StringBuffer stringBuffer = new StringBuffer();
        List<Review> reviews = null;

        if ( elementOfReview.trim().equals("NONE") ) {
            reviews = boardService.getReviewAllByRating();
        } else {

            String elements[] = elementOfReview.split("\\|");

            List<Integer> categoriList = new ArrayList<>();

            switch (elements[0]){
                case "1":
                    for(int i=1; i<elements.length; i++){
                        categoriList.add(Integer.parseInt(elements[i]));
                    }
                    reviews = boardService.getReviewAllByRatingInCate(new ReviewKeyAndCateVO(null, categoriList));
                    break;
                case "2":
                    reviews = boardService.getReviewAllByRatingInKey(new ReviewKeyAndCateVO(elements[1], null));
                    break;
                case "3":
                    for(int i=2; i<elements.length; i++){
                        categoriList.add(Integer.parseInt(elements[i]));
                    }
                    reviews = boardService.getReviewAllByRatingInKeyAndCate(new ReviewKeyAndCateVO(elements[1], categoriList));
                    break;
            }

        }

        if (reviews == null || reviews.size() == 0) {
            return "NULL";
        }

        int cnt = 0;
        for (Review review : reviews) {
            stringBuffer.append(review.id + "|");
            stringBuffer.append(review.name + "|");
            stringBuffer.append(review.category + "|");
            stringBuffer.append(review.place + "|");
            stringBuffer.append(review.content + "|");
            stringBuffer.append(review.rating + "|");
            stringBuffer.append(review.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|");
            stringBuffer.append(review.view);
            cnt ++;

            if (cnt != reviews.size() ){
                stringBuffer.append("@@");
            }
        }

        return stringBuffer.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getReviewAllByCategory", method = RequestMethod.POST)
    public String getReviewAllByCategory(@RequestBody String body) throws Exception {

        String categories[] = body.replace("=", "").split("\\|");

        List<Integer> categoriList = new ArrayList<>();

        for(int i=0; i<categories.length; i++) {
            categoriList.add(Integer.parseInt(categories[i]));
        }

        List<Review> reviews = boardService.getReviewAllByCategory(new ReviewKeyAndCateVO(null, categoriList));

        if (reviews == null || reviews.size() == 0) {
            return "NULL";
        }

        StringBuffer stringBuffer = new StringBuffer();

        int cnt = 0;
        for (Review review : reviews) {
            stringBuffer.append(review.id + "|");
            stringBuffer.append(review.name + "|");
            stringBuffer.append(review.category + "|");
            stringBuffer.append(review.place + "|");
            stringBuffer.append(review.content + "|");
            stringBuffer.append(review.rating + "|");
            stringBuffer.append(review.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|");
            stringBuffer.append(review.view);
            cnt ++;

            if (cnt != reviews.size() ){
                stringBuffer.append("@@");
            }
        }

        return stringBuffer.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getReviewAllByKeyword", method = RequestMethod.POST)
    public String getReviewAllByKeyword(@RequestBody String body) throws Exception {

        String rating = body.replace("=", "");
        List<Review> reviews = boardService.getReviewAllByKeyword(rating);

        if (reviews == null || reviews.size() == 0) {
            return "NULL";
        }

        StringBuffer stringBuffer = new StringBuffer();

        int cnt = 0;
        for (Review review : reviews) {
            stringBuffer.append(review.id + "|");
            stringBuffer.append(review.name + "|");
            stringBuffer.append(review.category + "|");
            stringBuffer.append(review.place + "|");
            stringBuffer.append(review.content + "|");
            stringBuffer.append(review.rating + "|");
            stringBuffer.append(review.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|");
            stringBuffer.append(review.view);
            cnt ++;

            if (cnt != reviews.size() ){
                stringBuffer.append("@@");
            }
        }

        return stringBuffer.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/setReview", method = RequestMethod.POST)
    public String setReview(@RequestBody String body) throws Exception {
        String reviewOfElement[] = body.replace("=", "").split("\\|");
        Review review = new Review(
            0,
            reviewOfElement[0],
            Integer.parseInt(reviewOfElement[1]),
            reviewOfElement[2],
            reviewOfElement[3],
            Integer.parseInt(reviewOfElement[4]),
            LocalDateTime.parse(reviewOfElement[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            Integer.parseInt(reviewOfElement[6])
        );

        try {
            boardService.setReview(review);
        }catch (Exception e) {
            return "fail";
        }
        return "success";
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/updateReview", method = RequestMethod.POST)
    public String updateReview(@RequestBody String body) throws Exception {

        String reviewOfElement[] = body.replace("=", "").split("\\|");
        Review review = new Review(
                Integer.parseInt(reviewOfElement[0]),
                null,
                0,
                null,
                reviewOfElement[1],
                0,
                null,
                0
        );

        try {
            boardService.updateReview(review);
        }catch (Exception e) {
            return "fail";
        }
        return "success";
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/updateReviewByView", method = RequestMethod.POST)
    public String updateReviewByView(@RequestBody String body) throws Exception {

        String reviewOfElement[] = body.replace("=", "").split("\\|");
        Review review = new Review(
                Integer.parseInt(reviewOfElement[0]),
                null,
                0,
                null,
                null,
                0,
                null,
                Integer.parseInt(reviewOfElement[1])
        );

        try {
            boardService.updateReviewByView(review);
        }catch (Exception e) {
            return "fail";
        }
        return "success";
    }


    @CrossOrigin("*")
    @RequestMapping(value = "/deleteReview", method = RequestMethod.POST)
    public String deleteReview(@RequestBody String body) throws Exception {

        String id = body.replace("=", "");
        try {
            boardService.deleteReview(Integer.parseInt(id));
        }catch (Exception e) {
            return "fail";
        }
        return "success";
    }

}
