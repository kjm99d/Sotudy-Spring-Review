package com.example.study.repository;

import com.example.study.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class CsvMemberRepositoryTest {

    private MemberRepository memberRepository;


    @BeforeEach
    void beforeEach()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        CsvMemberRepository repository = ac.getBean(CsvMemberRepository.class);
        if (null != repository)
        {
            memberRepository = repository;
        }

        memberRepository.clear();
    }



    @Test
    @DisplayName("저장소에 데이터가 삽입되는가")
    void CsvSaveTest()
    {
        memberRepository.save(new Member("a0", "b0"));
        memberRepository.save(new Member("a1", "b1"));
        memberRepository.save(new Member("a2", "b2"));
        memberRepository.save(new Member("a3", "b3"));
    }

    @Test
    @DisplayName("4명의 멤버를 추가 했을 경우 Count는 4가 되어야한다.")
    void GetCountFourTest()
    {
        CsvSaveTest();

        int nMember = memberRepository.GetMemberCount();
        Assertions.assertThat(nMember).isEqualTo(4);

        Assertions.assertThat(nMember).isNotEqualTo(2);
    }


    @Test
    @DisplayName("만약 id(Unique) 값을 기준으로 User를 찾지 못할 경우 null을 반환한다.")
    void FindByIdReturnNull()
    {
        memberRepository.save(new Member("a", "b"));
        String username = memberRepository.findById(2L);
        Assertions.assertThat(username).isEqualTo(null);
    }


}
