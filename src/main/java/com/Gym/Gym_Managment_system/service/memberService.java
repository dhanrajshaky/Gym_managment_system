package com.Gym.Gym_Managment_system.service;

import com.Gym.Gym_Managment_system.model.Member;
import com.Gym.Gym_Managment_system.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class memberService {
    @Autowired
    private MemberRepo memberRepo;

    public Member addMember(Member member){
        return memberRepo.save(member);
    }

    public List<Member> getAllMember(){
        return memberRepo.findAll();
    }

}
