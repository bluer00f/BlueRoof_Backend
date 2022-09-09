package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.model.information.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {


    Subscription findByUserId(Long id);
}
