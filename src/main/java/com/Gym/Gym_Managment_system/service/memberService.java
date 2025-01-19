package com.Gym.Gym_Managment_system.service;

import com.Gym.Gym_Managment_system.model.Member;
import com.Gym.Gym_Managment_system.repository.MemberRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private  List<Member> users=new ArrayList<>();//assuming in-memory list for storing users
    //Create a user with default role "USER"
    public Member  createUser(String name,String email,String membership){
        long id = users.size() +1; //Generate an auto-Increment ID (or use your own logic)
        Member member= new Member(id,name,email,membership,"USER");//Default role is user
        users.add(member);
        return member ;
    }

}
