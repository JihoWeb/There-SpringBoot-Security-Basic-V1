package there.spring.security.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import there.spring.security.model.Member;

import java.util.ArrayList;
import java.util.Collection;

// Security가 /login 주소 요청을 가로채 로그인을 진행한다.
// 로그인이 완료되면 security session을 만든다.
// at "Security ContextHolder"
// Security Instance -> Authentication Instance
// Member information in Authentication
// Member Object -> UserDetails Object

// Security Session -> Authentication -> UserDetails
public class PrincipleDetails implements UserDetails {

    private Member member; // Composition

    public PrincipleDetails(Member member) {
        this.member = member;
    }

    // 해당 회원의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> member.getRole());
        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // ex: 회원이 1년 동안 로그인을 하지 않았을 경우에 false(휴면 계정)
        return true;
    }
}
