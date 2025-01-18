package com.Gym.Gym_Managment_system.repository;

import com.Gym.Gym_Managment_system.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member,Long> {

}
