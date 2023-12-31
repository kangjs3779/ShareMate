package com.example.shareMate.mapper;

import com.example.shareMate.domain.Board;
import com.example.shareMate.domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO Member (username, password, nickname, phoneNum, address, email, name)
            VALUES (#{username}, #{password}, #{nickname}, #{phoneNum}, #{address}, #{email}, #{name})
            """)
    Integer addMember(Member member);

    @Select("""
            SELECT *
            FROM Member m LEFT JOIN Authority a
                on m.username = a.memberId
            WHERE m.username = #{username}
            """)
    @ResultMap("memberMap")
    Member selectByUsername(String username);

    @Select("""
            SELECT * FROM Member WHERE username = #{username}
            """)
    Member selectMemberByUsername(String username);

    @Update("""
            UPDATE Member
            SET address = #{address},
                phoneNum = #{phoneNum}
            WHERE username = #{username}
            """)
    Integer modifyMemberByUsername(Member member);

    @Delete("""
            DELETE FROM Member WHERE username = #{username}
            """)
    Integer deleteMemberByUsername(Member member);

    @Select("""
            SELECT * FROM Member WHERE email = #{email}
            """)
    Member selectMemberByEmail(String email);

    @Select("""
            SELECT * FROM Member
            """)
    List<Member> selectAllMember();

    @Select("""
            SELECT * FROM Board WHERE memberId = #{memberId} ORDER BY inserted DESC
            """)
    List<Board> selectMyBoardByUsername(String memberId);

    @Select("""
            SELECT * FROM Member WHERE nickname = #{nickname}
            """)
    Member selectByNickname(String nickname);

    @Select("""
            SELECT * FROM Member WHERE email = #{email} AND name = #{name}
            """)
    Member selectMemberByNameAndEmail(String email, String name);

    @Update("""
            UPDATE Member
            SET password = #{password}
            WHERE username = #{username}
            """)
    Integer modifyPasswordByUsername(Member member);

    @Select("""
            SELECT * FROM Member WHERE email = #{email} AND username = #{id}
            """)
    Member selectMemberByIdAndEmail(String email, String id);
}
