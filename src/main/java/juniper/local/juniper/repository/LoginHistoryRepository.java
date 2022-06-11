package juniper.local.juniper.repository;

import juniper.local.juniper.domain.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

    @Query("select lh from LoginHistory lh where lh.member.id = :memberId ")
    List<LoginHistory> getMemberLoginHistory(Long memberId);

    @Query("select lh from LoginHistory lh inner join fetch Member m ")
    List<LoginHistory> getAllLoginHistory();
}
