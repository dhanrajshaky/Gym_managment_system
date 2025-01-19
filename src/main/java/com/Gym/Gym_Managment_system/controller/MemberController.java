package com.Gym.Gym_Managment_system.controller;

import com.Gym.Gym_Managment_system.model.Member;
import com.Gym.Gym_Managment_system.service.memberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    private memberService memberService;

    @PostMapping("/add")
    public Member addMember(@Valid  @RequestBody Member member){

        //add logic for default role
        if (member.getRole()==null){
            member.setRole("USER");
        }
        return memberService.addMember(member);
    }

    @GetMapping("/all")
    public Page<Member> getAllMember(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return memberService.getAllMember(pageable);
    }


    @PutMapping("/update-{id}")
    public ResponseEntity<Member> updatedMember(@PathVariable Long id , @Valid @RequestBody Member member){
        Member updatedMember = memberService.updateMember(id,member);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/delete-{id}")
    public  ResponseEntity<Void> deleteMember(@PathVariable Long id ){
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search-{keyword}")
    public List<Member> searchMember(@PathVariable("keyword") String keyword){
        return memberService.searchMember(keyword);
    }

    @GetMapping("/filter-{plan}")
    public List<Member> filterByMembershipPlan(@PathVariable("plan") String membershipPlan){
        return memberService.filterByMembershipPlan(membershipPlan);
    }

}
