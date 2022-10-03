package test.hotpotato.blueroof_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.hotpotato.blueroof_test.model.information.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findByUserId(Long id);
}