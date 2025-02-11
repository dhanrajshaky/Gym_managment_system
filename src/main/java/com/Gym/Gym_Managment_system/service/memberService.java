package com.Gym.Gym_Managment_system.service;

import com.Gym.Gym_Managment_system.model.Member;
import com.Gym.Gym_Managment_system.repository.MemberRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class memberService {
    @Autowired
    private MemberRepo memberRepo;

    public Member addMember(Member member){
        return memberRepo.save(member);
    }

    public Page<Member> getAllMember(Pageable pageable){
        return memberRepo.findAll(pageable);
    }

    public Member updateMember(Long id , Member member){
        Member existingMember = memberRepo.findById(id).orElseThrow(()->new RuntimeException("Member not found "));

        existingMember.setName(member.getName());
        existingMember.setEmail(member.getEmail());
        existingMember.setMembershipPlan(member.getMembershipPlan());

        return  memberRepo.save(existingMember);


    }

    public void deleteMember(Long id ){
        Member existingMember = memberRepo.findById(id).orElseThrow(() -> new RuntimeException("Member not found "));
        memberRepo.delete(existingMember);
    }

    public List<Member> searchMember(String keyword){
        return memberRepo.findByName(keyword);
    }

    public List<Member>  filterByMembershipPlan(String membershipPlan){
        return memberRepo.findByMembershipPlan(membershipPlan);
    }



}
