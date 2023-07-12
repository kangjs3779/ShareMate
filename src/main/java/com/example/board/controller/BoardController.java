package com.example.board.controller;

import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("board")
    public void board(Model model) {
        //게시물 조회
        Map<String, Object> info = boardService.selectBoard();

        model.addAllAttributes(info);
    }

    @GetMapping("boardDetail")
    public void boardDetail(@RequestParam("detailId") Integer detailId) {

    }
}
