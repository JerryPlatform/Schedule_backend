package juniper.local.juniper.repository;

import juniper.local.juniper.domain.MemberAuthMgt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberAuthMgtRepository extends JpaRepository<MemberAuthMgt, Long> {
    @Query("select m from MemberAuthMgt m where m.account = :account ")
    MemberAuthMgt getMemberAuthMgtsByAccount(String account);
}
