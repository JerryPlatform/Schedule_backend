package juniper.local.juniper.repository;

import juniper.local.juniper.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
