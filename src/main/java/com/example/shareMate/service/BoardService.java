package com.example.shareMate.service;

import com.example.shareMate.domain.*;
import com.example.shareMate.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BoardCommentMapper boardCommentMapper;
    @Autowired
    private BoardCommentService boardCommentService;
    @Autowired
    private LikeBoardMapper likeBoardMapper;
    @Autowired
    private OttMapper ottMapper;

    public Map<String, Object> selectAllBoard(Authentication authentication) {
        Map<String, Object> info = new HashMap<>();

        //게시물 전체 조회
        List<Board> list = boardMapper.selectBoardList();

        for (Board board : list) {
            //댓글 갯수
            Integer commentCount = boardCommentMapper.selectCommentByBoarId(board.getId());
            board.setCommentCount(commentCount);

            //좋아요 갯수
            Integer likeCount = likeBoardMapper.selectLikeCountByBoardId(board.getId());
            board.setLikeCount(likeCount);

            //좋아요 확인
            Like like = likeBoardMapper.checkLikeByUsernameAndBoardId(authentication.getName(), board.getId());
            if(like != null) {
                //좋아요를 누른 게시글이면
                board.setLikeCheck(true);
            }
        }

        //ott서비스 전체 조회
        List<Ott> otts = ottMapper.selectOtt();

        //map에 저장
        info.put("list", list);
        info.put("otts", otts);

        return info;
    }

    public Map<String, Object> selectBoardByDetailId(Integer boardId) {
        Map<String, Object> info = new HashMap<>();

        // 조회수 업데이트
        boardMapper.addViewCount(boardId);

        //상세 페이지 조회
        Board board = boardMapper.selectBoardByBoardId(boardId);

        //ott 서비스 조회
        Ott ott = ottMapper.selectOttByOttId(board.getOttId());

        //한 명당 내는 비용 조회
        Integer costPerPersonInt = ott.getCost() / ott.getLimitedAttendance();
        DecimalFormat df = new DecimalFormat("###,###");
        String costPerPersonString = df.format(costPerPersonInt);

        ott.setCostPerPerson(costPerPersonString);

        //map에 저장
        info.put("board", board);
        info.put("ott", ott);

        return info;
    }

    public boolean addBoard(Board board) {
        //게시글 추가
        Integer count;

        count = boardMapper.addBoard(board);

        return count == 1;
    }

    public boolean deleteBoard(Integer boardId, Member member) {
        //게시글 삭제를 위한 메소드
        Integer count = 0;
        Member origin = memberMapper.selectMemberByUsername(member.getUsername());

        //비밀번호 확인
        if (passwordEncoder.matches(member.getPassword(), origin.getPassword())) {
            //댓글이 있는지 확인
            List<BoardComment> comments = boardCommentMapper.selectAllComment(boardId);

            if (comments.size() != 0) {
                //댓글이 있으면 삭제
                for (BoardComment comment : comments) {
                    boardCommentService.deleteComment(comment);
                }
            }

            //좋아요 있는지 확인
            Integer like = likeBoardMapper.selectLikeCountByBoardId(boardId);

            if(like != 0 ) {
                //좋아요가 있으면
                likeBoardMapper.deleteLikeByBoardId(boardId);
            }

            //게시글 삭제
            count = boardMapper.deleteBoardByBoardId(boardId);
        }
        return count == 1;
    }

    public boolean modifyBoardByBoardId(Board board) {
        //게시글 수정
        Integer count = 0;

        count = boardMapper.modifyBoardByBoardId(board);

        return count == 1;
    }

    public List<Board> ottSearch(Integer ottId, Authentication authentication, String page) {
        //ott서비스 검색을 포함한 게시물 전체 조회
        List<Board> list =  boardMapper.selectBoardByOtt(ottId, authentication.getName(), page);

        for (Board board : list) {
            //댓글 갯수
            Integer commentCount = boardCommentMapper.selectCommentByBoarId(board.getId());
            board.setCommentCount(commentCount);

            //좋아요 갯수
            Integer likeCount = likeBoardMapper.selectLikeCountByBoardId(board.getId());
            board.setLikeCount(likeCount);

            //좋아요 확인
            Like like = likeBoardMapper.checkLikeByUsernameAndBoardId(authentication.getName(), board.getId());
            if(like != null) {
                //좋아요를 누른 게시글이면
                board.setLikeCheck(true);
            }
        }

        return list;
    }

    public Map<String, Object> home() {
        Map<String, Object> info = new HashMap<>();

        //홈페이지 포워드
        List<Ott> otts = ottMapper.selectOtt();

        for(Ott ott : otts) {
            DecimalFormat df = new DecimalFormat("###,###");
            //한 명당 내는 비용 조회
            Integer costPerPersonInt = ott.getCost() / ott.getLimitedAttendance();
            String costPerPersonString = df.format(costPerPersonInt);

            //절약할 수 있는 금액 총액 조회
            Integer saveMoneyInt = costPerPersonInt * (ott.getLimitedAttendance() - 1);
            String saveMoneyString = df.format(saveMoneyInt);

            ott.setCostPerPerson(costPerPersonString);
            ott.setSaveMoney(saveMoneyString);

        }

        info.put("otts", otts);

        return info;
    }
}
