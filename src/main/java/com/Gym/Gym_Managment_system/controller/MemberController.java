package com.Gym.Gym_Managment_system.controller;

import com.Gym.Gym_Managment_system.model.Member;
import com.Gym.Gym_Managment_system.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    private memberService memberService;

    @PostMapping
    public Member addMember(@RequestBody Member member){
        return memberService.addMember(member);
    }

    @GetMapping("/all")
    public List<Member> getAllMember(){
        return memberService.getAllMember();
    }
}
