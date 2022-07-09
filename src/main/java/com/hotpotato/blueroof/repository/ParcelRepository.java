package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.model.appointment.Parcel;
import com.hotpotato.blueroof.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {

}