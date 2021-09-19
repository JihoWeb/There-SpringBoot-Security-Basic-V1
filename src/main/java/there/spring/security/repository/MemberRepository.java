package there.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import there.spring.security.model.Member;

// @Repository Annotation 없어도 IoC 된다.
// 이유는 JpaRepository 상속이기 때문
public interface MemberRepository extends JpaRepository<Member, Integer> {
    // "findBy?" prefix는 문법
    // i.e. select * from member where username = ?
    Member findByUsername(String username); // JPA Query methods 방식
}
