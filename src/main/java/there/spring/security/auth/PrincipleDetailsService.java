package there.spring.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import there.spring.security.model.Member;
import there.spring.security.repository.MemberRepository;

// Security 설정에서 loginProcessingUrl("/login")
// login 요청이 오면 자동적으로 UserDetailsService 타입으로 IoC 되어 있는 loadUserByUsername 함수 실행
@Service
public class PrincipleDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    // Security Session(Authentication(UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);
        return member != null
                ? new PrincipleDetails(member)
                : null;
    }

}
