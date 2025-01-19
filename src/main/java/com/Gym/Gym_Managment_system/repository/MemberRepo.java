package com.Gym.Gym_Managment_system.repository;

import com.Gym.Gym_Managment_system.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member,Long> {

    List<Member> findByName(String name);
    List<Member> findByMembershipPlan(String membershipPlan);
}
